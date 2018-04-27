/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.persistence.RatingRepository;

/**
 *
 * @author ruben
 */
public class JpaRatingRepository extends CafeteriaJpaRepositoryBase<Rating, Long> implements RatingRepository{
    
    public JpaRatingRepository() {
    }
    
    
}
