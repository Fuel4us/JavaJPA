package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.finance.POS;
import eapli.ecafeteria.persistence.POSRepository;
import java.util.Optional;

class JpaPOSRepository extends CafeteriaJpaRepositoryBase<POS, Long> implements POSRepository{
    @Override
    public Iterable<Long> findAllPOS() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<POS> findPOSByID(Long id) {
        return null;
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
        
        for(POS pos : findAll()){
            if(pos.isOpen()){
                pos.close();
                verify = true;
            }
        }
        return verify;
    }
    
    
}
