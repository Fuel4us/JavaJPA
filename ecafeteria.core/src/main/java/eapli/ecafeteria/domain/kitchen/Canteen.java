/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.util.Strings;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 *
 * @author pedro
 */
//@Entity
//public class Canteen implements AggregateRoot<String>, Serializable{
//    
//    private static final long serialVersionUID = 1L;
//
//    // ORM primary key
//    @Id
//    @GeneratedValue
//    private Long pk;
//    @Version
//    private Long version;
//
//    // business ID
//    @Column(unique=true)
//    private String name;
//    @OneToOne
//    private Meal mealCode;
//    @OneToMany
//    private CanteenShift canteenShiftCode;
//    private String description;
//
//    protected Canteen() {
//        // for ORM
//    }
//
//    public Canteen(String name, CanteenShift canteenShiftCode, String description) {
//        if (Strings.isNullOrEmpty(name)) {
//            throw new IllegalArgumentException();
//        }
//        this.canteenShiftCode = name;
//        this.description = description;
//    }
//
//    public String description() {
//        return this.description;
//    }
//
//    public void changeDescriptionTo(String newDescription) {
//        this.description = newDescription;
//    }
//
//    @Override
//    public String id() {
//        return this.acronym;
//    }
//
//    @Override
//    public boolean is(String id) {
//        return id.equalsIgnoreCase(this.acronym);
//    }
//
//    @Override
//    public boolean sameAs(Object other) {
//        // FIXME implement this method
//        return false;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof Material)) {
//            return false;
//        }
//
//        final Material other = (Material) o;
//        return id().equals(other.id());
//    }
//
//    @Override
//    public int hashCode() {
//        return this.acronym.hashCode();
//    }
//    
//}
