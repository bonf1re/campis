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
 * @author Eddy
 */
public class ProductDAO {
    /*Select a Product*/
    public static Product searchProductByCode(String codProd) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM campis.product WHERE id_product =" + codProd;
        try {
            ResultSet rsProd = DBUtil.dbExecuteQuery(selectStmt);
            Product prod = getProductFromResultSet(rsProd);
            return prod;
        } catch (SQLException e) {
            System.out.print("No se encuentra el producto con el codigo " + codProd + " ,error ocurrido: " + e);
            throw e;
        }
    }

    private static Product getProductFromResultSet(ResultSet rs) throws SQLException {
        Product prod = null;
        if (rs.next()) {
            prod = new Product(rs.getString(1), rs.getString(2), rs.getString(3), 
                         rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getString(7), 
                         rs.getFloat(8), rs.getInt(9), rs.getString(10));
        }
        return prod;
    }
    /**************************************************************************/

    /*Select a List of Products*/
    public static ObservableList<Product> getProducts() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM campis.product";
        try {
            ResultSet rsProds = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Product> prodList = getProdList(rsProds);
            return prodList;
        } catch (SQLException e) {
            System.out.println("Lista de productos no encontrado, sentencia Select fallo: " + e);
            throw e;
        }
    }

    private static ObservableList<Product> getProdList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Product> prodList = FXCollections.observableArrayList();
        while (rs.next()) {
            prodList.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), 
                         rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getString(7), 
                         rs.getFloat(8), rs.getInt(9), rs.getString(10)));
        }
        return prodList;
    }
    /**
     * @param name
     * @param description
     * @param weight
     * @param trademark
     * @param price
     * @param id_measure
     * @param id_type
     * @throws java.sql.SQLException*
     * @throws java.lang.ClassNotFoundException***********************************************************************************/
    
    /*Insert a Product*/
    public static void insertProduct(String name, String description, int p_stock, int c_stock,
                                     float weight, String trademark, float price, int id_measure, String id_type) throws SQLException, ClassNotFoundException {
        String insrtStmt = "INSERT INTO campis.product(id_product,name,description,p_stock,c_stock,"
                + "weight,trademark,base_price,id_unit_of_measure,id_product_type) VALUES("
                + "DEFAULT,'" + name +"','" 
                + description + "',"
                + p_stock + "," 
                + c_stock + ","
                + weight + ",'" 
                + trademark + "',"
                + price + ","
                + id_measure+ ",'" 
                + id_type + "');";
        try{
            DBUtil.dbExecuteQuery(insrtStmt);
        }catch(SQLException e){
            System.out.println("Error tratando de insertar un producto: " + e);
        }
    }
    /*******************************************************************************/
    
}
