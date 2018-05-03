package eapli.ecafeteria.domain.kitchen;

import eapli.framework.presentation.console.ShowMessageAction;
import javax.persistence.Embeddable;

/**
 *
 * @author joaotiagow
 */
@Embeddable
public class HeuristicB implements Heuristic {
    
    private String name;
    
    public HeuristicB(String name){
        this.name = name;
    }

    @Override
    public void doHeuristicLogic() {
        new ShowMessageAction("Under Development").execute();
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
