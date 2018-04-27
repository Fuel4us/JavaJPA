package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class JpaCanteenShiftRepository extends CafeteriaJpaRepositoryBase<CanteenShift, Calendar> implements CanteenShiftRepository{

    @Override
    public boolean close(Calendar cal) {
        Optional<CanteenShift> cs = this.findByDate(cal);
        return cs.get().close();
    }
    
    private Optional<CanteenShift> findByDate(Calendar cal) {
        boolean verify;
        
        final Map<String, Object> params = new HashMap<>();
        params.put("year", cal.get(Calendar.YEAR));
        Optional<CanteenShift> calYear = matchOne("e.dateCS.get(Calendar.YEAR)=:year", params);
        verify = calYear.isPresent();
        
        if(verify == true){
            final Map<String, Object> params2 = new HashMap<>();
            params2.put("day_of_year", cal.get(Calendar.DAY_OF_YEAR));
            return matchOne("e.dateCS.get(Calendar.DAY_OF_YEAR)=:day_of_year", params2);
        }
        
        return Optional.empty();
    }
    
}
