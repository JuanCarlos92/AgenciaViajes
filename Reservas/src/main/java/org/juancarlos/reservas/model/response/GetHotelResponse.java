package org.juancarlos.reservas.model.response;

import lombok.*;
import org.juancarlos.reservas.model.dto.HotelDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetHotelResponse extends Response {
    private HotelDTO hotelDTO;

}
