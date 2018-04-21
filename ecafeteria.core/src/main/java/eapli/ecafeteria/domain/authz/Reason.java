/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.authz;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author pedromonteiro
 */
@Entity
public class Reason implements Serializable, ValueObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reasonId;
    private String comment;
    private ReasonType reason;

    protected Reason() {
        //For ORM
    }
    

    public Reason(ReasonType rt, String comment) {
        this.reason = rt;
        this.comment = comment;
    }
   

    public Long id() {
        return reasonId;
    }

    @Override
    public String toString() {
        return "Reason:" + reason + "\nComment: " + comment;
    }

    
    
    
    
}
