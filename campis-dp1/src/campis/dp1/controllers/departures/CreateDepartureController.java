/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.departures;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import static campis.dp1.controllers.products.EditController.getMeasure;
import static campis.dp1.controllers.vehicles.CreateVehicleController.getWarehouses;
import campis.dp1.models.Batch;
import campis.dp1.models.BatchDisplay;
import campis.dp1.models.Client;
import campis.dp1.models.DispatchMove;
import campis.dp1.models.Product;
import campis.dp1.models.UnitOfMeasure;
import campis.dp1.models.Warehouse;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class CreateDepartureController implements Initializable {

    private Main main;
    Integer id_selected, selected_id;
    private ObservableList<Batch> batch;
    private ObservableList<BatchDisplay> batchView;
    private ObservableList<BatchDisplay> batchView2 = FXCollections.observableArrayList();

    @FXML
    private TableView<BatchDisplay> departureZoneTable;
    @FXML
    private TableColumn<BatchDisplay, Integer> idBatchColumn;
    @FXML
    private TableColumn<BatchDisplay, String> productColumn;
    @FXML
    private TableColumn<BatchDisplay, Integer> quantityColumn;
    @FXML
    private TableColumn<BatchDisplay, String> measureColumn;
    @FXML
    private TableColumn<BatchDisplay, String> expColumn;
    @FXML
    private TableView<BatchDisplay> batchDepartureTable;
    @FXML
    private TableColumn<BatchDisplay, Integer> idBatchColumn2;
    @FXML
    private TableColumn<BatchDisplay, String> productColumn2;
    @FXML
    private TableColumn<BatchDisplay, Integer> quantityColumn2;
    @FXML
    private TableColumn<BatchDisplay, String> measureColumn2;
    @FXML
    private TableColumn<BatchDisplay, String> expColumn2;
    @FXML
    private JFXCheckBox checkboxClient;
    @FXML
    private JFXCheckBox checkboxZone;
    @FXML
    private JFXComboBox<String> reasonField;
    @FXML
    private JFXComboBox<String> clientField;
    @FXML
    private JFXComboBox<String> zoneField;

    @FXML
    private void goListDepartures() throws IOException {
        main.showListDepartures();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reasonField.getItems().addAll("Rompimiento de Productos", "Producto Vencido", "Para Ventas", "Traslado de Almacén");
        batch = FXCollections.observableArrayList();
        batch = getListBatch();
        /* First Table */
        departureZoneTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    this.id_selected = newValue.getId_batch().getValue().intValue();
                }
        );
        idBatchColumn.setCellValueFactory(cellData -> cellData.getValue().getId_batch().asObject());
        productColumn.setCellValueFactory(cellData -> cellData.getValue().getLocation());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
        measureColumn.setCellValueFactory(cellData -> cellData.getValue().getHeritage());
        expColumn.setCellValueFactory(cellData -> cellData.getValue().getExpiration_date());
        /* Second Table */
        batchDepartureTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    this.selected_id = newValue.getId_batch().getValue().intValue();
                }
        );
        idBatchColumn2.setCellValueFactory(cellData -> cellData.getValue().getId_batch().asObject());
        productColumn2.setCellValueFactory(cellData -> cellData.getValue().getLocation());
        quantityColumn2.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
        measureColumn2.setCellValueFactory(cellData -> cellData.getValue().getHeritage());
        expColumn2.setCellValueFactory(cellData -> cellData.getValue().getExpiration_date());
        loadData(batch);

    }

    private ObservableList<Batch> getListBatch() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Batch.class);
        ObservableList<Batch> returnable = FXCollections.observableArrayList();
        List list = criteria.list();
        for (int i = 0; i < list.size(); i++) {
            returnable.add((Batch) list.get(i));
        }
        return returnable;
    }

    private void loadData(ObservableList<Batch> batch) {
        batchView = FXCollections.observableArrayList();
        for (int i = 0; i < batch.size(); i++) {
            String nameProd = getNameProd(batch.get(i).getId_product());
            int meas = getIntMeasure(batch.get(i).getId_product());
            String measure = getMeasure(meas);
            BatchDisplay batchDisp = new BatchDisplay(batch.get(i).getId_batch(), batch.get(i).getQuantity(),
                    batch.get(i).getBatch_cost(), batch.get(i).getArrival_date().toString(),
                    batch.get(i).getExpiration_date().toString(), batch.get(i).getId_product(),
                    batch.get(i).getType_batch(), nameProd,
                    Boolean.toString(batch.get(i).isState()), measure);
            batchView.add(batchDisp);
        }
        departureZoneTable.setItems(null);
        departureZoneTable.setItems(batchView);
    }

    private String getNameProd(int id_product) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product", id_product));
        List list = criteria.list();
        Product prod = (Product) list.get(0);
        String returnable = prod.getName();
        return returnable;
    }

    @FXML
    private void addAction(ActionEvent event) {
        ContextFX.getInstance().setId(id_selected);
        addBatch();
    }

    @FXML
    private void lessAction(ActionEvent event) {
        ContextFX.getInstance().setId(selected_id);
        lessBatch();
    }

    private void addBatch() {
        Integer idBatch = ContextFX.getInstance().getId();
        batch = FXCollections.observableArrayList();
        batch = getListBatch();
        for (int i = 0; i < batch.size(); i++) {
            if (idBatch.compareTo(batch.get(i).getId_batch()) == 0) {
                String nameProd = getNameProd(batch.get(i).getId_product());
                int meas = getIntMeasure(batch.get(i).getId_product());
                String measure = getMeasure(meas);
                BatchDisplay batchDisp = new BatchDisplay(batch.get(i).getId_batch(), batch.get(i).getQuantity(),
                        batch.get(i).getBatch_cost(), batch.get(i).getArrival_date().toString(),
                        batch.get(i).getExpiration_date().toString(), batch.get(i).getId_product(),
                        batch.get(i).getType_batch(), nameProd,
                        Boolean.toString(batch.get(i).isState()), measure);
                batchView2.add(batchDisp);
                for (int j = 0; j < batchView.size(); j++) {
                    if (idBatch.compareTo(batchView.get(j).getId_batch().getValue()) == 0) {
                        batchView.remove(j);
                    }
                }
            }
        }
        batchDepartureTable.setItems(null);
        batchDepartureTable.setItems(batchView2);
        departureZoneTable.setItems(null);
        departureZoneTable.setItems(batchView);
    }

    private void lessBatch() {
        Integer idBatch = ContextFX.getInstance().getId();
        batch = FXCollections.observableArrayList();
        batch = getListBatch();
        for (int i = 0; i < batch.size(); i++) {
            if (idBatch.compareTo(batch.get(i).getId_batch()) == 0) {
                String nameProd = getNameProd(batch.get(i).getId_product());
                int meas = getIntMeasure(batch.get(i).getId_product());
                String measure = getMeasure(meas);
                BatchDisplay batchDisp = new BatchDisplay(batch.get(i).getId_batch(), batch.get(i).getQuantity(),
                        batch.get(i).getBatch_cost(), batch.get(i).getArrival_date().toString(),
                        batch.get(i).getExpiration_date().toString(), batch.get(i).getId_product(),
                        batch.get(i).getType_batch(), nameProd,
                        Boolean.toString(batch.get(i).isState()), measure);
                batchView.add(batchDisp);
                for (int j = 0; j < batchView2.size(); j++) {
                    if (idBatch.compareTo(batchView2.get(j).getId_batch().getValue()) == 0) {
                        batchView2.remove(j);
                    }
                }
            }
        }
        departureZoneTable.setItems(null);
        departureZoneTable.setItems(batchView);
        batchDepartureTable.setItems(null);
        batchDepartureTable.setItems(batchView2);
    }

    @FXML
    private void checkboxZoneAction(ActionEvent event) {
        if (checkboxZone.isSelected()) {
            List<Warehouse> warehouses = getWarehouse();
            for (int i = 0; i < warehouses.size(); i++) {
                zoneField.getItems().add(warehouses.get(i).getName());
            }
        } else {
            zoneField.getItems().clear();
        }
    }

    @FXML
    private void checkboxClientAction(ActionEvent event) {
        if (checkboxClient.isSelected()) {
            List<Client> clients = getClients();
            for (int i = 0; i < clients.size(); i++) {
                clientField.getItems().add(clients.get(i).getName());
            }
        } else {
            clientField.getItems().clear();
        }
    }

    private List<Client> getClients() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Client.class);
        List<Client> list = criteria.list();
        return list;
    }

    private List<Warehouse> getWarehouse() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Warehouse.class);
        List<Warehouse> list = criteria.list();
        return list;
    }

    @FXML
    private void goCreateDeparture(ActionEvent event) throws IOException {
        String client = this.clientField.getValue();
        String zone = this.zoneField.getValue();
        String reason = this.reasonField.getValue();

        if (client.compareTo("") != 0) {
            Integer id_owner = getIdClient(client);
            Integer id_type_owner = 2;
            Integer idReason = 0;
            if (reason.compareTo("Rompimiento de Productos") == 0 || reason.compareTo("Producto Vencido") == 0
                    || reason.compareTo("Traslado de Almacén") == 0) {
                idReason = 2;
            } else if (reason.compareTo("Para Ventas") == 0) {
                idReason = 1;
            }
            createDeparture(id_type_owner, id_owner, idReason);
        } else if (zone.compareTo("") != 0) {
            Integer id_owner = getIdZone(zone);
            Integer id_type_owner = 1;
            Integer idReason = 0;
            if (reason.compareTo("Rompimiento de Productos") == 0 || reason.compareTo("Producto Vencido") == 0
                    || reason.compareTo("Traslado de Almacén") == 0) {
                idReason = 2;
            } else if (reason.compareTo("Para Ventas") == 0) {
                idReason = 1;
            }
            createDeparture(id_type_owner, id_owner, idReason);
        }
        this.goListDepartures();
    }

    private Integer getIdClient(String client) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Client.class);
        criteria.add(Restrictions.eq("name", client));
        List<Client> list = criteria.list();
        Integer idcli = list.get(0).getId_client();
        return idcli;
    }

    private Integer getIdZone(String zone) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Warehouse.class);
        criteria.add(Restrictions.eq("name", zone));
        List<Warehouse> list = criteria.list();
        Integer idzone = list.get(0).getId();
        return idzone;
    }

    private void createDeparture(Integer id_type_owner, Integer id_owner, Integer idReason) {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DispatchMove dispatch = new DispatchMove(id_type_owner, id_owner, currentTimestamp, idReason);
        session.save(dispatch);
        session.getTransaction().commit();
        session.close();
    }

    private Integer getIntMeasure(int id_product) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product", id_product));
        List<Product> list = criteria.list();
        Integer meas = list.get(0).getId_unit_of_measure();
        return meas;
    }

}
