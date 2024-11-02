import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RestConstants } from '../rest-constants';

@Injectable({
  providedIn: 'root',
})
export class EdicionService {
  private baseUrlRevistas: string;
  private baseUrlEdiciones: string;

  constructor(private http: HttpClient, private restConstants: RestConstants) {
    this.baseUrlRevistas = this.restConstants.getApiURL() + 'revistas';
    this.baseUrlEdiciones = this.restConstants.getApiURL() + 'ediciones';
  }

  getEdiciones(idRevista: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrlRevistas}/${idRevista}/ediciones`);
  }

  insertarEdicion(formData: FormData): Observable<any> {
    return this.http.post<any>(this.baseUrlEdiciones, formData);
  }
}
