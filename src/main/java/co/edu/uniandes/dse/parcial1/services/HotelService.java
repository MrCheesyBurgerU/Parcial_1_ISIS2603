package co.edu.uniandes.dse.parcial1.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.HotelEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.HotelRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HotelService {

    @Autowired
	HotelRepository hotelRepository;

    @Transactional
    public HotelEntity createHotel(HotelEntity hotel) throws IllegalOperationException{
        log.info("Inicia el proceso de creación de un hotel");

        //Verificando capacidad

        if(hotel.getCapacidad()<=200||hotel.getCapacidad()>=1000){
            throw new IllegalOperationException("La capacidad maxima ingresada no es valida");
        }

        //Verificando horas in out
        if(hotel.getHoraCheckOut()>hotel.getHoraCheckIn()){
            throw new IllegalOperationException("Las horas de CheckIn y CheckOut no son validas");
        }
        
        if((hotel.getHoraCheckIn()-hotel.getHoraCheckOut())<2){
            throw new IllegalOperationException("Las horas de CheckIn y CheckOut no son validas");
        }

        log.info("Finaliza el proceso de creación de un hotel");
        return hotelRepository.save(hotel);
    }
    

}
