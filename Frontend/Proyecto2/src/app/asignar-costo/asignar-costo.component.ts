import { Component, OnInit } from '@angular/core';
import { AdminService } from '../Services/admin.service';
import { AuthService } from '../Services/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-asignar-costo',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './asignar-costo.component.html',
  styleUrls: ['./asignar-costo.component.css']
})
export class AsignarCostoComponent implements OnInit {
  costoActual: number = 0;
  cantidadRevistas: number = 0;
  nuevoCosto: number = 0;

  constructor(private adminService: AdminService, private authService: AuthService) {}

  ngOnInit(): void {
    this.obtenerInformacionActual();
  }

  obtenerInformacionActual(): void {
    this.adminService.obtenerCostoDiarioAsignado().subscribe(
      response => {
        this.costoActual = response.costoDiario;
      },
      error => {
        console.error(error);
        alert('Error al obtener el costo diario asignado');
      }
    );

    this.adminService.contarRevistasActivas().subscribe(
      response => {
        this.cantidadRevistas = response.cantidadRevistas;
      },
      error => {
        console.error(error);
        alert('Error al obtener la cantidad de revistas activas');
      }
    );
  }

  asignarNuevoCosto(): void {
    this.adminService.actualizarCostoDiario(this.nuevoCosto).subscribe(
      response => {
        console.log(response);
        alert('Costo diario actualizado correctamente');
        this.obtenerInformacionActual(); // Actualizar la información después de asignar el nuevo costo
      },
      error => {
        console.error(error);
        alert('Error al actualizar el costo diario');
      }
    );
  }

  procesarCobroDiario(): void {
    this.adminService.procesarCobroDiario().subscribe(
      response => {
        console.log(response);
        alert('Cobro diario procesado correctamente');
      },
      error => {
        console.error(error);
        alert('Error al procesar el cobro diario');
      }
    );
  }
}