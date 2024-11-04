import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { RestConstants } from '../rest-constants';
import { AuthService } from '../Services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-recargar-cartera-editor',
  standalone: true,
  imports: [FormsModule, NgIf, CommonModule],
  templateUrl: './recargar-cartera-editor.component.html',
  styleUrls: ['./recargar-cartera-editor.component.css']
})
export class RecargarCarteraComponent implements OnInit {
  monto: string = ''; // Inicializar como una cadena vacía
  mensaje: string = '';
  totalCartera: number = 0;

  constructor(
    private http: HttpClient,
    private router: Router,
    private restConstants: RestConstants,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.obtenerSaldoCartera();
  }

  obtenerSaldoCartera(): void {
    const idUsuario = this.authService.getIdUsuario();
    const url = `${this.restConstants.getApiURL()}cartera/saldo/${idUsuario}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
    });

    this.http.get<any>(url, { headers }).subscribe(
      (response: any) => {
        this.totalCartera = response.saldo;
      },
      (error) => {
        console.error('Error obteniendo el saldo de la cartera:', error);
      }
    );
  }

  onSubmit() {
    const idUsuario = this.authService.getIdUsuario();
    const url = `${this.restConstants.getApiURL()}cartera/agregar`;
    const body = {
      idUsuario: idUsuario,
      monto: parseFloat(this.monto) // Convertir a número
    };
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`,
      'Content-Type': 'application/json'
    });

    this.http.post<any>(url, body, { headers }).subscribe(
      (response: any) => {
        this.mensaje = response.message;
        this.monto = ''; // Limpiar el campo monto
        this.obtenerSaldoCartera(); // Actualizar el saldo después de agregar
      },
      (error) => {
        this.mensaje = error.error.message || 'Error al recargar la cartera.';
        this.monto = ''; // Limpiar el campo monto en caso de error
      }
    );
  }
}
