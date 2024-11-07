import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { RestConstants } from '../rest-constants';
import { tap } from 'rxjs';
import { catchError } from 'rxjs';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnuncioService {

  private apiURL: string;
  private anunciosActualizados = new Subject<void>();

  constructor(private http: HttpClient, private restConstants: RestConstants) {
    this.apiURL = `${this.restConstants.getApiURL()}anuncio`;
  }

  private createHeaders(token: string): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  crearAnuncioTexto(anuncio: any, token: string): Observable<any> {
    const headers = this.createHeaders(token);
    return this.http.post(`${this.apiURL}/crearTexto`, anuncio, { headers }).pipe(
      tap(() => {
        this.notificarActualizacionAnuncios();
      })
    );
  }
  
  crearAnuncioTextoImagen(anuncio: any, token: string): Observable<any> {
    const headers = this.createHeaders(token);
    return this.http.post(`${this.apiURL}/crearTextoImagen`, anuncio, { headers }).pipe(
      tap(() => {
        this.notificarActualizacionAnuncios();
      })
    );
  }
  
  crearAnuncioVideo(anuncio: any, token: string): Observable<any> {
    const headers = this.createHeaders(token);
    return this.http.post(`${this.apiURL}/crearVideo`, anuncio, { headers }).pipe(
      tap(() => {
        this.notificarActualizacionAnuncios();
      })
    );
  }
  

  obtenerAnunciosActivos(): Observable<any> {
    return this.http.get(`${this.apiURL}/anunciosActivos`);
  }

  // Método para notificar actualizaciones en anuncios
  notificarActualizacionAnuncios() {
    this.anunciosActualizados.next();
  }

  obtenerActualizacionesAnuncios(): Observable<void> {
    return this.anunciosActualizados.asObservable();
  }
  obtenerMisAnuncios(idUsuario: number, token: string): Observable<any> {
    const headers = this.createHeaders(token);
    return this.http.get(`${this.apiURL}/misAnuncios/${idUsuario}`, { headers });
  }
  cambiarEstadoAnuncio(idAnuncio: number, nuevoEstado: string, token: string): Observable<any> {
    const headers = this.createHeaders(token);
    return this.http.put(`${this.apiURL}/${idAnuncio}/estado/${nuevoEstado}`, {}, { headers, responseType: 'text' as 'json' }).pipe(
      tap(() => {
        console.log(`Estado del anuncio ${idAnuncio} cambiado a ${nuevoEstado}`);
        this.notificarActualizacionAnuncios(); // Notificar cambio en tiempo real
      }),
      catchError(error => {
        console.error("Error al cambiar el estado del anuncio:", error);
        return throwError(() => new Error('Hubo un problema al cambiar el estado del anuncio.'));
      })
    );
  }
    
 
}