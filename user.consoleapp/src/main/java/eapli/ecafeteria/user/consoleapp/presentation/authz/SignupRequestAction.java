/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.authz;

import eapli.framework.actions.Action;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class SignupRequestAction implements Action {

    @Override
    public boolean execute() {
	return new SignupRequestUI().show();
	// return new AddUserUI().show();
    }
}
