
import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistroComponent } from './registro/registro.component';
import { HomepageComponent } from './homepage/homepage.component';
import { PerfilComponent } from './perfil/perfil.component';
import { MisPublicacionesComponent } from './mis-publicaciones/mis-publicaciones.component';
import { CrearRevistaComponent } from './crear-revista/crear-revista.component';
import { RevistaComponent } from './revista/revista.component';
import { CargarEdicionComponent } from './cargar-edicion/cargar-edicion.component';
import { VerArchivoComponent } from './ver-archivo/ver-archivo.component';
import { RecargarCarteraComponent } from './recargar-cartera-editor/recargar-cartera-editor.component';
import { CarteraGlobalComponent } from './cartera-global/cartera-global.component';
import { EliminarRevistaComponent } from './eliminar-revista/eliminar-revista.component';
import { RevistasSuscriptorComponent } from './revistas-suscriptor/revistas-suscriptor.component';
import { MisSuscripcionesComponent } from './mis-suscripciones/mis-suscripciones.component';
import { RevistaInfoComponent } from './revista-info/revista-info.component';
import { AsignarCostoComponent } from './asignar-costo/asignar-costo.component';
import { ReportesComponent } from './reportes/reportes.component';
import { ReportePopularComponent } from './reporte-popular-admin/reporte-popular-admin.component';
import { PrecioAnunciosComponent } from './precio-anuncios/precio-anuncios.component';
import { provideRouter, RouterModule } from '@angular/router'; 
import { authGuardGuard } from './auth-guard.guard';
//nuevos
import { ComprarAnuncioComponent } from './comprar-anuncio/comprar-anuncio.component';
import { MisAnunciosComponent } from './mis-anuncios/mis-anuncios.component';

//component para editar perfil 
import { EditarPerfilComponent } from './editar-perfil/editar-perfil.component';
//reportes del editor
import { ReportesEditorComponent } from './reportes-editor/reportes-editor.component';
import { PerfilEditoresComponent } from './perfil-editores/perfil-editores.component';







export const routes: Routes = [
  // todos los usuarios
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: '', component: HomepageComponent },
  { path: 'perfil', component: PerfilComponent },
  { path: 'ver-archivo/:idRevista/:idEdicion', component: VerArchivoComponent },
  { path: 'editar-perfil', component: EditarPerfilComponent },

  // editor
  { path: 'misPublicaciones', component: MisPublicacionesComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Editor'] } },
  { path: 'crear-revista', component: CrearRevistaComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Editor'] } },
  { path: 'revista/:idRevista', component: RevistaComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Editor'] } },
  { path: 'cargar-edicion/:idRevista', component: CargarEdicionComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Editor'] } },
  { path: 'eliminar-revista', component: EliminarRevistaComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Editor'] } },
  { path: 'reportes-editor', component: ReportesEditorComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Editor'] } },
  
  // editor, comprador de anuncios
  { path: 'recargar-cartera', component: RecargarCarteraComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Editor', 'Comprador_Anuncios'] } },

  // admin
  { path: 'recargar-cartera-global', component: CarteraGlobalComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Administrador'] } },
  { path: 'asignar-costo', component: AsignarCostoComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Administrador'] } },
  { path: 'reportes', component: ReportesComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Administrador'] } },
  { path: 'reporte-popular', component: ReportePopularComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Administrador'] } },
  { path: 'precio-anuncios', component: PrecioAnunciosComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Administrador'] } },

  // suscriptor
  { path: 'revistas', component: RevistasSuscriptorComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Suscriptor'] } },
  { path: 'mis-suscripciones', component: MisSuscripcionesComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Suscriptor'] } },
  { path: 'revista-info/:idRevista', component: RevistaInfoComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Suscriptor'] } },
  { path: 'perfil-editores', component: PerfilEditoresComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Suscriptor'] } },

  //comprador de anuncios
  { path: 'comprar-anuncio', component: ComprarAnuncioComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Comprador_Anuncios'] } },
  { path: 'mis-anuncios', component: MisAnunciosComponent, canActivate: [authGuardGuard], data: { expectedRoles: ['Comprador_Anuncios'] } },
];

// agrega la referencia al registro
export const appRoutingProviders: any[] = [
  provideRouter(routes)
];