package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.RegisterCanteenShiftController;
import eapli.ecafeteria.domain.finance.WorkSession;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.WorkSessionRepository;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CanteenShiftBootstrapper implements Action {
    
    @Override
    public boolean execute() {
        try {
            register();
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(AllergenBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public void register() throws DataIntegrityViolationException, DataConcurrencyException {
        final RegisterCanteenShiftController csController = new RegisterCanteenShiftController();
        final WorkSessionRepository wsRepository = PersistenceContext.repositories().workSession();
        
        Iterable<WorkSession> Iws = wsRepository.findAll();
        WorkSession ws1 = Iws.iterator().next();
        
        //uma string em formato YYYYMMDD, para o dia de hoje
        Calendar cal = Calendar.getInstance();
        String canteenShiftDate;
        
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        if(month <= 9)
            canteenShiftDate = Integer.toString(year) + "0" + Integer.toString(month) + Integer.toString(day);
        else
            canteenShiftDate = Integer.toString(year) + Integer.toString(month) + Integer.toString(day);
        
        //para um dia que já passou
        csController.registerCanteenShift("20180101", ws1);
        
        //para o dia atual
        csController.registerCanteenShift(canteenShiftDate, ws1);
    }
}
