/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.cafeteriauser;

import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author pedromonteiro
 */
public class AllergensPrinter implements Visitor<Allergen> {

    @Override
    public void visit(Allergen visitee) {
        System.out.printf("%-20s%-10s%n", visitee.getAcronym(), visitee.getDescription());
    }
    
}
