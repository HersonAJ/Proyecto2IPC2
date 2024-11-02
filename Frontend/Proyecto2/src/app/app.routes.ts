import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistroComponent } from './registro/registro.component';
import { HomepageComponent } from './homepage/homepage.component';
import { provideRouter, RouterModule } from '@angular/router'; //agregue esto para tratar de hacer la referencia al registro
import { importProvidersFrom } from '@angular/core';
import { PerfilComponent } from './perfil/perfil.component';
import { MisPublicacionesComponent } from './mis-publicaciones/mis-publicaciones.component';
import { CrearRevistaComponent } from './crear-revista/crear-revista.component';
import { RevistaComponent } from './revista/revista.component';
import { CargarEdicionComponent } from './cargar-edicion/cargar-edicion.component';
import { VerArchivoComponent } from './ver-archivo/ver-archivo.component';
//import { RecargarCarteraComponent } from './recargar-cartera/recargar-cartera.component';



export const routes: Routes = [

  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent},
  { path: '', component: HomepageComponent},
  { path: 'perfil', component: PerfilComponent},
  { path: 'misPublicaciones', component: MisPublicacionesComponent},
  { path: 'crear-revista', component: CrearRevistaComponent},
  { path: 'revista/:idRevista', component: RevistaComponent},
  { path: 'cargar-edicion/:idRevista', component: CargarEdicionComponent},
  { path: 'ver-archivo/:idRevista/:idEdicion', component: VerArchivoComponent},
  //{ path: 'recargar-cartera', component: RecargarCarteraComponent}
];

//agregue esto para tratar de hacer la referencia al registro
export const appRoutingProviders: any[] = [
  provideRouter(routes)
];