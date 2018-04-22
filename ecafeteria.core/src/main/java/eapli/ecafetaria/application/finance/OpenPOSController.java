/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.application.finance;

import eapli.ecafetaria.domain.finance.POS;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Josué Lapa
 */
public class OpenPOSController {

    //private final POSRepository posRepository = new PersistenceContext.repositories().POSRepository();
    
    public List<POS> showPOSList(){
        
        return null;
    }

    /**
     *
     * @return returns true if the POS was successfully opened or false if it is already openned
     */
    public boolean openPOS(/*recebe qual a POS em questao*/){
        return false;
    }
}
