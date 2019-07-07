import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { CalculadoraMatriz } from '../app/model/calculadoraMatriz';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AppService {
  private url = "http://localhost:8080";
  constructor( private http: HttpClient ) { }

  crearResultado(calculadoraMatriz: CalculadoraMatriz): any{
    return this.http.post(this.url + '/creacion-resultados', calculadoraMatriz, httpOptions);
  }
}
