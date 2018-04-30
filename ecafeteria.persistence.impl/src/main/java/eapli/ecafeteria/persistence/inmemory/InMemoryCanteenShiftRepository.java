package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Calendar;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;

public class InMemoryCanteenShiftRepository extends InMemoryRepository<CanteenShift, String> implements CanteenShiftRepository {

    @Override
    protected String newKeyFor(CanteenShift entity) {
        return entity.id();
    }

    @Override
    public void forEach(Consumer<? super CanteenShift> cnsmr) {
        super.forEach(cnsmr); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Spliterator<CanteenShift> spliterator() {
        return super.spliterator(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CanteenShift close(Calendar cal) {
        String canteenShiftDate;
        
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        if(month <= 9)
            canteenShiftDate = Integer.toString(year) + "0" + Integer.toString(month) + Integer.toString(day);
        else
            canteenShiftDate = Integer.toString(year) + Integer.toString(month) + Integer.toString(day);
        
        Optional<CanteenShift> cs = matchOne(e -> e.id().equals(canteenShiftDate));
        if(!cs.get().close())
            return null;
        return cs.get();
    }

    @Override
    public boolean openNewShift(Calendar cal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<CanteenShift> findCurrentDayShift() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verifyByDate(String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
