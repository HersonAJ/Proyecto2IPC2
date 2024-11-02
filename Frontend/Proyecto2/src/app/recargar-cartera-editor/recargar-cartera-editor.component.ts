import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { RestConstants } from '../rest-constants';
import { AuthService } from '../Services/auth.service';

@Component({
  selector: 'app-recargar-cartera-editor',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './recargar-cartera-editor.component.html',
  styleUrls: ['./recargar-cartera-editor.component.css']
})
export class RecargarCarteraComponent {
  monto: string = ''; // Inicializar como una cadena vacía
  mensaje: string = '';

  constructor(
    private http: HttpClient,
    private router: Router,
    private restConstants: RestConstants,
    private authService: AuthService
  ) {}

  onSubmit() {
    const idUsuario = this.authService.getIdUsuario();
    const url = `${this.restConstants.getApiURL()}cartera/agregar`;
    const body = {
      idUsuario: idUsuario,
      monto: parseFloat(this.monto) // Convertir a número
    };

    this.http.post(url, body).subscribe(
      (response: any) => {
        this.mensaje = response.message;
        this.monto = ''; // Limpiar el campo monto
      },
      (error) => {
        this.mensaje = error.error.message || 'Error al recargar la cartera.';
        this.monto = ''; // Limpiar el campo monto en caso de error
      }
    );
  }
}
