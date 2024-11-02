import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { UsuarioService } from '../Services/usuario.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css'],
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule]
})
export class RegistroComponent {
  registroForm: FormGroup;
  selectedFile: File | null = null; // Variable to store the selected file

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private router: Router
  ) {
    this.registroForm = this.fb.group({
      nombre: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      fotoPerfil: [null],
      descripcion: ['', Validators.required],
      hobbies: [[], Validators.required],
      temasInteres: [[], Validators.required],
      tipoUsuario: ['', Validators.required],
      saldoCartera: [0]
    });
  }

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }

  onSubmit() {
    if (this.registroForm.valid) {
      const formData = new FormData(); // Append form fields to the FormData object
      formData.append('nombre', this.registroForm.get('nombre')?.value);
      formData.append('email', this.registroForm.get('email')?.value);
      formData.append('password', this.registroForm.get('password')?.value);
      formData.append('descripcion', this.registroForm.get('descripcion')?.value);
      formData.append('hobbies', this.registroForm.get('hobbies')?.value.join(','));
      formData.append('temasInteres', this.registroForm.get('temasInteres')?.value.join(','));
      formData.append('tipoUsuario', this.registroForm.get('tipoUsuario')?.value);
      formData.append('saldoCartera', this.registroForm.get('saldoCartera')?.value);

      // Append file if selected
      if (this.selectedFile) {
        formData.append('fotoPerfil', this.selectedFile, this.selectedFile.name);
      }

      this.usuarioService.registrarUsuario(formData).subscribe(
        (response) => {
          console.log('Usuario registrado:', response);
          alert('Registro exitoso');
          this.router.navigate(['/']);
        },
        (error) => {
          if (error.status === 409) {
            alert('El correo electrónico ya está registrado.');
          } else {
            console.error('Error al registrar usuario:', error);
          }
        }
      );
    } else {
      this.registroForm.markAllAsTouched();
      const errores = [];

      if (!this.registroForm.get('nombre')?.valid) {
        errores.push('El nombre es obligatorio');
      }
      if (!this.registroForm.get('email')?.valid) {
        errores.push('El correo electrónico es obligatorio');
      }
      if (!this.registroForm.get('contraseña')?.valid) {
        errores.push('La contraseña es obligatoria');
      }
      if (!this.registroForm.get('descripcion')?.valid) {
        errores.push('La descripción es obligatoria');
      }
      if (!this.registroForm.get('hobbies')?.valid) {
        errores.push('Los hobbies son obligatorios');
      }
      if (!this.registroForm.get('temasInteres')?.valid) {
        errores.push('Los temas de interés son obligatorios');
      }
      if (!this.registroForm.get('tipoUsuario')?.valid) {
        errores.push('El tipo de usuario es obligatorio');
      }

      alert('Por favor, complete los siguientes campos obligatorios: \n' + errores.join('\n'));
    }
  }

  mostrarSaldoCartera() {
    const tipoUsuario = this.registroForm.get('tipoUsuario')?.value;
    return tipoUsuario === 'Comprador_Anuncios';
  }
}


