package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.finance.POS;
import eapli.ecafeteria.persistence.POSRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.NoResultException;

class JpaPOSRepository extends CafeteriaJpaRepositoryBase<POS, Long> implements POSRepository {

    @Override
    public Optional<POS> findPOSByID(Long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        try {
            return matchOne("e.id = :id", params);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean openPOS(Long id) {

        POS pos = findPOSByID(id).get();

        if (!pos.isOpen()) {

            //abre POS
            if (pos.open()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<POS> findOpenToClose() {

        List<POS> posList = new ArrayList<>();

        for (POS pos : findAll()) {
            if (pos.isOpen()) {
                pos.close();
                posList.add(pos);
            }
        }
        
        return posList;
    }

}
