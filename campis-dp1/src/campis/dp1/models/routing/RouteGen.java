/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models.routing;

import campis.dp1.models.CGraph;
import campis.dp1.models.CNode;
import campis.dp1.models.Coord;
import campis.dp1.models.Coordinates;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.lang.String;
/**
 *
 * @author sergio
 */
public class RouteGen {
    private CGraph paths;
    private int[][] map;
    private double alpha = 0.35;
    private Map<String,Route> dict = new HashMap<>();    
    
    public void printDict(){
        ArrayList<String> keys = new ArrayList<String>(dict.keySet());
        for (String key : keys) {
            Route route = this.dict.get(key);
            System.out.println("The dict has this path in the "+key+" pair:");
            for (int i = 0; i < route.getPaths().size(); i++) {
                route.getPaths().get(i).print_c();
            }
            System.out.println();
        }
    }
    
    public Route printChosenOrder(ArrayList<Coordinates> prods){
        Route returnable = new Route();
        System.out.println("The chosen route in Tabu was:");
        for (int i = 0; i < prods.size(); i++) {
            Route route;
            if (i==0){
                route = this.retrieve(new Coord(0,0),
                    (normalize_start(new Coord(prods.get(i).getY(),prods.get(i).getX()))));                
            }else if (i<prods.size()-1){
                route = this.retrieve((normalize_start(new Coord(prods.get(i-1).getY(),prods.get(i-1).getX()))),
                    (normalize_start(new Coord(prods.get(i).getY(),prods.get(i).getX()))));
            }else{
                route = this.retrieve((normalize_start(new Coord(prods.get(i-1).getY(),prods.get(i-1).getX()))),
                    (normalize_start(new Coord(prods.get(i).getY(),prods.get(i).getX()))));
                for (int j = 0; j < route.getPaths().size(); j++) {
                    returnable.addPath(route.getPaths().get(j));
                    route.getPaths().get(j).print_c();
                }
                route = this.retrieve((normalize_start(new Coord(prods.get(i).getY(),prods.get(i).getX()))),
                    new Coord(0,0));
            }
            for (int j = 0; j < route.getPaths().size(); j++) {
                    returnable.addPath(route.getPaths().get(j));
                    route.getPaths().get(j).print_c();
                }
            
        }
        System.out.println();
        return returnable;
    }
    
    public Double calculateTabuRouteCost(ArrayList<Coordinates> prods){
        int cost = 0;
        for (int i = 0; i < prods.size()-1; i++) {
            Route route;
            if (i==0){
                route = this.calculateRoute(new Coord(0,0),
                    normalize_start(new Coord(prods.get(i).getY(),prods.get(i).getX())));                
            }else if (i<prods.size()-1){
                route = this.calculateRoute(normalize_start(new Coord(prods.get(i-1).getY(),prods.get(i-1).getX())),
                    normalize_start(new Coord(prods.get(i).getY(),prods.get(i).getX())));
            }else{
                route = this.calculateRoute(normalize_start(new Coord(prods.get(i-1).getY(),prods.get(i-1).getX())),
                    normalize_start(new Coord(prods.get(i).getY(),prods.get(i).getX())));
                this.addRoute(route);
                cost+=route.getCost();
                route = this.calculateRoute(normalize_start(new Coord(prods.get(i).getY(),prods.get(i).getX())),
                    new Coord(0,0));
            }
            this.addRoute(route);
            cost+=route.getCost();
            
        }
        
        return (double) cost;
    }
    
    public ArrayList<Route> getRoutes(ArrayList<Coordinates> prods){
        ArrayList<Route> returnable = new ArrayList<>();
        for (int i = 0; i < prods.size(); i++) {
            Route route;
            if (i==0){
                route = this.calculateRoute(new Coord(0,0),
                    normalize_start(new Coord(prods.get(i).getY(),prods.get(i).getX())));                
            }else if (i<prods.size()-1){
                route = this.calculateRoute(normalize_start(new Coord(prods.get(i-1).getY(),prods.get(i-1).getX())),
                    normalize_start(new Coord(prods.get(i).getY(),prods.get(i).getX())));
            }else{
                route = this.calculateRoute(normalize_start(new Coord(prods.get(i-1).getY(),prods.get(i-1).getX())),
                    normalize_start(new Coord(prods.get(i).getY(),prods.get(i).getX())));
                returnable.add(route);
                route = this.calculateRoute(normalize_start(new Coord(prods.get(i).getY(),prods.get(i).getX())),
                    new Coord(0,0));
            }
            returnable.add(route);
        }
        return returnable;
    }
    
    
    public RouteGen(int[][] real_map, CGraph routesGraph) {
        this.paths=routesGraph;
        this.map=real_map;
    }
    
