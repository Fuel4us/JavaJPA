package eapli.ecafeteria.app.pos.console.presentation.finance;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import eapli.framework.actions.Action;

/**
 *
 * @author Josué Lapa
 */
public class ClosePOSServiceAction implements Action{

    @Override
    public boolean execute() {
        return new ClosePOSServiceUI().show();
    }
    
}
