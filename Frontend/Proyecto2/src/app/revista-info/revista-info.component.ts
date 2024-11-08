import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { EdicionService } from '../Services/edicion.service';
import { Router } from '@angular/router';
import { RevistaInfoService } from '../Services/revista-info.service';
import { AuthService } from '../Services/auth.service';

@Component({
  selector: 'app-revista-info',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './revista-info.component.html',
  styleUrls: ['./revista-info.component.css'],
})
export class RevistaInfoComponent implements OnInit {
  revista: any = {};  // Inicializar como un objeto vacío
  ediciones: any[] = [];
  comentarios: any[] = [];  // Lista de comentarios
  nuevoComentario: string = '';  // Nuevo comentario
  fechaComentario: string = '';  // Fecha del comentario
  idRevista: number;
  meGustas: any[] = [];  // Lista de Me Gusta

  constructor(
    private edicionesService: EdicionService,
    private revistaInfo: RevistaInfoService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService // Inyectar AuthService
  ) {
    this.idRevista = Number(this.route.snapshot.paramMap.get('idRevista'));
  }

  ngOnInit(): void {
    console.log('ngOnInit se ha ejecutado'); // Confirmar si ngOnInit se ejecuta
    const idRevista = this.route.snapshot.paramMap.get('idRevista');
    console.log('idRevista en ngOnInit:', idRevista); // Confirmar si idRevista se obtiene
    if (idRevista) {
      this.revistaInfo.getRevista(Number(idRevista)).subscribe((data) => {
        this.revista = data;
        console.log('Revista:', this.revista); // Verificar el contenido de revista
      });

      this.revistaInfo.getEdiciones(Number(idRevista)).subscribe((data) => {
        this.ediciones = data;
        console.log('Ediciones:', this.ediciones); // Verificar que las ediciones se están cargando
      });

      this.revistaInfo.getComentarios(Number(idRevista)).subscribe((data) => {
        this.comentarios = data;
        console.log('Comentarios:', this.comentarios); // Verificar que los comentarios se están cargando
      });

      this.revistaInfo.getMeGustas(Number(idRevista)).subscribe((data) => {
        this.meGustas = data;
        console.log('Me Gusta:', this.meGustas); // Verificar que los Me Gusta se están cargando
      });
    } else {
      console.warn('idRevista no está disponible'); // Verificar si falta el idRevista
    }
  }

  agregarComentario(): void {
    const idUsuario = this.authService.getIdUsuario(); // Obtener idUsuario
    if (this.nuevoComentario.trim() && this.fechaComentario.trim() && idUsuario !== null) {
      const comentario = {
        idUsuario: idUsuario, // Usar el idUsuario del AuthService
        idRevista: this.idRevista,
        contenido: this.nuevoComentario,
        fechaCreacion: this.fechaComentario  // Utilizar la fecha manual
      };
  
      this.revistaInfo.agregarComentario(comentario).subscribe(
        (response) => {
          this.comentarios.push(response);
          this.nuevoComentario = '';  // Limpiar el campo de texto
          this.fechaComentario = '';  // Limpiar el campo de fecha
        },
        (error) => {
          if (error.status === 403) {
            alert('La revista no permite comentarios.');
          } else {
            alert('Error al agregar comentario.');
            console.error('Error al agregar comentario', error);
          }
        }
      );
    } else {
      console.warn('Faltan datos para agregar el comentario'); // Mensaje de advertencia si faltan datos
    }
  }
  

  darMeGusta(): void {
    const idUsuario = this.authService.getIdUsuario(); // Obtener idUsuario
    if (idUsuario !== null) {
      const meGusta = {
        idUsuario: idUsuario,
        idRevista: this.idRevista,
        fechaMeGusta: new Date().toISOString().split('T')[0]  // Formato de fecha YYYY-MM-DD
      };
  
      this.revistaInfo.agregarMeGusta(meGusta).subscribe(
        (response) => {
          this.meGustas.push(response);
        },
        (error) => {
          if (error.status === 403) {
            alert('La revista no permite "Me Gusta".');
          } else {
            alert('Error al agregar "Me Gusta".');
            console.error('Error al agregar "Me Gusta"', error);
          }
        }
      );
    } else {
      alert('Usuario no autenticado.');
    }
  }
  

  verPdf(idEdicion: number): void {
    this.router.navigate(['ver-archivo', this.idRevista, idEdicion]);
  }

  getMeGustaCount(): number {
    return this.meGustas.length;
  }
}
