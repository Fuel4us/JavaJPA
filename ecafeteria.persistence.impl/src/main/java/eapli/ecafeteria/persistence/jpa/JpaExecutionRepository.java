/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.Execution;
import eapli.ecafeteria.persistence.ExecutionRepository;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
public class JpaExecutionRepository extends CafeteriaJpaRepositoryBase<Execution, Long> implements ExecutionRepository {

    public JpaExecutionRepository(String persistenceUnitName) {
        super(persistenceUnitName);
    }
}
