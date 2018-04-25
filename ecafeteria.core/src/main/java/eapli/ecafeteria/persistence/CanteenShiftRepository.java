package eapli.ecafeteria.persistence;

import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;

public interface CanteenShiftRepository extends DataRepository<Calendar, Designation>{
    
    boolean close(Calendar cal);
}
