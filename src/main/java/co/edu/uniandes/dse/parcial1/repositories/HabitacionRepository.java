package co.edu.uniandes.dse.parcial1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniandes.dse.parcial1.entities.HabitacionEntity;

public interface HabitacionRepository extends JpaRepository<HabitacionEntity,Long> {
    
}
