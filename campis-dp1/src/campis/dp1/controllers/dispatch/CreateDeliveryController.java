/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.dispatch;

import campis.dp1.Main;
import campis.dp1.models.DispatchOrder;
import campis.dp1.models.Delivery;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author Gina Bustamante
 */
public class CreateDeliveryController implements Initializable {
    private Main main;
    
    @FXML
    private JFXTextField addressField;
    @FXML
    private JFXTextField vehicleField;
    
    @FXML
    private Label addressMessage;
    @FXML
    private Label vehicleMessage;
    @FXML
    private Label do_message;
    
    @FXML
    private JFXComboBox<String> dipatchOrderCb;
    
    private ObservableList<DispatchOrder> dispatchOrders;
    private Integer selected_do;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        setUpDipatchOrderCb();
        this.selected_do = 0;
        
         dipatchOrderCb.getSelectionModel().selectedItemProperty().addListener( (ObservableValue<? extends String> options, String oldValue, String newValue) -> {
            System.out.println(newValue);
            this.selected_do = Integer.parseInt(newValue);         
        }); 
    }
    
    @FXML
    private void setUpDipatchOrderCb() {
       this.dispatchOrders = getDispatchOrders();

        System.out.println("campis.dp1.controllers.dispatch.CreateDeliveryController.setUpDipatchOrderCb()");
        System.out.println(dispatchOrders.size());
        
       for (int i = 0; i < dispatchOrders.size(); i++) {
           dipatchOrderCb.getItems().add(this.dispatchOrders.get(i).getId_dispatch_order().toString());
       }

    }
    
     private ObservableList<DispatchOrder> getDispatchOrders() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(DispatchOrder.class);
        criteria.add(Restrictions.eq("status", "POR DESPACHAR"));
        
        ObservableList<DispatchOrder> returnable = FXCollections.observableArrayList();
        
        List list = criteria.list();
        
        for (int i = 0; i < list.size(); i++) {
            returnable.add((DispatchOrder) list.get(i));
        }
        
        session.close();
        sessionFactory.close();
        return returnable;
    }
     
    public boolean validation() {

        boolean addressValid = addressField.getText().length() == 0;
        boolean vhValid = vehicleField.getText().length() == 0;
        boolean doValid = this.selected_do == 0;

        
        addressMessage.setText("");
        vehicleMessage.setText("");
        do_message.setText("");

        if (addressValid) {
            addressMessage.setText("Campo obligatorio");
        }

        if (vhValid) {
            vehicleMessage.setText("Campo obligatorio");
        }
        
        if (doValid) {
            do_message.setText("Campo obligatorio");
        }

        return (!addressValid && !vhValid && !doValid);
    }

    @FXML
    private void insertDelivery(ActionEvent event) throws IOException {
        if (validation()) {
            Delivery d = new Delivery(addressField.getText(), vehicleField.getText(), this.selected_do);
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String qryStr = "INSERT INTO campis.delivery VALUES(DEFAULT,'" + d.getLocation()+ "',"
                    + "'"+d.getVh_plate()+"',"
                    + "'"+d.getId_dispatch_order()+"')";
            SQLQuery qry = session.createSQLQuery(qryStr);
            qry.executeUpdate();
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            this.goDeliveryList();
        }
    }

    @FXML
    private void goDeliveryList() throws IOException {
        main.showDeliveryList();
    }
    
}
