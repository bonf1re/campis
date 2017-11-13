/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models.utils;

import campis.dp1.models.CGraph;
import campis.dp1.models.Coord;
import campis.dp1.models.Coordinates;
import campis.dp1.models.routing.RouteGen;
import campis.dp1.models.routing.Route;
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

    
    
    public ArrayList<Coordinates> toCoordinates(ArrayList<Coord> products) {
        ArrayList<Coordinates> returnable = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Coord aux = products.get(i);
            returnable.add(new Coordinates(aux.x,aux.y));
        }
        return returnable;
    }

    public ArrayList<Coord> getRoute(ArrayList<Coordinates> order, RouteGen routeGen) {
        ArrayList<Coord> returnable =  new ArrayList<>();
        ArrayList<Route> routes = new ArrayList<>(routeGen.getRoutes(order));
        for (int i = 0; i < routes.size(); i++) {
            Route route = routes.get(i);
            ArrayList<Coord> paths = route.getPaths();
            for (int j = 0; j < paths.size(); j++) {
                returnable.add(new Coord(paths.get(j)));
            }
        }
        return returnable;
    }
        
}
