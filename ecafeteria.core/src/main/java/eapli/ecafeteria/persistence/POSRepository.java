package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.finance.POS;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.List;
import java.util.Optional;

public interface POSRepository extends DataRepository<POS, Long> {


    public Optional<POS> findPOSByID(Long id);

    public boolean openPOS(Long id);

    public List<POS> findOpenToClose();

}
