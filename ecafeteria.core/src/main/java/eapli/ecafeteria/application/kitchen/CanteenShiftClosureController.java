package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.persistence.CanteenShiftRepository;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.Calendar;

public class CanteenShiftClosureController implements Controller {

    private final POSRepository posRepository = PersistenceContext.repositories().POS();
    private final CanteenShiftRepository csRepository = PersistenceContext.repositories().canteenShift();

    /**
     * It does not return false if there are no open POS 
     * @return true if sucess
     */
    public boolean canteenShiftClosure() {
        posRepository.findOpenToClose();

        return csRepository.close(Calendar.getInstance());
    }

}
