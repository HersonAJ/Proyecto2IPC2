import { Component, OnInit } from '@angular/core';
import { RevistaService } from '../Services/revista.service';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../Services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-eliminar-revista',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './eliminar-revista.component.html',
  styleUrls: ['./eliminar-revista.component.css']
})
export class EliminarRevistaComponent implements OnInit {
  revistas: any[] = [];

  constructor(private revistaService: RevistaService, private authService: AuthService) { }

  ngOnInit(): void {
    const idUsuario = this.authService.getIdUsuario();
    if (idUsuario !== null) {
      this.cargarRevistas(idUsuario);
    } else {
      console.error('Error: No se pudo obtener el idUsuario');
    }
  }

  cargarRevistas(idUsuario: number): void {
    this.revistaService.getRevistasPorUsuario(idUsuario).subscribe(
      response => {
        this.revistas = response;
      },
      error => {
        console.log(error);
      }
    );
  }

  eliminarRevista(idRevista: number): void {
    const idUsuario = this.authService.getIdUsuario();
    if (idUsuario !== null) {
      this.revistaService.cambiarEstadoRevistaPrueba(idRevista).subscribe(
        response => {
          console.log(response);
          this.cargarRevistas(idUsuario);
        },
        error => {
          console.log(error);
        }
      );
    } else {
      console.error('Error: No se pudo obtener el idUsuario');
    }
  }
  
}
