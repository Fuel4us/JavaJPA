package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;
import java.util.Optional;

public interface CanteenShiftRepository extends DataRepository<CanteenShift, String> {

    boolean verifyByDate(String canteenShiftDate);
    
    /**
     *
     * @param cal - it will compare the date in YYYYMMDD
     * @return true if found it and if is open, to close it
     */
    CanteenShift close(Calendar cal);

    public Optional<CanteenShift> findCurrentDayShift();
}
