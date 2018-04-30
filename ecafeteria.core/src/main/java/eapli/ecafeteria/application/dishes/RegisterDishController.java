package eapli.ecafeteria.application.dishes;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Allergens;
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
 * @author Jorge Santos ajs@isep.ipp.pt
 */
/**
 * Class changed by João Pereira_1150478
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class RegisterDishController implements Controller {

    private final ListDishTypeService svc = new ListDishTypeService();

    private final DishRepository dishRepository = PersistenceContext.repositories().dishes();
    private final MaterialRepository matRepository = PersistenceContext.repositories().materials();
    private final AllergenRepository allergRepository = PersistenceContext.repositories().allergen();

    public Dish registerDish(final DishType dishType, final String name, final Integer calories, final Integer salt,
            final double price, List<Allergens> allergList, Set<Material> ingredientsList) throws DataIntegrityViolationException, DataConcurrencyException {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        String allergenConcated = "";
        for (Allergens alrg : allergList) {
            allergenConcated += alrg.getAllergen() + "; ";
        }
        final Dish newDish = new Dish(dishType, Designation.valueOf(name), new NutricionalInfo(calories, salt),
                Money.euros(price), allergenConcated, ingredientsList);

        return this.dishRepository.save(newDish);
    }

    public Iterable<DishType> dishTypes() {
        return this.svc.activeDishTypes();
    }

    public List<Material> getAllMaterials() {
        List<Material> listIngredients = new ArrayList<>();
        for (Material mat : matRepository.findAll()) {
            listIngredients.add(mat);
        }
        return listIngredients;
    }

    public List<Allergens> getAllAllergens() {
        List<Allergens> listAllergens = new ArrayList<>();
        for (Allergens allerg : allergRepository.findAll()) {
            listAllergens.add(allerg);
        }
        return listAllergens;
    }
}
