/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.saleConditions;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.SaleCondition;
import campis.dp1.models.SaleConditionDisplay;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author david
 */
public class ListSaleConditionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Main main;
    private ObservableList<SaleCondition> condiciones;
    private ObservableList<SaleConditionDisplay> condicionesView;
    private int selected_id;
    
     @FXML
    private TableView<SaleConditionDisplay> saleCondTable;

    @FXML
    private TableColumn<SaleConditionDisplay, Integer> codColumn;

     @FXML
    private TableColumn<SaleConditionDisplay, String> initialColumn;

    @FXML
    private TableColumn<SaleConditionDisplay, String> endColumn;

    @FXML
    private TableColumn<SaleConditionDisplay, Float> amountColumn;

    @FXML
    private TableColumn<SaleConditionDisplay, String> typeColumn;

    @FXML
    private TableColumn<SaleConditionDisplay, String> totakeColumn;

    @FXML
    private TableColumn<SaleConditionDisplay, Integer> limitColumn;

    @FXML
    private JFXDatePicker pickerInitial;

    @FXML
    private JFXDatePicker pickerFInal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    private void cargarData() throws SQLException, ClassNotFoundException {
        condiciones = FXCollections.observableArrayList();
        condicionesView = FXCollections.observableArrayList();
        condiciones = getSaleConditions();
        //for (int i = 0; i < condiciones.size(); i++) {
        //    SaleConditionDisplay sc = new SaleConditionDisplay(condiciones.get(i).getId_sale_condition(), condiciones.get(i).getInitial_date(),
        //            condiciones.get(i).getFinal_date(), condiciones.get(i).getAmount(), condiciones.get(i).getId_sale_condition_type(),
        //            condiciones.get(i).getId_to_take(),condiciones.get(i).getLimits());
        //    condicionesView.add(sc);
        //}
        saleCondTable.setItems(null);
        saleCondTable.setItems(condicionesView);
    }
    
    private ObservableList<SaleCondition> getSaleConditions() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SaleCondition.class);
        List lista = criteria.list();
        ObservableList<SaleCondition> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((SaleCondition) lista.get(i));
        }
        sessionFactory.close();
        return returnable;
    }
    
    @FXML
    private void goNewSaleCondition(ActionEvent event) throws IOException {
        main.showNewSaleCondition();
    }

    @FXML
    private void goEditSaleCondition(ActionEvent event) throws IOException {
        ContextFX.getInstance().setId(selected_id);
        main.showEditSaleCondition();
    }
    
    @FXML
    private void goViewSaleCondition(ActionEvent event) throws IOException {
        ContextFX.getInstance().setId(selected_id);
        main.showViewSaleCondition();
    }
    
    
    
    private void deleteSaleCondition(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SaleCondition.class);
        SaleCondition sc = new SaleCondition();
        sc.setId_sale_condition(cod);
        session.delete(sc);
        session.getTransaction().commit();

        sessionFactory.close();
    }
    
    
    
    
    @FXML
    private void deleteSaleCondition(ActionEvent event) throws SQLException, ClassNotFoundException {
        ContextFX.getInstance().setId(selected_id);
        Integer id_sale_condition = ContextFX.getInstance().getId();
        deleteSaleCondition(selected_id);
        for (int i = 0; i < condiciones.size(); i++) {
            if(condiciones.get(i).getId_sale_condition().compareTo(id_sale_condition) == 0){
                condiciones.remove(i);
            }
        }
        cargarData();
    }
}
