/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.dailylab;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;

@FacesValidator("limitCheckboxMenuValidator")
public class LimitCheckboxMenuValidator implements Validator {

    public LimitCheckboxMenuValidator() {

    }

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException { 
        Integer minLimit = Integer.parseInt((String)component.getAttributes().get("minLimit"));
        SelectCheckboxMenu myComponent = (SelectCheckboxMenu)component;

        if (((String[])myComponent.getSubmittedValue()).length < minLimit) {
            FacesMessage msg
                    = new FacesMessage("Limit failed",
                            "Min selection must be " + minLimit);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        } else {
            FacesMessage msg
                    = new FacesMessage("Limit sucess",
                            "You Passed! you have selected " + minLimit + " or more");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            context.addMessage("form:message", msg);
        }

    }
}
