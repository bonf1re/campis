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
import campis.dp1.models.DispatchOrder;
import campis.dp1.models.DispatchOrderLine;
import campis.dp1.models.Product;
import campis.dp1.models.UnitOfMeasure;
import campis.dp1.models.Warehouse;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
public class CreateNormalDepartureController implements Initializable {

    private Main main;
    Integer id_selected, selected_id;
    private ObservableList<Batch> batch;
    private ObservableList<BatchDisplay> batchView;
    private ObservableList<BatchDisplay> batchView2 = FXCollections.observableArrayList();
    private ObservableList<DispatchOrder> dispatchOr = FXCollections.observableArrayList();
    private ObservableList<DispatchOrderLine> dispatchOrLine = FXCollections.observableArrayList();
    
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
    private JFXTextField reasonField;
    @FXML
    private Button addButton;
    @FXML
    private Button lessButton;
    @FXML
    private JFXCheckBox checkboxDispatch;
    @FXML
    private JFXComboBox<String> dispatchField;

    @FXML
    private void goListDepartures() throws IOException {
        main.showListDepartures();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reasonField.setText("Para Venta");
        dispatchOr = getDispatchOrders();
        batch = FXCollections.observableArrayList();
        
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

    }
    
    @FXML
    private void generateList(ActionEvent event) {
        int cod = Integer.parseInt(dispatchField.getValue());
        dispatchOrLine = getListDisp(cod);
        ObservableList<Batch> batchAux = FXCollections.observableArrayList();
        batchAux = getListBatch();
        for (int i = 0; i < dispatchOrLine.size(); i++) {
            for (int j = 0; j < batchAux.size(); j++) {
                int a = dispatchOrLine.get(i).getId_prod(); 
                int b = batchAux.get(j).getId_product();
                if(a == b){
                    batch.add(batchAux.get(j));
                }
            }
        }
        loadData(batch);
    }
    
    private ObservableList<DispatchOrderLine> getListDisp(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(DispatchOrderLine.class);
        criteria.add(Restrictions.eq("id_dispatch_order", cod));
        ObservableList<DispatchOrderLine> returnable = FXCollections.observableArrayList();
        List list = criteria.list();
        for (int i = 0; i < list.size(); i++) {
            returnable.add((DispatchOrderLine) list.get(i));
        }
        return returnable;
    }
    
    private ObservableList<Batch> getListBatch() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Batch.class);
        criteria.add(Restrictions.eq("type_batch", 1));
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
    private void checkboxDispatchAction(ActionEvent event) {
        if (checkboxDispatch.isSelected()) {
            for (int i = 0; i < dispatchOr.size(); i++) {
                dispatchField.getItems().add(Integer.toString(dispatchOr.get(i).getId_dispatch_order()));
            }
        } else {
            dispatchField.getItems().clear();
        }
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
        String zone = this.dispatchField.getValue();
        String reason = this.reasonField.getText();
        if (zone != null) {
            Integer id_owner = 3;  //getIdClient(zone);
            Integer id_type_owner = 1;
            Integer idReason = 0;
            Integer id_batch = 0;
            if (reason.compareTo("Traslado de Almacén") == 0) {
                idReason = 2;
            }
            for (int i = 0; i < batchDepartureTable.getItems().size(); i++) {
                id_batch = batchDepartureTable.getItems().get(i).getId_batch().getValue();
                createDeparture(id_type_owner, id_owner, idReason, id_batch);
            }
            this.goListDepartures();
        } else {
            Integer id_owner = 0;
            Integer id_type_owner = 0;
            Integer idReason = 0;
            Integer id_batch = 0;
            if(reason.compareTo("Producto Dañado")==0 || reason.compareTo("Producto Vencido")==0){
                idReason = 1;
            }
            for (int i = 0; i < batchDepartureTable.getItems().size(); i++) {
                id_batch = batchDepartureTable.getItems().get(i).getId_batch().getValue();
                createDeparture(id_type_owner, id_owner, idReason, id_batch);
            }
            this.goListDepartures();
        }
    }

    private void createDeparture(Integer id_type_owner, Integer id_owner, Integer idReason, Integer idBatch) {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DispatchMove dispatch = new DispatchMove(id_type_owner, id_owner, currentTimestamp, idReason, idBatch);
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

    private ObservableList<DispatchOrder> getDispatchOrders() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(DispatchOrder.class);
        ObservableList<DispatchOrder> returnable = FXCollections.observableArrayList();
        List list = criteria.list();
        for (int i = 0; i < list.size(); i++) {
            returnable.add((DispatchOrder) list.get(i));
        }
        return returnable;
    }

}
