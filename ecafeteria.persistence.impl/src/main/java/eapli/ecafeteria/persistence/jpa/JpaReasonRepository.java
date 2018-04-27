/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.authz.Reason;
import eapli.ecafeteria.domain.authz.ReasonType;
import eapli.ecafeteria.persistence.ReasonRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author pedromonteiro
 */
public class JpaReasonRepository extends JpaAutoTxRepository<Reason, Long>  implements ReasonRepository{

    public JpaReasonRepository(String persistenceUnitName) {
        super(persistenceUnitName);
    }
    
}
