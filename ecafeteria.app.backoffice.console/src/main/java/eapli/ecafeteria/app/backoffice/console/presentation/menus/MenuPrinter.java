package eapli.ecafeteria.app.backoffice.console.presentation.menus;

import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
@SuppressWarnings("squid:S106")
public class MenuPrinter implements Visitor<Menu> {

    @Override
    public void visit(Menu visitee) {
        System.out.printf(visitee.toString());
    }
}
