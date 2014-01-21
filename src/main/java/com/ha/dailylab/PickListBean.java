/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.dailylab;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.EditableValueHolder;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author hatemalimam
 */
@ManagedBean
@ViewScoped
public class PickListBean implements Serializable {

    private List<DualListModel<String>> dsList = new ArrayList<DualListModel<String>>();

    /**
     * Creates a new instance of PickListBean
     */
    public PickListBean() {
        fillList();
    }

    public List<DualListModel<String>> getDsList() {
        return dsList;
    }

    public void setDsList(List<DualListModel<String>> dsList) {
        this.dsList = dsList;
    }

    public void onDSTransfer() {
        System.out.print("DSTransfer");
        for (DualListModel<String> str1 : dsList) {
            System.out.print("RemovedLBEntry:");           

            for (String dsName1 : str1.getTarget()) {
                System.out.print("RemovedLB:" + dsName1);
            }
        }
    }

    public void fillList() {
        List<String> source = new ArrayList<String>();
        List<String> target = new ArrayList<String>();
        source.add("Istanbul");
        source.add("Ankara");
        source.add("Izmir");
        source.add("Antalya");
        source.add("Bursa");

        DualListModel cities = new DualListModel<String>(source, target);
        dsList.add(cities);
    }

    public void handleTransfer(TransferEvent event) {
        DualListModel<String> dualListModel = (DualListModel<String>)((EditableValueHolder) event.getComponent()).getValue();
        
        if(event.isAdd()) {
            DualListModel<String> dualListModelInner = dsList.get(dsList.indexOf(dualListModel));
            for(String soruce : dualListModelInner.getSource()) {
                for(String targetToRemove: ((ArrayList<String>)event.getItems())) {
                    dualListModelInner.getSource().remove(targetToRemove);
                }
            }
            for(String target : dualListModelInner.getTarget()) {
                for(String targetToAdd: ((ArrayList<String>)event.getItems())) {
                    dualListModelInner.getTarget().add(targetToAdd);
                }
            }
            dsList.remove(dsList.get(dsList.indexOf(dualListModel)));
            dsList.add(dualListModel);
        }
    }

}
