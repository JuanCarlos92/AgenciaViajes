import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RequestService, RequestServiceOptions } from './request.service';
import { GetHotelListResponse } from '../models/response/GetHotelListResponse.model';
import { GetHotelResponse } from '../models/response/GetHotelResponse.model';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ArmorService {
    constructor(private requestService: RequestService) { }

    public getHotelList(): Observable<GetHotelListResponse> {
        let url = `${environment.hotel}`;

        const requestServiceOptions: RequestServiceOptions = {
            url: url,
            method: 'GET',
            responseType: 'json',
        };
        return this.requestService.request(requestServiceOptions, false, true);
    }

    public getHotel(id: number): Observable<GetHotelResponse> {
        let url = `${environment.hotel}/${id}`;
        const requestServiceOptions: RequestServiceOptions = {
            url: url,
            method: 'GET',
            responseType: 'json',
        };
        return this.requestService.request(requestServiceOptions, false, true);
    }

}