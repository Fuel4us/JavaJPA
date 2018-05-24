/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Complaint;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.ComplaintRepository;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Query;

/**
 *
 * @author Hernani Gil
 */
public class JpaComplaintRepository extends CafeteriaJpaRepositoryBase<Complaint, Long> implements ComplaintRepository {

    public JpaComplaintRepository() {

    }
    
    @Override
    public void RemoveComplaint(Complaint complaint) {
        if (this.entityManager().contains(complaint)) {
            this.entityManager().getTransaction().begin();
            this.entityManager().remove(complaint);
            this.entityManager().getTransaction().commit();
        }
    }
}
