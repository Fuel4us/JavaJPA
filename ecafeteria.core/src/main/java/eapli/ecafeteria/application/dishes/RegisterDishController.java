package eapli.ecafeteria.application.dishes;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.dishes.NutricionalInfo;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt Class changed by <1150478@isep.ipp.pt>
 */
public class RegisterDishController implements Controller {

    /**
     * Instance variables.
     *
     */
    private final ListDishTypeService svc = new ListDishTypeService();
    private final DishRepository dishRepository = PersistenceContext.repositories().dishes();
    private final MaterialRepository matRepository = PersistenceContext.repositories().materials();
    private final AllergenRepository allergRepository = PersistenceContext.repositories().allergen();

    /**
     * Method that does the register of a dish, with its characteristics passed
     * by parameter.
     *
     * @param dishType
     * @param name
     * @param calories
     * @param salt
     * @param price
     * @param allergList
     * @param ingredientsList
     * @return
     * @throws DataIntegrityViolationException
     * @throws DataConcurrencyException
     */
    public Dish registerDish(final DishType dishType, final String name, final Integer calories, final Integer salt,
            final double price, Set<Allergen> allergList, Set<Material> ingredientsList) throws DataIntegrityViolationException, DataConcurrencyException {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        final Dish newDish = new Dish(dishType, Designation.valueOf(name), new NutricionalInfo(calories, salt),
                Money.euros(price), allergList, ingredientsList);

        return this.dishRepository.save(newDish);
    }

    /**
     * Returns the dish types.
     *
     * @return
     */
    public Iterable<DishType> dishTypes() {
        return this.svc.activeDishTypes();
    }

    /**
     * Returns the whole list of materials
     *
     * @return
     */
    public List<Material> getAllMaterials() {
        List<Material> listIngredients = new ArrayList<>();
        for (Material mat : matRepository.findAll()) {
            listIngredients.add(mat);
        }
        return listIngredients;
    }

    /**
     * Returns a list of all the existing allergens.
     *
     * @return
     */
    public List<Allergen> getAllAllergens() {
        List<Allergen> listAllergens = new ArrayList<>();
        for (Allergen allerg : allergRepository.findAll()) {
            listAllergens.add(allerg);
        }
        return listAllergens;
    }
}
