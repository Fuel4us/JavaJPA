/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.authz;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author pedromonteiro
 */
public class CafetariaUserPrinter implements Visitor<CafeteriaUser>{

    @Override
    public void visit(CafeteriaUser visitee) {
        System.out.printf("%-10s%-30s%-30s%n", visitee.user().username(),
                        visitee.user().name().firstName(), visitee.user().name().lastName());
    }
    
}
