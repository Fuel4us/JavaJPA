package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.finance.POS;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                try {
                    this.save(pos);
                } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                    Logger.getLogger(JpaPOSRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }
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
