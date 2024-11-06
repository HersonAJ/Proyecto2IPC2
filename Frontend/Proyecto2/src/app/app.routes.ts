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
import { RecargarCarteraComponent } from './recargar-cartera-editor/recargar-cartera-editor.component';
import { CarteraGlobalComponent } from './cartera-global/cartera-global.component';

import { EliminarRevistaComponent } from './eliminar-revista/eliminar-revista.component';
import { RevistasSuscriptorComponent } from './revistas-suscriptor/revistas-suscriptor.component';
//siguente a git 
import { MisSuscripcionesComponent } from './mis-suscripciones/mis-suscripciones.component';
import { RevistaInfoComponent } from './revista-info/revista-info.component';
//nuevos
import { AsignarCostoComponent } from './asignar-costo/asignar-costo.component';
import { ReportesComponent } from './reportes/reportes.component';
import { ReportePopularComponent } from './reporte-popular-admin/reporte-popular-admin.component';
import { PrecioAnunciosComponent } from './precio-anuncios/precio-anuncios.component';



export const routes: Routes = [


  //todos los usuarios
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent},
  { path: '', component: HomepageComponent},
  { path: 'perfil', component: PerfilComponent},
  { path: 'ver-archivo/:idRevista/:idEdicion', component: VerArchivoComponent},

  //editor
  { path: 'misPublicaciones', component: MisPublicacionesComponent},
  { path: 'crear-revista', component: CrearRevistaComponent},
  { path: 'revista/:idRevista', component: RevistaComponent}, 
  { path: 'cargar-edicion/:idRevista', component: CargarEdicionComponent},
  { path: 'eliminar-revista', component: EliminarRevistaComponent},
  
// editor, comprador de anuncios
  { path: 'recargar-cartera', component: RecargarCarteraComponent},

  //admin
  { path: 'recargar-cartera-global', component: CarteraGlobalComponent},
  { path: 'asignar-costo', component: AsignarCostoComponent },
  { path: 'reportes', component: ReportesComponent },
  { path: 'reporte-popular', component: ReportePopularComponent },
  { path: 'precio-anuncios', component: PrecioAnunciosComponent },

//suscriptor
  { path: 'revistas', component: RevistasSuscriptorComponent},
  { path: 'mis-suscripciones', component: MisSuscripcionesComponent},
  { path: 'revista-info/:idRevista', component: RevistaInfoComponent },


];

//agregue esto para tratar de hacer la referencia al registro
export const appRoutingProviders: any[] = [
  provideRouter(routes)
];