import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CategoriasService } from '../Services/categoria.service';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  categorias: any[] = [];
  isLoading: boolean = true;
  errorMessage: string = '';

  constructor(private categoriasService: CategoriasService) {}

  ngOnInit(): void {
    this.categoriasService.obtenerRevistasPorCategoria().subscribe(
      data => {
        // Transformar el objeto recibido en un array
        this.categorias = Object.keys(data).map(key => {
          return { categoria: key, revistas: data[key] };
        });
        this.isLoading = false;
      },
      error => {
        this.errorMessage = 'Error al cargar las categorías.';
        this.isLoading = false;
      }
    );
  }
}
