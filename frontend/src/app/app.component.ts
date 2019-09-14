import { Component, OnInit } from '@angular/core';
import { CalculadoraMatriz } from './model/calculadoraMatriz';
import { AppService } from './app.service';
import Swal from 'sweetalert2';
import { Matriz } from './model/Matriz';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
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
  contadorOperaciones: number;

  constructor(private service: AppService) { }

  ngOnInit() {
    if (this.casosPrueba <= 0 && this.inicioLaPrueba) {
      this.finalizar();
    }
  }

  iniciarPrueba() {
    this.contadorOperaciones = 0;
    if(this.casosPrueba<=0){
        this.mensaje("Los casos de prueba deben ser mayores a 0",2);
        return;
      }
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
    if(this.intervalo1 > this.calculadoraMatriz.n || this.intervalo2 > this.calculadoraMatriz.n
      || this.intervalo1 <= 0){
        this.mensaje("Los intervalos son incorrectos",2);
        return;
      }
    this.operacion = 1
    if (this.intervalo1 <= this.intervalo2) {
      this.service.consultar(this.intervalo1, this.intervalo2).subscribe(res => {
        this.mensaje("El resultado es: " + res, 1);
        this.contadorOperaciones++;
        if(this.contadorOperaciones >= this.calculadoraMatriz.m){
          this.maximoLlamados();
        }
      }, error => this.mensaje(error.error.mensaje, 2));
    } else {
      this.mensaje("El primer intervalo debe ser menor o igual al segundo", 2);
    }
  }

  actualizacion() {
    if(this.matriz.x > this.calculadoraMatriz.n || this.matriz.y > this.calculadoraMatriz.n
      || this.matriz.z > this.calculadoraMatriz.n){
        this.mensaje("Los intervalos son mayores que la cantidad de matrices",2);
        return;
      }
    if(!(this.matriz.x == this.matriz.y && this.matriz.x == this.matriz.z)){
      this.mensaje("Los valores de X, Y y Z deben ser iguales",2);
      return;
    }
    this.operacion = 2;
    this.service.actualizar(this.matriz).subscribe(
      res => {
        this.mensaje("Cambiada la matriz de coordenadas" + res.x + ", " + res.y + ", " + res.z + " por: " + res.w, 1);
        this.contadorOperaciones++;
        if(this.contadorOperaciones >= this.calculadoraMatriz.m){
          this.maximoLlamados();
        }
      }, error => this.mensaje(error.error.mensaje, 2));
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

  finalizoPrueba():boolean{
    return this.inicioLaPrueba && this.casosPrueba==0
  }
}
