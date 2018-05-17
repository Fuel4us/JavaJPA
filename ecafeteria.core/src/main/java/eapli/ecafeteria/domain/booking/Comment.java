/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Hilario Coelho
 * changed by João Pereira <1150478@isep.ipp.pt>
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;
    private String resposta;

    public Comment() {
    }

    public Comment(String comment) {
        this.comment = comment;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    
    /**
     * Returns the comment.
     * Created by João Pereira <1150478@isep.ipp.pt>
     * @return 
     */
    public String getRealComment() {
        return comment;
    }
    
    /**
     * Returns the answer of the comment.
     * Created by João Pereira <1150478@isep.ipp.pt>
     * @return 
     */
    public String getResposta() {
        return resposta;
    }

    @Override
    public String toString() {
        return "Comment{" + "comment=" + comment + ", resposta=" + resposta + '}';
    }
}
