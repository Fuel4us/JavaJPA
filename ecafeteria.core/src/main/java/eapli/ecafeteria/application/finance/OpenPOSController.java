/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.finance;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.finance.POS;
import eapli.ecafeteria.domain.finance.WorkSession;
import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.text.SimpleDateFormat;
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
    public Iterable<POS> showPOSList() {
        return posRepository.findAll();
    }

    /**
     *
     * @param posId
     * @return returns true if the POS was successfully opened or false if it is
     * already openned
     */
    public boolean openPOS(Long posId) {
        if (posRepository.openPOS(posId, AuthorizationService.session().authenticatedUser())) {
            //verificar se o shift da cantina esta aberto ou nao

            Optional<CanteenShift> canteenShift = canteenShiftRepository.findCurrentDayShift();

            if (canteenShift == null) {
                //abre o shift do dia atual(1ª caixa)

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");

                String formatted = format1.format(cal.getTime());
                
                //abre um novo turno de cantina
                //canteenShiftRepository.save(new CanteenShift(Calendar.getInstance(), new WorkSession()));
            }

            return true;
        }
        return false;
    }
}
