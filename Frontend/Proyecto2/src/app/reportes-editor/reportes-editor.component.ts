import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReporteComentariosEditorComponent } from '../reporte-comentarios-editor/reporte-comentarios-editor.component';
import { ReporteSuscripcionEditorComponent } from '../reporte-suscripcion-editor/reporte-suscripcion-editor.component';
import { ReporteMeGustaEditorComponent } from "../reporte-megusta-editor/reporte-megusta-editor.component";

@Component({
  selector: 'app-reportes-editor',
  standalone: true,
  imports: [CommonModule, FormsModule, ReporteComentariosEditorComponent, ReporteComentariosEditorComponent, ReporteComentariosEditorComponent, ReporteSuscripcionEditorComponent, ReporteMeGustaEditorComponent],
  templateUrl: './reportes-editor.component.html',
  styleUrls: ['./reportes-editor.component.css']
})
export class ReportesEditorComponent {
  reporteSeleccionado: string | null = null;

  seleccionarReporte(reporte: string) {
    this.reporteSeleccionado = reporte;
  }
}
