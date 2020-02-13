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
@Component ("Subsampling")
public class SubsamplingFiltering implements Filter {

    public SubsamplingFiltering() {
    }
    
    @Override
    public ArrayList<Point> filter(Blueprint blpr) {
        ArrayList<Point> finales = new ArrayList<Point>();
        int cont = 1;
        for(Point p:blpr.getPoints()){
            if(cont%3!=0){
                finales.add(p);
            }
            cont++;
        }
        return finales;
        
    }
    
}
