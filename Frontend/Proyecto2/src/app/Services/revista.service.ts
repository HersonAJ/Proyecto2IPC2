import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RestConstants } from '../rest-constants';

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

  //metodo para enviar el token en el header
  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwtToken');
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  //metodo para obtener las revistas por usuario
  getRevistasPorUsuario(idUsuario: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.get(`${this.baseUrl}/${idUsuario}`, { headers });
  }

  //metodo para obtener una revista
  getRevista(idRevista: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.get(`${this.baseUrl}/${idRevista}`, { headers });
  }

  //metodo para cambiar el estado de una revista
  cambiarEstadoRevista(idRevista: number, estado: string): Observable<any> {
    const headers = this.getAuthHeaders();
    const body = { estado };
    return this.http.put(`${this.baseUrl}/${idRevista}/estado`, body, { headers });
  }
}