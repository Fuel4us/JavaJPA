/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Complaint;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Hernani Gil
 */
public interface ComplaintRepository extends DataRepository<Complaint, Long> {
    
}
