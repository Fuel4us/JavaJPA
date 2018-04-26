package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.persistence.CanteenShiftRepository;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Calendar;

public class CanteenShiftClosureController {
    
    private final POSRepository posRepository = PersistenceContext.repositories().POS();
    private final CanteenShiftRepository csRepository = PersistenceContext.repositories().canteenShift();
    
    public boolean canteenShiftClosure(){
        posRepository.findOpenToClose();
        
        return csRepository.close(Calendar.getInstance());
    }
    
}
