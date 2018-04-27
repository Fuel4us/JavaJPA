/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.kitchen.MealLot;
import eapli.ecafeteria.persistence.MealLotRepository;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
public class JpaMealLotRepository extends CafeteriaJpaRepositoryBase<MealLot, Long> implements MealLotRepository {

    @Override
    public Iterable<MealLot> findAllByLot(Lot lot) {
        final Map<String, Object> params = new HashMap<>();
        params.put("lot", lot);

        return match("e.lot = :lot", params);
    }
}
