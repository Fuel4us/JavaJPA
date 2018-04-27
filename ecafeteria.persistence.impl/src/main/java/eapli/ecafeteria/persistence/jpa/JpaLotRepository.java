package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.persistence.LotRepository;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Query;

/**
 * @author Gonçalo Silva (1161140)
 */
public class JpaLotRepository extends CafeteriaJpaRepositoryBase<Lot, Long> implements LotRepository {

    @Override
    public Long findPkWithCode(int lotCode) {
        final Map<String, Object> params = new HashMap<>();
        params.put("lot", lotCode);

        Query query = entityManager().createQuery("select l.pk from " + this.entityClass.getSimpleName() + " l where l.lotCode = :lotCode");
        query.setParameter("lotCode", lotCode);

        return (Long) query.getSingleResult();
    }
}
