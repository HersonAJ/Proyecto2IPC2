import { Component, NgModule } from '@angular/core';
import { HeaderComponent } from './header/header.component';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { NgFor } from '@angular/common';
import { AnuncioComponent } from "./anuncio/anuncio.component";
import { CollapseModule } from 'ngx-bootstrap/collapse';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [HeaderComponent, RouterOutlet, RouterLink, RouterLinkActive, AnuncioComponent, CollapseModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Proyecto2';
}
