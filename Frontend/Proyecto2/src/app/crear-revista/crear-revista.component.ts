import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RevistaService } from '../Services/revista.service';
import { AuthService } from '../Services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-crear-revista',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './crear-revista.component.html'
})
export class CrearRevistaComponent implements OnInit {
  revistaForm: FormGroup;
  idUsuario: number | null = null;

  constructor(
    private fb: FormBuilder,
    private revistaService: RevistaService,
    private authService: AuthService, // Utiliza AuthService
    private router: Router
  ) {
    this.revistaForm = this.fb.group({
      titulo: ['', Validators.required],
      descripcion: ['', Validators.required],
      categoria: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.idUsuario = this.authService.getIdUsuario(); // Obtener el idUsuario desde AuthService
    if (this.idUsuario === null) {
      console.error('Error: No se pudo obtener el idUsuario');
    }
  }

  onSubmit() {
    if (this.revistaForm.valid && this.idUsuario !== null) {
      const revistaData = {
        ...this.revistaForm.value,
        idUsuario: this.idUsuario
      };
      console.log('Revista Data:', JSON.stringify(revistaData)); // Verificar el JSON antes de enviar
      this.revistaService.crearRevista(revistaData).subscribe(response => {
        console.log('Revista creada exitosamente', response);
        alert('Revista creada exitosamente');
        this.router.navigate(['misPublicaciones']);
      }, error => {
        console.error('Error al crear la revista', error);
        alert('Error al crear la revista');
      });
    } else {
      console.error('Formulario inválido o idUsuario no disponible');
    }
  }
}
