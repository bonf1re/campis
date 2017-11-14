/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.reports;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.ReportKardexDisplay;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
public class KardexReportController implements Initializable {
    Main main;
    int type;
    private ObservableList<ReportKardexDisplay> kardexView;
    /**
     * Initializes the controller class.
     */
    
     @FXML
    private TableView<ReportKardexDisplay> kardexTable;

    @FXML
    private TableColumn<ReportKardexDisplay, String> dateColumn;

    @FXML
    private TableColumn<ReportKardexDisplay, String> productColumn;

    @FXML
    private TableColumn<ReportKardexDisplay, String> unitColumn;

    @FXML
    private TableColumn<ReportKardexDisplay, Integer> quanColumn;

    @FXML
    private TableColumn<ReportKardexDisplay, String> userColumn;

    @FXML
    private TableColumn<ReportKardexDisplay, String> wareColumn;

    @FXML
    private TableColumn<ReportKardexDisplay, String> locationColumn;

    @FXML
    private TableColumn<ReportKardexDisplay, String> plateColumn;

    @FXML
    private TableColumn<ReportKardexDisplay, String> movtypeColumn;

    @FXML
    private Label lbltype;

    @FXML
    private Label lblEnd;

    @FXML
    private Label lblInit;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type = ContextFX.getInstance().getId_type();
        lblInit.setText(ContextFX.getInstance().getInit_date().toString());
        lblEnd.setText(ContextFX.getInstance().getEnd_date().toString());
        
        switch(type) {
            case 0 :
               lbltype.setText("Todos los productos");
               break; // optional

            case 1 :
               lbltype.setText("tipo " + ContextFX.getInstance().getLabeltoPrint1());
               break; // optional

            
            default : // Optional
                lbltype.setText(ContextFX.getInstance().getLabeltoPrint1());
        }
        
        try {
            dateColumn.setCellValueFactory(cellData -> cellData.getValue().getMov_date());
            productColumn.setCellValueFactory(cellData -> cellData.getValue().getProduct());
            unitColumn.setCellValueFactory(cellData -> cellData.getValue().getUnitM());
            quanColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
            userColumn.setCellValueFactory(cellData -> cellData.getValue().getUser());
            wareColumn.setCellValueFactory(cellData -> cellData.getValue().getWarehouse());
            locationColumn.setCellValueFactory(cellData -> cellData.getValue().getLocation());
            plateColumn.setCellValueFactory(cellData -> cellData.getValue().getPlate());
            movtypeColumn.setCellValueFactory(cellData -> cellData.getValue().getMov_type());
            
            /**/
            
            cargarData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(KardexReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    
    private void cargarData() throws SQLException, ClassNotFoundException {
        Date i = ContextFX.getInstance().getInit_date();
        Date e = ContextFX.getInstance().getEnd_date();
        
        //prep
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        
        String queryStr = ""
                + "WITH movs as (\n" +
                "        SELECT id_movement,mov_date,id_user,quantity,id_vehicle,mov_type,id_warehouse,id_zone,id_batch\n" +
                "        FROM campis.movement\n";
        queryStr +="        WHERE\n" +
                "          mov_date >= '"+i.toGMTString()+"' AND\n" +
                "          mov_date <= '"+e.toGMTString()+"'\n" +
                "), movs_users_products as (\n" +
                "        SELECT \n" +
                "               movs.id_movement,\n" +
                "               movs.mov_date,\n" +
                "               p.name as name_product,\n" +
                "               unid.description as un_description,\n" +
                "               b.id_product as id_product,\n" +
                "               p.id_product_type,\n" +
                "               u.firstname || ' ' || u.lastname as emp_name,\n" +
                "               movs.quantity,\n" +
                "               movs.mov_type\n" +
                "         from movs, campis.product p, campis.unit_of_measure unid, campis.batch b, campis.users u\n" +
                "         where movs.id_batch = b.id_batch and\n" +
                "               b.id_product = p.id_product and\n" +
                "               p.id_unit_of_measure = unid.id_unit_of_measure and\n" +
                "               movs.id_user = u.id_user\n" +
                "), movs_location as (\n" +
                "        SELECT\n" +
                "               movs.id_movement,\n" +
                "               z.pos_x, \n" +
                "               z.pos_y,\n" +
                "               z.pos_z,\n" +
                "               w.name as name_w,\n" +
                "               v.plate\n" +
                "        FROM movs, campis.warehouse w, campis.zone z, campis.vehicle v\n" +
                "        where movs.id_warehouse=w.id_warehouse and\n" +
                "              movs.id_zone = z.id_zone and\n" +
                "              movs.id_vehicle = v.id_vehicle\n" +
                ")\n" +
                "\n" +
                "SELECT\n" +
                "    mup.mov_date,\n" +
                "    mup.name_product,\n" +
                "    mup.un_description,\n" +
                "    mup.quantity,\n" +
                "    mup.emp_name,\n" +
                "    ml.name_w,\n" +
                "    '[' || ml.pos_x || ',' || ml.pos_y || ',' || ml.pos_z || ']' as position,\n" +
                "    ml.plate,\n" +
                "    CASE\n" +
                "        WHEN (mup.mov_type < 2) \n" +
                "                THEN 'Entrada'\n" +
                "        WHEN (mup.mov_type = 3) \n" +
                "                THEN 'Salida a despacho'\n" +
                "                ELSE 'Salida a almacén'\n" +
                "        END AS mov_type\n" +
                "    from movs_users_products mup, movs_location ml\n" +
                "    where mup.id_movement = ml.id_movement\n";
        if (ContextFX.getInstance().getId_type() == 1){
            queryStr += "and mup.id_product_type = " + ContextFX.getInstance().getId_objective().toString();
        }else if (ContextFX.getInstance().getId_type() == 2){
            queryStr += "and mup.id_product = " + ContextFX.getInstance().getId_objective().toString();
        }
        
        queryStr +=" order by mov_date";
        
        
        
        SQLQuery query = session.createSQLQuery(queryStr);
        List<Object[]> rows = query.list();
        
        
        //end
        session.close();
        sessionFactory.close();
        
        kardexView = FXCollections.observableArrayList();
        for(Object[] row : rows){
            ReportKardexDisplay r = new ReportKardexDisplay(row[0].toString(), row[1].toString(),row[2].toString(),
                                                            Integer.parseInt(row[3].toString()),row[4].toString(),row[5].toString(),
                                                            row[6].toString(),row[7].toString(),row[8].toString());
            kardexView.add(r);
        }
        
        kardexTable.setItems(null);
        kardexTable.setItems(kardexView);
    }
    
    @FXML
    private void goKardexConfig(ActionEvent event) throws IOException {
        main.showKardexReportConf();
    }
}
