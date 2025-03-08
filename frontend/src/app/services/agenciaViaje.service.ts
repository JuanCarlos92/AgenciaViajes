import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RequestService, RequestServiceOptions } from './request.service';


@Injectable({
    providedIn: 'root'
})
export class AgenciaViajeService {
    constructor(private requestService: RequestService) { }


    public login(user: any, pwd: any): Observable<any> {
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

    public reserva(idHotel: any, idVuelo: any, dni: any, nombre: any, plazaVuelo: any, plazaHotel: any): Observable<any> {
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

    public getReservasId(id: number): Observable<any> {
        let url = `http://localhost:4200/reservas/${id}`;

        const requestServiceOptions: RequestServiceOptions = {
            url: url,
            method: 'GET',
            responseType: 'json',
        };
        return this.requestService.request(requestServiceOptions, false, true);
    }

    public deleteReserva(id: number): Observable<any> {

        let url = `http://localhost:4200/reservas/${id}`;

        const requestServiceOptions: RequestServiceOptions = {
            url: url,
            method: 'DELETE',
            responseType: 'text',
        };
        return this.requestService.request(requestServiceOptions, false, true);
    }


    public getHoteles(): Observable<any> {
        let url = `http://localhost:4200/hoteles`;

        const requestServiceOptions: RequestServiceOptions = {
            url: url,
            method: 'GET',
            responseType: 'json',
        };
        return this.requestService.request(requestServiceOptions, false, true);
    }

    public postHotel(nombre: any, categoria: any, precio: any, disponible: any): Observable<any> {
        let url = `http://localhost:4200/hoteles`;
        let body = {
            "nombre": nombre,
            "categoria": categoria,
            "precio": precio,
            "disponible": disponible
        }

        const requestServiceOptions: RequestServiceOptions = {
            url: url,
            method: 'POST',
            body: JSON.stringify(body),
            responseType: 'json',
        };
        return this.requestService.request(requestServiceOptions, false, true);

    }
    public postVuelo(company: any, fecha: any, precio: any, plazas: any): Observable<any> {
        let url = `http://localhost:4200/vuelos`;
        let body = {
            "company": company,
            "fecha": fecha,
            "precio": precio,
            "plazas": plazas
        }

        const requestServiceOptions: RequestServiceOptions = {
            url: url,
            method: 'POST',
            body: JSON.stringify(body),
            responseType: 'json',
        };
        return this.requestService.request(requestServiceOptions, false, true);

    }

    public deleteHoteles(id: number): Observable<any> {

        let url = `http://localhost:4200/hoteles/${id}`;

        const requestServiceOptions: RequestServiceOptions = {
            url: url,
            method: 'DELETE',
            responseType: 'text',
        };
        return this.requestService.request(requestServiceOptions, false, true);
    }

    public getVuelos(): Observable<any> {
        let url = `http://localhost:4200/vuelos`;

        const requestServiceOptions: RequestServiceOptions = {
            url: url,
            method: 'GET',
            responseType: 'json',
        };
        return this.requestService.request(requestServiceOptions, false, true);
    }

    public deleteVuelos(id: number): Observable<any> {

        let url = `http://localhost:4200/vuelos/${id}`;

        const requestServiceOptions: RequestServiceOptions = {
            url: url,
            method: 'DELETE',
            responseType: 'text',
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


