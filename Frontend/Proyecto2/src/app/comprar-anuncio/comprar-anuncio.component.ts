import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AnuncioService } from '../Services/anuncio.service';
import { AuthService } from '../Services/auth.service';

@Component({
  selector: 'app-comprar-anuncio',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './comprar-anuncio.component.html',
  styleUrls: ['./comprar-anuncio.component.css']
})
export class ComprarAnuncioComponent {
  anuncio: any = {
    tipo: 'Texto',
    contenido: '',
    imagen: '',
    video: '',
    idUsuario: null,
    fechaInicio: '',
    fechaFin: '',
    estado: 'Activo'
  };
  duracion: string = '1 día';
  token: string = '';
  selectedFile: File | null = null;

  constructor(
    private anuncioService: AnuncioService,
    private authService: AuthService
  ) {
    this.token = localStorage.getItem('jwtToken') || '';
    this.anuncio.idUsuario = this.authService.getIdUsuario();
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  crearAnuncio() {
    if (this.anuncio.tipo === 'Texto') {
      this.anuncioService.crearAnuncioTexto(this.anuncio, this.token).subscribe(response => {
        console.log('Anuncio de texto creado:', response);
      }, error => {
        console.error('Error al crear anuncio de texto:', error);
      });
    } else if (this.anuncio.tipo === 'Texto e Imagen' && this.selectedFile) {
      this.anuncioService.crearAnuncioTextoImagen(this.anuncio, this.selectedFile, this.token).subscribe(response => {
        console.log('Anuncio de texto e imagen creado:', response);
      }, error => {
        console.error('Error al crear anuncio de texto e imagen:', error);
      });
    } else if (this.anuncio.tipo === 'Video') {
      this.anuncioService.crearAnuncioVideo(this.anuncio, this.token).subscribe(response => {
        console.log('Anuncio de video creado:', response);
      }, error => {
        console.error('Error al crear anuncio de video:', error);
      });
    }
  }
}
