import { Component } from '@angular/core';
import { CalculadoraMatriz } from './model/calculadoraMatriz';
import { AppService } from './app.service';
import Swal from 'sweetalert2';

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

  constructor( private service: AppService ) { }

  iniciarPrueba(){
    localStorage.setItem('inicioLaPrueba','true');
    this.inicioLaPrueba = JSON.parse(localStorage.getItem('inicioLaPrueba'));
    this.cargarValoresEntrada = true;
  }

  creacionResultados(){
    this.service.crearResultado(this.calculadoraMatriz).subscribe(
      res => {
        this.mensaje("Cantidad de matrices: "+res.n+", Número de operaciones: "+res.m,1);
        this.cargarFormularios = true;
      }, error => {
        this.mensaje(error.error.mensaje, 2);
      });
  }



  mensaje(mensaje: string, tipoMensaje: number){
    Swal.fire(tipoMensaje == 1? 'Excelente!' : 'Error!', mensaje, tipoMensaje == 1? 'success' : 'error');
  }

  consulta(){
    this.operacion = 1
  }

  actualizacion(){
    this.operacion = 2;
  }
}
