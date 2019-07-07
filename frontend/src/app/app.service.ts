import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { CalculadoraMatriz } from '../app/model/calculadoraMatriz';
import { Observable } from 'rxjs';

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

  public consultar(intervalo1: number, intervalo2: number): Observable<number>{
    return this.http.get<number>(this.url + '/consulta?x1='+intervalo1+'&y1='+intervalo1+'&z1='+intervalo1+'&x2='+intervalo2+'&y2='+intervalo2+'&z2='+intervalo2, httpOptions);
  }
}

