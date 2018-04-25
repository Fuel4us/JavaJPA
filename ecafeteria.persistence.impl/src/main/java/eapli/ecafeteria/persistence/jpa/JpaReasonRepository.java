/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.authz.Reason;
import eapli.ecafeteria.domain.authz.ReasonType;
import eapli.ecafeteria.persistence.ReasonRepository;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author pedromonteiro
 */
public class JpaReasonRepository extends CafeteriaJpaRepositoryBase<Reason, Long> implements ReasonRepository{


    @Override
    public Iterable<Reason> findByReasonType(ReasonType rt) {
        final Map<String, Object> params = new HashMap<>();
        params.put("reasonType", rt);
        
        return match("e.reason=reasonType", params);
    }
    
}
