package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcial1.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcial1.entities.HotelEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import({ HotelHabitacionService.class, HabitacionService.class })

public class HotelHabitacionServiceTest {

    @Autowired
    private HotelHabitacionService hotelHabitacionService;

    @Autowired
    private HabitacionService habitacionService;

    @Autowired
	private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();

	private HotelEntity hotel = factory.manufacturePojo(HotelEntity.class);

        @BeforeEach
    void setUp(){
        clearData();
    }

    private void clearData(){
        entityManager.getEntityManager().createQuery("delete from HabitacionEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from HotelEntity").executeUpdate();
    }

    @Test
    void addHabitacion() throws IllegalOperationException{

        HotelEntity hotel = factory.manufacturePojo(HotelEntity.class);
    
        HabitacionEntity newEntity = factory.manufacturePojo(HabitacionEntity.class);
        newEntity.setCapacidad(3);
        habitacionService.createHabitacion(newEntity);

        HabitacionEntity entity = hotelHabitacionService.addHabitacion(hotel.getId(), newEntity.getId());
        assertNotNull(entity);

        assertEquals(newEntity.getId(), entity.getId());
        assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
        assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        assertEquals(newEntity.getImagen(), entity.getImagen());
        assertEquals(newEntity.getNombre(), entity.getNombre());
        assertEquals(newEntity.getTipo(), entity.getTipo());
    }
    
}
