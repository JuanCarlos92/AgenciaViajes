package org.juancarlos.reservas.service;

import org.juancarlos.reservas.model.dto.ReservaDTO;
import org.juancarlos.reservas.model.request.ReservaRequest;

import java.util.List;

public interface ReservaService {
ReservaDTO reservar(ReservaRequest reservaRequest) throws Exception;
ReservaDTO getReservaId(Long id);
}
