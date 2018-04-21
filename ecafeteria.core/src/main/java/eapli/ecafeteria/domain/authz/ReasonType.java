/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.authz;



/**
 *
 * @author pedromonteiro
 */
public enum ReasonType {
    REASON1("Reason 1"), REASON2("Reason 2");
        
        private final String reasonContent;
        
        ReasonType(final String resonContent){
            this.reasonContent = resonContent;
        }

        @Override
        public String toString() {
            return reasonContent;
        }
    
}
