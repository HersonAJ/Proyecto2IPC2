/*
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { RestConstants } from '../rest-constants';

@Injectable({
  providedIn: 'root'
})
export class PerfilService {
  private apiUrl: string;

  constructor(private http: HttpClient, private restConstants: RestConstants) {
    this.apiUrl = this.restConstants.getApiURL() + 'perfil';
  }

  getPerfil(): Observable<any> {
    const token = localStorage.getItem('jwtToken');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any>(this.apiUrl, { headers, withCredentials: true }).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'Error desconocido';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      switch (error.status) {
        case 400:
          errorMessage = 'Solicitud incorrecta (400)';
          break;
        case 401:
          errorMessage = 'No autorizado (401)';
          break;
        case 403:
          errorMessage = 'Prohibido (403)';
          break;
        case 404:
          errorMessage = 'No encontrado (404)';
          break;
        case 500:
          errorMessage = 'Error interno del servidor (500)';
          break;
        default:
          errorMessage = `Código de error: ${error.status}\nMensaje: ${error.message}`;
      }
    }
    console.error(errorMessage);
    return throwError(errorMessage);
  }
}
  */

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RestConstants } from '../rest-constants';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PerfilService {
  private apiUrl: string;

  constructor(private http: HttpClient, private restConstants: RestConstants) {
    this.apiUrl = this.restConstants.getApiURL() + 'perfil';
  }

  getPerfil(): Observable<any> {
    const token = localStorage.getItem('jwtToken');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any>(this.apiUrl, { headers, withCredentials: true });
  }

  actualizarPerfil(formData: FormData): Observable<any> {
    const token = localStorage.getItem('jwtToken');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.put(`${this.apiUrl}/actualizar`, formData, { headers });
  }

  private handleError(error: any): Observable<never> {
    let errorMessage = 'Error desconocido';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      switch (error.status) {
        case 400:
          errorMessage = 'Solicitud incorrecta (400)';
          break;
        case 401:
          errorMessage = 'No autorizado (401)';
          break;
        case 403:
          errorMessage = 'Prohibido (403)';
          break;
        case 404:
          errorMessage = 'No encontrado (404)';
          break;
        case 500:
          errorMessage = 'Error interno del servidor (500)';
          break;
        default:
          errorMessage = `Código de error: ${error.status}\nMensaje: ${error.message}`;
      }
    }
    console.error(errorMessage);
    return throwError(errorMessage);
  }

  actualizarContrasena(nuevaContraseña: string): Observable<any> {
    const token = localStorage.getItem('jwtToken');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`).set('Content-Type', 'application/json');
    const body = { nuevaContraseña: nuevaContraseña };
    return this.http.put(`${this.apiUrl}/actualizarContraseña`, body, { headers, withCredentials: true }).pipe(
      catchError(this.handleError)
    );
}
}
