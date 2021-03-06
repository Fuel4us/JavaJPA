//package eapli.ecafeteria.persistence.inmemory;
//
//import eapli.ecafeteria.bootstrapers.ECafeteriaBootstrapper;
//import eapli.ecafeteria.persistence.BookingRepository;
//import eapli.ecafeteria.persistence.CafeteriaUserRepository;
//import eapli.ecafeteria.persistence.CanteenShiftRepository;
//import eapli.ecafeteria.persistence.DishReportingRepository;
//import eapli.ecafeteria.persistence.DishRepository;
//import eapli.ecafeteria.persistence.DishTypeRepository;
//import eapli.ecafeteria.persistence.HeuristicRepository;
//import eapli.ecafeteria.persistence.MaterialRepository;
//import eapli.ecafeteria.persistence.MealRepository;
//import eapli.ecafeteria.persistence.RepositoryFactory;
//import eapli.ecafeteria.persistence.SignupRequestRepository;
//import eapli.ecafeteria.persistence.UserRepository;
//import eapli.framework.persistence.repositories.TransactionalContext;
//import eapli.ecafeteria.persistence.LotRepository;
//
///**
// *
// * Created by nuno on 20/03/16.
// */
//public class InMemoryRepositoryFactory implements RepositoryFactory {
//
//    static {
//        // only needed because of the in memory persistence
//        new ECafeteriaBootstrapper().execute();
//    }
//
//    @Override
//    public UserRepository users(TransactionalContext tx) {
//        return new InMemoryUserRepository();
//    }
//
//    @Override
//    public UserRepository users() {
//        return users(null);
//    }
//
//    @Override
//    public DishTypeRepository dishTypes() {
//        return new InMemoryDishTypeRepository();
//    }
//
//    @Override
//    public CafeteriaUserRepository cafeteriaUsers(TransactionalContext tx) {
//
//        return new InMemoryCafeteriaUserRepository();
//    }
//
//    @Override
//    public CafeteriaUserRepository cafeteriaUsers() {
//        return cafeteriaUsers(null);
//    }
//
//    @Override
//    public SignupRequestRepository signupRequests() {
//        return signupRequests(null);
//    }
//
//    @Override
//    public SignupRequestRepository signupRequests(TransactionalContext tx) {
//        return new InMemorySignupRequestRepository();
//    }
//
//    @Override
//    public DishRepository dishes() {
//        return new InMemoryDishRepository();
//    }
//
//    @Override
//    public MaterialRepository materials() {
//        return new InMemoryMaterialRepository();
//    }
//
//    @Override
//    public TransactionalContext buildTransactionalContext() {
//        // in memory does not support transactions...
//        return null;
//    }
//
//    @Override
//    public DishReportingRepository dishReporting() {
//        return new InMemoryDishReportingRepository();
//    }
//
//    @Override
//    public MealRepository meals() {
//        return new InMemoryMealRepository();
//    }
//
//    @Override
//    public LotRepository lots() {
//        return new InMemoryLotRepository();
//    }
//
//    @Override
//    public BookingRepository booking() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
////    @Override
////    public HeuristicRepository heuristics() {
////        return new InMemoryHeuristicRepository();
////    }
//
////    @Override
////    public CanteenShiftRepository canteenShift() {
////        return new InMemoryCanteenShiftRepository();
////    }
//}
