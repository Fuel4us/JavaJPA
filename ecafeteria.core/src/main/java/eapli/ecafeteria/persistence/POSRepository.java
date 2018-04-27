package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.finance.POS;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

public interface POSRepository extends DataRepository<POS, Long> {

    /**
     *
     * @return true, if at least one POS is open
     */
    public Iterable<Long> findAllPOS();

    public Optional<POS> findPOSByID(Long id);

    public boolean openPOS(Long id);

    public boolean findOpenToClose();

}
