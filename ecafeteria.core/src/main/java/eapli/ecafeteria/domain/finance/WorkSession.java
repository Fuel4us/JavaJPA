/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.finance;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 *
 * @author Josué Lapa
 */
@Entity
public class WorkSession implements AggregateRoot<Integer>, Serializable{
    
    private static final long serialVersionUID = 1L;

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;
    
    //Business id
    @Column(unique = true)
    private int workSessionCode;
    
    //atributos

    //construtor
    
    @Override
    public boolean sameAs(Object other) {
        final WorkSession ws = (WorkSession) other;
        return this.id().equals(ws.id());
    }

    @Override
    public boolean is(Integer otherId) {
        return this.workSessionCode == otherId;
    }

    @Override
    public Integer id() {
        return this.workSessionCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.workSessionCode;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WorkSession other = (WorkSession) obj;
        if (this.workSessionCode != other.workSessionCode) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
