import { HotelDTO } from "../HotelDTO.model";

export interface GetHotelResponse {
    hotelDTO: HotelDTO;
    codError: string;
    isOk: boolean;
}