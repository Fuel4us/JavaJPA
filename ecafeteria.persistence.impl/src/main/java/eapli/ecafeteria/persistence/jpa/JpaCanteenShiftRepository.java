package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import java.util.Calendar;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.persistence.Query;

class JpaCanteenShiftRepository extends CafeteriaJpaRepositoryBase<CanteenShift, String> implements CanteenShiftRepository{

    @Override
    public void forEach(Consumer<? super CanteenShift> cnsmr) {
        super.forEach(cnsmr); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Spliterator<CanteenShift> spliterator() {
        return super.spliterator(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean verifyByDate(String CSdate) {
        Query query = entityManager().createQuery("select e.pk from " + this.entityClass.getSimpleName() + " e where e.CSdate = :CSdate");
        query.setParameter("dateCS", CSdate);

        return (String) query.getSingleResult() == null;
    }
    
    @Override
    public boolean close(Calendar cal) {
        Optional<CanteenShift> cs = this.findByDate(cal);
        if(!cs.isPresent())
            return false;
        return cs.get().close();
    }
    
    private Optional<CanteenShift> findByDate(Calendar cal) {
        String dateCS;
        
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        if(month <= 9)
            dateCS = Integer.toString(year) + "0" + Integer.toString(month) + Integer.toString(day);
        else
            dateCS = Integer.toString(year) + Integer.toString(month) + Integer.toString(day);
        
        return matchOne("e.dateCS=:dateCS", "dateCS", dateCS);
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
