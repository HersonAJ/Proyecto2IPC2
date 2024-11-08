import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReportesEditorService } from '../Services/reportes-editor.service';
import { AuthService } from '../Services/auth.service';

@Component({
  selector: 'app-reporte-megusta-editor',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './reporte-megusta-editor.component.html',
  styleUrls: ['./reporte-megusta-editor.component.css']
})
export class ReporteMeGustaEditorComponent {
  fechaInicio: string = '';
  fechaFin: string = '';
  idRevista: number | null = null;
  reporteMeGustas: any[] = [];
  idUsuario: number | null = null;

  constructor(
    private reportesEditorService: ReportesEditorService,
    private authService: AuthService
  ) {
    this.idUsuario = this.authService.getIdUsuario();
  }

  generarReporte() {
    if (this.idRevista) {
      this.reportesEditorService.obtenerMeGustasPorRevista(this.idRevista, this.fechaInicio, this.fechaFin)
        .subscribe(response => {
          this.reporteMeGustas = response;
        }, error => {
          console.error('Error al obtener el reporte de me gusta por revista:', error);
        });
    } else {
      this.reportesEditorService.obtenerMeGustasPorFecha(this.fechaInicio, this.fechaFin)
        .subscribe(response => {
          this.reporteMeGustas = response;
        }, error => {
          console.error('Error al obtener el reporte de me gusta por fecha:', error);
        });
    }
  }
}

