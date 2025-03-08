import { Component } from '@angular/core';
import { AgenciaViajeService } from '../../services/agenciaViaje.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reserva',
  imports: [CommonModule, FormsModule],
  templateUrl: './reserva.component.html',
  styleUrl: './reserva.component.css'
})
export class ReservaComponent {

  isAdmin = false;

  msg = '';

  todasReservas:any;

  hotelSeleccionado:any;

  listaHoteles = ['HOTEL 1', 'HOTEL 2', 'HOTEL 3'];

  constructor(private agenciaViajeService: AgenciaViajeService) {
    // IMPORTANTE! PARA VER SI UN USUARIO ES ADMIN O NO
    this.isAdmin = localStorage.getItem('role') === 'ADMIN';
    //
    this.agenciaViajeService.getReservas().subscribe(res => {
      this.todasReservas = res['reservasDTO'];
      console.log(this.todasReservas);
    });
  }

  reservar() {
    this.agenciaViajeService.reserva(1,2,"12345678A","Juan Carlos",1,1).subscribe(res => {
      this.msg = 'Reserva completada con existo'
    }, error => {
       this.msg = 'Esto ha ido como la mierda'
    });
  }

  delete() {
    console.log(this.hotelSeleccionado);
  }
}
