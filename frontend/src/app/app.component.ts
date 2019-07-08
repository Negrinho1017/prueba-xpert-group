import { Component } from '@angular/core';
import { CalculadoraMatriz } from './model/calculadoraMatriz';
import { AppService } from './app.service';
import Swal from 'sweetalert2';
import { Matriz } from './model/Matriz';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  casosPrueba: number = 0;
  inicioLaPrueba: boolean = localStorage.getItem('inicioLaPrueba') === "true" ? true : false;
  cargarValoresEntrada: boolean = false;
  cargarFormularios: boolean = false;
  calculadoraMatriz: CalculadoraMatriz = new CalculadoraMatriz();
  operacion: number;
  intervalo1: number;
  intervalo2: number;
  matriz: Matriz = new Matriz();

  constructor(private service: AppService) { }

  iniciarPrueba() {
    localStorage.setItem('inicioLaPrueba', 'true');
    this.inicioLaPrueba = JSON.parse(localStorage.getItem('inicioLaPrueba'));
    this.cargarValoresEntrada = true;
  }

  creacionResultados() {
    this.service.crearResultado(this.calculadoraMatriz).subscribe(
      res => {
        this.mensaje("Cantidad de matrices: " + res.n + ", Número de operaciones: " + res.m, 1);
        this.cargarFormularios = true;
      }, error => {
        this.mensaje(error.error.mensaje, 2);
      });
  }



  mensaje(mensaje: string, tipoMensaje: number) {
    Swal.fire(tipoMensaje == 1 ? 'Excelente!' : 'Error!', mensaje, tipoMensaje == 1 ? 'success' : 'error');
  }

  consulta() {
    this.operacion = 1
    if (this.intervalo1 < this.intervalo2) {
      this.service.consultar(this.intervalo1, this.intervalo2).subscribe(res => {
        this.mensaje("El resultado es: " + res, 1);
      }, error => {
        this.mensaje(error.error.mensaje, 2);
        if (this.service.maximoLlamados().subscribe(res => res)) {
          this.maximoLlamados();
        }
        if (this.casosPrueba <= 0) {
          this.finalizar();
        }
      });
    } else {
      this.mensaje("El primer intervalo debe ser mayor al segundo", 2);
    }
  }

  actualizacion() {
    this.operacion = 2;
    this.service.actualizar(this.matriz).subscribe(
      res => {
        this.mensaje("Cambiada la matriz de coordenadas" + res.x + ", " + res.y + ", " + res.z + " por: " + res.w, 1);
      }, error => {
        this.mensaje(error.error.mensaje, 2);
        if (this.service.maximoLlamados().subscribe(res => res)) {
          this.maximoLlamados();
        }
        if (this.casosPrueba <= 0) {
          this.finalizar();
        }
      });
  }

  finalizar() {
    localStorage.setItem('inicioLaPrueba', 'false');
    this.reiniciarValores();
    window.location.reload();
  }

  maximoLlamados() {
    this.casosPrueba--;
    this.reiniciarValores();
  }

  reiniciarValores(){
    this.operacion = 0;
    this.cargarValoresEntrada = false;
    this.cargarFormularios = false;
  }
}
