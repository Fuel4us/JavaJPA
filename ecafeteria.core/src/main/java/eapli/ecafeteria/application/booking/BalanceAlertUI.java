/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.framework.presentation.console.AbstractUI;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Rúben Ribeiro (1160998)
 */
public class BalanceAlertUI implements Observer {
    public BalanceAlertUI(){
    }

    public void showAlert(){
        System.out.println("\n\nAlert:\n\nBalance below limit.");
    }

    @Override
    public void update(Observable o, Object arg) {
        showAlert();
    }
}
