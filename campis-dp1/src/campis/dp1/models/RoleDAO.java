/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import campis.dp1.util.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Marco
 */
public class RoleDAO {
    public static Role searchRole (String roleId) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM campis.ole WHERE id_role="+roleId;
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRole = DBUtil.dbExecuteQuery(selectStmt);
 
            //Send ResultSet to the getroleFromResultSet method and get role object
            Role role = getRoleFromResultSet(rsRole);
 
            //Return role object
            return role;
        } catch (SQLException e) {
            System.out.println("While searching an role with " + roleId + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
    
    private static Role getRoleFromResultSet(ResultSet rs) throws SQLException
    {
        Role role = null;
        if (rs.next()) {
            role = new Role();
           role.setRoleId(rs.getInt("id_role"));
           role.setDescription(rs.getString("description"));
        }
        return role;
    }
    
    public static ObservableList<Role> searchRoles () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM campis.role";
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRole = DBUtil.dbExecuteQuery(selectStmt);
 
            //Send ResultSet to the getroleList method and get role object
            ObservableList<Role> roleList = getRoleList(rsRole);
 
            //Return role object
            return roleList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    //Select * from roles operation
    private static ObservableList<Role> getRoleList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Role objects
        ObservableList<Role> roleList = FXCollections.observableArrayList();

        while (rs.next()) {
            Role role = new Role();
            role.setRoleId(rs.getInt("id_role"));
            role.setDescription(rs.getString("description"));

            roleList.add(role);
        }

        return roleList;
    }

    public static void insertRole (String description) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                        "INSERT INTO campis.role\n" +
                        "( description)\n" +
                        "VALUES\n" +
                        "('"+description+"');";

        //Execute DELETE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
