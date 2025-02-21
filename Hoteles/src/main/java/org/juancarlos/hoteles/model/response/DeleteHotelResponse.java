package org.juancarlos.hoteles.model.response;

import lombok.*;
import org.juancarlos.hoteles.model.dto.HotelDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteHotelResponse extends Response {
    private HotelDTO hotelDTO;
}
