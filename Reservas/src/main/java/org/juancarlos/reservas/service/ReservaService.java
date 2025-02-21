package org.juancarlos.reservas.service;

import org.juancarlos.reservas.model.dto.ReservaDTO;

import java.util.List;

public interface ReservaService {
List<ReservaDTO> getReservasList();
ReservaDTO getReservaId(Long id);
}
