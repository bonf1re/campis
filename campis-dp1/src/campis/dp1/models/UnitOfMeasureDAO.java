/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import campis.dp1.util.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eddy
 */
public class UnitOfMeasureDAO {
    public static int searchCodMeasure(String measure) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT DISTINCT id_unit_of_measure FROM campis.unit_of_measure WHERE  description=" + measure;
        try {
            ResultSet rsMeasure = DBUtil.dbExecuteQuery(selectStmt);
            int codMeasure = rsMeasure.getRow();
            return codMeasure;
        } catch (SQLException e) {
            System.out.print("No se encuentra la unidad de medida con esa descripcion, error ocurrido: " + e);
            throw e;
        }
    }
    
    private static int getCodMeasureFromResultSet(ResultSet rs) throws SQLException{
        UnitOfMeasure measure = null;
        if(rs.next()){
           measure = new UnitOfMeasure();
           measure.setId_measure(rs.getInt("id_unit_of_measure"));
           measure.setDescrip(rs.getString("description")); 
        }
        return measure.getId_measure();
    }
}
