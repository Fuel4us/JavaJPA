/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.delivery.Delievery;
import eapli.ecafeteria.persistence.DelieveryRepository;
import eapli.framework.domain.Designation;

/**
 *
 * @author Ana Mafalda Silva
 */
public class JpaDelieveryRepository extends CafeteriaJpaRepositoryBase<Delievery, Long> implements DelieveryRepository{

   
    public Delievery findByName(Designation name) {
        // TODO use parameters instead of string concatenation
        return (matchOne("e.name.designation='" + name + "'")).get();
    }
}
    

