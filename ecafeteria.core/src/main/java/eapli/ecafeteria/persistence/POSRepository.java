package eapli.ecafeteria.persistence;

import eapli.ecafetaria.domain.finance.POS;
import eapli.framework.persistence.repositories.DataRepository;

public interface POSRepository extends DataRepository<POS, Long>{
    
    /**
     *
     * @return true, if at least one POS is open
     */
    public boolean findOpenToClose();
    
}
