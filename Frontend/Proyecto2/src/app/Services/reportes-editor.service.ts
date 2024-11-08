import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RestConstants } from '../rest-constants';

@Injectable({
  providedIn: 'root'
})
export class ReportesEditorService {

  private apiURL: string;

  constructor(private http: HttpClient, private restConstants: RestConstants) {
    this.apiURL = `${this.restConstants.getApiURL()}reportesEditor`;
  }

  obtenerComentariosPorUsuario(idUsuario: number, fechaInicio: string, fechaFin: string): Observable<any> {
    const url = `${this.apiURL}/comentariosPorUsuario?idUsuario=${idUsuario}&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`;
    return this.http.get(url);
  }

  obtenerComentariosPorRevista(idRevista: number, fechaInicio: string, fechaFin: string): Observable<any> {
    const url = `${this.apiURL}/comentariosPorRevista?idRevista=${idRevista}&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`;
    return this.http.get(url);
  }

  // Nuevos métodos para manejar reportes de suscripciones
  obtenerSuscripcionesPorUsuario(idUsuario: number, fechaInicio: string, fechaFin: string): Observable<any> {
    const url = `${this.apiURL}/suscripcionesPorUsuario?idUsuario=${idUsuario}&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`;
    return this.http.get(url);
  }

  obtenerSuscripcionesPorRevista(idRevista: number, fechaInicio: string, fechaFin: string): Observable<any> {
    const url = `${this.apiURL}/suscripcionesPorRevista?idRevista=${idRevista}&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`;
    return this.http.get(url);
  }

  // Nuevos métodos para manejar reportes de "Me Gusta"
  obtenerMeGustasPorFecha(fechaInicio: string, fechaFin: string): Observable<any> {
    const url = `${this.apiURL}/meGustas?fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`;
    return this.http.get(url);
  }

  obtenerMeGustasPorRevista(idRevista: number, fechaInicio: string, fechaFin: string): Observable<any> {
    const url = `${this.apiURL}/meGustasPorRevista?idRevista=${idRevista}&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`;
    return this.http.get(url);
  }
}

