import { Component, importProvidersFrom, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ReservaComponent } from "./pages/reserva/reserva.component";
import { LoginComponent } from "./pages/login/login.component";
import { CommonModule } from '@angular/common';
import { AgenciaViajeService } from './services/agenciaViaje.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ReservaComponent, LoginComponent,CommonModule],
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
