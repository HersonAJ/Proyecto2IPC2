import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReportesEditorService } from '../Services/reportes-editor.service';
import { AuthService } from '../Services/auth.service';

@Component({
  selector: 'app-reporte-suscripcion-editor',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './reporte-suscripcion-editor.component.html',
  styleUrls: ['./reporte-suscripcion-editor.component.css']
})
export class ReporteSuscripcionEditorComponent {
  fechaInicio: string = '';
  fechaFin: string = '';
  idRevista: number | null = null;
  reporteSuscripciones: any[] = [];
  idUsuario: number | null = null;

  constructor(
    private reportesEditorService: ReportesEditorService,
    private authService: AuthService
  ) {
    this.idUsuario = this.authService.getIdUsuario();
  }

  generarReporte() {
    if (this.idRevista) {
      this.reportesEditorService.obtenerSuscripcionesPorRevista(this.idRevista, this.fechaInicio, this.fechaFin)
        .subscribe(response => {
          this.reporteSuscripciones = response;
        }, error => {
          console.error('Error al obtener el reporte de suscripciones por revista:', error);
        });
    } else {
      this.reportesEditorService.obtenerSuscripcionesPorUsuario(this.idUsuario!, this.fechaInicio, this.fechaFin)
        .subscribe(response => {
          this.reporteSuscripciones = response;
        }, error => {
          console.error('Error al obtener el reporte de suscripciones por usuario:', error);
        });
    }
  }
}
