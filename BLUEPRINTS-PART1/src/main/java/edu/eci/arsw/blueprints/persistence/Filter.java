/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import java.util.ArrayList;

/**
 *
 * @author Andrés Quintero
 */
public interface Filter {
    
    public ArrayList<Point> filter(Blueprint blpr);
    
}
