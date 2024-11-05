import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RestConstants } from '../rest-constants';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RevistaInfoService {
  private baseUrlRevistas: string;
  private baseUrlEdiciones: string;
  private baseUrlComentarios: string;
  private baseUrlMeGusta: string;

  constructor(private http: HttpClient, private restConstants: RestConstants) {
    this.baseUrlRevistas = this.restConstants.getApiURL() + 'revistas';
    this.baseUrlEdiciones = this.restConstants.getApiURL() + 'ediciones';
    this.baseUrlComentarios = this.restConstants.getApiURL() + 'comentarios';
    this.baseUrlMeGusta = this.restConstants.getApiURL() + 'megustas';
  }

  getEdiciones(idRevista: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrlRevistas}/${idRevista}/ediciones`);
  }

  getRevista(idRevista: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrlRevistas}/${idRevista}`);
  }
  
  getComentarios(idRevista: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrlComentarios}/${idRevista}`);
    }

  agregarComentario(comentario: any): Observable<any> {
    return this.http.post<any>(this.baseUrlComentarios, comentario);
  }

  getMeGustas(idRevista: number): Observable <any[]> {
    return this.http.get<any[]>(`${this.baseUrlMeGusta}/${idRevista}`);
  }

  agregarMeGusta(meGusta: any): Observable <any> {
    return this.http.post<any>(this.baseUrlMeGusta, meGusta);
  }
}