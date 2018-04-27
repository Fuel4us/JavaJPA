package eapli.ecafeteria.bootstrapers;

import java.util.logging.Logger;

import eapli.ecafeteria.application.dishes.RegisterDishController;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mcn
 * changed by João Pereira_1150478
 */
public class DishBootstrapper implements Action {

    @Override
    public boolean execute() {
        final DishTypeRepository dishTypeRepo = PersistenceContext.repositories().dishTypes();
        final DishType vegie = dishTypeRepo.findByAcronym(TestDataConstants.DISH_TYPE_VEGIE).get();
        final DishType fish = dishTypeRepo.findByAcronym(TestDataConstants.DISH_TYPE_FISH).get();
        final DishType meat = dishTypeRepo.findByAcronym(TestDataConstants.DISH_TYPE_MEAT).get();
        Set<String> allergens1 = new HashSet<>();
        Set<String> allergens2 = new HashSet<>();
        Set<String> allergens3 = new HashSet<>();

        allergens1.add(TestDataConstants.ALLERGEN_CRUSTACEOS);
        allergens1.add(TestDataConstants.ALLERGEN_GLUTEN);

        allergens2.add(TestDataConstants.ALLERGEN_PEIXES);
        allergens2.add(TestDataConstants.ALLERGEN_CRUSTACEOS);

        allergens3.add(TestDataConstants.ALLERGEN_GLUTEN);

        register(vegie, TestDataConstants.DISH_NAME_TOFU_GRELHADO, 140, 1, 2.99, allergens1);
        register(vegie, TestDataConstants.DISH_NAME_LENTILHAS_SALTEADAS, 180, 1, 2.85, allergens2);
        register(fish, TestDataConstants.DISH_NAME_BACALHAU_A_BRAZ, 250, 2, 3.99, allergens3);
        register(fish, TestDataConstants.DISH_NAME_LAGOSTA_SUADA, 230, 2, 24.99, allergens3);
        register(meat, TestDataConstants.DISH_NAME_PICANHA, 375, 2, 4.99, allergens2);
        register(meat, TestDataConstants.DISH_NAME_COSTELETA_A_SALSICHEIRO, 475, 2, 3.99, allergens1);

        return true;
    }

    /**
     *
     */
    private void register(DishType dishType, String description, int cal, int salt, double price, Set<String> allergens) {
        final RegisterDishController controller = new RegisterDishController();
        try {
            controller.registerDish(dishType, description, cal, salt, price, allergens);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }
}