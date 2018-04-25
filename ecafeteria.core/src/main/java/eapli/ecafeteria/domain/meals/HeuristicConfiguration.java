package eapli.ecafeteria.domain.meals;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 */
@Entity
public class HeuristicConfiguration {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Heuristic> heuristicsAvailable;
    
    public HeuristicConfiguration() {
        heuristicsAvailable = new LinkedList<>();
    }

    public List<Heuristic> heuristics() {
        return heuristicsAvailable;
    }
}
