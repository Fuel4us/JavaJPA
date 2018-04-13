/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.framework.domain.Designation;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Bernardo Carreira
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, Designation> implements MealRepository {
    
    @Override
	public Optional<Meal> findByName(Designation name) {
		final Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		return matchOne("e.name=:name", params);
	}
}
