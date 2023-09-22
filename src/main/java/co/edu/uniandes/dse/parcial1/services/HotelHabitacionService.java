package co.edu.uniandes.dse.parcial1.services;



import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcial1.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcial1.entities.HotelEntity;
import co.edu.uniandes.dse.parcial1.repositories.HabitacionRepository;
import co.edu.uniandes.dse.parcial1.repositories.HotelRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HotelHabitacionService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Transactional
    public HabitacionEntity addHabitacion(Long hotelId, Long habitacionId){
        log.info("Inicia el proceso de asociarle una habitacion al hotel con id = {0}", hotelId);
        Optional<HabitacionEntity> habitacionEntity = habitacionRepository.findById(habitacionId);
        if(habitacionEntity.isEmpty()){
            throw new EntityNotFoundException("La habitacion buscada no existe en la persistencia");
        }

        Optional<HotelEntity> hotelEntity = hotelRepository.findById(hotelId);
        if(hotelEntity.isEmpty()){
            throw new EntityNotFoundException("El hotel buscado no existe en la persistencia");
        }

        hotelEntity.get().getHabitaciones().add(habitacionEntity.get());
        log.info("Termina el proceso de asociarle una habitacion al hotel con id = {0}", hotelId);
        return habitacionEntity.get();
    }

    
}
