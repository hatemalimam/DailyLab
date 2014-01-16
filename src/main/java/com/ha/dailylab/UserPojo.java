/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.dailylab;

/**
 *
 * @author hatemalimam
 */
public class UserPojo {
    
    private String name;

    public UserPojo() {
        
    }
    
    public UserPojo(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
