import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RevistaService } from '../Services/revista.service';
import { AuthService } from '../Services/auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-crear-revista',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule, FormsModule],
  templateUrl: './crear-revista.component.html'
})
export class CrearRevistaComponent implements OnInit {
  revistaForm: FormGroup;
  idUsuario: number | null = null;
  nuevaEdicion: any = {};

  constructor(
    private fb: FormBuilder,
    private revistaService: RevistaService,
    private authService: AuthService,
    private router: Router
  ) {
    this.revistaForm = this.fb.group({
      titulo: ['', Validators.required],
      descripcion: ['', Validators.required],
      categoria: ['', Validators.required],
      tags: ['', Validators.required],
      permiteComentarios: [false],
      permiteMegusta: [false],
      permiteSuscripcion: [false],
      fechaCreacion: ['', Validators.required] // Agregado
    });
  }

  ngOnInit() {
    this.idUsuario = this.authService.getIdUsuario();
    if (this.idUsuario === null) {
      console.error('Error: No se pudo obtener el idUsuario');
    }
  }

  onSubmit() {
    console.log('Formulario:', this.revistaForm.value);
    console.log('Id Usuario antes de crear revista:', this.idUsuario);
    
    if (this.revistaForm.valid && this.idUsuario !== null) {
      const tagsArray = this.revistaForm.value.tags.split(',').map((tag: string) => ({ nombre: tag.trim() }));
      const revistaData = {
        ...this.revistaForm.value,
        idUsuario: this.idUsuario,
        costoPorDia: 0,
        tags: tagsArray
      };
  
      console.log('Revista Data:', JSON.stringify(revistaData));
      this.revistaService.crearRevista(revistaData).subscribe(
        response => {
          console.log('Revista creada exitosamente', response);
          alert('Revista creada exitosamente');
          this.router.navigate(['misPublicaciones']);
        },
        error => {
          console.error('Error al crear la revista', error);
          alert('Error al crear la revista');
        }
      );
    } else {
      console.error('Formulario inválido o idUsuario no disponible');
    }
  }
  
}
