package eapli.ecafeteria.domain.kitchen;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 */
@Entity
public class HeuristicConfiguration implements AggregateRoot<Long>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Heuristic heuristicInUse;

    protected HeuristicConfiguration() {
    }

    public HeuristicConfiguration(Heuristic heuristicInUse) {
        this.heuristicInUse = heuristicInUse;
    }

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.heuristicInUse.toString();
    }

    public Heuristic getHeuristicInUse() {
        return heuristicInUse;
    }
}
