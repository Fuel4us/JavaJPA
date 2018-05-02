/**
 *
 */
package eapli.ecafeteria.persistence;

import eapli.framework.persistence.repositories.TransactionalContext;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

    /**
     * factory method to create a transactional context to use in the
     * repositories
     *
     * @return
     */
    TransactionalContext buildTransactionalContext();

    /**
     *
     * @param autoTx the transactional context to enrol
     * @return
     */
    UserRepository users(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    UserRepository users();

    DishTypeRepository dishTypes();
    
    MealRepository meals();
    
    MenuRepository menus();

    /**
     *
     * @param autoTx the transactional context to enroll
     * @return
     */
    CafeteriaUserRepository cafeteriaUsers(TransactionalContext autoTx);

    
    CafeteriaUserRepository cafeteriaUsers();

    /**
     *
     * @param autoTx the transactional context to enroll
     * @return
     */
    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    SignupRequestRepository signupRequests();

    DishRepository dishes();

    MaterialRepository materials();

    /**
     * ************************
     * reporting
     *************************
     */
    /**
     * @return
     */
    DishReportingRepository dishReporting();
    
    LotRepository lots();
    
    MealLotRepository mealLots();
    
    ExecutionRepository execution();
    
    BookingRepository booking();
    
    HeuristicRepository heuristics();
    
    KitchenLimitRepository kitchenLimit();
    
    CanteenShiftRepository canteenShift();
    
    WorkSessionRepository workSession();
    
    POSRepository POS();
    
    /*
    * POSUserRepository
    */
    //POSUserRepository POSUser();
    
    /*
    * Movement
    */
    MovementRepository movement();
    
    
    /**
     * Reason Repository
     * @return ReasoRepository
     */

    
    ReasonRepository reason();
    
    MealPlanRepository mealplans();
    
    MealPlanItemRepository mealplanitems();
    
    MealPlanItemQuantityRepository mealplanitemquantities();
    
    RatingRepository rating();

    ShiftRepository shift();
    
     AllergenRepository allergen();

    public DelieveryRepository delieveries();
}
