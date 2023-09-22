package co.edu.uniandes.dse.parcial1.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Data

public class HabitacionEntity extends BaseEntity{

    enum TIPO_HABITACION {
		PRESIDENCIAL, SUITE, DOBLE, ESTANDAR
	}

    private String nombre;
    private String descripcion;
    private String imagen;
    private TIPO_HABITACION tipo;
    private Integer capacidad;

    @PodamExclude
    @ManyToOne
    private HotelEntity hotel;

    
}
