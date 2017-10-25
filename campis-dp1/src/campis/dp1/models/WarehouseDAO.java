/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

/**
 *
 * @author sergio
 */

import campis.dp1.util.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Marco
 */
public class WarehouseDAO {
    public static Warehouse searchWarehouse (String warehouseId) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM campis.warehouse WHERE id_warehouse="+warehouseId;
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsWarehouse = DBUtil.dbExecuteQuery(selectStmt);
 
            //Send ResultSet to the getwarehouseFromResultSet method and get warehouse object
            Warehouse warehouse = getWarehouseFromResultSet(rsWarehouse);
 
            //Return warehouse object
            return warehouse;
        } catch (SQLException e) {
            System.out.println("While searching a warehouse with " + warehouseId + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
    
    private static Warehouse getWarehouseFromResultSet(ResultSet rs) throws SQLException
    {
        Warehouse warehouse = null;
        if (rs.next()) {
           warehouse = new Warehouse(rs.getInt("id_warehouse"),
                    rs.getString("name"),
                    rs.getInt("length"),
                    rs.getInt("width"),
                    rs.getBoolean("status"));
        }
        return warehouse;
    }
    
    public static ObservableList<Warehouse> searchWarehouses () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM campis.warehouse";
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsWarehouse = DBUtil.dbExecuteQuery(selectStmt);
 
            //Send ResultSet to the getwarehouseList method and get warehouse object
            ObservableList<Warehouse> warehouseList = getWarehouseList(rsWarehouse);
 
            //Return warehouse object
            return warehouseList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    //Select * from warehouses operation
    private static ObservableList<Warehouse> getWarehouseList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Warehouse objects
        ObservableList<Warehouse> warehouseList = FXCollections.observableArrayList();

        while (rs.next()) {
            Warehouse warehouse = new Warehouse(rs.getInt("id_warehouse"),
                    rs.getString("name"),
                    rs.getInt("length"),
                    rs.getInt("width"),
                    rs.getBoolean("status"));

            warehouseList.add(warehouse);
        }

        return warehouseList;
    }

    public static void insertWarehouse (String name, int length, int width, boolean status) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                        "INSERT INTO campis.warehouse\n" +
                        "( name, length, width, status)\n" +
                        "VALUES\n" +
                        "('"+name+"', '"
                        +length+"' , '"
                        +width+"' , '"
                        +status+"');";

        //Execute DELETE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
