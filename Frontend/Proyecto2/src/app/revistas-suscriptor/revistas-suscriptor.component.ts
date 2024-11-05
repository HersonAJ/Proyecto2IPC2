import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RevistaSuscriptorService } from '../Services/revista-suscriptor.service';
import { AuthService } from '../Services/auth.service';
import { RouterModule } from '@angular/router';
import { RevistaInfoService } from '../revista-info.service'; 

interface Revista {
  idRevista: number;
  titulo: string;
  descripcion: string;
  categoria: string;
  fechaCreacion: Date;
  meGustaCount?: number; 
}

@Component({
  selector: 'app-revistas-suscriptor',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, RouterModule],
  templateUrl: './revistas-suscriptor.component.html',
  styleUrls: ['./revistas-suscriptor.component.css'],
  providers: [RevistaInfoService] 
})
export class RevistasSuscriptorComponent implements OnInit {
  revistas: Revista[] = [];
  categoria: string = '';
  tag: string = '';
  idUsuario: number | null = null;
  fechaSuscripcion: string | null = null;
  mostrarCampoFecha: boolean = false;
  idRevistaSeleccionada: number | null = null;

  constructor(
    private revistaService: RevistaSuscriptorService,
    private authService: AuthService,
    private revistaInfoService: RevistaInfoService  // Inyectar RevistaInfoService
  ) {}

  ngOnInit(): void {
    this.idUsuario = this.authService.getIdUsuario();
    this.revistaService.getTodasLasRevistas().subscribe(
      (revistas: Revista[]) => {
        this.revistas = revistas;
        this.revistas.forEach(revista => {
          this.revistaInfoService.getMeGustas(revista.idRevista).subscribe(
            (meGustas: any[]) => {
              revista.meGustaCount = meGustas.length;
            },
            error => {
              console.error('Error al obtener Me Gusta', error);
            }
          );
        });
      },
      error => {
        console.error('Error al obtener las revistas', error);
      }
    );
  }

  suscribirse(idRevista: number): void {
    console.log('Botón de suscripción presionado');
    console.log(`ID de la revista seleccionada: ${idRevista}`);
    
    this.idRevistaSeleccionada = idRevista;
    this.mostrarCampoFecha = true; // Muestra el campo de fecha
  }

  confirmarSuscripcion(): void {
    console.log('Confirmación de suscripción iniciada');
    console.log(`Fecha de suscripción ingresada: ${this.fechaSuscripcion}`);
    console.log(`ID de usuario: ${this.idUsuario}`);
    console.log(`ID de revista: ${this.idRevistaSeleccionada}`);

    if (this.idUsuario !== null && this.fechaSuscripcion) {
      this.revistaService.suscribirseRevista(
        this.idUsuario,
        this.idRevistaSeleccionada!,
        this.fechaSuscripcion
      ).subscribe(
        response => {
          alert(response.message);
          this.actualizarRevistas();
          this.mostrarCampoFecha = false; // Oculta el campo de fecha
        },
        error => {
          if (error.status === 409) {
            // Manejo específico para el conflicto de suscripción existente
            alert('Ya está suscrito a esta revista.');
          } else {
            alert('Error al realizar la suscripción.');
            console.error('Error al suscribirse a la revista', error);
          }
          this.mostrarCampoFecha = false;
        }
      );
    } else {
      alert('Por favor, proporciona una fecha de suscripción válida.');
    }
  }

  actualizarRevistas(): void {
    this.revistaService.getTodasLasRevistas().subscribe(
      (revistas: Revista[]) => {
        this.revistas = revistas;
        this.revistas.forEach(revista => {
          this.revistaInfoService.getMeGustas(revista.idRevista).subscribe(
            (meGustas: any[]) => {
              revista.meGustaCount = meGustas.length;
            },
            error => {
              console.error('Error al obtener Me Gusta', error);
            }
          );
        });
      },
      error => console.error('Error al actualizar las revistas', error)
    );
  }



  buscarPorCategoria(): void {
    this.revistaService.getRevistasPorCategoria(this.categoria).subscribe(
      (revistas: Revista[]) => {
        this.revistas = revistas;
        this.revistas.forEach(revista => {
          this.revistaInfoService.getMeGustas(revista.idRevista).subscribe(
            (meGustas: any[]) => {
              revista.meGustaCount = meGustas.length;
            },
            error => {
              console.error('Error al obtener Me Gusta', error);
            }
          );
        });
      },
      error => {
        if (error.status === 404) {
          alert('No se encontraron revistas para la categoría especificada.');
          this.revistas = [];
        } else {
          console.error('Error al buscar por categoría', error);
        }
      }
    );
  }

  buscarPorTag(): void {
    this.revistaService.getRevistasPorTag(this.tag).subscribe(
      (revistas: Revista[]) => {
        this.revistas = revistas;
        this.revistas.forEach(revista => {
          this.revistaInfoService.getMeGustas(revista.idRevista).subscribe(
            (meGustas: any[]) => {
              revista.meGustaCount = meGustas.length;
            },
            error => {
              console.error('Error al obtener Me Gusta', error);
            }
          );
        });
      },
      error => {
        if (error.status === 404) {
          alert('No se encontraron revistas para el tag especificado.');
          this.revistas = [];
        } else {
          console.error('Error al buscar por tag', error);
        }
      }
    );
  }
}
