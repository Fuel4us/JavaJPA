/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;


import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.kitchen.Execution;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
public class RegisterMealsActuallyCookedService {

    private final ExecutionRepository repository = PersistenceContext.repositories().execution();
    private final MealRepository mRepository = PersistenceContext.repositories().meals();

    public RegisterMealsActuallyCookedService() {

    }

     public Execution registerMealsActuallyMade(long mealCode, int cookedMeals) throws DataConcurrencyException, DataIntegrityViolationException {
        Meal meal = mRepository.findById(mealCode).get();
        final Execution mealsActuallyMade = new Execution(meal, cookedMeals);
        return this.repository.save(mealsActuallyMade);
    }
     
    public boolean checkedMeal(long mealCode){
        return mRepository.findById(mealCode).isPresent();
    }
    
    public List<Meal> getAllMeals() {
        Iterable<Meal> it = mRepository.findAll();
        List<Meal> list = new ArrayList<>();
        if (!it.iterator().hasNext()) {
            System.out.println("There are no meals");
        } else {
            for (Meal meal : mRepository.findAll()) {

                list.add(meal);
            }
        }

        return list;
    }

    public List<Date> displayMealsByDate(List<Meal> list) {
        List<Date> dateList = new ArrayList<>();
        if (list.size() > 0) {
            for (Meal meal
                    : list) {
                if (!dateList.contains(meal.getMealDate())) {
                    dateList.add(meal.getMealDate());
                }
            }
        }
        return dateList;
    }

    public List<Dish> displayMealsByDish(List<Meal> list) {
        List<Dish> dishList = new ArrayList<>();
        if (list.size() > 0) {
            for (Meal meal
                    : list) {
                if (!dishList.contains(meal.getDish())) {
                    dishList.add(meal.getDish());
                }
            }
        }

        return dishList;
    }

    public List<Meal> displayMealsByMeal(List<Meal> list) {
        List<Meal> mealList = new ArrayList<>();
        if (list.size() > 0) {
            for (Meal meal
                    : list) {
                if (!mealList.contains(meal)) {
                    mealList.add(meal);
                }
            }
        }

        return mealList;
    }

    public List<MealType> displayMealsByMealType(List<Meal> list) {
        List<MealType> mealTypeList = new ArrayList<>();
        if (list.size() > 0) {
            for (Meal meal
                    : list) {
                if (!mealTypeList.contains(meal.getMealType())) {
                    mealTypeList.add(meal.getMealType());
                }
            }
        }
        return mealTypeList;
    }
}
