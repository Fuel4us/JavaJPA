/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Complaint;
import eapli.ecafeteria.persistence.ComplaintRepository;

/**
 *
 * @author Hernani Gil
 */
public class JpaComplaintRepository extends CafeteriaJpaRepositoryBase<Complaint, Long> implements ComplaintRepository {

    public JpaComplaintRepository() {
    }
    
}
