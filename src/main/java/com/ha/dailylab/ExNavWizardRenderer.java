/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.dailylab;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.component.wizard.Wizard;

/**
 *
 * @author hatemalimam
 */
public class ExNavWizardRenderer extends org.primefaces.component.wizard.WizardRenderer{
    
    @Override
    protected void encodeMarkup(FacesContext facesContext, Wizard wizard) throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();
        String clientId = wizard.getClientId(facesContext);
        String styleClass = wizard.getStyleClass() == null ? "ui-wizard ui-widget" : "ui-wizard ui-widget " + wizard.getStyleClass();

        writer.startElement("div", wizard);
        writer.writeAttribute("id", clientId, "id");
        writer.writeAttribute("class", styleClass, "styleClass");
        if(wizard.getStyle() != null) {
            writer.writeAttribute("style", wizard.getStyle(), "style");
        }

        if(wizard.isShowStepStatus()) {
            encodeStepStatus(facesContext, wizard);
        }
        
        if(wizard.isShowNavBar()) {
            encodeNavigators(facesContext, wizard);
        }
        
        encodeContent(facesContext, wizard);

        writer.endElement("div");
    }
    
}
