package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

public interface CanteenShiftRepository extends DataRepository<CanteenShift, String> {

    boolean verifyByDate(String canteenShiftDate);
    
    /**
     * For the current day
     * @return
     */
    CanteenShift close();

    public Optional<CanteenShift> findCurrentDayShift();
}
