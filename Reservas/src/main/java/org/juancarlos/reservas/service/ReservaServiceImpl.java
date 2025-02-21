package org.juancarlos.reservas.service;

import org.juancarlos.reservas.converter.ReservaConverter;
import org.juancarlos.reservas.entity.ReservaEntity;
import org.juancarlos.reservas.model.dto.ReservaDTO;
import org.juancarlos.reservas.model.request.ReservaRequest;
import org.juancarlos.reservas.model.response.GetHotelResponse;
import org.juancarlos.reservas.model.response.GetVueloResponse;
import org.juancarlos.reservas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class ReservaServiceImpl implements ReservaService {

    @Value("urlHoteles")
    String urlHoteles;
    @Value("urlVuelos")
    String urlVuelos;

    @Autowired
    private WebClient webClient;
    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public ReservaDTO reservar(ReservaRequest reservaRequest) throws Exception {

       GetHotelResponse getHotelResponse = webClient.get()
                .uri(urlHoteles+"/"+reservaRequest.getIdHotel())
                //.header("Authorization", "Basic " + getBase64(user,pass))
                .retrieve()
                .bodyToMono(GetHotelResponse.class)
                .block();

        GetVueloResponse getVueloResponse = webClient.get()
                .uri(urlVuelos+"/"+reservaRequest.getIdVuelo())
                //.header("Authorization", "Basic " + getBase64(user,pass))
                .retrieve()
                .bodyToMono(GetVueloResponse.class)
                .block();

        if (getHotelResponse.getHotelDTO().getDisponible() == 0 || getVueloResponse.getVueloDTO().getPlazas() == 0){
            throw new Exception("No se ha podido realizar la reserva");
        }


        ReservaEntity entity = new ReservaEntity();
        entity.setIdHotel(reservaRequest.getIdHotel());
        entity.setIdVuelo(reservaRequest.getIdVuelo());
        entity.setDni(reservaRequest.getDni());
        entity.setNombre(reservaRequest.getNombre());

        reservaRepository.save(entity);

        webClient.put()
                .uri(urlVuelos+"/"+reservaRequest.getIdVuelo()+"/reservar/"+(getVueloResponse.getVueloDTO().getPlazas() - 1))
                //.header("Authorization", "Basic " + getBase64(user,pass))
                .retrieve()
                .bodyToMono(GetVueloResponse.class)
                .block();

        webClient.put()
                .uri(urlHoteles+"/"+reservaRequest.getIdHotel()+"/disponibilidad/"+(getHotelResponse.getHotelDTO().getDisponible() - 1))
                //.header("Authorization", "Basic " + getBase64(user,pass))
                .retrieve()
                .bodyToMono(GetHotelResponse.class)
                .block();

        return ReservaConverter.reservaEntityToDTO(entity);


    }

    @Override
    public ReservaDTO getReservaId(Long id) {
        ReservaEntity reservaEntity = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva con id: " + id + " no encontrada"));
        return ReservaConverter.reservaEntityToDTO(reservaEntity);
    }
}

