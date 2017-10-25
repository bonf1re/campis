/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;
import campis.dp1.util.DBUtil;
import java.sql.SQLException;
/**
 *
 * @author Marco
 */
public class UserDAO {
	public static void insertRole (String firstname, String lastname, String username, String email, String password, Integer role_id) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                        "INSERT INTO campis.users\n" +
                        "( firstname, lastname, username, email, password, id_role, created_at, updated_at)\n" +
                        "VALUES\n" +
                        "('"+firstname+"', '"+lastname+"', '"+username+"', '"+email+"', '"+password+"', '"+role_id+"', current_timestamp, current_timestamp);";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
