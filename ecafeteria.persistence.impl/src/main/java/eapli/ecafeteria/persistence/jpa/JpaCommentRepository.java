/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Comment;
import eapli.ecafeteria.persistence.CommentRepository;

/**
 *
 * @author Hilario Coelho
 */
public class JpaCommentRepository extends CafeteriaJpaRepositoryBase<Comment, Integer> implements CommentRepository {
    public JpaCommentRepository() {
    }
}