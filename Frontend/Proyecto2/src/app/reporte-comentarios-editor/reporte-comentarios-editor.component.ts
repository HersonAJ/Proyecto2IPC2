import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReportesEditorService } from '../Services/reportes-editor.service';
import { AuthService } from '../Services/auth.service';

@Component({
  selector: 'app-reporte-comentarios-editor',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './reporte-comentarios-editor.component.html',
  styleUrls: ['./reporte-comentarios-editor.component.css']
})
export class ReporteComentariosEditorComponent {
  fechaInicio: string = '';
  fechaFin: string = '';
  idRevista: number | null = null;
  reporteComentarios: any[] = [];
  idUsuario: number | null = null;

  constructor(
    private reportesEditorService: ReportesEditorService,
    private authService: AuthService
  ) {
    this.idUsuario = this.authService.getIdUsuario();
  }

  generarReporte() {
    if (this.idRevista) {
      this.reportesEditorService.obtenerComentariosPorRevista(this.idRevista, this.fechaInicio, this.fechaFin)
        .subscribe(response => {
          this.reporteComentarios = response;
        }, error => {
          console.error('Error al obtener el reporte de comentarios por revista:', error);
        });
    } else {
      this.reportesEditorService.obtenerComentariosPorUsuario(this.idUsuario!, this.fechaInicio, this.fechaFin)
        .subscribe(response => {
          this.reporteComentarios = response;
        }, error => {
          console.error('Error al obtener el reporte de comentarios por usuario:', error);
        });
    }
  }
}
