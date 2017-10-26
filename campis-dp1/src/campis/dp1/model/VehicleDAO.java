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
 * @author david
 */
public class VehicleDAO {
     public static Vehicle searchVehicle (String idVehicle) throws SQLException, ClassNotFoundException {
         String selectStmt = "SELECT v.id_vehicle, v.max_weight, v.speed, v.active, w.name "+
                             "FROM vehicle v, warehouse w "+
                             "WHERE v.id_vehicle = "+idVehicle+" AND "+
                                   "w.id_warehouse = v.id_warehouse";
  
         //Execute SELECT statement
         try {
             //Get ResultSet from dbExecuteQuery method
             ResultSet rsVehicle = DBUtil.dbExecuteQuery(selectStmt);
  
             //Send ResultSet to the getVehicleFromResultSet method and get role object
             Vehicle vehicle = getVehicleFromResultSet(rsVehicle);
  
             //Return vehicle object
             return vehicle;
         } catch (SQLException e) {
             System.out.println(e);
             //Return exception
             throw e;
         }
     }
     
     private static Vehicle getVehicleFromResultSet(ResultSet rs) throws SQLException
     {
         Vehicle vehicle = null;
         if (rs.next()) {
            vehicle = new Vehicle();
            vehicle.setIdVehicle(rs.getInt("id_vehicle"));
            vehicle.setMaxWeight(rs.getFloat("max_weight"));
            vehicle.setMaxSpeed(rs.getInt("speed"));
            vehicle.setActive(rs.getBoolean("active"));
            vehicle.setWarehouse(rs.getString("warehouse"));
         }
         return vehicle;
     }
     
     public static ObservableList<Vehicle> searchVehicles () throws SQLException, ClassNotFoundException {
         //Declare a SELECT statement
         String selectStmt = "SELECT v.id_vehicle, v.max_weight, v.speed, v.active, w.name "+
                             "FROM vehicle v, warehouse w where w.id_warehouse = v.id_warehouse;";
  
         //Execute SELECT statement
         try {
             //Get ResultSet from dbExecuteQuery method
             ResultSet rsVehicle = DBUtil.dbExecuteQuery(selectStmt);
  
             //Send ResultSet to the getVehicleList method and get vehicle object
             ObservableList<Vehicle> vehicleList = getVehicleList(rsVehicle);
  
             //Return vehicle object
             return vehicleList;
         } catch (SQLException e) {
             System.out.println("SQL select operation has been failed: " + e);
             //Return exception
             throw e;
         }
     }
 
     
     private static ObservableList<Vehicle> getVehicleList(ResultSet rs) throws SQLException, ClassNotFoundException {
         //Declare a observable List which comprises of Vehicle objects
         ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();
 
         while (rs.next()) {
             Vehicle vehicle = new Vehicle();
             vehicle.setIdVehicle(rs.getInt("id_vehicle"));
             vehicle.setMaxWeight(rs.getFloat("max_weight"));
             vehicle.setMaxSpeed(rs.getInt("speed"));
             vehicle.setActive(rs.getBoolean("active"));
             vehicle.setWarehouse(rs.getString("warehouse"));
 
             vehicleList.add(vehicle);
         }
 
         return vehicleList;
     }
 
     public static void insertVehicle (String maxWeight, String maxSpeed, String active, String idWarehouse) throws SQLException, ClassNotFoundException {
         
         String updateStmt =
                         "INSERT INTO campis.vehicle " +
                         "(max_weight, speed, active, id_warehouse) " +
                         "VALUES " +
                         "( "+maxWeight+","+maxSpeed+","+active+","+idWarehouse+" );";
 
         
         try {
             DBUtil.dbExecuteUpdate(updateStmt);
         } catch (SQLException e) {
             System.out.print("Error occurred while INSERT Operation: " + e);
             throw e;
         }
     }
 
}
