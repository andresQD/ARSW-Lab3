/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import java.util.ArrayList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Andrés Quintero
 */
public class ProgramBlueprints {

    public static void main(String a[]) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices blueps = ac.getBean(BlueprintsServices.class);
        Point[] pts0 = new Point[]{new Point(1, 1), new Point(1, 1), new Point(1, 1)};
        Point[] pts1 = new Point[]{new Point(1, 2), new Point(2, 1), new Point(3, 1)};
    
        Blueprint nbp = new Blueprint("Andres", "ISIS");
        Blueprint nbp1 = new Blueprint("Nicolas", "ICIV");
        Blueprint nbp2 = new Blueprint("Nicolas", "IIND");
        Blueprint nbp3 = new Blueprint("Felipe", "IMEC");
        Blueprint nbp7 = new Blueprint("Camila", "AYPR", pts0);
        ArrayList<Blueprint> todos;
        ArrayList<Blueprint> todosN;
        ArrayList<Point> puntosC;
    
        try {
            blueps.addNewBlueprint(nbp);
            blueps.addNewBlueprint(nbp1);
            blueps.addNewBlueprint(nbp2);
            blueps.addNewBlueprint(nbp3);
            blueps.addNewBlueprint(nbp7);
            todos = blueps.getAllBlueprints();
            
            for (int i=0;i<todos.size();i++){
                System.out.println(todos.get(i).getName());
            }
            
            Blueprint nbp4 =blueps.getBlueprint("Andres", "ISIS");
            System.out.println(nbp4.getName()+ " " + nbp4.getAuthor());
           
            todosN = blueps.getBlueprintsByAuthor("Nicolas");
            System.out.println("Cantidad de planes de Nicolas: " + todosN.size());
            for (int j=0;j<todosN.size();j++){
                System.out.println(todosN.get(j).getName());
            }
            
            puntosC = blueps.getFilter(nbp7);
            System.out.println(nbp7.getName() + " " + nbp7.getAuthor());
            for (int k=0;k<puntosC.size();k++){
                System.out.println("X: " + puntosC.get(k).getX() + " " + "Y: " + puntosC.get(k).getY());
            }
            
            
           
        } catch (BlueprintNotFoundException ex){
            ex.printStackTrace();   
        }
            
        }

}
