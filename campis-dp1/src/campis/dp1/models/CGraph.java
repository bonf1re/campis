/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sergio
 */
public class CGraph {
    private Map<String,CNode> nodes = new HashMap<>();
    
    public void addNode(CNode cnode){
        String key="[ "+cnode.getPos().y+", "+cnode.getPos().x+"]";
        this.nodes.put(key, cnode);
    }
    
    public CNode getNode(String key){
        return this.nodes.get(key);
    }
    
    public CNode getNode(int y, int x){
        String key="[ "+y+", "+x+"]";
        return this.nodes.get(key);
    }
    
    public void delNode(String key){
        // del all occurrences of node in 
        int y = this.nodes.get(key).ubication.y;
        int x = this.nodes.get(key).ubication.x;
        for (String key_ : this.nodes.keySet()){
            CNode aux = this.nodes.get(key_);
            aux.delPath(new Coord(y,x));
        }
        // final step
        this.nodes.remove(key);
    }

    public void setup() {
        System.out.println("This method will configure the paths between nodes");
    }
}
