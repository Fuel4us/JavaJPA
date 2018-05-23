package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.finance.POS;
import eapli.ecafeteria.domain.finance.POSState;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public boolean openPOS(Long id, SystemUser cashier) {

        POS pos = findPOSByID(id).get();

        if (!pos.isOpen()) {
            //abre POS
            if (pos.open()) {

                pos.setCashier(AuthorizationService.session().authenticatedUser());

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

    @Override
    public boolean closeCurrentPOS() {
        for (POS pos : this.findAll()) {
            if (pos.currentCashier() != null) {
                if (pos.currentCashier().equals(AuthorizationService.session().authenticatedUser())) {
                    pos.setCashier(null);
                    pos.close();
                    try {
                        this.save(pos);
                        return true;
                    } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                        Logger.getLogger(JpaPOSRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return false;
    }

}
