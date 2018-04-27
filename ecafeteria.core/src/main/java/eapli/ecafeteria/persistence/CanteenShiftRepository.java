package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;

public interface CanteenShiftRepository extends DataRepository<CanteenShift, Calendar>{
    
    /**
     *
     * @param cal - it will compare the date in DDMMYYYY
     * @return true if found it and if is open, to close it
     */
    boolean close(Calendar cal);
}
