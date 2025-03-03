import { HotelDTO } from "../HotelDTO.model";

export interface PutHotelResponse {
    hotelDTO: HotelDTO;
    codError: string;
    isOk: boolean;
}