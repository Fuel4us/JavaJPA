/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 *
 * @author pedro
 */
@Entity
public class CanteenShift {
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
//    @OneToMany
//    private Meal mealCode;
//    @OneToMany
//    private CanteenShift canteenShiftCode;
//    private String description;
//
//    protected Canteen() {
//        // for ORM
//    }
//
//    public Canteen(String name, Meal mealCode, CanteenShift canteenShiftCode, String description) {
//        if (Strings.isNullOrEmpty(name)) {
//            throw new IllegalArgumentException();
//        }
//        this.name = name;
//        this.mealCode = mealCode;
//        this.canteenShiftCode = canteenShiftCode;
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
//        return this.name;
//    }
//
//    @Override
//    public boolean is(String id) {
//        return id.equalsIgnoreCase(this.name);
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
//        return this.name.hashCode();
//    }
}
