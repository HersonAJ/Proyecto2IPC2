import { Component, OnInit } from '@angular/core';
import { AdminService } from '../Services/admin.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-precio-anuncios',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './precio-anuncios.component.html',
  styleUrls: ['./precio-anuncios.component.css']
})
export class PrecioAnunciosComponent implements OnInit {
  preciosAnuncios: any[] = [];
  nuevoPrecio: any = {
    tipo: '',
    duracion: '',
    precio: 0
  };

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.obtenerPreciosAnuncios();
  }

  obtenerPreciosAnuncios(): void {
    const tiposAnuncios = ['Texto', 'Texto e Imagen', 'Video'];
    const duraciones = ['1 día', '3 días', '1 semana', '2 semanas'];

    tiposAnuncios.forEach(tipo => {
      duraciones.forEach(duracion => {
        this.adminService.obtenerPrecioAnuncio(tipo, duracion).subscribe(
          response => {
            this.preciosAnuncios.push({
              tipo: tipo,
              duracion: duracion,
              precio: response.precio
            });
          },
          error => {
            console.error(error);
            alert('Error al obtener los precios de los anuncios');
          }
        );
      });
    });
  }

  fijarPrecioAnuncio(): void {
    const { tipo, duracion, precio } = this.nuevoPrecio;

    if (precio < 0) {
      alert('El precio no puede ser negativo');
      return;
    }

    this.adminService.fijarPrecioAnuncio(tipo, duracion, precio).subscribe(
      response => {
        console.log(response);
        alert('Precio del anuncio fijado correctamente');
        this.preciosAnuncios = [];
        this.obtenerPreciosAnuncios(); // Actualizar la lista de precios
      },
      error => {
        console.error(error);
        alert('Error al fijar el precio del anuncio');
      }
    );
  }
}