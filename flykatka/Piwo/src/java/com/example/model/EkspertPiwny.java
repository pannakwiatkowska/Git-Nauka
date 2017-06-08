/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;


import java.util.*;

/**
 *
 * @author katarzyna_bialach
 */
public class EkspertPiwny {
    public List getMarki(String kolor){
        List marki = new ArrayList();
        if (kolor.equals("jasny")){
            marki.add("Heineken");
            marki.add("Lech");
        }
        else if (kolor.equals("bursztynowy")){
           marki.add("Kasztelan");
           marki.add("Marcowe") ;    
        }
        else if (kolor.equals("brunatny")){
            marki.add("Koziel");
            marki.add("Warka Strong");
    }
        else {
            marki.add("Guinness");
            marki.add("Porter");
           }
        return (marki);
}
}