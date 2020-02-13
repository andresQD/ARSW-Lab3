/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.RedundancyFiltering;
import edu.eci.arsw.blueprints.persistence.SubsamplingFiltering;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {

    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts0 = new Point[]{new Point(40, 40), new Point(15, 15)};
        Blueprint bp0 = new Blueprint("mack", "mypaint", pts0);

        ibpp.saveBlueprint(bp0);

        Point[] pts = new Point[]{new Point(0, 0), new Point(10, 10)};
        Blueprint bp = new Blueprint("john", "thepaint", pts);

        ibpp.saveBlueprint(bp);

        assertNotNull("Loading a previously stored blueprint returned null.", ibpp.getBlueprint(bp.getAuthor(), bp.getName()));

        assertEquals("Loading a previously stored blueprint returned a different blueprint.", ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);

    }

    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts = new Point[]{new Point(0, 0), new Point(10, 10)};
        Blueprint bp = new Blueprint("john", "thepaint", pts);

        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }

        Point[] pts2 = new Point[]{new Point(10, 10), new Point(20, 20)};
        Blueprint bp2 = new Blueprint("john", "thepaint", pts2);

        try {
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        } catch (BlueprintPersistenceException ex) {

        }

    }

    @Test
    public void Blueprins() {
        InMemoryBlueprintPersistence nimbp = new InMemoryBlueprintPersistence();

        Blueprint nbp = new Blueprint("Andres", "ISIS");
        Blueprint nbp1 = new Blueprint("Johan", "ICIV");
        Blueprint nbp2 = new Blueprint("Nicolas", "IIND");
        Blueprint nbp3 = new Blueprint("Felipe", "IMEC");
        try {
            nimbp.saveBlueprint(nbp);
            nimbp.saveBlueprint(nbp1);
            nimbp.saveBlueprint(nbp2);
            nimbp.saveBlueprint(nbp3);
            Blueprint nbp4 = nimbp.getBlueprint("Andres", "ISIS");
            assertEquals(nbp4.getAuthor(), "Andres");
            Blueprint nbp5 = nimbp.getBlueprint("Felipe", "IMEC");
            assertEquals(nbp5.getAuthor(), "Felipe");
            Blueprint nbp6 = nimbp.getBlueprint("Nicolas", "IIND");
            assertEquals(nbp6.getName(), "IIND");
        } catch (BlueprintNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BlueprintPersistenceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void BlueprintsAuthorSpecific() {
        InMemoryBlueprintPersistence nibp = new InMemoryBlueprintPersistence();

        Blueprint nbp = new Blueprint("Andres", "ISIS");
        Blueprint nbp1 = new Blueprint("Nicolas", "ICIV");
        Blueprint nbp2 = new Blueprint("Nicolas", "IIND");
        Blueprint nbp3 = new Blueprint("Felipe", "IMEC");
        try {
            nibp.saveBlueprint(nbp);
            nibp.saveBlueprint(nbp1);
            nibp.saveBlueprint(nbp2);
            nibp.saveBlueprint(nbp3);
            assertEquals((nibp.getBlueprintsByAuthor("Andres")).size(), 1);
            assertEquals((nibp.getBlueprintsByAuthor("Nicolas")).size(), 2);
        } catch (BlueprintNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BlueprintPersistenceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
     @Test
    public void allBlueprints() {
        InMemoryBlueprintPersistence nibp = new InMemoryBlueprintPersistence();

        Blueprint nbp = new Blueprint("Andres", "ISIS");
        Blueprint nbp1 = new Blueprint("Nicolas", "ICIV");
        Blueprint nbp2 = new Blueprint("Nicolas", "IIND");
        Blueprint nbp3 = new Blueprint("Felipe", "IMEC");
        try {
            nibp.saveBlueprint(nbp);
            nibp.saveBlueprint(nbp1);
            nibp.saveBlueprint(nbp2);
            nibp.saveBlueprint(nbp3);
            assertEquals((nibp.getAllBlueprints()).size(), 5);
        } catch (BlueprintNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BlueprintPersistenceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    public void redundancyFilteringTest() {
        RedundancyFiltering rF = new RedundancyFiltering();
        Point[] pts0 = new Point[]{new Point(1, 1), new Point(1, 1), new Point(1, 1)};
        Blueprint nbp = new Blueprint("Andres", "ISIS",pts0);
        ArrayList<Point> obtenido = rF.filter(nbp);
        ArrayList<Point> verdadera = new ArrayList<Point>();
        verdadera.add(new Point(1,1));
        assertEquals(obtenido.size(), verdadera.size());
    }
      
    @Test
    public void SubsamplingFilteringTest() {
        SubsamplingFiltering rF = new SubsamplingFiltering();
        Point[] pts0 = new Point[]{new Point(1, 2), new Point(2, 1), new Point(3, 1)};
        Blueprint nbp = new Blueprint("Andres", "ISIS",pts0);
        ArrayList<Point> obtenido = rF.filter(nbp);
        ArrayList<Point> verdadera = new ArrayList<Point>();
        verdadera.add(new Point(1,2));
        verdadera.add(new Point(2,1));
        assertEquals(obtenido.size(), verdadera.size());
    }
    

}
