import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from './Services/auth.service';

export const authGuardGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const authService = inject(AuthService);

  const expectedRoles = route.data?.['expectedRoles'] || []; // Esperamos un arreglo de roles
  const userRole = authService.getTipoUsuario();

  if (!expectedRoles.includes(userRole)) {
    alert('No tienes los permisos para estar aquí.');
    router.navigate(['']);  // Ruta a la que quieres redirigir si el usuario no está autorizado
    return false;
  }
  return true;
};
