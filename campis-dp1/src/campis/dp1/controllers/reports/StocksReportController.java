/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.reports;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.ReportKardexDisplay;
import campis.dp1.models.ReportStocksDisplay;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author david
 */
public class StocksReportController implements Initializable {
    
    
    Main main;
    private ObservableList<ReportStocksDisplay> stocksView;
    
    @FXML
    private TableView<ReportStocksDisplay> stocksTable;

    @FXML
    private TableColumn<ReportStocksDisplay, String> productColumn;

    @FXML
    private TableColumn<ReportStocksDisplay, String> unitColumn;

    @FXML
    private TableColumn<ReportStocksDisplay, String> typeColumn;

    @FXML
    private TableColumn<ReportStocksDisplay, Integer> scoColumn;

    @FXML
    private TableColumn<ReportStocksDisplay, Integer> sphColumn;

    @FXML
    private TableColumn<ReportStocksDisplay, String> whColumn;

    
    
    
    @FXML
    private Label lbltype;

    @FXML
    private Label lblWh;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbltype.setText(ContextFX.getInstance().getLabeltoPrint1());
        lblWh.setText(ContextFX.getInstance().getLabeltoPrint2());
        
        try {
            productColumn.setCellValueFactory(cellData -> cellData.getValue().getProduct());
            unitColumn.setCellValueFactory(cellData -> cellData.getValue().getUnitM());
            typeColumn.setCellValueFactory(cellData -> cellData.getValue().getProductType());
            scoColumn.setCellValueFactory(cellData -> cellData.getValue().getCstock().asObject());
            sphColumn.setCellValueFactory(cellData -> cellData.getValue().getPstock().asObject());
            whColumn.setCellValueFactory(cellData -> cellData.getValue().getWarehouse());
            
            /**/
            
            cargarData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(KardexReportController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }

    public void cargarData() throws SQLException, ClassNotFoundException {
        Integer typeP = ContextFX.getInstance().getId_type();
        Integer wh = ContextFX.getInstance().getId_objective();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        String queryStr = "select p.name as product_name,\n" +
                            "       u.description as unit_name,\n" +
                            "       tp.description as type_name,\n" +
                            "       pw.c_stock,\n" +
                            "       pw.p_stock,\n" +
                            "       w.name as warehouse_name\n" +
                            "from campis.productxwarehouse pw\n" +
                            "        INNER JOIN campis.product p on p.id_product = pw.id_product\n" +
                            "        INNER JOIN campis.unit_of_measure u on u.id_unit_of_measure = p.id_unit_of_measure \n" +
                            "        INNER JOIN campis.product_type tp on tp.id_product_type = p.id_product_type \n" +
                            "        INNER JOIN campis.warehouse w on w.id_warehouse = pw.id_warehouse \n" +
                            " WHERE 1 = 1 \n";    
       if (typeP != 0){
           queryStr += " AND p.id_product_type = " + typeP.toString();
       }
       if (wh != 0 ){         
           queryStr += " AND pw.id_warehouse = " + wh.toString();
       }
       
        queryStr += " ORDER by c_stock desc ";
        
        
        SQLQuery query = session.createSQLQuery(queryStr);
        List<Object[]> rows = query.list();
        
        session.close();
        sessionFactory.close();
        
        stocksView = FXCollections.observableArrayList();
        for(Object[] row : rows){
            ReportStocksDisplay r = new ReportStocksDisplay(row[0].toString(), row[1].toString(),row[2].toString(),
                                                            Integer.parseInt(row[3].toString()),Integer.parseInt(row[4].toString())
                                                            ,row[5].toString());
            stocksView.add(r);
        }
        
        stocksTable.setItems(null);
        stocksTable.setItems(stocksView);
        
    }
    
    @FXML
    private void goStocksConfig(ActionEvent event) throws IOException {
        main.showStocksReportConf();
    }
    
}
