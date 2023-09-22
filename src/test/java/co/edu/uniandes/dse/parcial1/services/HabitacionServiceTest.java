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

import co.edu.uniandes.dse.parcial1.entities.HabitacionEntity;

import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(HabitacionService.class)

public class HabitacionServiceTest {

    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();
    
    @BeforeEach
    void setUp(){
        clearData();
    }

    private void clearData(){
        entityManager.getEntityManager().createQuery("delete from HotelEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from HabitacionEntity").executeUpdate();
    }
    
    @Test
    void testCreateHabitacion() throws IllegalOperationException{
        HabitacionEntity newEntity = factory.manufacturePojo(HabitacionEntity.class);

        //Setting valid attributes

        newEntity.setCapacidad(3);
        HabitacionEntity result = habitacionService.createHabitacion(newEntity);
        assertNotNull(result);

        HabitacionEntity entity = entityManager.find(HabitacionEntity.class, result.getId());

        assertEquals(newEntity.getId(), entity.getId());
        assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
        assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        assertEquals(newEntity.getImagen(), entity.getImagen());
        assertEquals(newEntity.getNombre(), entity.getNombre());
        assertEquals(newEntity.getTipo(), entity.getTipo());
        
    }

    @Test
    void testCreateHabitacionInvalidCapacidad(){

        assertThrows(IllegalOperationException.class, ()->{
            HabitacionEntity newEntity = factory.manufacturePojo(HabitacionEntity.class);
            //Setting attributes

            newEntity.setCapacidad(7);
            habitacionService.createHabitacion(newEntity);
        
    });
}
}
