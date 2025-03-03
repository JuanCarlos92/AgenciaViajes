import { HotelDTO } from "../HotelDTO.model";

export interface PostHotelResponse {
    hotelDTO: HotelDTO;
    codError: string;
    isOk: boolean;
}