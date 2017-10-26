/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.model;

import campis.dp1.util.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Gina Bustamante
 */
public class RackDAO {
    
    /*Insert a Rack*/
    public static void insertRack(int ncols, int npisos, int x_pos, int y_pos) throws SQLException, ClassNotFoundException {
    
        String insrtStmt = "INSERT INTO campis.rack(id_warehouse,pos_x,pos_y,n_columns,n_floors) VALUES("
                + "1," + x_pos +"," 
                + y_pos + ","
                + ncols + "," 
                + npisos + ");";
         
        try{
            DBUtil.dbExecuteUpdate(insrtStmt);
        }catch(SQLException e){
            System.out.println("Error tratando de insertar un rack: " + e);
        }
    }
    
}
