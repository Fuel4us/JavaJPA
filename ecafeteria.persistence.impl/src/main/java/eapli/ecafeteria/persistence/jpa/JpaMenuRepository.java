/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author joao reis (1160600)
 */
public class JpaMenuRepository extends CafeteriaJpaRepositoryBase<Menu, Long> implements MenuRepository {

    @Override
    public Optional<Menu> findById(Long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return matchOne("e.id = :id", params);
    }

    /**
     * Joao Reis -1160600 (not tested)
     *
     * @param beginning beginning of the time period
     * @param end end of the time period
     * @return all menus in repository that either start or end in the defined
     * menu period
     */
    @Override
    public Iterable<Menu> findByMenuPeriod(Date beginning, Date end) {
        final Map<String, Object> params = new HashMap<>();
        params.put("dateBeg", beginning);
        params.put("dateEnd", end);

        return match("(e.startDate >= :dateBeg and e.startDate<= :dateEnd) or (e.endDate >= :dateBeg and e.endDate<= :dateEnd)", params);
    }

    @Override
    public Iterable<Menu> findByState(boolean state) {
        final Map<String, Object> params = new HashMap<>();
        params.put("state", state);
        return match("(e.published == state)", params);
    }

}
