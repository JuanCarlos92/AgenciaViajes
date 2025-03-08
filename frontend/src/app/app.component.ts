import { Component, importProvidersFrom, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HotelesComponent } from "./pages/hoteles/hoteles.component";
import { ReservaComponent } from "./pages/reserva/reserva.component";
import { VuelosComponent } from "./pages/vuelos/vuelos.component";
import { LoginComponent } from "./pages/login/login.component";
import { CommonModule } from '@angular/common';
import { AgenciaViajeService } from './services/agenciaViaje.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HotelesComponent, ReservaComponent, VuelosComponent, LoginComponent,CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
  // providers: [importProvidersFrom(HttpClientModule)]
})
export class AppComponent implements OnInit {

  isHotel = false;
  isVuelos = false;
  isReserva = false;
  isLogin = true;
  title = 'frontend';

  constructor(private agenciaViajeService: AgenciaViajeService) { }

  ngOnInit(): void {
  }

  irAReserva() {
    this.isHotel = false;
    this.isVuelos = false;
    this.isReserva = true;
    this.isLogin = false;
  }


}
