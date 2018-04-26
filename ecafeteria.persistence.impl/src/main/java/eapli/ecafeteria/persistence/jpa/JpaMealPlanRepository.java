package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

import java.util.Optional;

/**
 * @author Gonçalo Fonseca <1150503@isep.ipp.pt>
 */
public class JpaMealPlanRepository implements MealPlanRepository {
    @Override
    public void delete(MealPlan entity) throws DataIntegrityViolationException {

    }

    @Override
    public void delete(Long entityId) throws DataIntegrityViolationException {

    }

    @Override
    public MealPlan save(MealPlan entity) throws DataConcurrencyException, DataIntegrityViolationException {
        return null;
    }

    @Override
    public Iterable<MealPlan> findAll() {
        return null;
    }

    @Override
    public Optional<MealPlan> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public long count() {
        return 0;
    }
}
