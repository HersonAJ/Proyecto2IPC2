import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { RestConstants } from '../rest-constants';

import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiURL: string;
  private autenticado = new BehaviorSubject<boolean>(this.hasToken());
  autenticado$ = this.autenticado.asObservable();
  private tipoUsuario: string | null = null;
  private idUsuario: number | null = null;

  constructor(private http: HttpClient, private restConstants: RestConstants) {
    this.apiURL = this.restConstants.getApiURL();
    this.setTipoUsuarioFromToken();
  }

  private hasToken(): boolean {
    return !!localStorage.getItem('jwtToken');
  }

  private setTipoUsuarioFromToken(): void {
    const token = localStorage.getItem('jwtToken');
    if (token) {
      const decodedToken: any = jwtDecode(token);
      console.log('Decoded token:', decodedToken); // Agregue este log
      this.tipoUsuario = decodedToken.tipoUsuario || null;
      console.log('Tipo de usuario:', this.tipoUsuario); // Agregue este log
      this.idUsuario = decodedToken.idUsuario || null;
      console.log('Id Usuario:', this.idUsuario );
    }
  }

  login(username: string, contraseña: string): Observable<{ token: string }> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { username, contraseña };
    return this.http.post<{ token: string }>(`${this.apiURL}login`, body, { headers });
  }

  setToken(token: string): void {
    localStorage.setItem('jwtToken', token);
    this.setTipoUsuarioFromToken();
    this.autenticado.next(true);
    this.setTipoUsuarioFromToken(); // Establece el tipo de usuario cuando se establece el token
  
  }

  logout(): void {
    localStorage.removeItem('jwtToken');
    this.autenticado.next(false);
    this.tipoUsuario = null; // Restablece el tipo de usuario en logout
  }

  getTipoUsuario(): string | null {
    return this.tipoUsuario;
  }

  getIdUsuario (): number | null {
    return this.idUsuario;
  }
}