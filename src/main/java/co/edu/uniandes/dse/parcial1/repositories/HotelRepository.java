package co.edu.uniandes.dse.parcial1.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniandes.dse.parcial1.entities.HotelEntity;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long>{
    
}
