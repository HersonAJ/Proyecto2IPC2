import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RestConstants } from '../rest-constants';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RevistaService {
  private baseUrl: string;

  constructor(private http: HttpClient, private restConstants: RestConstants) {
    this.baseUrl = this.restConstants.getApiURL() + 'revistas';
  }

  crearRevista(revista: any): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.post(this.baseUrl, revista, { headers });
  }

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwtToken');
    return new HttpHeaders().set('Authorization', `Bearer ${token}`).set('Content-Type', 'application/json');
  }
  

  getRevistasPorUsuario(idUsuario: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.get(`${this.baseUrl}/${idUsuario}`, { headers });
  }

  getRevista(idRevista: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.get(`${this.baseUrl}/${idRevista}`, { headers });
  }

  actualizarPermiteComentarios(idRevista: number, permiteComentarios: boolean): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.put(`${this.baseUrl}/${idRevista}/comentarios?permiteComentarios=${permiteComentarios}`, null, { headers });
  }

  actualizarPermiteMeGusta(idRevista: number, permiteMeGusta: boolean): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.put(`${this.baseUrl}/${idRevista}/megusta?permiteMeGusta=${permiteMeGusta}`, null, { headers });
  }

  actualizarPermiteSuscripcion(idRevista: number, permiteSuscripcion: boolean): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.put(`${this.baseUrl}/${idRevista}/suscripcion?permiteSuscripcion=${permiteSuscripcion}`, null, { headers });
  }

  //metodo de prueba

  cambiarEstadoRevistaPrueba(idRevista: number): Observable<any> {
    const url = `${this.baseUrl}/${idRevista}/estado`;
    return this.http.put(url, null);
  }
}