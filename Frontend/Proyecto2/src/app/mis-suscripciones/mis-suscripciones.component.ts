import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RevistaSuscriptorService } from '../Services/revista-suscriptor.service';
import { AuthService } from '../Services/auth.service';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-mis-suscripciones',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './mis-suscripciones.component.html',
  styleUrls: ['./mis-suscripciones.component.css'],
  providers: [RevistaSuscriptorService, AuthService]
})
export class MisSuscripcionesComponent implements OnInit {
  revistas: any[] = [];

  constructor(private revistaSuscriptorService: RevistaSuscriptorService, private authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.obtenerRevistasSuscritas();
  }

  obtenerRevistasSuscritas() {
    const idUsuario = this.authService.getIdUsuario();
    if (idUsuario !== null) {
      this.revistaSuscriptorService.getRevistasSuscritasPorUsuario(idUsuario).subscribe(
        (data: any) => {
          this.revistas = data;
        },
        (error: any) => {
          console.error('Error al obtener las revistas suscritas', error);
        }
      );
    } else {
      console.error('Usuario no autenticado');
    }
  }

  irARevistaInfo(idRevista: number) {
    console.log('Navegando a /revista-info con idRevista:', idRevista);
    this.router.navigate(['/revista-info', idRevista]);
  }
  
}
