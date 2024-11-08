import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { PerfilService } from '../Services/perfil.service';

@Component({
  selector: 'app-editar-password',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './editar-password.component.html',
  styleUrls: ['./editar-password.component.css']
})
export class EditarPasswordComponent {
  contrasenaForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private perfilService: PerfilService,
    private router: Router
  ) {
    this.contrasenaForm = this.fb.group({
      nuevaContrasena: ['', [Validators.required, Validators.minLength(6)]],
      confirmacionContrasena: ['', [Validators.required]]
    }, { validators: this.passwordsMatch });
  }

  passwordsMatch(group: FormGroup) {
    const pass = group.controls['nuevaContrasena'].value;
    const confirmPass = group.controls['confirmacionContrasena'].value;
    return pass === confirmPass ? null : { notSame: true };
  }

  actualizarContrasena() {
    if (this.contrasenaForm.valid) {
      const nuevaContrasena = this.contrasenaForm.get('nuevaContrasena')?.value;
      this.perfilService.actualizarContrasena(nuevaContrasena).subscribe(
        (response) => {
          alert('Contraseña actualizada correctamente');
          this.router.navigate(['/perfil']);
        },
        (error) => {
          console.error('Error al actualizar la contraseña:', error);
          alert('Error al actualizar la contraseña');
        }
      );
    } else {
      this.contrasenaForm.markAllAsTouched();
      alert('Por favor, complete todos los campos correctamente.');
    }
  }
}
