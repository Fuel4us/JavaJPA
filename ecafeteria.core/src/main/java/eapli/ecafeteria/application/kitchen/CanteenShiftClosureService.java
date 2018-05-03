package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

public class CanteenShiftClosureService {

    final ExecutionRepository repository= PersistenceContext.repositories().execution();

    public CanteenShiftClosureService(){

    }


}
