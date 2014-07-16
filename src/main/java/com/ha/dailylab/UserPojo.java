/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.dailylab;

import java.util.ArrayList;
import java.util.List;

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
    
    public static void main(String args[]) {
        Double stash = 200d;
        Double fooBarBasePrice = 1d;
        List<Double> boughtBars = new ArrayList<Double>();
        
        while(stash > 0 && stash > fooBarBasePrice + (fooBarBasePrice * 20 / 100)) {
            if(boughtBars.isEmpty()) {
                boughtBars.add(fooBarBasePrice);
                stash -= fooBarBasePrice;
            } else {
                fooBarBasePrice += fooBarBasePrice * 20 / 100;                
                stash -= fooBarBasePrice;
                boughtBars.add(fooBarBasePrice);
            }             
        }
        System.out.println(stash);
        System.out.println(boughtBars.size());
    }
}
