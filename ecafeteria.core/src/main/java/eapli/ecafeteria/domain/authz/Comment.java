/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.authz;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author pedromonteiro
 */
@Embeddable
public class Comment implements Serializable, ValueObject{
    
    private String comment;

    /**
     * For ORM
     */
    public Comment() {
        // ORM
    }
    
    public Comment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return comment;
    }
    
   
    
}
