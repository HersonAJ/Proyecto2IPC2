import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RevistaService } from '../Services/revista.service';
import { AuthService } from '../Services/auth.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { take, tap } from 'rxjs/operators';
import { RouterModule } from '@angular/router';

interface Publicacion {
  idRevista: number;
  titulo: string;
  descripcion: string;
  categoria: string;
  fechaCreacion: string;
  permiteComentarios: boolean;
  permiteMegusta: boolean;
  permiteSuscripcion: boolean;
}

@Component({
  selector: 'app-mis-publicaciones',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, RouterModule],
  templateUrl: './mis-publicaciones.component.html',
  styleUrls: ['./mis-publicaciones.component.css']
})
export class MisPublicacionesComponent implements OnInit, OnDestroy {
  publicaciones: Publicacion[] = [];
  subscription: Subscription | null = null;

  constructor(
    private revistaService: RevistaService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const idUsuario = this.authService.getIdUsuario();
    if (idUsuario !== null) {
      this.revistaService.getRevistasPorUsuario(idUsuario).subscribe((revistas: Publicacion[]) => {
        this.publicaciones = revistas;
      }, error => {
        console.error('Error al obtener las publicaciones', error);
      });
    } else {
      console.error('Error: No se pudo obtener el idUsuario');
    }
  }

  togglePermiteComentarios(idRevista: number, currentState: boolean): void {
    const newState = !currentState;
    this.revistaService.actualizarPermiteComentarios(idRevista, newState).subscribe(() => {
      const publicacion = this.publicaciones.find(pub => pub.idRevista === idRevista);
      if (publicacion) {
        publicacion.permiteComentarios = newState;
      }
      console.log('Permite Comentarios actualizado exitosamente');
    }, error => console.error('Error al actualizar Permite Comentarios', error));
  }

  togglePermiteMeGusta(idRevista: number, currentState: boolean): void {
    const newState = !currentState;
    this.revistaService.actualizarPermiteMeGusta(idRevista, newState).subscribe(() => {
      const publicacion = this.publicaciones.find(pub => pub.idRevista === idRevista);
      if (publicacion) {
        publicacion.permiteMegusta = newState;
      }
      console.log('Permite Me Gusta actualizado exitosamente');
    }, error => console.error('Error al actualizar Permite Me Gusta', error));
  }

  togglePermiteSuscripcion(idRevista: number, currentState: boolean): void {
    const newState = !currentState;
    this.revistaService.actualizarPermiteSuscripcion(idRevista, newState).subscribe(() => {
      const publicacion = this.publicaciones.find(pub => pub.idRevista === idRevista);
      if (publicacion) {
        publicacion.permiteSuscripcion = newState;
      }
      console.log('Permite Suscripciones actualizado exitosamente');
    }, error => console.error('Error al actualizar Permite Suscripciones', error));
  }

  
  agregarNuevaRevista(): void {
    this.router.navigate(['crear-revista']);
  }

  ingresarRevista(idRevista: number): void {
    this.router.navigate(['revista', idRevista]);
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  eliminarRevistas(): void {
    this.router.navigate(['eliminar-revista']);
  }
  
}
