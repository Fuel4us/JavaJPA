package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;
import java.util.Optional;

public interface CanteenShiftRepository extends DataRepository<CanteenShift, String> {

    boolean verifyByDate(String CSdate);
    
    /**
     *
     * @param cal - it will compare the date in YYYYMMDD
     * @return true if found it and if is open, to close it
     */
    boolean close(Calendar cal);

    public boolean openNewShift(Calendar cal);

    public Optional<CanteenShift> findCurrentDayShift();
}
