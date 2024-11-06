import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reportes',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.css']
})
export class ReportesComponent {

  constructor(private router: Router) {}

  // Método para navegar al componente de Reporte de Revistas Más Populares
  generarReporteRevistasMasPopulares(): void {
    this.router.navigate(['/reporte-popular']);
  }

  // metodos para mas reportes
}

