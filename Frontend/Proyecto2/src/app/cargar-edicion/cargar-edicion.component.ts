import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { EdicionService } from '../Services/edicion.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cargar-edicion',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './cargar-edicion.component.html',
  styleUrls: ['./cargar-edicion.component.css'],
})
export class CargarEdicionComponent {
  nuevaEdicion = {
    tituloEdicion: '',
    fechaCreacion: '',
    archivoPdf: null
  };

  idRevista: number;

  constructor(
    private edicionesService: EdicionService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.idRevista = Number(this.route.snapshot.paramMap.get('idRevista'));
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    this.nuevaEdicion.archivoPdf = file;
  }

  cargarEdicion(form: NgForm): void {
    if (form.valid) {
      const formData = new FormData();
      formData.append('idRevista', this.idRevista.toString());
      formData.append('tituloEdicion', this.nuevaEdicion.tituloEdicion);
      formData.append('fechaCreacion', this.nuevaEdicion.fechaCreacion);

      if (this.nuevaEdicion.archivoPdf) {
        formData.append('archivoPdf', this.nuevaEdicion.archivoPdf);
      }

      this.edicionesService.insertarEdicion(formData).subscribe(response => {
        alert('Edición cargada exitosamente:');
        //this.router.navigate(['revista', this.idRevista]);
      }, error => {
        alert("Error al cargar la edicion");
        console.error('Error al cargar la edición:', error);
      });
    }
  }
}
