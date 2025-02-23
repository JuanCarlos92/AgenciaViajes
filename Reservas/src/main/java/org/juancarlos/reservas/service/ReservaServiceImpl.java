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

import java.util.List;


@Service
public class ReservaServiceImpl implements ReservaService {

    @Value("${urlHoteles}")
    private String urlHoteles;
    @Value("${urlVuelos}")
    private String urlVuelos;

    @Autowired
    private WebClient webClient;
    @Autowired
    private ReservaRepository reservaRepository;

    //Metodo realizar la reserva del hotel y vuelo
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

        webClient.post()
                .uri(urlVuelos+"/"+reservaRequest.getIdVuelo()+"/"+ reservaRequest.getPlazaHotel())
                //.header("Authorization", "Basic " + getBase64(user,pass))
                .retrieve()
                .bodyToMono(GetVueloResponse.class)
                .block();

        webClient.post()
                .uri(urlHoteles+"/"+reservaRequest.getIdHotel()+"/"+ reservaRequest.getPlazaVuelo())
                //.header("Authorization", "Basic " + getBase64(user,pass))
                .retrieve()
                .bodyToMono(GetHotelResponse.class)
                .block();

        return ReservaConverter.reservaEntityToDTO(entity);

    }

    //Metodo Obtener Lista reservas
    @Override
    public List<ReservaDTO> getReservasList() {
        List<ReservaEntity> reservaEntity = reservaRepository.findAll();

        return ReservaConverter.reservaEntityToDTO(reservaEntity);
    }

    //Metodo Obtener ID reserva
    @Override
    public ReservaDTO getReservaId(Long id) {
        ReservaEntity reservaEntity = reservaRepository.findById(id).orElseThrow(()-> new RuntimeException("Reserva no encontrada"));

        return ReservaConverter.reservaEntityToDTO(reservaEntity);
    }

    //Metodo Obtener nombre reserva
    @Override
    public ReservaDTO getReservaNombre(String nombre) {
        ReservaEntity reservaEntity = reservaRepository.findReservaByNombre(nombre);

        return ReservaConverter.reservaEntityToDTO(reservaEntity);
    }

    //Metodo obtener dni reserva
    @Override
    public ReservaDTO getReservaDNI(String dni) {
        ReservaEntity reservaEntity = reservaRepository.findReservaByDni(dni);

        return ReservaConverter.reservaEntityToDTO(reservaEntity);
    }

    //Metodo Obtener nombre y dni reserva
    @Override
    public ReservaDTO getReservaNombreYDni(String nombre, String dni) {
        ReservaEntity reservaEntity = reservaRepository.findReservaByNombreAndDni(nombre,dni);

        return ReservaConverter.reservaEntityToDTO(reservaEntity);
    }

    //Metodo eliminar reserva por ID
    @Override
    public void deleteReserva(Long id) {
        ReservaEntity reservaEntity = reservaRepository.findById(id).orElseThrow(()-> new RuntimeException("Reserva no encontrada"));

        reservaRepository.delete(reservaEntity);

    }

}

