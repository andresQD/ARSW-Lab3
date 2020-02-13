/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author hcadavid
 */
@Component ("InMemory")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    private final Map<Tuple<String, String>, Blueprint> blueprints = new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts = new Point[]{new Point(140, 140), new Point(115, 115)};
        Blueprint bp = new Blueprint("_authorname_", "_bpname_ ", pts);
        blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);

    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName()))) {
            throw new BlueprintPersistenceException("The given blueprint already exists: " + bp);
        } else {
            blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        if (blueprints.containsKey(new Tuple<>(author, bprintname))) {
            return blueprints.get(new Tuple<>(author, bprintname));

        } else {
            throw new BlueprintNotFoundException("The given blueprint doensn't exists: " + bprintname);

        }
    }

    @Override
    public ArrayList<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        ArrayList<Blueprint> newsBluePrints = new ArrayList<Blueprint>();
        for (Blueprint bp : blueprints.values()) {
            if (bp.getAuthor() == author) {
                newsBluePrints.add(bp);
            }
        }
        if (newsBluePrints.size() == 0) {
            throw new BlueprintNotFoundException("The given Author doesn't exists: " + author);
        } else {
            return newsBluePrints;
        }
    }

    @Override
    public ArrayList<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
        ArrayList<Blueprint> newsBluePrints = new ArrayList<Blueprint>();
        for (Blueprint bp : blueprints.values()) {

            newsBluePrints.add(bp);

        }
        if (newsBluePrints.size() == 0) {
            throw new BlueprintNotFoundException("Theres not blueprints " );
        } else {
            return newsBluePrints;
        }
    }

}
