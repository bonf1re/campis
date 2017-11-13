/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.requestOrder;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Client;
import campis.dp1.models.RequestDisplay;
import campis.dp1.models.RequestOrder;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Boolean.TRUE;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Eddy
 */
public class ListController implements Initializable{
    
    private Main main;
    private ObservableList<RequestOrder> requestList;
    private ObservableList<RequestDisplay> requestView;
    String name;
    private int selected_id;
    
    @FXML
    private JFXTextField searchField;
    @FXML
    private TableView<RequestDisplay> requestTable;
    @FXML
    private TableColumn<RequestDisplay, Integer> codColumn;
    @FXML
    private TableColumn<RequestDisplay, String> clientColumn;
    @FXML
    private TableColumn<RequestDisplay, Float> amountColumn;
    @FXML
    private TableColumn<RequestDisplay, String> stateColumn;
    @FXML
    private TableColumn<RequestDisplay, Integer> priorityColumn;
    
    @FXML
    private void goCreateRequestOrder() throws IOException {
        main.showCreateRequestOrder();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.selected_id = 0;
        requestTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
          if(newValue == null) {
              return;
          }
          this.selected_id = newValue.idRequestProperty().getValue().intValue();
        });
        try {
        codColumn.setCellValueFactory(cellData -> cellData.getValue().idRequestProperty().asObject());
        clientColumn.setCellValueFactory(cellData -> cellData.getValue().nomClient());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().totalAmountProperty().asObject());
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        priorityColumn.setCellValueFactory(cellData -> cellData.getValue().priority().asObject());
        loadData();
        } catch(NullPointerException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadData() {
        requestList = FXCollections.observableArrayList();
        requestView = FXCollections.observableArrayList();
        requestList = getRequests();
        for (int i = 0; i < requestList.size(); i++) {
            name = getName(requestList.get(i).getId_client());
            RequestDisplay request = new RequestDisplay(requestList.get(i).getId_request_order(), 
                                    name, requestList.get(i).getTotal_amount(), 
                                    requestList.get(i).getStatus(),requestList.get(i).getPriority());
            requestView.add(request);
        }
        requestTable.setItems(null);
        requestTable.setItems(requestView);
    }

    private ObservableList<RequestOrder> getRequests() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RequestOrder.class);
        List list = criteria.list();
        ObservableList<RequestOrder> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            returnable.add((RequestOrder) list.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private String getName(Integer id_client) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Client.class);
        criteria.add(Restrictions.eq("id_client",id_client));
        String nameCli;
        List rsName = criteria.list();
        Client result = (Client) rsName.get(0);
        nameCli = result.getName();
        session.close();
        sessionFactory.close();
        return nameCli;
    }
    
    @FXML
    private void goEditRequest(ActionEvent event) throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showEditRequestOrder();
        }
    }
    
    @FXML
    private void goViewRequest(ActionEvent event) throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showViewRequest();
        }
    }
    
    @FXML
    private void deleteRequest(ActionEvent event) {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            Integer id_request = ContextFX.getInstance().getId();
            deleteRequest(id_request);
            for (int i = 0; i < requestList.size(); i++) {
                if(requestList.get(i).getId_request_order().compareTo(id_request) == 0){
                    requestList.remove(i);
                }
            }
            loadData();
        }
    }

    private void deleteRequest(int id_request) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RequestOrder.class);
        RequestOrder request = new RequestOrder();
        request.setId_request_order(id_request);
        session.delete(request);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
    
    @FXML
    private void searchRequest(ActionEvent event) {
        String text = this.searchField.getText();
        if(text.compareTo("") == 0) {
            loadData();
        } else {
            requestList = FXCollections.observableArrayList();
            requestView = FXCollections.observableArrayList();
            requestList = getSearchList(text);
            if(requestList == null){
                requestTable.setItems(null);
            } else {
                for (int i = 0; i < requestList.size(); i++) {
                    name = getName(requestList.get(i).getId_client());
                    RequestDisplay request = new RequestDisplay(requestList.get(i).getId_request_order(), 
                                    name, requestList.get(i).getTotal_amount(), 
                                    requestList.get(i).getStatus(),requestList.get(i).getPriority());
                    requestView.add(request);
                }
            }
            requestTable.setItems(null);
            requestTable.setItems(requestView);
        }
    }

    private ObservableList<RequestOrder> getSearchList(String text) {
        ObservableList<RequestOrder> returnable;
        returnable = FXCollections.observableArrayList();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RequestOrder.class);
        List<RequestOrder> list = criteria.list();
        for (int i = 0; i < list.size(); i++) {
            name = getName(list.get(i).getId_client());
            if(name.contains(text) == TRUE) {
                returnable.add(list.get(i));
            }
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }
}
