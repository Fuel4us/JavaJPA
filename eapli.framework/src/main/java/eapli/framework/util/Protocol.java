/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.util;

/**
 *
 * @author Hilario Coelho
 */
public class Protocol {
    public boolean success;
    public String message;
    public Object data;
    
    public Protocol(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
    
    public Protocol(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    public Protocol(boolean success) {
        this.success = success;
    }
}