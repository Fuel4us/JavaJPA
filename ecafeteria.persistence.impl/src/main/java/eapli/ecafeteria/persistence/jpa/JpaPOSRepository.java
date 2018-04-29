package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.finance.POS;
import eapli.ecafeteria.persistence.POSRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class JpaPOSRepository extends CafeteriaJpaRepositoryBase<POS, Long> implements POSRepository {

    @Override
    public Optional<POS> findPOSByID(Long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return matchOne("e.id = :id", params);
    }

    @Override
    public boolean openPOS(Long id) {

        POS pos = findPOSByID(id).get();

        if (!pos.isOpen()) {

            //abre POS
            return true;
        }
        return false;
    }

    @Override
    public boolean findOpenToClose() {

        boolean verify = false;

        for (POS pos : findAll()) {
            if (pos.isOpen()) {
                pos.close();
                verify = true;
            }
        }
        return verify;
    }

}
