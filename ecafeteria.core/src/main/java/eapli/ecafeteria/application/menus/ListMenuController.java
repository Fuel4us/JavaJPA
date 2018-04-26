/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.application.Controller;

/**
 *
 * @author Berccar
 */
public class ListMenuController implements Controller {
    
    private final ListMenuService svc = new ListMenuService();
    
    public Iterable<Menu> allUnpublishedMenus() {
        return svc.unpublishedMenus();
    }
    
}
