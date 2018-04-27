package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Calendar;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;

public class InMemoryCanteenShiftRepository extends InMemoryRepository<CanteenShift, Calendar> implements CanteenShiftRepository {

    @Override
    protected Calendar newKeyFor(CanteenShift entity) {
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
    public boolean close(Calendar cal) {
        Optional<CanteenShift> cs = matchOne(e -> e.id().equals(cal));
        return cs.get().close();
    }

    @Override
    public boolean open(Calendar cal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<CanteenShift> findCurrentDayShift() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
