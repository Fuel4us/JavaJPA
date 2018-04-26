package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import java.util.Calendar;

public class JpaCanteenShiftRepository extends CafeteriaJpaRepositoryBase<CanteenShift, Calendar> implements CanteenShiftRepository{

    @Override
    public boolean close(Calendar cal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
