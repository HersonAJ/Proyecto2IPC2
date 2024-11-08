import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormControl } from '@angular/forms';
import { PerfilService } from '../Services/perfil.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { PerfilComponent } from '../perfil/perfil.component';

@Component({
  selector: 'app-editar-perfil',
  templateUrl: './editar-perfil.component.html',
  styleUrls: ['./editar-perfil.component.css'],
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule]
})
export class EditarPerfilComponent implements OnInit {
  perfilForm: FormGroup;
  selectedFile: File | null = null;

  constructor(
    private fb: FormBuilder,
    private perfilService: PerfilService,
    private router: Router,
    private perfilComponent: PerfilComponent // Inyectar el componente PerfilComponent
  ) {
    this.perfilForm = this.fb.group({
      nombre: ['', Validators.required],
      email: [{ value: '', disabled: true }, [Validators.required, Validators.email]], // Email deshabilitado
      fotoPerfil: [null],
      descripcion: ['', Validators.required],
      hobbies: ['', Validators.required],
      temasInteres: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.cargarPerfil();
  }

  cargarPerfil() {
    this.perfilService.getPerfil().subscribe((data: any) => {
      this.perfilForm.patchValue({
        nombre: data.nombre,
        email: data.email,
        descripcion: data.descripcion,
        hobbies: data.hobbies.join(','), // Convertir array a string separado por comas
        temasInteres: data.temasInteres.join(',') // Convertir array a string separado por comas
      });
      if (data.fotoPerfil) {
        this.selectedFile = this.base64ToFile(data.fotoPerfil, 'profile-image.jpg');
      }
      // Deshabilitar el campo email
      this.perfilForm.get('email')?.disable();
    }, error => {
      console.error('Error al cargar perfil', error);
    });
  }

  get emailControl(): FormControl {
    return this.perfilForm.get('email') as FormControl;
  }

  base64ToFile(base64: string, filename: string): File {
    const byteString = atob(base64);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const intArray = new Uint8Array(arrayBuffer);
    for (let i = 0; i < byteString.length; i++) {
      intArray[i] = byteString.charCodeAt(i);
    }
    const blob = new Blob([intArray], { type: 'image/jpeg' });
    return new File([blob], filename, { type: 'image/jpeg' });
  }

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }

  actualizarPerfil() {
    if (this.perfilForm.valid) {
      const formData = new FormData();
      formData.append('nombre', this.perfilForm.get('nombre')?.value);
      formData.append('email', this.emailControl.value);
      formData.append('descripcion', this.perfilForm.get('descripcion')?.value);
      formData.append('hobbies', this.perfilForm.get('hobbies')?.value);
      formData.append('temasInteres', this.perfilForm.get('temasInteres')?.value);

      if (this.selectedFile) {
        formData.append('fotoPerfil', this.selectedFile, this.selectedFile.name);
      }

      this.perfilService.actualizarPerfil(formData).subscribe(
        (response) => {
          console.log('Perfil actualizado exitosamente');
          this.perfilComponent.actualizarPerfilDatos(response);
          alert('Perfil actualizado correctamente');
          this.perfilComponent.obtenerPerfil(); // Recargar datos del perfil
          this.perfilComponent.toggleEditMode(); // Salir del modo de edición
        },
        error => {
          console.error('Error al actualizar perfil', error);
        }
      );
    } else {
      this.perfilForm.markAllAsTouched();
      const errores = [];

      if (!this.perfilForm.get('nombre')?.valid) {
        errores.push('El nombre es obligatorio');
      }
      if (!this.perfilForm.get('email')?.valid) {
        errores.push('El correo electrónico es obligatorio');
      }
      if (!this.perfilForm.get('descripcion')?.valid) {
        errores.push('La descripción es obligatoria');
      }
      if (!this.perfilForm.get('hobbies')?.valid) {
        errores.push('Los hobbies son obligatorios');
      }
      if (!this.perfilForm.get('temasInteres')?.valid) {
        errores.push('Los temas de interés son obligatorios');
      }

      alert('Por favor, complete los siguientes campos obligatorios: \n' + errores.join('\n'));
    }
  }
}
