/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.reports;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Batch;
import campis.dp1.models.ReportExpirationDisplay;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author david
 */
public class ExpirationReportController implements Initializable {
    Main main;
    private ObservableList<Batch> batchs;
    private ObservableList<ReportExpirationDisplay> expirationView;

     @FXML
    private TableView<ReportExpirationDisplay> expirationTable;

    @FXML
    private TableColumn<ReportExpirationDisplay, String> dateColumn;

    @FXML
    private TableColumn<ReportExpirationDisplay, String> productColumn;

    @FXML
    private TableColumn<ReportExpirationDisplay, String> unitColumn;

    @FXML
    private TableColumn<ReportExpirationDisplay, Integer> quanColumn;

    @FXML
    private TableColumn<ReportExpirationDisplay, String> locationColumn;

    @FXML
    private TableColumn<ReportExpirationDisplay, String> movtypeColumn;

    @FXML
    private Label lblEnd;

    @FXML
    private Label lblInit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblInit.setText(ContextFX.getInstance().getInit_date().toString());
        lblEnd.setText(ContextFX.getInstance().getEnd_date().toString());
        
        try {
            dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDate());
            productColumn.setCellValueFactory(cellData -> cellData.getValue().getProduct());
            unitColumn.setCellValueFactory(cellData -> cellData.getValue().getUnit());
            quanColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
            locationColumn.setCellValueFactory(cellData -> cellData.getValue().getLocation());            
            /**/
            
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(KardexReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ObservableList<Batch> getBatchs() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Batch.class);
        SimpleDateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        criteria.add(Restrictions.between("expiration_date", ContextFX.getInstance().getInit_date(), ContextFX.getInstance().getEnd_date())); 
        List lista = criteria.list();
        ObservableList<Batch> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((Batch)lista.get(i));
        }
        session.close();
        sessionFactory.close();

        return returnable;
    }
    
    public void loadData() throws SQLException, ClassNotFoundException {
        batchs = FXCollections.observableArrayList();
        expirationView = FXCollections.observableArrayList();
        batchs = getBatchs();
        for (int i = 0; i < batchs.size(); i++) {
            ReportExpirationDisplay complaint = new ReportExpirationDisplay(batchs.get(i).getExpiration_date().toString(), batchs.get(i).getId_product(), batchs.get(i).getQuantity(), batchs.get(i).getLocation());
            expirationView.add(complaint);
        }
        expirationTable.setItems(null);
        expirationTable.setItems(expirationView);
    }
    
    @FXML
    private void goExpirationConfig(ActionEvent event) throws IOException {
        main.showExpirationReportConf();
    }
}