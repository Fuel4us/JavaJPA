package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import eapli.framework.util.Strings;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.persistence.NoResultException;
import javax.persistence.Query;

class JpaCanteenShiftRepository extends CafeteriaJpaRepositoryBase<CanteenShift, String> implements CanteenShiftRepository {

    @Override
    public void forEach(Consumer<? super CanteenShift> cnsmr) {
        super.forEach(cnsmr); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Spliterator<CanteenShift> spliterator() {
        return super.spliterator(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verifyByDate(String canteenShiftDate) {
        Query query = entityManager().createQuery("select e.pk from " + this.entityClass.getSimpleName() + " e where e.canteenShiftDate=:canteenShiftDate");
        query.setParameter("canteenShiftDate", canteenShiftDate);

        return Strings.isNullOrEmpty((String) query.getSingleResult());
    }

    @Override
    public CanteenShift close(Calendar cal) {
        Optional<CanteenShift> cs = this.findByDate(cal);
        if (!cs.isPresent()) {
            return null;
        }

        if (!cs.get().close()) {
            return null;
        }
        return cs.get();
    }

    private Optional<CanteenShift> findByDate(Calendar cal) {
        String canteenShiftDate;

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        if (month <= 9) {
            canteenShiftDate = Integer.toString(year) + "0" + Integer.toString(month) + Integer.toString(day);
        } else {
            canteenShiftDate = Integer.toString(year) + Integer.toString(month) + Integer.toString(day);
        }

        return matchOne("e.canteenShiftDate=:canteenShiftDate", "canteenShiftDate", canteenShiftDate);
    }

    @Override
    public Optional<CanteenShift> findCurrentDayShift() {
        final Map<String, Object> params = new HashMap<>();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");

        String formatted = format1.format(cal.getTime());

        params.put("canteenShiftDate", formatted);
        try {
            return matchOne("e.canteenShiftDate=:canteenShiftDate", params);
        } catch (NoResultException e) {
            return null;
        }
    }

}
