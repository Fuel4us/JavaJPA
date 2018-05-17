/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author pedromonteiro
 */
@Entity
public class NutritionalProfile implements  Serializable, AggregateRoot<Long>{

    @Id
    private Long id;
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "allergen_list")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Allergen> allergenList;
    
    protected NutritionalProfile(){};
    
    public boolean addAllergen(Allergen allergen){
        return allergenList.add(allergen);
    }
    
    public boolean removeAllergen(Allergen allergen){
        return allergenList.remove(allergen);
    }
    
    public Iterable<Allergen> userAllergens(){
        return allergenList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean is(Long otherId) {
        return AggregateRoot.super.is(otherId);
    }

    @Override
    public Long id() {
        return this.id;
    }
    
}
