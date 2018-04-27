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
        final Map<String, Object> params = new HashMap<>();
        params.put("date", cal);
        return matchOne("e.dateCS=:date", params);
    }
    
}
