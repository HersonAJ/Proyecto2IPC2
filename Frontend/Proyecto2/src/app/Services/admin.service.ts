import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RestConstants } from '../rest-constants';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private apiURL: string;

  constructor(
    private http: HttpClient,
    private restConstants: RestConstants,
    private authService: AuthService
  ) {
    this.apiURL = this.restConstants.getApiURL();
  }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwtToken');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  actualizarCostoDiario(nuevoCostoPorDia: number): Observable<any> {
    const headers = this.getHeaders();
    return this.http.put<any>(`${this.apiURL}cobro/actualizarCostoDiario/${nuevoCostoPorDia}`, {}, { headers });
  }

  procesarCobroDiario(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.put<any>(`${this.apiURL}cobro/procesarCobroDiario`, {}, { headers });
  }

  contarRevistasActivas(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get<any>(`${this.apiURL}cobro/contarRevistasActivas`, { headers });
  }

  obtenerCostoDiarioAsignado(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get<any>(`${this.apiURL}cobro/obtenerCostoDiarioAsignado`, { headers });
  }

  fijarPrecioAnuncio(tipo: string, duracion: string, precio: number): Observable<any> {
    const headers = this.getHeaders();
    return this.http.put<any>(`${this.apiURL}cobro/fijarPrecioAnuncio?tipo=${tipo}&duracion=${duracion}&precio=${precio}`, {}, { headers });
  }

  obtenerPrecioAnuncio(tipo: string, duracion: string): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get<any>(`${this.apiURL}cobro/obtenerPrecioAnuncio?tipo=${tipo}&duracion=${duracion}`, { headers });
  }
}