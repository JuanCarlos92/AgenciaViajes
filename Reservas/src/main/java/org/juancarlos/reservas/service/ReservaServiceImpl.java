package org.juancarlos.reservas.service;

import org.juancarlos.reservas.converter.ReservaConverter;
import org.juancarlos.reservas.entity.ReservaEntity;
import org.juancarlos.reservas.model.dto.ReservaDTO;
import org.juancarlos.reservas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<ReservaDTO> getReservasList() {
        List<ReservaEntity> reservaEntities = reservaRepository.findAll();
        return ReservaConverter.reservaEntityToDTO(reservaEntities);
    }

    @Override
    public ReservaDTO getReservaId(Long id) {
        ReservaEntity reservaEntity = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva con id: " + id + " no encontrada"));
        return ReservaConverter.reservaEntityToDTO(reservaEntity);
    }
}

