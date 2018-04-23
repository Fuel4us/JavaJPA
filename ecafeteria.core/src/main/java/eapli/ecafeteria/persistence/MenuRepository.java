/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author Berccar
 */
public interface MenuRepository extends DataRepository<Menu, Designation> {
   
    Iterable<Menu> findByState(boolean state);
    
}
