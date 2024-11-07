import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AnuncioService } from '../Services/anuncio.service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-anuncio',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './anuncio.component.html',
  styleUrls: ['./anuncio.component.css']
})
export class AnuncioComponent implements OnInit, OnDestroy {
  anunciosTexto: any[] = [];
  anunciosImagen: any[] = [];
  anunciosVideo: any[] = [];
  currentTextoIndex: number = 0;
  currentImagenIndex: number = 0;
  currentVideoIndex: number = 0;
  private intervalSubscription: Subscription | null = null;
  private anunciosSubscription: Subscription | null = null;

  constructor(
    private anuncioService: AnuncioService,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit() {
    this.cargarAnuncios();
    this.startAdRotation();

    // Suscribirse a las actualizaciones de anuncios en tiempo real
    this.anunciosSubscription = this.anuncioService.obtenerActualizacionesAnuncios().subscribe(() => {
      this.cargarAnuncios();
    });
  }

  cargarAnuncios() {
    this.anuncioService.obtenerAnunciosActivos().subscribe(response => {
      // Si la respuesta es nula o no tiene anuncios, limpiamos los arrays y salimos del método.
      if (!response || response.length === 0) {
        console.warn('No hay anuncios activos disponibles.');
        this.anunciosTexto = [];
        this.anunciosImagen = [];
        this.anunciosVideo = [];
        return;
      }
  
      // Reiniciar los arreglos de anuncios
      this.anunciosTexto = [];
      this.anunciosImagen = [];
      this.anunciosVideo = [];
  
      response.forEach((anuncio: any) => {
        if (anuncio.tipo === 'Texto') {
          this.anunciosTexto.push(anuncio);
        } else if (anuncio.tipo === 'Texto e Imagen') {
          anuncio.imagen = this.sanitizer.bypassSecurityTrustUrl(anuncio.imagen);
          this.anunciosImagen.push(anuncio);
        } else if (anuncio.tipo === 'Video') {
          const videoId = this.extractVideoId(anuncio.video);
          anuncio.video = this.sanitizer.bypassSecurityTrustResourceUrl(`https://www.youtube.com/embed/${videoId}`);
          this.anunciosVideo.push(anuncio);
        }
      });
    }, error => {
      console.error('Error al obtener anuncios activos:', error);
    });
  }

  resetIndicesIfNeeded() {
    // Asegurarse de que los índices no excedan la longitud de los arreglos
    if (this.anunciosTexto.length === 0) {
      this.currentTextoIndex = 0;
    }
    if (this.anunciosImagen.length === 0) {
      this.currentImagenIndex = 0;
    }
    if (this.anunciosVideo.length === 0) {
      this.currentVideoIndex = 0;
    }
  }

  extractVideoId(url: string): string {
    const videoId = url.split('v=')[1];
    const ampersandPosition = videoId ? videoId.indexOf('&') : -1;
    return ampersandPosition !== -1 ? videoId.substring(0, ampersandPosition) : videoId || '';
  }

  startAdRotation() {
    this.intervalSubscription = interval(10000).subscribe(() => {
      // Cambiar índices solo si hay elementos en el arreglo
      if (this.anunciosTexto.length > 0) {
        this.currentTextoIndex = (this.currentTextoIndex + 1) % this.anunciosTexto.length;
      }
      if (this.anunciosImagen.length > 0) {
        this.currentImagenIndex = (this.currentImagenIndex + 1) % this.anunciosImagen.length;
      }
      if (this.anunciosVideo.length > 0) {
        this.currentVideoIndex = (this.currentVideoIndex + 1) % this.anunciosVideo.length;
      }
    });
  }

  ngOnDestroy() {
    if (this.intervalSubscription) {
      this.intervalSubscription.unsubscribe();
    }
    if (this.anunciosSubscription) {
      this.anunciosSubscription.unsubscribe();
    }
  }
}