/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1;

/**
 *
 * @author Eddy
 */
public class ContextFX {
    private final static ContextFX instance = new ContextFX();
    public static ContextFX getInstance(){
        return instance;
    }
    
    Integer id = null;

    public Integer getId() {
        Integer returnable = new Integer(this.id);
        this.id=null;
        return returnable;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
