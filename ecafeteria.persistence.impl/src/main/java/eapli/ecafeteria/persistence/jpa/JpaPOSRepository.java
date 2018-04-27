package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.finance.POS;
import eapli.ecafeteria.persistence.POSRepository;

class JpaPOSRepository extends CafeteriaJpaRepositoryBase<POS, Long> implements POSRepository{
    
    @Override
    public boolean findOpenToClose() {
        
        boolean verify = false;
        
        for(POS pos : findAll()){
            if(pos.isOpen()){
                pos.close();
                verify = true;
            }
        }
        return verify;
    }
    
}
