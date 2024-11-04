import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from '../Services/auth.service';
import { RestConstants } from '../rest-constants';

@Component({
  selector: 'app-cartera-global',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cartera-global.component.html',
  styleUrls: ['./cartera-global.component.css']
})
export class CarteraGlobalComponent implements OnInit {
  totalCarteraGlobal: number = 0;
  monto: number = 0;

  private apiURL: string;

  constructor(private http: HttpClient, private authService: AuthService, private restConstants: RestConstants) {
    this.apiURL = this.restConstants.getApiURL();
  }

  ngOnInit(): void {
    this.obtenerSaldoCarteraGlobal();
  }

  obtenerSaldoCarteraGlobal(): void {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
    });

    this.http.get<any>(`${this.apiURL}carteraGlobal/saldo`, { headers })
      .subscribe(
        response => {
          this.totalCarteraGlobal = response.saldo;
        },
        error => {
          console.error('Error obteniendo el saldo de la cartera global:', error);
        }
      );
  }

  agregarSaldo(): void {
    if (this.monto <= 0) {
      alert('El monto debe ser positivo.');
      return;
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`,
      'Content-Type': 'application/json'
    });

    const body = { monto: this.monto };

    this.http.post<any>(`${this.apiURL}carteraGlobal/agregar`, body, { headers })
      .subscribe(
        response => {
          alert('Saldo agregado exitosamente.');
          this.obtenerSaldoCarteraGlobal(); // Actualizar el saldo después de agregar
        },
        error => {
          console.error('Error agregando saldo:', error);
          alert('No se pudo agregar el saldo.');
        }
      );
  }
}

