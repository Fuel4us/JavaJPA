/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.dishes;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.application.Controller;
import java.util.HashMap;
import java.util.Map;
import javax.print.attribute.HashAttributeSet;

/**
 *
 * @author João Pires <1150455@isep.ipp.pt>
 */
public class CheckDishRatingController implements Controller {

    private final DishRepository dishRepo = PersistenceContext.repositories().dishes();

    private final RatingRepository ratingRepo = PersistenceContext.repositories().rating();

    public Iterable<Dish> listDishes() {
        return dishRepo.findAll();
    }

    public Map<Dish, Integer> getRatingPerDish(Dish dish) {

        Map<Dish, Integer> map = new HashMap<>();

        int avg;

        return map;
    }

}
