/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.authz;

import javax.persistence.Embeddable;



/**
 *
 * @author pedromonteiro
 */
@Embeddable
public enum ReasonType {
    REASON1("The user was always late."), REASON2("The user has not complied with a contract clause.");
        
        private final String reasonContent;
        
        ReasonType(final String resonContent){
            this.reasonContent = resonContent;
        }

        @Override
        public String toString() {
            return reasonContent;
        }
        
    
}
