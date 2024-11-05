import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RestConstants } from '../rest-constants';

@Injectable({
  providedIn: 'root'
})
export class RevistaSuscriptorService {
  private baseUrl: string;

  constructor(private http: HttpClient, private restConstants: RestConstants) {
    this.baseUrl = this.restConstants.getApiURL() + 'revistas-suscriptor';
  }

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwtToken');
    return new HttpHeaders().set('Authorization', `Bearer ${token}`).set('Content-Type', 'application/json');
  }

  getTodasLasRevistas(): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.get(`${this.baseUrl}`, { headers });
  }

  getRevistasPorCategoria(categoria: string): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.get(`${this.baseUrl}/categoria/${categoria}`, { headers });
  }

  getRevistasPorTag(tag: string): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.get(`${this.baseUrl}/tag/${tag}`, { headers });
  }
  suscribirseRevista(idUsuario: number, idRevista: number, fechaSuscripcion: string): Observable<any> {
    const headers = this.getAuthHeaders();
    const body = { idUsuario, fechaSuscripcion };
    return this.http.post(`${this.baseUrl}/${idRevista}/suscribirse`, body, { headers });
  }

  //metodo para obtener las suscripciones
    getRevistasSuscritasPorUsuario(idUsuario: number): Observable<any> {
      const headers = this.getAuthHeaders();
      return this.http.get(`${this.baseUrl}/suscritas/${idUsuario}`, { headers });
    }
}

