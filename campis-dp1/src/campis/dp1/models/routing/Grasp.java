/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models.routing;

import campis.dp1.models.CGraph;
import campis.dp1.models.CNode;
import campis.dp1.models.Coord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author sergio
 */
public class Grasp {
    // private RouteDictionary distances= new RouteDictionary(); Optimization will be implemented later
    private ArrayList<Coord> prodList;
    //private CGraph paths;
    //private int[][] map;
    private double alpha = 0.35;
    private RouteGen routeGen;
    
    public Grasp(int[][] real_map, CGraph routesGraph, ArrayList<Coord> batchesCoords){
        this.routeGen = new RouteGen(real_map, routesGraph);
        this.prodList = batchesCoords;
    }
    
    public GraspResults execute() {
        ArrayList<Coord> returnable = new ArrayList<>();
        ArrayList<Coord> returnable_p = new ArrayList<>();
        ArrayList<Coord> graspList = new ArrayList<>(this.prodList);
        // we start from coord 0,0
        returnable.add(new Coord(0,0));
        while(graspList.size()>0){
            ArrayList<Route>  routes = new ArrayList<>();
            for (int i = 0; i < graspList.size(); i++) {
                // always the last of the returnable route
                Coord c_destiny = graspList.get(i);
                Route g_route = this.routeGen.calculateRoute(returnable.get(returnable.size()-1),c_destiny);
                routes.add(g_route);
            }
            
            // Sort the paths
            sortRoutes(routes,graspList);
            Random generator = new Random();
            int k = generator.nextInt((int)(routes.size()*alpha+1));
            Route route_chosen=routes.remove(k); // Selected route
            this.routeGen.addRoute(route_chosen);
            returnable_p.add(graspList.remove(k)); // Remove from graspList
            for (int i = 0; i < route_chosen.getPaths().size(); i++) {
                returnable.add(route_chosen.getPaths().get(i));
            }
            
            
            if (graspList.size()==0){
                // special case before going out
                Route final_route = this.routeGen.calculateRoute(returnable.get(returnable.size()-1),new Coord(0,0));
                this.routeGen.addRoute(final_route);
                ArrayList<Coord> final_route_coords=final_route.getPaths();
                for (int i = 0; i < final_route_coords.size(); i++) {
                    returnable.add(final_route_coords.get(i));
                }
                break;
            }
        }
        
        return new GraspResults(returnable,returnable_p);
    }
    
    private void sortRoutes(ArrayList<Route> routes, ArrayList<Coord> graspList) {
        for(int i=0; i<routes.size();i++){
            for (int j = 0; j < routes.size(); j++) {
                if (routes.get(i).getCost() < routes.get(j).getCost() && j<1){
                    // swap routes
                    Route swap = new Route(routes.get(i));
                    routes.set(i, routes.get(j));
                    routes.set(j, swap);
                    // swap graspList
                    Coord swap_c = new Coord(graspList.get(i));
                    graspList.set(i, graspList.get(j));
                    graspList.set(j, swap_c);
                }
            }
        }
    }
    
    public RouteGen getRouteGen() {
        return this.routeGen;
    }

    
}
