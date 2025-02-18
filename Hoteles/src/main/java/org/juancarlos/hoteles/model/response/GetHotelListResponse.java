package org.juancarlos.hoteles.model.response;

import lombok.*;
import org.juancarlos.hoteles.model.dto.HotelDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetHotelListResponse {
    List<HotelDTO> hotelsDTO;
}
