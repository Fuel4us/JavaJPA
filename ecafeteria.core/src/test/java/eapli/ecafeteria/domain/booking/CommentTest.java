package eapli.ecafeteria.domain.booking;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro Vieira 1160634
 */
public class CommentTest {
    
    //same values than in Comment
    private static final String INITIAL_ANSWER = "there is no answer yet!";
    
    /**
     * Test of Comment can not be Null, of class Comment.
     */
    @Test
    public void ensureCommentIsNotNull() {
        System.out.println("-Ensure comment is not null");
        
        try{
            Comment c = new Comment(null);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Test of Comment can not be empty, of class Comment.
     */
    @Test
    public void ensureCommentIsNotEmpty() {
        System.out.println("-Ensure comment is not empty");
        
        try{
            Comment c = new Comment("");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Test of DefaultReplay constant, of class Comment.
     */
    @Test
    public void ensureDefaultReplayIsRight() {
        System.out.println("-Ensure default replay is right");
        
        Comment c = new Comment("Comment");
        String expResult = c.getAnswer();
        assertEquals(expResult, INITIAL_ANSWER);
    }
    
    /**
     * Test of getRealComment method, of class Comment.
     */
    @Test
    public void ensureGetRealCommentWorks() {
        System.out.println("-Ensure getRealComment Works");
        String expResult = "Comment";
        Comment c = new Comment(expResult);
        String result = c.getRealComment();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAnswer and changeAnswer methods, of class Comment.
     */
    @Test
    public void ensureGetAndChangeAnswerWorks() {
        System.out.println("-Ensure getAnswer and changeAnswer Works");
        String expResult = "Answer";
        Comment c = new Comment("Comment");
        c.changeAnswer(expResult);
        String result = c.getAnswer();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Comment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String comment = "Comment";
        Comment c = new Comment(comment);
        String expResult = "Comment{" + ", comment=" + comment + ", answer=" + INITIAL_ANSWER + '}';
        String result = c.toString();
        assertEquals(expResult, result);
    }
    
}
