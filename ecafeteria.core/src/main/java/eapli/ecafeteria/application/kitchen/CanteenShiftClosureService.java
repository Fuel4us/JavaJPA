package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

public class CanteenShiftClosureService {

    final ExecutionRepository executionRepository= PersistenceContext.repositories().execution();
    final BookingRepository bookingRepository = PersistenceContext.repositories().booking();

    public CanteenShiftClosureService(){

    }


}
