/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.dailylab;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.EditableValueHolder;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author hatemalimam
 */
@ManagedBean
@ViewScoped
public class PickListBean implements Serializable {

    private DualListModel<String> cities;

    /**
     * Creates a new instance of PickListBean
     */
    public PickListBean() {
        fillList();
    }

    public void fillList() {
        List<String> source = new ArrayList<String>();
        List<String> target = new ArrayList<String>();
        source.add("Istanbul");
        source.add("Ankara");
        source.add("Izmir");
        source.add("Antalya");
        source.add("Bursa");
        cities = new DualListModel<String>(source, target);
       
    }

    public DualListModel<String> getCities() {
        return cities;
    }

    public void setCities(DualListModel<String> cities) {
        this.cities = cities;
    }
    
    public void showValues() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Select Values", StringUtils.join(cities.getTarget(), ","));            
        RequestContext.getCurrentInstance().showMessageInDialog(message);                
    }

}
