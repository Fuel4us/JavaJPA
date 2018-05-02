package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import eapli.ecafeteria.persistence.DelieveryRepository;
import eapli.ecafeteria.persistence.DishReportingRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.ecafeteria.persistence.HeuristicRepository;
import eapli.ecafeteria.persistence.KitchenLimitRepository;
import eapli.ecafeteria.persistence.LotRepository;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.MealLotRepository;
import eapli.ecafeteria.persistence.MealPlanItemQuantityRepository;
import eapli.ecafeteria.persistence.MealPlanItemRepository;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.ReasonRepository;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.ecafeteria.persistence.WorkSessionRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;
import java.util.Optional;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(TransactionalContext autoTx) {
        return new JpaUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public DishTypeRepository dishTypes() {
        return new JpaDishTypeRepository();
    }

    @Override
    public JpaCafeteriaUserRepository cafeteriaUsers(TransactionalContext autoTx) {
        return new JpaCafeteriaUserRepository(autoTx);
    }

    @Override
    public JpaCafeteriaUserRepository cafeteriaUsers() {
        return new JpaCafeteriaUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public DishRepository dishes() {
        return new JpaDishRepository();
    }

    @Override
    public MaterialRepository materials() {
        return new JpaMaterialRepository();
    }

    @Override
    public TransactionalContext buildTransactionalContext() {
        return JpaAutoTxRepository
                .buildTransactionalContext(Application.settings().getPersistenceUnitName(), Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public DishReportingRepository dishReporting() {
        return new JpaDishReportingRepository();
    }

    @Override
    public MealRepository meals() {
        return new JpaMealRepository();
    }

    @Override
    public LotRepository lots() {
        return new JpaLotRepository();
    }

    public MealLotRepository mealLots() {
        return new JpaMealLotRepository();
    }

    @Override
    public BookingRepository booking() {
        return new JpaBookingRepository();
    }

    @Override
    public JpaMovementRepository movement() {
        return new JpaMovementRepository();
    }

    @Override
    public MenuRepository menus() {
        return new JpaMenuRepository();
    }

    @Override
    public ExecutionRepository execution() {
        return new JpaExecutionRepository();
    }

    @Override
    public HeuristicRepository heuristics() {
        return new JpaHeuristicRepository(Application.settings().getPersistenceUnitName());
    }
    
    @Override
    public KitchenLimitRepository kitchenLimit() {
        return new JpaKitchenLimitRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CanteenShiftRepository canteenShift() {
        return new JpaCanteenShiftRepository();
    }
    
    @Override
    public WorkSessionRepository workSession() {
        return new JpaWorkSessionRepository();
    }
   
    @Override
    public ReasonRepository reason() {
        return new JpaReasonRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public MealPlanRepository mealplans() {
        return new JpaMealPlanRepository();
    }

    @Override
    public RatingRepository rating() {
        return new JpaRatingRepository();
    }

    @Override
    public POSRepository POS() {
        return new JpaPOSRepository();
    }

    @Override
    public ShiftRepository shift() {
        return new JpaShiftRepository();
    }
    
    @Override
    public AllergenRepository allergen() {
        return new JpaAllergenRepository();
    }

    @Override
    public DelieveryRepository delieveries() {
         return new JpaDelieveryRepository();
    }

    @Override
    public MealPlanItemRepository mealplanitems() {
        return new JpaMealPlanItemRepository();
    }

    @Override
    public MealPlanItemQuantityRepository mealplanitemquantities() {
        return new JpaMealPlanItemQuantityRepository();
    }

}
