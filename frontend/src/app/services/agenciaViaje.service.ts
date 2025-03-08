import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RequestService, RequestServiceOptions } from './request.service';


@Injectable({
    providedIn: 'root'
})
export class AgenciaViajeService {
    constructor(private requestService: RequestService) { }


    public login(user:any, pwd:any): Observable<any> {
        let url = `http://localhost:4200/auth/login`;
        let body = {
            "username": user,
            "password": pwd
          }

        const requestServiceOptions: RequestServiceOptions = {
            url: url,
            method: 'POST',
            body: JSON.stringify(body),
            responseType: 'json',
        };
        return this.requestService.request(requestServiceOptions, false, true);
    }

    public reserva(idHotel: any, idVuelo:any, dni:any, nombre:any, plazaVuelo: any, plazaHotel:any): Observable<any> {
        let url = `http://localhost:4200/reservas`;
        let body = {
            "idHotel": idHotel,
            "idVuelo": idVuelo,
            "dni": dni,
            "nombre": nombre,
            "plazaVuelo": plazaVuelo,
            "plazaHotel": plazaHotel
        }

        const requestServiceOptions: RequestServiceOptions = {
            url: url,
            method: 'POST',
            body: JSON.stringify(body),
            responseType: 'json',
        };
        return this.requestService.request(requestServiceOptions, false, true);
    }

    public getReservas(): Observable<any> {
        let url = `http://localhost:4200/reservas`;

        const requestServiceOptions: RequestServiceOptions = {
            url: url,
            method: 'GET',
            responseType: 'json',
        };
        return this.requestService.request(requestServiceOptions, false, true);
    }

    // public getArmor(id: number): Observable<GetArmorResponse> {
    //     let url = `${environment.armor}/${id}`;
    //     const requestServiceOptions: RequestServiceOptions = {
    //         url: url,
    //         method: 'GET',
    //         responseType: 'json',
    //     };
    //     return this.requestService.request(requestServiceOptions, false, true);
    // }

    // public getArmorSetList(): Observable<GetArmorSetListResponse> {
    //     let url = `${environment.armorSet}`;

    //     const requestServiceOptions: RequestServiceOptions = {
    //         url: url,
    //         method: 'GET',
    //         responseType: 'json',
    //     };
    //     return this.requestService.request(requestServiceOptions, false, true);
    // }

    // public getArmorSet(id: number): Observable<GetArmorSetResponse> {
    //     let url = `${environment.armorSet}/${id}`;
    //     const requestServiceOptions: RequestServiceOptions = {
    //         url: url,
    //         method: 'GET',
    //         responseType: 'json',
    //     };
    //     return this.requestService.request(requestServiceOptions, false, true);
    // }

}


