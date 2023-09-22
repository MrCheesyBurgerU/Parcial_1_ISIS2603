package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcial1.entities.HotelEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(HotelService.class)
public class HotelServiceTest {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();
    
    @BeforeEach
    void setUp(){
        clearData();
    }

    private void clearData(){
        entityManager.getEntityManager().createQuery("delete from HabitacionEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from HotelEntity").executeUpdate();
    }

    @Test
    void testCreateHotel() throws IllegalOperationException{
        HotelEntity newEntity = factory.manufacturePojo(HotelEntity.class);

        //Setting valid attributes

        newEntity.setCapacidad(800);
        newEntity.setHoraCheckOut(12);
        newEntity.setHoraCheckIn(18);
        HotelEntity result = hotelService.createHotel(newEntity);
        assertNotNull(result);

        HotelEntity entity = entityManager.find(HotelEntity.class, result.getId());

        assertEquals(newEntity.getId(), entity.getId());
        assertEquals(newEntity.getCiudad(), entity.getCiudad());
        assertEquals(newEntity.getCantidadEstrellas(), entity.getCantidadEstrellas());
        assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
        assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        assertEquals(newEntity.getDireccion(), entity.getDireccion());
        assertEquals(newEntity.getHoraCheckIn(), entity.getHoraCheckIn());
        assertEquals(newEntity.getHoraCheckOut(), entity.getHoraCheckOut());
        assertEquals(newEntity.getImagen(), entity.getImagen());
        assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    @Test
    void testCreateHotelInvalidCapacidad(){

        assertThrows(IllegalOperationException.class, ()->{
            HotelEntity newEntity = factory.manufacturePojo(HotelEntity.class);
            //Setting attributes

            newEntity.setCapacidad(1001);
            newEntity.setHoraCheckOut(12);
            newEntity.setHoraCheckIn(18);
            hotelService.createHotel(newEntity);
        });
        
        
    }

    @Test
    void testCreateHotelInvalidHours(){

        assertThrows(IllegalOperationException.class, ()->{
            HotelEntity newEntity = factory.manufacturePojo(HotelEntity.class);
            //Setting attributes

            newEntity.setCapacidad(800);
            newEntity.setHoraCheckOut(12);
            newEntity.setHoraCheckIn(13);
            hotelService.createHotel(newEntity);
        });
        
        
    }
    
}
