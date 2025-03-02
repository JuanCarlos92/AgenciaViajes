package org.juancarlos.reservas.service;

import org.juancarlos.reservas.model.dto.ReservaDTO;
import org.juancarlos.reservas.model.request.ReservaRequest;

import java.util.List;

public interface ReservaService {
ReservaDTO reservar(ReservaRequest reservaRequest) throws Exception;
List<ReservaDTO> getReservasList();
ReservaDTO getReservaId(Long id);
ReservaDTO getReservaNombre(String nombre);
ReservaDTO getReservaDNI(String dni);
ReservaDTO getReservaNombreYDni(String dni, String nombre);
void deleteReserva(Long id);

}
