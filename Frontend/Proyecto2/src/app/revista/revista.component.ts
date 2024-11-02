import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { EdicionService } from '../Services/edicion.service';
import { RevistaService } from '../Services/revista.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-revista',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './revista.component.html',
  styleUrls: ['./revista.component.css'],
})
export class RevistaComponent implements OnInit {
  revista: any = {};
  ediciones: any[] = [];
  idRevista: number;

  constructor(
    private edicionesService: EdicionService,
    private revistaService: RevistaService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.idRevista = Number(this.route.snapshot.paramMap.get('idRevista'));
  }

  ngOnInit(): void {
    const idRevista = this.route.snapshot.paramMap.get('idRevista');
    if (idRevista) {
      this.revistaService.getRevista(Number(idRevista)).subscribe((data) => {
        this.revista = data;
      });

      this.edicionesService.getEdiciones(Number(idRevista)).subscribe((data) => {
        this.ediciones = data;
      });
    }
  }

  navegarACargarEdicion(): void {
    this.router.navigate(['cargar-edicion', this.idRevista ]);
  }

  verPdf(idEdicion: number): void {
    this.router.navigate(['ver-archivo', this.idRevista, idEdicion]);
  }
}