    public void insert(Coord p1, Coord p2, Route item){
        String key="[ "+p1.y+", "+p1.x+"] [ "+p2.y+", "+p2.x+"]";
        if (this.retrieve(p1, p2) == null) dict.put(key,item);
        return;
    }

    public Route retrieve(Coord p1, Coord p2){
        // Double retrieve
        String key1="[ "+p1.y+", "+p1.x+"] [ "+p2.y+", "+p2.x+"]";
        String key2="[ "+p2.y+", "+p2.x+"] [ "+p1.y+", "+p1.x+"]";
        if (dict.get(key1)!=null) return dict.get(key1);
        if (dict.get(key2)!=null){
            Route route = new Route(dict.get(key2));
            reverseRoute(route);
            return route;
        }
        return null;
    }


    void addRoute(Route route_chosen) {
        Coord first = route_chosen.getPaths().get(0);
        Coord last = route_chosen.getPaths().get(route_chosen.getPaths().size()-1);
        this.insert(first, last, route_chosen);
    }
    
    
    
    private Coord normalize_start(Coord c_prod){
        Coord retornable = new Coord(c_prod.y,c_prod.x);
        if (c_prod.y==0 && c_prod.x==0) return retornable;
        else if (this.map[c_prod.y][c_prod.x]==0) return retornable;
        else{
            // for loop to search for front
            for (int i = -1; i <2; i++) {
                for (int j = -1; j <2; j++) {
                 try{
                     if ((i!=j) && (i==0 || j==0) && this.map[c_prod.y+i][c_prod.x+j]==0){
                         retornable = new Coord(c_prod.y+i,c_prod.x+j);
                         return retornable;
                     }
                 }catch(Exception e){

                 }
                }
            }
        }
        return retornable;
    }
    
    public Route calculateRoute(Coord c_origin, Coord c_destiny_w){
        return this.calculateRoute(c_origin, c_destiny_w, 0);
    }
    

