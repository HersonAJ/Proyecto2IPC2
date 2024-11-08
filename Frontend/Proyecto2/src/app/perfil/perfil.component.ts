import { Component, OnInit } from '@angular/core';
import { PerfilService } from '../Services/perfil.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { EditarPerfilComponent } from '../editar-perfil/editar-perfil.component';
import { EditarPasswordComponent } from '../editar-password/editar-password.component';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule, EditarPerfilComponent, EditarPasswordComponent],
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {
  fotoPerfilBase64: string | null = null;
  nombre: string = 'Tu Nombre';
  email: string = 'tu.email@ejemplo.com';
  descripcion: string = 'Descripción del usuario';
  hobbies: string = 'Hobbies del usuario';
  temasInteres: string = 'Temas de interés';
  tipoUsuario: string = 'Tipo de usuario';
  errorMessage: string | null = null;
  editMode: boolean = false;
  passwordMode: boolean = false;

  constructor(
    private perfilService: PerfilService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const token = localStorage.getItem('jwtToken');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
    this.obtenerPerfil();
  }

  obtenerPerfil(): void {
    this.perfilService.getPerfil().subscribe({
      next: (perfil) => {
        this.actualizarPerfilDatos(perfil);
      },
      error: (error) => {
        this.errorMessage = 'Error al cargar el perfil. Por favor, inténtalo de nuevo.';
        console.error('Error al obtener el perfil:', error);
        this.router.navigate(['/login']);
      }
    });
  }

  actualizarPerfilDatos(perfil: any): void {
    this.nombre = perfil.nombre;
    this.email = perfil.email;
    this.descripcion = perfil.descripcion;
    this.hobbies = perfil.hobbies;
    this.temasInteres = perfil.temasInteres;
    this.tipoUsuario = perfil.tipoUsuario;
    this.fotoPerfilBase64 = `data:image/jpeg;base64,${perfil.fotoPerfil}`; // Aquí decodificas la imagen
  }

  toggleEditMode(): void {
    this.editMode = !this.editMode;
  }

  togglePasswordMode(): void {
    this.passwordMode = !this.passwordMode;
  }
}