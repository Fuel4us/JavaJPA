/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.delivery.Delievery;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.List;

/**
 *
 * @author Ana Mafalda Silva
 */
public interface DelieveryRepository extends DataRepository<Delievery, Long> {

    Delievery findByName(Designation name);

    List<Delievery> findCurrentShiftDeliveries();
}
