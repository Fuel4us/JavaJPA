package eapli.ecafeteria.domain.kitchen;

import eapli.framework.presentation.console.ShowMessageAction;
import javax.persistence.Embeddable;

/**
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 */
@Embeddable
public class HeuristicA implements Heuristic {
    
    public HeuristicA(){
    }
    
    @Override
    public void doHeuristicLogic() {
        new ShowMessageAction("Under Development").execute();
    }
}
