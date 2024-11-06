import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RestConstants } from '../rest-constants';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ReporteService {
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

  obtenerRevistasMasPopulares(fechaInicio: string, fechaFin: string): Observable<any> {
    const headers = this.getHeaders();
    const url = `${this.apiURL}reporte/revistasMasPopulares?fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`;
    return this.http.get<any>(url, { headers });
  }
}
