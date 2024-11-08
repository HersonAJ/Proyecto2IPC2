import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PerfilesEditoresService } from '../Services/perfil-editores.service';

@Component({
  selector: 'app-perfil-editores',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './perfil-editores.component.html',
  styleUrls: ['./perfil-editores.component.css']
})
export class PerfilEditoresComponent implements OnInit {

  editores: any[] = [];
  isLoading: boolean = true;
  errorMessage: string = '';

  constructor(private perfilesEditoresService: PerfilesEditoresService) {}

  ngOnInit(): void {
    this.perfilesEditoresService.obtenerEditores().subscribe(
      (data: any[]) => {
        this.editores = data.map((editor: any) => {
          if (editor.fotoPerfil) {
            // Asignar directamente la cadena Base64 de la respuesta al atributo fotoPerfilBase64
            editor.fotoPerfilBase64 = 'data:image/jpeg;base64,' + editor.fotoPerfil;
          }
          return editor;
        });
        this.isLoading = false;
      },
      error => {
        this.errorMessage = 'Error al cargar los perfiles de editores.';
        this.isLoading = false;
      }
    );
  }
}