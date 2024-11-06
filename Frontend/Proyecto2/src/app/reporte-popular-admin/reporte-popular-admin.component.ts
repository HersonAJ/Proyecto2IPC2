import { Component, OnInit } from '@angular/core';

import { ReporteService } from '../Services/reporte.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reporte-popular',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './reporte-popular-admin.component.html',
  styleUrls: ['./reporte-popular-admin.component.css']
})
export class ReportePopularComponent implements OnInit {
  fechaInicio: string = '';
  fechaFin: string = '';
  reporteRevistasMasPopulares: any[] = [];

  constructor(private reporteService: ReporteService) {}

  ngOnInit(): void {
    // Inicialización si es necesario
  }

  generarReporte(): void {
    if (this.fechaInicio && this.fechaFin) {
      this.reporteService.obtenerRevistasMasPopulares(this.fechaInicio, this.fechaFin).subscribe(
        response => {
          this.reporteRevistasMasPopulares = response;
        },
        error => {
          console.error(error);
          alert('Error al generar el reporte de revistas más populares');
        }
      );
    } else {
      alert('Por favor, especifique el intervalo de tiempo');
    }
  }
}
