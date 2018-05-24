/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.persistence.RatingRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author ruben
 */
public class JpaRatingRepository extends CafeteriaJpaRepositoryBase<Rating, Long> implements RatingRepository {

    public JpaRatingRepository() {
    }

    @Override
    public Iterable<Rating> getRatingByDish(Dish dish) {

        entityManager().getTransaction().begin();

        Query query;
        query = entityManager().createQuery("SELECT r FROM Booking b, Rating r , Meal m, Dish d WHERE r.id = b.rating.id AND b.meal.id = m.id AND m.dish.name = :dish");
        query.setParameter("dish", dish.name());

        return (Iterable<Rating>) query.getResultList();
    }
//    public Iterable<Rating> getRatingByDish(Dish dish) {
//        final Map<String, Object> params = new HashMap<>();
//        params.put("dish", dish);
//
//        try {
//            return match("e.dish = :dish", params);
//        } catch (NoResultException e) {
//            return null;
//        }
//
//    }
}
