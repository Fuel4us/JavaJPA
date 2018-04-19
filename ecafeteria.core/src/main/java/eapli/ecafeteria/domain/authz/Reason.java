/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.authz;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author pedromonteiro
 */
public class Reason {
    
    static class ReasonType{
        String type;

        public ReasonType(String description) {
            this.type = description;
        }
        
        @Override
        public int hashCode() {
            int hash = 3;
            hash = 97 * hash + Objects.hashCode(this.type);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ReasonType other = (ReasonType) obj;
            if (!Objects.equals(this.type, other.type)) {
                return false;
            }
            return true;
        }
        
    }
    
    private static List<ReasonType> reasonList = new ArrayList<>();
    public static boolean addReason(String reasonDescription){
        ReasonType type = new ReasonType(reasonDescription);
        if(reasonList.contains(type)) return false;
        reasonList.add(type);
        return true;
        
    }
    
    String comment;
    ReasonType reason;

    public Reason(ReasonType rt, String comment) {
        this.reason = rt;
        this.comment = comment;
    }
    
    public static List<ReasonType> reasonTypes(){
        return reasonList;
    }
    
    
    
    
}
