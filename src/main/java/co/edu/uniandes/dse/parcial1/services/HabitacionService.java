package co.edu.uniandes.dse.parcial1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcial1.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.HabitacionRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HabitacionService {
    
    @Autowired
    HabitacionRepository habitacionRepository;

    @Transactional
    public HabitacionEntity createHabitacion(HabitacionEntity habitacion) throws IllegalOperationException{
        log.info("Inicia el proceso de creacion de una habitacion");

        //Validando la capacidad
        if(habitacion.getCapacidad()<=1||habitacion.getCapacidad()>=6){
            throw new IllegalOperationException("La capacidad ingresada no es valida");
        }

        log.info("Finaliza el proceso de creacion de una habitacion");
        return habitacionRepository.save(habitacion);
    }
}
