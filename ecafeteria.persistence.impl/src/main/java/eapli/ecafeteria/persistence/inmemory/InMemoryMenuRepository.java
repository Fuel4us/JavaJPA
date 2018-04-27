/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Date;
import java.util.Optional;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class InMemoryMenuRepository extends InMemoryRepository<Menu, Long> implements MenuRepository {

    @Override
    protected Long newKeyFor(Menu entity) {
        return entity.id();
    }

    @Override
    public Optional<Menu> findById(Long id) {
        return matchOne(e -> e.id().equals(id));
    }

    @Override
    public Iterable<Menu> findByMenuPeriod(Date beginning, Date end) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Menu> findByState(boolean state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
