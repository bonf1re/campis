/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models.Utils;

import campis.dp1.models.Coord;
import campis.dp1.models.Coordinates;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class RoutingUtils {
    
    public RoutingUtils(){
        super();
    }

    public void printCoordinates(ArrayList<Coordinates> tabuInput) {
        System.out.println();
        for (int i = 0; i < tabuInput.size(); i++) {
            System.out.print(tabuInput.get(i).stringFormat());
            System.out.print("  ");
        }
        System.out.println();
    }
    
    public void printCoords(ArrayList<Coord> tabuInput) {
        System.out.println();
        for (int i = 0; i < tabuInput.size(); i++) {
            tabuInput.get(i).print_c();
        }
        System.out.println();
    }
        
}
