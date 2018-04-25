package eapli.ecafeteria.application.kitchen;

import eapli.ecafetaria.domain.finance.POS;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.List;

public class CanteenShiftClosureController {
    
//    private final CanteenShiftRepository posRepository = PersistenceContext.repositories().POS();
//    private final CanteenShiftRepository csRepository = PersistenceContext.repositories().canteenShift();
//    
//    //boolean ou String
//    public boolean canteenShiftClosure(Calendar cal){
//        List<POS> posList = posRepository.findOpen();
//        
//        if(posList == null)
//            return false;
//        
//        if(!posList.isEmpty() && !posRepository.close(posList))
//            return false;
//        
//        if (!csRepository.close(cal))
//            return false;
//        
//        return true;
//    }
    
}
