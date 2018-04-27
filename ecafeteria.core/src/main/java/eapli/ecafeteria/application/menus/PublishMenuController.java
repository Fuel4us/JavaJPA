/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Berccar
 */
public class PublishMenuController {

    private final ListMenuService svc = new ListMenuService();
    private final MenuRepository repo = PersistenceContext.repositories().menus();

    public boolean publishMenu(Menu menu) throws DataConcurrencyException, DataIntegrityViolationException {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        boolean done = false;

        if (menu != null) {
            done = menu.publishMenu();
            repo.save(menu);
        }

        return done;
    }

    public Iterable<Menu> listUnpublishedMenus() {

        return this.svc.unpublishedMenus();

    }

}
