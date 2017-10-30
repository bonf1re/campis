/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class CNode {
    Coord ubication = new Coord();
    ArrayList<Coord> paths=new ArrayList<>();
    
    public CNode(){
      super();
    }
    
    public CNode(Coord coord){
        this.ubication = new Coord(coord.y,coord.x);
    }
    
    public void addPath(Coord coord){
        this.paths.add(coord);
    }
    
    public void delPath(Coord coord){
        for (Coord path : paths) {
            if ((path.y == coord.y) && (path.x == coord.x)){
                this.paths.remove(path);
            }
        }
    }
    
    public Coord getPos(){
        return new Coord(this.ubication.y,this.ubication.x);
    }
}
