package eapli.ecafeteria.app.backoffice.console.presentation.authz;

import eapli.ecafeteria.application.authz.AddUserController;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.framework.actions.ReturnAction;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.util.Console;
import java.util.HashSet;
import java.util.Set;

/**
 * UI for adding a user to the application.
 *
 * Created by nuno on 22/03/16.
 */
public class AddUserUI extends AbstractUI {

    private final AddUserController theController = new AddUserController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {  
        final UserDataWidget userData = new UserDataWidget();

        userData.show();

        final Set<RoleType> roleTypes = new HashSet<>();
        boolean show;
        do {
            show = showRoles(roleTypes);
        } while (!show);
        
        String mecanographicNumber = null;
        if(roleTypes.contains(RoleType.CAFETERIA_USER))
            mecanographicNumber = Console.readLine("Mecanographic Number");
        
        try {
            if(mecanographicNumber == null)
                this.theController.addUser(userData.username(), userData.password(), userData.firstName(), userData.lastName(), userData.email(), roleTypes, false);
            else
                this.theController.addCafeteriaUser(userData.username(), userData.password(), userData.firstName(), userData.lastName(), userData.email(), roleTypes, mecanographicNumber, false);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            System.out.println("That username is already in use.");
        }

        return false;
    }

    private boolean showRoles(final Set<RoleType> roleTypes) {
        // TODO we could also use the "widget" classes from the framework...
        final Menu rolesMenu = buildRolesMenu(roleTypes);
        final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu);
        return renderer.show();
    }

    private Menu buildRolesMenu(final Set<RoleType> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;
        rolesMenu.add(new MenuItem(counter++, "No Role", new ReturnAction()));
        
        for (final RoleType roleType : getRoleTypes()) {
            rolesMenu.add(new MenuItem(counter++, roleType.name(), () -> roleTypes.add(roleType)));
        }
        
        return rolesMenu;
    }

    /**
     * Get all the existing RoleTypes.
     *
     * @return a list of RoleTypes
     */
    private RoleType[] getRoleTypes() {
        // TODO NMB: Should this method have direct access to RoleTypes?
        return RoleType.values();
    }

    @Override
    public String headline() {
        return "Add User";
    }
}
