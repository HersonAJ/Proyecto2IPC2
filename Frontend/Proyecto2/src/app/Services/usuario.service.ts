import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RestConstants } from '../rest-constants';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private baseUrl: string;

  constructor(private http: HttpClient, private restConstants: RestConstants) {
    this.baseUrl = this.restConstants.getApiURL() + 'usuarios';
  }

    registrarUsuario(formData: FormData): Observable<any> {
      return this.http.post(this.baseUrl, formData);
    }

}

