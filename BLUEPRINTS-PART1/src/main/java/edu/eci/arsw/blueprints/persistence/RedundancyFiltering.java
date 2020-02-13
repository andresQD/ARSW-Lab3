/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import java.util.ArrayList;
import org.springframework.stereotype.Component;


/**
 *
 * @author Andrés Quintero
 */
@Component ("Redundancy")
public class RedundancyFiltering implements Filter {

    public RedundancyFiltering() {
    }

    @Override
    public ArrayList<Point> filter(Blueprint blpr) {
        ArrayList<Point> finales = new ArrayList<Point>();
        for(Point p:blpr.getPoints()){
            if(!compare(finales,p)){
                finales.add(p);
            }
        }
        return finales;
    }
    
    public boolean compare(ArrayList<Point> puntos, Point p){
         for(Point po:puntos){
             if (po.getX()==p.getX() && po.getY()==p.getY()){
                 return true;
             }
         }
         return false;
    }
    
    
    
}
