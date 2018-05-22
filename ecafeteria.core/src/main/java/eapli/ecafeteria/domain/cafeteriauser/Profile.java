/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Leandro
 */
@Entity
public class Profile implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private static final double DEFAULT_BALANCE_LIMIT = 0.0;
    
    
    @Id
    @GeneratedValue
    private Long id;
    
    private double balanceLimit;
    
    protected Profile(){
        this.balanceLimit = DEFAULT_BALANCE_LIMIT;
    }
    
    /**
     * Adds a new user Balance limit
     * @param balanceLimit new balance limit
     * @return True if a new balance is added, false if not
     */
    public boolean addBalanceLimit(double balanceLimit){
        if(balanceLimit > 0){
            this.balanceLimit = balanceLimit;
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Return the current user balance limit
     * @return Value of the current balance limit
     */
    public double getBalanceLimit(){
        return balanceLimit;
    }
    
}
