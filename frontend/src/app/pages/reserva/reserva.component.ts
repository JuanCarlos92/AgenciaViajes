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
  msgDelete = '';
  msgPost = '';
  listaReservas: any;
  hotelSeleccionado: any;
  vueloSeleccionado: any;
  reservaSeleccionada: any;
  hotelesDisponible: any;
  listaHoteles: any;
  VuelosDisponible: any;
  listaVuelos: any;
  reservaRealizada: any;
  mostrarListaReservas = false;
  mostrarListaHoteles = false;
  mostrarListaVuelos = false;
  mostrarOpcionesEliminar = false;
  mostrarFormularioHotel = false;
  mostrarFormularioVuelo = false;

  // Variables para el formulario reserva
  hotelSeleccionadoDisponible: any;
  vueloSeleccionadoDisponible: any;
  nombre: any;
  dni: any;
  plazaHotel: any;
  plazaVuelo: any;

  // Variables para el formulario de creación de hotel
  hotelNombre: string = '';
  hotelCategoria: string = '';
  hotelPrecio: number = 0;
  hotelDisponible: number = 0;

  // Variables para el formulario de creación de vuelo
  vuelocompany: string = '';
  vueloFecha: string = '';
  vueloPrecio: number = 0;
  vueloPlazas: number = 0;


  constructor(private agenciaViajeService: AgenciaViajeService) {
    // IMPORTANTE! PARA VER SI UN USUARIO ES ADMIN O NO
    this.isAdmin = localStorage.getItem('role') === 'ADMIN';

    this.cargarReservas();
    this.cargarHoteles();
    this.cargarVuelos();

  }

  cargarReservas() {
    this.agenciaViajeService.getReservas().subscribe(res => {
      this.listaReservas = res['reservasDTO'];
      console.log(this.listaReservas);
    });
  }

  cargarHoteles() {
    this.agenciaViajeService.getHoteles().subscribe(res => {
      this.hotelesDisponible = res['hotelsDTO'].filter((hotel: any) => hotel.disponible > 0);
      this.listaHoteles = res['hotelsDTO'];
    });
  }

  cargarVuelos() {
    this.agenciaViajeService.getVuelos().subscribe(res => {
      this.VuelosDisponible = res['vuelosDTO'].filter((vuelo: any) => vuelo.plazas > 0);
      this.listaHoteles = res['vuelosDTO'];
    });
  }

  reservar() {
    if (!this.hotelSeleccionadoDisponible || !this.vueloSeleccionadoDisponible || !this.dni || !this.nombre || !this.plazaVuelo || !this.plazaHotel) {
      this.msg = 'Por favor, rellena todos los campos del formulario de reserva';
      return;
    }
    this.agenciaViajeService.reserva(this.hotelSeleccionadoDisponible, this.vueloSeleccionadoDisponible, this.dni, this.nombre, this.plazaVuelo, this.plazaHotel).subscribe(res => {
      this.msg = `Reserva realizada: Nombre: ${this.nombre}, DNI: ${this.dni}, Hotel: ${this.hotelSeleccionadoDisponible}, Vuelo: ${this.vueloSeleccionadoDisponible}`;
      this.cargarReservas();
    }, error => {
      this.msg = 'Esto ha ido como la mierda';
    });
  }

  obtenerReservaRealizada(id: number) {
    this.agenciaViajeService.getReservasId(id).subscribe(res => {
      this.reservaRealizada = res['reservaDTO'];
      console.log(this.reservaRealizada);
    });
  }

  deleteReserva() {
    if (this.reservaSeleccionada) {
      this.agenciaViajeService.deleteReserva(this.reservaSeleccionada).subscribe(res => {
        this.msgDelete = 'La reserva seleccionada ha sido eliminada';
        this.listaReservas = this.listaReservas.filter((reserva: any) => reserva.id !== this.reservaSeleccionada);
        this.cargarReservas();
        this.reservaSeleccionada = null;
      }, error => {
        this.msgDelete = 'Error al eliminar la reserva';
      });
    } else {
      this.msgDelete = 'Por favor, selecciona una reserva para eliminar';
    }
  }

  deleteHotel() {
    if (this.hotelSeleccionado) {
      this.agenciaViajeService.deleteReserva(this.hotelSeleccionado).subscribe(res => {
        this.msgDelete = 'La reserva seleccionada ha sido eliminada';
        this.listaHoteles = this.listaHoteles.filter((hotel: any) => hotel.id !== this.hotelSeleccionado);
        this.cargarHoteles();
        this.hotelSeleccionado = null;
      }, error => {
        this.msgDelete = 'Error al eliminar el hotel';
      });
    } else {
      this.msgDelete = 'Por favor, selecciona un hotel para eliminar';
    }
  }

  deleteVuelo() {
    if (this.vueloSeleccionado) {
      this.agenciaViajeService.deleteReserva(this.vueloSeleccionado).subscribe(res => {
        this.msgDelete = 'La reserva seleccionada ha sido eliminada';
        this.listaVuelos = this.listaVuelos.filter((hotel: any) => hotel.id !== this.vueloSeleccionado);
        this.cargarVuelos();
        this.vueloSeleccionado = null;
      }, error => {
        this.msgDelete = 'Error al eliminar el vuelo';
      });
    } else {
      this.msgDelete = 'Por favor, selecciona un vuelo para eliminar';
    }
  }

  postHotel() {
    if (!this.hotelNombre || !this.hotelCategoria || !this.hotelPrecio || !this.hotelDisponible) {
      this.msgPost = 'Por favor, rellena todos los campos del formulario de hotel';
      return;
    }
    this.agenciaViajeService.postHotel(this.hotelNombre, this.hotelCategoria, this.hotelPrecio, this.hotelDisponible).subscribe(res => {
      this.msgPost = 'Hotel añadido con éxito';
      this.cargarHoteles();
      this.hotelNombre = '';
      this.hotelCategoria = '';
      this.hotelPrecio = 0;
      this.hotelDisponible = 0;
    }, error => {
      this.msgPost = 'Esto ha ido como la mierda';
    });
  }

  postVuelo() {
    if (!this.vuelocompany || !this.vueloFecha || !this.vueloPrecio || !this.vueloPlazas) {
      this.msgPost = 'Por favor, rellena todos los campos del formulario de vuelo';
      return;
    }
    this.agenciaViajeService.postVuelo(this.vuelocompany, this.vueloFecha, this.vueloPrecio, this.vueloPlazas).subscribe(res => {
      this.msgPost = 'Vuelo añadido con éxito';
      this.cargarVuelos();
      this.vuelocompany = '';
      this.vueloFecha = '';
      this.vueloPrecio = 0;
      this.vueloPlazas = 0;
    }, error => {
      this.msgPost = 'Esto ha ido como la mierda';
    });
  }


  toggleListaReservas() {
    this.mostrarListaReservas = !this.mostrarListaReservas;
    if (this.mostrarListaReservas) {
      this.mostrarListaHoteles = false;
      this.mostrarListaVuelos = false;
    }
  }

  toggleListaHoteles() {
    this.mostrarListaHoteles = !this.mostrarListaHoteles;
    if (this.mostrarListaHoteles) {
      this.mostrarListaReservas = false;
      this.mostrarListaVuelos = false;
    }
  }

  toggleListaVuelos() {
    this.mostrarListaVuelos = !this.mostrarListaVuelos;
    if (this.mostrarListaVuelos) {
      this.mostrarListaReservas = false;
      this.mostrarListaHoteles = false;
    }
  }

  toggleOpcionesEliminar() {
    this.mostrarOpcionesEliminar = !this.mostrarOpcionesEliminar;
  }

  toggleFormularioHotel() {
    this.mostrarFormularioHotel = !this.mostrarFormularioHotel;
  }

  toggleFormularioVuelo() {
    this.mostrarFormularioVuelo = !this.mostrarFormularioVuelo;
  }
}