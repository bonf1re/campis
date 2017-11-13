/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.clients;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Client;
import campis.dp1.models.ClientDisplay;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javafx.scene.control.Button;

/**
 *
 * @author Marco
 */
public class ListController implements Initializable {
    private Main main;
    private ObservableList<Client> clients;
    private ObservableList<ClientDisplay> clientsView;
    private int selected_id;
    private int id_role;

    @FXML
    private TableView<ClientDisplay> tableClient;
    @FXML
    private TableColumn<ClientDisplay,String> nameCol;
    @FXML
    private TableColumn<ClientDisplay,String> rucCol;
    @FXML
    private TableColumn<ClientDisplay,String> phoneCol;
    @FXML
    private TableColumn<ClientDisplay,String> emailCol;
    @FXML
    private Button createButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    @FXML
    private void goCreateClient() throws IOException {
        main.showCreateClient();
    } 

    @FXML
    private void goEditClient() throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showEditClient();
        }
    }

    @FXML
    private void goShowClient(ActionEvent event) throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showViewClient();
        }
    }

    private ObservableList<Client> getClients() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Client.class);
        List lista = criteria.list();
        ObservableList<Client> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((Client)lista.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    } 

    private void loadData() throws SQLException, ClassNotFoundException {
        clients = FXCollections.observableArrayList();
        clientsView = FXCollections.observableArrayList();
        clients = getClients();
        for (int i = 0; i < clients.size(); i++) {
            ClientDisplay client = new ClientDisplay(clients.get(i).getId_client(), clients.get(i).getName(), clients.get(i).getRuc(), clients.get(i).getEmail(), clients.get(i).getPhone());
            clientsView.add(client);
        }
        tableClient.setItems(null);
        tableClient.setItems(clientsView);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selected_id = 0;
        ContextFX.getInstance().modifyValidation(createButton, editButton, deleteButton, id_role, "clients");
        tableClient.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.getId_client().getValue().intValue();
            }
        );
        try {
            nameCol.setCellValueFactory(cellData -> cellData.getValue().getName());
            rucCol.setCellValueFactory(cellData -> cellData.getValue().getRuc());
            phoneCol.setCellValueFactory(cellData -> cellData.getValue().getPhone());
            emailCol.setCellValueFactory(cellData -> cellData.getValue().getEmail());
            /**/
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        private void deleteClient(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Client.class);
        Client prod = new Client();
        prod.setId_client(cod);
        session.delete(prod);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @FXML
    private void deleteClient(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            Integer id_client = ContextFX.getInstance().getId();
            deleteClient(selected_id);
            for (int i = 0; i < clients.size(); i++) {
                if(clients.get(i).getId_client().compareTo(id_client) == 0){
                    clients.remove(i);
                }
            }
            loadData();
        }
    }
}
