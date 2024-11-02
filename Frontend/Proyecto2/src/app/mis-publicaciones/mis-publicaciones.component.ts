import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RevistaService } from '../Services/revista.service';
import { AuthService } from '../Services/auth.service';
import { Router } from '@angular/router';

interface Publicacion {
  idRevista: number;
  titulo: string;
  descripcion: string;
  categoria: string;
  fechaCreacion: string;
  permiteComentarios: boolean;
  permiteMegusta: boolean;
}

@Component({
  selector: 'app-mis-publicaciones',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './mis-publicaciones.component.html',
  styleUrls: ['./mis-publicaciones.component.css']
})
export class MisPublicacionesComponent implements OnInit {
  publicaciones: Publicacion[] = [];

  constructor(
    private revistaService: RevistaService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const idUsuario = this.authService.getIdUsuario();
    if (idUsuario !== null) {
      this.revistaService.getRevistasPorUsuario(idUsuario).subscribe(
        (revistas: Publicacion[]) => {
          this.publicaciones = revistas;
        },
        error => {
          console.error('Error al obtener las publicaciones', error);
        }
      );
    } else {
      console.error('Error: No se pudo obtener el idUsuario');
    }
  }

  actualizarEstado(idRevista: number): void {
    console.log(`Actualizando estado de la revista con id ${idRevista}`);
  }

  eliminarRevista(idRevista: number): void {
    const confirmed = window.confirm('¿Está seguro de que desea eliminar esta revista?');
    if (confirmed) {
      this.revistaService.cambiarEstadoRevista(idRevista, 'Inactivo').subscribe(() => {
        alert('Revista eliminada exitosamente');
        this.publicaciones = this.publicaciones.filter(pub => pub.idRevista !== idRevista);
      }, error => {
        alert('Error al eliminar la revista');
        console.error('Error al eliminar la revista:', error);
      });
    }
  }

  agregarNuevaRevista(): void {
    this.router.navigate(['crear-revista']);
  }

  ingresarRevista(idRevista: number): void {
    this.router.navigate(['revista', idRevista]);
  }
}
