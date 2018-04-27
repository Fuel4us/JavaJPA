/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 1160629@isep.ipp.pt
 */
@Entity
public class LimitConfiguration implements AggregateRoot<Long>, Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Embedded
    @AttributeOverride(name="limitValue", column = @Column(name="YellowLimit"))
    private Limit yellowLimit;
    
    @Embedded
    @AttributeOverride(name="limitValue", column = @Column(name="RedLimit"))
    private Limit redLimit;

    public LimitConfiguration(Limit yellowLimit, Limit redLimit){
        
    }
    
    /**
     * Initializes limits with default values
     */
    public LimitConfiguration() {
        try {
            yellowLimit = new Limit(50);
            redLimit = new Limit(70);
        } catch(Exception e){
            //This exception should not happen
            System.out.println(e.getMessage());
        }
    }
    public boolean configureYellowLimit(double limitValue){
        return configureLimit(yellowLimit, limitValue);
    }
    
    public boolean configureRedLimit(double limitValue){
        return configureLimit(redLimit, limitValue);
    }
    
    private boolean configureLimit(Limit limit, double limitValue){
        try{
            limit.configureLimitValue(limitValue);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long id() {
        return this.id;
    }
}