    public Route calculateRoute(Coord c_origin, Coord c_destiny_w, int opt2_counter) {
        Route real_returnable = new Route();
        Route returnable = new Route();
        Coord c_destiny = normalize_start(c_destiny_w);
        //System.out.println("Normalized coordinate is: [ "+c_destiny.y+", "+c_destiny.x+"].");
        if (this.retrieve(c_origin, c_destiny) != null){
            //System.out.println("dict is working");
            return this.retrieve(c_origin, c_destiny);
        }
        if (c_destiny.y==c_origin.y && c_destiny.x == c_origin.x){
            returnable.addPath(c_origin);
            returnable.addPath(c_destiny);
            return returnable;
        }
        if (frontTofront(c_origin,c_destiny)){
            returnable.addPath(c_origin);
            returnable.addPath(c_destiny);
            return returnable;
        }
        // Will calculate route backwards
        ArrayList<CNode> destiny_nodes= new ArrayList<>();
        ArrayList<CNode> origin_nodes= new ArrayList<>();
        //System.out.println("Origin coordinate is: [ "+c_origin.y+", "+c_origin.x+"] n Destiny coordinate is: [ "+c_destiny.y+", "+c_destiny.x+"].");
        ArrayList<String> keys =  new ArrayList<String>(this.paths.keySet());
        for (int i = 0; i < keys.size(); i++) {
            CNode node = this.paths.getNode(keys.get(i));
            if (frontTofront(node.getPos(),c_destiny)==true){
                destiny_nodes.add(node);
                if (destiny_nodes.size()==2) break;
            }            
        }
        
        for (int i = 0; i < keys.size(); i++) {
            CNode node = this.paths.getNode(keys.get(i));
            if (frontTofront(node.getPos(),c_origin)==true){
                origin_nodes.add(node);
                if (origin_nodes.size()==2) break;
            }            
        }
        int grasp_counter=0;
        while(grasp_counter<50){
            grasp_counter++;
            returnable=new Route();
            Random generator = new Random();
            CNode destiny_corner;
            try{
                destiny_corner = destiny_nodes.get(generator.nextInt(destiny_nodes.size()));
            }catch (Exception e){
                System.out.println("Can't find destiny corners for coord: "+c_destiny.y+", "+c_destiny.x);
                continue;
            }
            
            CNode origin_corner;
            try{
                origin_corner = origin_nodes.get(generator.nextInt(origin_nodes.size()));
            }catch (Exception e){
                System.out.println("Can't find origin corners for coord: "+c_origin.y+", "+c_origin.x);
                continue;
            }
            
            // will use pitagoric distance, only moving between nodes
            returnable.addPath(c_destiny);
            returnable.addPath(destiny_corner.getPos());
            //System.out.println("Entering calculateRoute while loop");
            Coord previous_c = null;
            Boolean exit_loop;
            while (same_coord(destiny_corner.getPos(),origin_corner.getPos())==false){
                exit_loop=false;
                //System.out.print("\nCoords are: ");
                //origin_corner.getPos().print_c();
                //System.out.print(" , ");
                //destiny_corner.getPos().print_c();
                //System.out.println(" .");
                ArrayList<Integer> aux_costs = new ArrayList<>();
                ArrayList<Coord> aux_nodes = new ArrayList<>();
                for (int i = 0; i < destiny_corner.getPaths().size(); i++) {
                    Coord ver_coord = destiny_corner.getPaths().get(i);
                    if (previous_c!=null &&  same_coord(ver_coord,previous_c)) continue;
                    if (same_coord(ver_coord,origin_corner.getPos())){
                         Coord chosen = ver_coord;
                        returnable.addPath(new Coord(chosen));
                        destiny_corner = this.paths.getNode(chosen.y, chosen.x);
                        exit_loop = true;
                        break;
                    }
                    if (previous_c!=null && ver_coord.c_distance(origin_corner.getPos()) >= previous_c.c_distance(origin_corner.getPos()))
                        continue;
                    aux_nodes.add(ver_coord);
                    int cost = ver_coord.c_distance(c_origin);
                    aux_costs.add(cost);
                }
                if (exit_loop==true) break;
                if (aux_nodes.size()==0){
                    // we go backwards one step
                    returnable.getPaths().remove(returnable.getPaths().size()-1);
                    destiny_corner = this.paths.getNode(previous_c.y,previous_c.x);
                    if (returnable.getPaths().size()==0) previous_c=null;
                    else previous_c = new Coord(returnable.getPaths().get(returnable.getPaths().size()-1));
                }else{
                    // sorting per cost
                    sortPerCost(aux_costs,aux_nodes);
                    Coord chosen = aux_nodes.get(generator.nextInt((int) (aux_nodes.size()*alpha+1)));
                    //returnable.increaseCost(chosen.c_distance(returnable.getPaths().get(returnable.getPaths().size()-1)));
                    returnable.addPath(new Coord(chosen));
                    previous_c = new Coord(destiny_corner.getPos());
                    destiny_corner = this.paths.getNode(chosen.y, chosen.x);
                }

            }
            if (same_coord(c_origin, returnable.getPaths().get(returnable.getPaths().size()-1)) == true){
                reverseRoute(returnable);
                //return returnable;
                if ((grasp_counter==1) || (real_returnable.getCost()>returnable.getCost())) real_returnable = new Route(returnable);
                continue;
            }
            //returnable.increaseCost(c_origin.c_distance(returnable.getPaths().get(returnable.getPaths().size()-1)));
            returnable.addPath(c_origin);
            reverseRoute(returnable);
            //return returnable;
            if ((grasp_counter==1) || (real_returnable.getCost()>returnable.getCost())) real_returnable = new Route(returnable);
                continue;
        }
        // OPT 2
        // Check if route order matches the coordinates
        Coord last_c = real_returnable.getPaths().get(real_returnable.getPaths().size()-1);
        Coord first_c = real_returnable.getPaths().get(0);
        if (first_c.c_distance(c_origin) >0 || last_c.c_distance(c_destiny)>0){
            reverseRoute(real_returnable);
        }
        OPT2_coords(real_returnable,opt2_counter);
        
        return real_returnable;
    }
    
      
    
