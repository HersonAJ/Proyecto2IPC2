import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AnuncioService } from '../Services/anuncio.service';
import { AuthService } from '../Services/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-mis-anuncios',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './mis-anuncios.component.html',
  styleUrls: ['./mis-anuncios.component.css']
})
export class MisAnunciosComponent implements OnInit {
  anuncios: any[] = [];
  token: string | null = null;
  idUsuario: number | null = null;
  private anuncioSubscription: Subscription | null = null;

  constructor(private anuncioService: AnuncioService, private authService: AuthService) {}

  ngOnInit() {
    this.token = localStorage.getItem('jwtToken');
    this.idUsuario = this.authService.getIdUsuario();

    if (this.token && this.idUsuario) {
      this.cargarAnuncios();
    }
this.anuncioSubscription = this.anuncioService.obtenerActualizacionesAnuncios().subscribe(() => {
  this.cargarAnuncios();
})

  }

  cargarAnuncios() {
    this.anuncioService.obtenerMisAnuncios(this.idUsuario!, this.token!).subscribe(response => {
      this.anuncios = response;
    }, error => {
      console.error('Error al obtener anuncios:', error);
    });
  }

  cambiarEstado(idAnuncio: number, nuevoEstado: string) {
    if (this.token) {
      this.anuncioService.cambiarEstadoAnuncio(idAnuncio, nuevoEstado, this.token).subscribe(() => {
        this.cargarAnuncios(); // Recargar los anuncios después de cambiar el estado
        this.anuncioService.notificarActualizacionAnuncios();
      }, error => {
        console.error('Error al cambiar estado del anuncio:', error);
      });
    }
  }
}
