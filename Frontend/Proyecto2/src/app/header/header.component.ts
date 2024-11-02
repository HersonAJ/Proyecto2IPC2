import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../Services/auth.service';
import { LogoutService } from '../Services/logout.service';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, RouterOutlet, CommonModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [LogoutService] 
})
export class HeaderComponent implements OnInit {
  sesionIniciada: boolean = false;
  tipoUsuario: string | null = null;

  constructor(private authService: AuthService, private logoutService: LogoutService, private router: Router, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.authService.autenticado$.subscribe((autenticado: boolean) => {
      this.sesionIniciada = autenticado;
if(autenticado) {
  this.tipoUsuario = this.authService.getTipoUsuario();
} else {
  this.tipoUsuario = null;
}

      //this.tipoUsuario = this.authService.getTipoUsuario();
      console.log('Tipo de usuario en HeaderComponent:', this.tipoUsuario); 
      this.cdr.detectChanges();
    });
  }

  cerrarSesion(): void {
    const token = localStorage.getItem('jwtToken');
    if (token) {
      this.logoutService.logout(token).subscribe(
        () => {
          this.authService.logout();
          this.sesionIniciada = false;
          this.tipoUsuario = null;
          this.cdr.detectChanges();
          this.router.navigate(['/login']);
        },
        (error) => {
          console.error('Error en el logout:', error);
        }
      );
    } else {
      this.authService.logout();
      this.sesionIniciada = false;
      this.tipoUsuario = null;
      this.cdr.detectChanges();
      this.router.navigate(['/login']);
    }
  }
}

