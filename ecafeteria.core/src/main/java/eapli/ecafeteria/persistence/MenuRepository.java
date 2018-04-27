/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Date;
import java.util.Optional;

/**
 *
 * @author Berccar
 */
public interface MenuRepository extends DataRepository<Menu, Long> {

    Optional<Menu> findById(Long id);

    Iterable<Menu> findByState(boolean state);

    /**
     * finds a list of menus that exist in a specific period (Joao Reis -
     * 1160600)
     *
     * @param beginning beginning of established time period
     * @param end end of established time period
     * @return menus that either have a start date or end date in the
     * established time period
     */
    Iterable<Menu> findByMenuPeriod(Date beginning, Date end);
}