    private void reverseRoute(Route returnable) {
        for (int i = 0, j = returnable.getPaths().size() -1;i<j; i++) {
            returnable.getPaths().add(i,returnable.getPaths().remove(j));
            
     }
    }


    private void sortPerCost(ArrayList<Integer> aux_costs, ArrayList<Coord> aux_nodes) {
        
        for (int i = 0; i < aux_costs.size(); i++) {
            for (int j = 0; j < aux_costs.size(); j++) {
                if (aux_costs.get(i) < aux_costs.get(j) && j<i){
                    // For costs
                    Integer swap = new Integer(aux_costs.get(i));
                    aux_costs.set(i, aux_costs.get(j));
                    aux_costs.set(j, swap);
                    
                    // For coords
                    Coord swap_c = new Coord(aux_nodes.get(i));
                    aux_nodes.set(i, aux_nodes.get(j));
                    aux_nodes.set(j, swap_c);
                }
            }
        }
    }

    private boolean same_coord(Coord c1, Coord c2) {
        return (c1.y == c2.y && c1.x == c2.x);
    }

    private boolean frontTofront(Coord c_origin, Coord c_destiny) {
        if (c_origin.y != c_destiny.y && c_origin.x != c_destiny.x) return false;
        if (c_origin.y == c_destiny.y){
            int x_min;
            int x_max;
            if (c_origin.x>c_destiny.x){
                x_min=c_destiny.x;
                x_max=c_origin.x;
            }else{
                x_min=c_origin.x;
                x_max=c_destiny.x;
            }
            
            for (int i = 1; i <= x_max-x_min; i++) {
                if (this.map[c_origin.y][x_min+i]!=0) return false;
            }
        }else{
            int y_min;
            int y_max;
            if (c_origin.y>c_destiny.y){
                y_min=c_destiny.y;
                y_max=c_origin.y;
            }else{
                y_min=c_origin.y;
                y_max=c_destiny.y;
            }
            for (int i = 1; i <= y_max-y_min; i++) {
                if (this.map[y_min+i][c_origin.x]!=0) return false;
            }
        }
        return true;
    }

    private void OPT2_coords(Route real_returnable, int opt2_counter) {
        if (opt2_counter>3)
            return;
        if (real_returnable.getPaths().size()<4) return;
        
//        int r_step = real_returnable.getPaths().size()/16;
        // For for in for
//        Random r_gen = new Random();
        ArrayList<Coord> route_cp = new ArrayList<>(real_returnable.getPaths());
        for (int i = 0; i <route_cp.size()-4; i+=4) {
            //  --d    b
            //      \/ |
            //     /  \|
            //  --a    c
            // Bc of the dict, here only should be nodes interacting with themselves
            Route a_c=calculateRoute(route_cp.get(i), route_cp.get(i+2),opt2_counter+1);
            Route c_b=calculateRoute(route_cp.get(i+2), route_cp.get(i+1),opt2_counter+1);
            Route b_d=calculateRoute(route_cp.get(i+1), route_cp.get(i+3),opt2_counter+1);
            if ((a_c.getCost()+ 
                 c_b.getCost()+
                 b_d.getCost())
                    
                    <
                    
                    (calculateRoute(route_cp.get(i), route_cp.get(i+1),1+opt2_counter).getCost()+
                    calculateRoute(route_cp.get(i+1), route_cp.get(i+2),1+opt2_counter).getCost()+
                    calculateRoute(route_cp.get(i+2), route_cp.get(i+3),1+opt2_counter).getCost())){
                ArrayList<Coord>  aux_coords = new ArrayList<>();
                aux_coords.addAll(a_c.getPaths());
                aux_coords.addAll(c_b.getPaths());
                aux_coords.addAll(b_d.getPaths());
                int diff =aux_coords.size()-6;
                for (int j = 0; j < 4; j++) {
                    route_cp.remove(i);
                }
                route_cp.addAll(i, aux_coords);
//                i+=diff+r_step+r_gen.nextInt(r_step+1);
                i+=diff;
            }
        }
        
        int r_size = route_cp.size();
        real_returnable = new Route();
        for (int i = 0; i < r_size-1; i++) {
            real_returnable.addPath(route_cp.get(i));
        }
        
    }
}
