package co.edu.uniandes.dse.parcial1.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Data

public class HotelEntity extends BaseEntity {

    private String nombre;
    private String imagen;
    private String descripcion;
    private String direccion;
    private String pais;
    private String ciudad;
    private Integer horaCheckIn;
    private Integer horaCheckOut;
    private Integer capacidad;
    private Integer cantidadEstrellas;

    @PodamExclude
    @OneToMany(mappedBy="hotel", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<HabitacionEntity> habitaciones;
}
