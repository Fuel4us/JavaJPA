/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.finance;

import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.Calendar;
import java.util.Optional;

/**
 *
 * @author Josué Lapa
 */
public class OpenPOSController implements Controller {

    private final POSRepository posRepository = PersistenceContext.repositories().POS();
    private final CanteenShiftRepository canteenShiftRepository = PersistenceContext.repositories().canteenShift();

    /**
     *
     * @return
     */
    public Iterable<Long> showPOSList() {
        return posRepository.findAllPOS();
    }

    /**
     *
     * @param posId
     * @return returns true if the POS was successfully opened or false if it is
     * already openned
     */
    public boolean openPOS(Long posId) {
        if (posRepository.openPOS(posId)) {
            //verificar se o shift da cantina esta aberto ou nao

            Optional<CanteenShift> canteenShift = canteenShiftRepository.findCurrentDayShift();
            if (canteenShift.isPresent() && canteenShift.get().isClosed()) {
                //muda estado da worksession para carregamento
                //
            } else {
                if (!canteenShift.isPresent()) {
                    //abre o shift do dia atual(1ª caixa)
                    canteenShiftRepository.open(Calendar.getInstance());
                }
            }
            return true;
        }
        return false;
    }
}
