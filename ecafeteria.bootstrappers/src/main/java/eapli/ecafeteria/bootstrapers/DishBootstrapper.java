package eapli.ecafeteria.bootstrapers;

import java.util.logging.Logger;

import eapli.ecafeteria.application.dishes.RegisterDishController;
import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mcn changed by João Pereira_1150478
 */
public class DishBootstrapper implements Action {

    @Override
    public boolean execute() {

        final DishTypeRepository dishTypeRepo = PersistenceContext.repositories().dishTypes();
        final MaterialRepository matRepo = PersistenceContext.repositories().materials();
        final AllergenRepository allergRepository = PersistenceContext.repositories().allergen();
        final DishType vegie = dishTypeRepo.findByAcronym(TestDataConstants.DISH_TYPE_VEGIE).get();
        final DishType fish = dishTypeRepo.findByAcronym(TestDataConstants.DISH_TYPE_FISH).get();
        final DishType meat = dishTypeRepo.findByAcronym(TestDataConstants.DISH_TYPE_MEAT).get();

        Set<Allergen> allergList = new HashSet<>();
        Set<Material> ingredientsList = new HashSet<>();

        for (Allergen allerg : allergRepository.findAll()) {
            allergList.add(allerg);
        }
        for (Material material : matRepo.findAll()) {
            ingredientsList.add(material);
        }

        register(vegie, TestDataConstants.DISH_NAME_TOFU_GRELHADO, 140, 1, 2.99, allergList, ingredientsList);
        register(vegie, TestDataConstants.DISH_NAME_LENTILHAS_SALTEADAS, 180, 1, 2.85, allergList, ingredientsList);
        register(fish, TestDataConstants.DISH_NAME_BACALHAU_A_BRAZ, 250, 2, 3.99, allergList, ingredientsList);
        register(fish, TestDataConstants.DISH_NAME_LAGOSTA_SUADA, 230, 2, 24.99, allergList, ingredientsList);
        register(meat, TestDataConstants.DISH_NAME_PICANHA, 375, 2, 4.99, allergList, ingredientsList);
        register(meat, TestDataConstants.DISH_NAME_COSTELETA_A_SALSICHEIRO, 475, 2, 3.99, allergList, ingredientsList);

        return true;
    }

    /**
     *
     */
    private void register(DishType dishType, String description, int cal, int salt, double price, Set<Allergen> allergens, Set<Material> ingredientsList) {
        final RegisterDishController controller = new RegisterDishController();
        try {
            controller.registerDish(dishType, description, cal, salt, price, allergens, ingredientsList);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }
}
