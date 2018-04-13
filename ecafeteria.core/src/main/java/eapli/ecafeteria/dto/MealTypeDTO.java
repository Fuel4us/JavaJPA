/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.dto;

import eapli.framework.dto.DTO;

/**
 *
 * @author Bernardo Carreira
 *
 * a pure DTO for meal types
 */
@SuppressWarnings("squid:ClassVariableVisibilityCheck")
public class MealTypeDTO implements DTO{

    public String acronym;
    public String description;
    public boolean active;

    public MealTypeDTO(String acronym2, String description2, boolean active2) {
        acronym = acronym2;
        description = description2;
        active = active2;
    }
}
