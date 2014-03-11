/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.dailylab;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author hatemalimam
 */
@ManagedBean
@ViewScoped
public class MainBean implements Serializable {

    /**
     * Creates a new instance of MainBean
     */
    private String currentNav;

    private List<UserPojo> list;

    private String selectedTextInArea;

    private Map<String, String> map;
    
    private List<String> selectedList;

    public MainBean() {
        currentNav = "/checkBoxesJQuery/main.xhtml";
        fillList();
    }

    public void updateNav() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        currentNav = (String) map.get("currentNav");
    }

    public void setSelectedText() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        selectedTextInArea = (String) map.get("selectedText");
    }

    public String getCurrentNav() {
        return currentNav;
    }

    public void setCurrentNav(String currentNav) {
        this.currentNav = currentNav;
    }

    public List<UserPojo> getList() {
        return list;
    }

    public void setList(List<UserPojo> list) {
        this.list = list;
    }

    public void fillList() {
        list = new ArrayList<UserPojo>();
        list.add(new UserPojo("Jack"));
        list.add(new UserPojo("Jhon"));
        list.add(new UserPojo("Smack"));
        list.add(new UserPojo("Jimmy"));
        list.add(new UserPojo("Dummu"));
        list.add(new UserPojo("Stakr"));
        list.add(new UserPojo("Simi"));

        map = new HashMap<String, String>();
        map.put("Jack", "Jack");
        map.put("Jhon", "Jhon");
        map.put("Smack", "Smack");
        map.put("Jimmy", "Jimmy");
        map.put("Dummu", "Dummu");
        map.put("Stakr", "Stakr");
        map.put("Simi", "Simi");
    }

    public String getSelectedTextInArea() {
        return selectedTextInArea;
    }

    public void setSelectedTextInArea(String selectedTextInArea) {
        this.selectedTextInArea = selectedTextInArea;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<String> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(List<String> selectedList) {
        this.selectedList = selectedList;
    }
    
    
}
