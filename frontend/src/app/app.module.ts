import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import { HotelesComponent } from './pages/hoteles/hoteles.component';
import { LoginComponent } from './pages/login/login.component';
import { ReservaComponent } from './pages/reserva/reserva.component';
import { VuelosComponent } from './pages/vuelos/vuelos.component';
import { BrowserModule } from '@angular/platform-browser';



@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    BrowserModule
  ]
})
export class AppModule { }
