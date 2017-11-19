/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.suppliers;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Client;
import campis.dp1.models.ClientDisplay;
import campis.dp1.models.Supplier;
import campis.dp1.models.SupplierDisplay;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Eddy
 */
public class ListController implements Initializable {

    private Main main;
    private ObservableList<Supplier> suppliers;
    private ObservableList<SupplierDisplay> suppliersView;
    private int selected_id;

    @FXML
    private JFXTextField searchField;
    @FXML
    private TableView<SupplierDisplay> tableSupplier;
    @FXML
    private TableColumn<SupplierDisplay, String> nameCol;
    @FXML
    private TableColumn<SupplierDisplay, String> rucCol;
    @FXML
    private TableColumn<SupplierDisplay, String> addressCol;
    @FXML
    private TableColumn<SupplierDisplay, String> phoneCol;
    @FXML
    private TableColumn<SupplierDisplay, String> emailCol;

    private ObservableList<Supplier> getSearchList(String text) {
        ObservableList<Supplier> returnable;
        returnable = FXCollections.observableArrayList();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT * FROM campis.supplier;";
        SQLQuery qry = session.createSQLQuery(qryStr);
        List<Object[]> rows = qry.list();
        for (Object[] row : rows) {
            Supplier sup = new Supplier(Integer.parseInt(row[0].toString()),row[1].toString(),
                                row[2].toString(),row[3].toString(),row[4].toString(),row[5].toString());
            if (sup.getName().contains(text) == TRUE) {
                returnable.add(sup);
            }
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }

    @FXML
    private void searchSupplier(ActionEvent event) throws SQLException, ClassNotFoundException {
        String text = this.searchField.getText();
        if (text.compareTo("") == 0) {
            loadData();
        } else {
            suppliers = FXCollections.observableArrayList();
            suppliersView = FXCollections.observableArrayList();
            suppliers = getSearchList(text);
            if (suppliers == null) {
                tableSupplier.setItems(null);
            } else {
                for (int i = 0; i < suppliers.size(); i++) {
                    SupplierDisplay supplier = new SupplierDisplay(suppliers.get(i).getId_supplier(), suppliers.get(i).getName(),
                            suppliers.get(i).getRuc(), suppliers.get(i).getAddress(),
                            suppliers.get(i).getEmail(), suppliers.get(i).getPhone());
                    suppliersView.add(supplier);
                }
            }
            tableSupplier.setItems(null);
            tableSupplier.setItems(suppliersView);
        }
    }

    @FXML
    private void goCreateSupplier(ActionEvent event) throws IOException {
        main.showSupplierCreate();
    }

    @FXML
    private void goEditSupplier(ActionEvent event) throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showSupplierEdit();
        }
    }

    private void deleteSupplier(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "DELETE FROM campis.supplier WHERE id_supplier=" + cod;
        SQLQuery qry = session.createSQLQuery(qryStr);
        qry.executeUpdate();
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @FXML
    private void deleteSupplier(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            Integer id_client = ContextFX.getInstance().getId();
            deleteSupplier(selected_id);
            for (int i = 0; i < suppliers.size(); i++) {
                if (suppliers.get(i).getId_supplier().compareTo(id_client) == 0) {
                    suppliers.remove(i);
                }
            }
            loadData();
        }
    }

    @FXML
    private void goShowSupplier(ActionEvent event) throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showSupplierVisualize();
        }
    }

    private ObservableList<Supplier> getSuppliers() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT * FROM campis.supplier;";
        SQLQuery qry = session.createSQLQuery(qryStr);
        List<Object[]> rows = qry.list();
        ObservableList<Supplier> returnable = FXCollections.observableArrayList();
        for (Object[] row : rows) {
            Supplier sup = new Supplier(Integer.parseInt(row[0].toString()),row[1].toString(),
                                row[2].toString(),row[3].toString(),row[4].toString(),row[5].toString());
            returnable.add(sup);
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private void loadData() throws SQLException, ClassNotFoundException {
        suppliers = FXCollections.observableArrayList();
        suppliersView = FXCollections.observableArrayList();
        suppliers = getSuppliers();
        for (int i = 0; i < suppliers.size(); i++) {
            SupplierDisplay supplier = new SupplierDisplay(suppliers.get(i).getId_supplier(), suppliers.get(i).getName(),
                    suppliers.get(i).getRuc(), suppliers.get(i).getAddress(),
                    suppliers.get(i).getEmail(), suppliers.get(i).getPhone());
            suppliersView.add(supplier);
        }
        tableSupplier.setItems(null);
        tableSupplier.setItems(suppliersView);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.selected_id = 0;
        tableSupplier.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    this.selected_id = newValue.getId_supplier().getValue().intValue();
                }
        );
        try {
            nameCol.setCellValueFactory(cellData -> cellData.getValue().getName());
            rucCol.setCellValueFactory(cellData -> cellData.getValue().getRuc());
            phoneCol.setCellValueFactory(cellData -> cellData.getValue().getPhone());
            addressCol.setCellValueFactory(cellData -> cellData.getValue().getAddress());
            emailCol.setCellValueFactory(cellData -> cellData.getValue().getEmail());
            /**/
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
