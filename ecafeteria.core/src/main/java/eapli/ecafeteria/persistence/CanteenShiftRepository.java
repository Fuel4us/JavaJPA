package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;

public interface CanteenShiftRepository extends DataRepository<CanteenShift, Calendar>{
    
    boolean close(Calendar cal);
}
