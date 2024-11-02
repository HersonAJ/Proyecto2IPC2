import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from '../Services/auth.service';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, HttpClientModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  contrasenia: string = '';
  mensaje: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(): void {
    this.authService.login(this.username, this.contrasenia).subscribe(
      (response: { token: string }) => {
        console.log('Login exitoso:', response.token);
        this.authService.setToken(response.token);
        alert('Bienvenido, ' + this.username + '!');
        this.router.navigate(['/']); // Redirige al homepage
      },
      (error) => {
        console.error('Error en el login:', error);
        alert('Credenciales incorrectas. Por favor, inténtelo de nuevo.');
      }
    );
  }
}