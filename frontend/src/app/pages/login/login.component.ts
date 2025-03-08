import { Component, EventEmitter, Output } from '@angular/core';
import { AgenciaViajeService } from '../../services/agenciaViaje.service';
import { FormsModule } from '@angular/forms';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  @Output() reservaEvent = new EventEmitter<boolean>();

  user: any;
  pwd: any;
  msg: any;

  constructor(private agenciaViajeService: AgenciaViajeService) { }

  reserva(){
    this.reservaEvent.emit(true);
  }

  login() {
    this.agenciaViajeService.login(this.user, this.pwd).subscribe(res => {
      localStorage.setItem('token', res['token']);
      const token: any = jwtDecode(res['token']);
      localStorage.setItem('role', token['role']);
      this.reservaEvent.emit(true)
    }, error => {
      this.msg = 'Error en el servicio'
    });
  }
}
