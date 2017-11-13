/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models.utils;

import java.util.List;

/**
 *
 * @author sergio
 */
public class ListUtils {
    public ListUtils(){
        super();
    }
    
    public void reverseList(List returnable){
        for (int i = 0, j = returnable.size() -1;i<j; i++) {
            returnable.add(i,returnable.remove(j));
     }
    }
}
