/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.dispatch;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import static campis.dp1.controllers.products.EditController.getMeasure;
import static campis.dp1.controllers.products.EditController.searchCodMeasure;
import static campis.dp1.controllers.products.EditController.searchCodType;
import static campis.dp1.controllers.vehicles.CreateVehicleController.getWarehouses;
import campis.dp1.models.Batch;
import campis.dp1.models.BatchDisplay;
import campis.dp1.models.Client;
import campis.dp1.models.DispatchMove;
import campis.dp1.models.DispatchOrder;
import campis.dp1.models.DispatchOrderLine;
import campis.dp1.models.Product;
import campis.dp1.models.ProductDisplay;
import campis.dp1.models.RequestOrder;
import campis.dp1.models.UnitOfMeasure;
import campis.dp1.models.Warehouse;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Boolean.FALSE;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author david
 */
public class CreateNormalDepartureController implements Initializable {

    private Main main;
    Integer id_selected, selected_id;
    Integer num = 0;
    private ObservableList<Batch> batch;
    private ObservableList<BatchDisplay> batchView;
    private ObservableList<BatchDisplay> batchView2 = FXCollections.observableArrayList();
    private ObservableList<DispatchOrder> dispatchOr = FXCollections.observableArrayList();
    private ObservableList<DispatchOrderLine> dispatchOrLine = FXCollections.observableArrayList();
    private ObservableList<Batch> batchListAux = FXCollections.observableArrayList();

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
    private TableColumn<BatchDisplay, Integer> stockColumn;
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
        stockColumn.setCellValueFactory(cellData -> cellData.getValue().getId_product().asObject());
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
        ObservableList<Batch> batchAux = getListBatch();
        for (int i = 0; i < dispatchOrLine.size(); i++) {
            for (int j = 0; j < batchAux.size(); j++) {
                if (batchAux.get(j).getId_product() == dispatchOrLine.get(i).getId_prod()) {
                    batch.add(batchAux.get(j));
                }
            }
        }
        loadData(batch);
    }

    private Batch getBatch(int codProd) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Batch.class);
        Conjunction objCriteria = Restrictions.conjunction();
        objCriteria.add(Restrictions.eq("id_product", codProd));
        criteria.add(objCriteria);
        List<Batch> list = criteria.list();
        Batch returnable = list.get(0);
        session.close();
        sessionFactory.close();
        return returnable;
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
        session.close();
        sessionFactory.close();
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
        criteria.add(Restrictions.disjunction().add(Restrictions.eq("type_batch", 1))).add(Restrictions.gt("quantity", 0));
        ObservableList<Batch> returnable = FXCollections.observableArrayList();
        List list = criteria.list();
        for (int i = 0; i < list.size(); i++) {
            returnable.add((Batch) list.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private void loadData(ObservableList<Batch> batch) {
        batchView = FXCollections.observableArrayList();
        for (int j = 0; j < dispatchOrLine.size(); j++) {
            for (int i = 0; i < batch.size(); i++) {
                String nameProd = getNameProd(batch.get(i).getId_product());
                int meas = getIntMeasure(batch.get(i).getId_product());
                String measure = getMeasure(meas);
                if (dispatchOrLine.get(j).getId_prod() == batch.get(i).getId_product()) {
                    BatchDisplay batchDisp = new BatchDisplay(batch.get(i).getId_batch(), dispatchOrLine.get(j).getQuantity(),
                            batch.get(i).getBatch_cost(), batch.get(i).getArrival_date().toString(),
                            batch.get(i).getExpiration_date().toString(), batch.get(i).getQuantity(),
                            batch.get(i).getType_batch(), nameProd,
                            Boolean.toString(batch.get(i).isState()), measure);
                    batchView.add(batchDisp);
                }
                /*else {
                    int cd = dispatchOrLine.get(j).getId_prod();
                    Batch batchAus = getBatch(dispatchOrLine.get(j).getId_prod());
                    nameProd = getNameProd(batchAus.getId_product());
                    meas = getIntMeasure(batchAus.getId_product());
                    measure = getMeasure(meas);
                    BatchDisplay batchDisp = new BatchDisplay(batchAus.getId_product(), dispatchOrLine.get(j).getQuantity(),
                            batchAus.getBatch_cost(), batchAus.getArrival_date().toString(),
                            batchAus.getExpiration_date().toString(), 0,
                            batchAus.getType_batch(), nameProd,
                            Boolean.toString(batchAus.isState()), measure);
                    batchView.add(batchDisp);
                }*/
            }
            if (batch.isEmpty()) {
                int cd = dispatchOrLine.get(j).getId_prod();
                Batch batchAus = getBatch(dispatchOrLine.get(j).getId_prod());
                String nameProd = getNameProd(batchAus.getId_product());
                int meas = getIntMeasure(batchAus.getId_product());
                String measure = getMeasure(meas);
                BatchDisplay batchDisp = new BatchDisplay(batchAus.getId_product(), dispatchOrLine.get(j).getQuantity(),
                        batchAus.getBatch_cost(), batchAus.getArrival_date().toString(),
                        batchAus.getExpiration_date().toString(), 0,
                        batchAus.getType_batch(), nameProd,
                        Boolean.toString(batchAus.isState()), measure);
                batchView.add(batchDisp);
            }
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
        session.close();
        sessionFactory.close();
        return returnable;
    }

    @FXML
    private void addAction(ActionEvent event) {
        ContextFX.getInstance().setId(id_selected);
        addBatch();
    }

    private Batch getOneBatch(int idBatch) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Batch.class);
        criteria.add(Restrictions.eq("id_batch", idBatch));
        List list = criteria.list();
        Batch returnable = (Batch) list.get(0);
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private void addBatch() {
        Integer idBatch = ContextFX.getInstance().getId();
        Batch auxi = getOneBatch(idBatch);
        batch = FXCollections.observableArrayList();
        batch = getListBatch();
        for (int i = 0; i < batch.size(); i++) {
            if (auxi.getId_batch() == batch.get(i).getId_batch()) {
                String nameProd = getNameProd(batch.get(i).getId_product());
                int meas = getIntMeasure(batch.get(i).getId_product());
                String measure = getMeasure(meas);
                BatchDisplay batchDisp = new BatchDisplay(batch.get(i).getId_batch(), auxi.getQuantity(),
                        batch.get(i).getBatch_cost(), batch.get(i).getArrival_date().toString(),
                        batch.get(i).getExpiration_date().toString(), batch.get(i).getId_product(),
                        batch.get(i).getType_batch(), nameProd,
                        Boolean.toString(batch.get(i).isState()), measure);
                batchView2.add(batchDisp);
                num = num + 1;
                for (int j = 0; j < batchView.size(); j++) {
                    if (auxi.getId_batch() == batchView.get(j).getId_batch().getValue()) {
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

    private Integer getCli(int codRequest) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RequestOrder.class);
        criteria.add(Restrictions.eq("id_request_order", codRequest));
        List list = criteria.list();
        RequestOrder rq = (RequestOrder) list.get(0);
        Integer returnable = rq.getId_client();
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private Integer getIdClient(int codDispatch) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(DispatchOrder.class);
        criteria.add(Restrictions.eq("id_dispatch_order", codDispatch));
        List list = criteria.list();
        DispatchOrder disp = (DispatchOrder) list.get(0);
        session.close();
        sessionFactory.close();
        Integer idClient = getCli(disp.getId_request_order());
        return idClient;
    }

    private void updateBatch(int codBatch) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Batch set type_batch = :newTypeBatch where id_batch = :oldIdBatch");
        query.setParameter("newTypeBatch", 5);
        query.setParameter("oldIdBatch", codBatch);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    private DispatchOrder getDispatchOrder(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(DispatchOrder.class);
        criteria.add(Restrictions.eq("id_dispatch_order", cod));
        List<DispatchOrder> list = criteria.list();
        DispatchOrder dpOrd = list.get(0);
        session.close();
        sessionFactory.close();
        return dpOrd;
    }

    private void createDispatchOrder(DispatchOrder dpOrd) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DispatchOrder dispatch = new DispatchOrder(dpOrd.getId_request_order(), dpOrd.getPriority(), "EN PROGRESO");
        session.save(dispatch);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

        for (int j = 0; j < batchDepartureTable.getItems().size(); j++) {
            for (int i = 0; i < dispatchOrLine.size(); i++) {
                if (dispatchOrLine.get(i).getId_prod().compareTo(batchDepartureTable.getItems().get(i).getId_product().getValue()) != 0) {
                    createDispatchOrderLine(dispatch.getId_dispatch_order(), dispatchOrLine.get(i).getId_prod(),
                            dispatchOrLine.get(i).getQuantity());
                }
            }
        }
    }

    private void createDispatchOrderLine(int codDispOrd, int prod, int quant) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DispatchOrderLine dispOrdLine = new DispatchOrderLine(codDispOrd, prod, quant);
        session.save(dispOrdLine);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    private void updateDispatchOrder(int codDpOrd, String mess) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update DispatchOrder set status = :newStatus where id_dispatch_order = :oldDpOrd");
        query.setParameter("newStatus", mess);
        query.setParameter("oldDpOrd", codDpOrd);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    private void updateDispatchOrder2(int codDpOrd, String mess) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update DispatchOrder set status = :newStatus where id_request_order = :oldDpOrd");
        query.setParameter("newStatus", mess);
        query.setParameter("oldDpOrd", codDpOrd);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        updateReqOrder(codDpOrd, mess);
    }

    private void updateReqOrder(int codDpOrd, String mess) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query2 = session.createQuery("update RequestOrder set status = :newStatus where id_request_order = :oldDpOrd");
        query2.setParameter("newStatus", mess);
        query2.setParameter("oldDpOrd", codDpOrd);
        int result2 = query2.executeUpdate();
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    private void updateBatchQ(int codBatch, int q) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Batch set quantity = :newQuantity where id_batch = :oldIdBatch");
        query.setParameter("newQuantity", q);
        query.setParameter("oldIdBatch", codBatch);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @FXML
    private void goCreateDeparture(ActionEvent event) throws IOException {
        String dispatchOrder = this.dispatchField.getValue();
        String reason = this.reasonField.getText();
        if (dispatchOrder != null) {
            Integer id_owner = getIdClient(Integer.parseInt(dispatchOrder));
            Integer id_type_owner = 2;
            Integer id_batch = 0;
            Integer idReason = 1;
            if (dispatchOrLine.size() != batchDepartureTable.getItems().size()) {
                DispatchOrder dpOrd = getDispatchOrder(Integer.parseInt(this.dispatchField.getValue()));
                String status = "INCOMPLETO";
                updateDispatchOrder(dpOrd.getId_dispatch_order(), status);
                createDispatchOrder(dpOrd);
            } else {
                int m = 0;
                for (int i = 0; i < batchDepartureTable.getItems().size(); i++) {
                    id_batch = batchDepartureTable.getItems().get(i).getId_batch().getValue();
                    String nProd = batchDepartureTable.getItems().get(i).getLocation().getValue();
                    int coProd = Product.getNProduct(nProd);
                    Batch aux = getOneBatch(id_batch);
                    int q = 0;
                    if (dispatchOrLine.get(i).getId_prod() == aux.getId_product()) {
                        q = dispatchOrLine.get(i).getQuantity();
                    }
                    createDeparture(id_type_owner, id_owner, idReason, id_batch);
                    if (aux.getQuantity() == q) {
                        m = m + 1;
                        updateBatch(id_batch);
                    } else if (aux.getQuantity() > q) {
                        q = aux.getQuantity() - q;
                        updateBatchQ(id_batch, q);
                    } else if (aux.getQuantity() < q) {
                        q = q - aux.getQuantity();
                        updateDispatchOrderLine(Integer.parseInt(this.dispatchField.getValue()), q, coProd);
                    }
                    if (m == batchDepartureTable.getItems().size()) {
                        DispatchOrder dpOrd = getDispatchOrder(Integer.parseInt(this.dispatchField.getValue()));
                        String status = "ENTREGADO";
                        updateDispatchOrder2(dpOrd.getId_request_order(), status);
                    }
                }
            }
            this.goListDepartures();
        }
    }

    private void updateDispatchOrderLine(int codDisp, int q, int coProd) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update DispatchOrderLine set quantity = :newQuantity "
                + "where id_dispatch_order = :oldDpOrder AND id_product = :oldIdProd");
        query.setParameter("newQuantity", q);
        query.setParameter("oldDpOrder", codDisp);
        query.setParameter("oldIdProd", coProd);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    private void createDeparture(Integer id_type_owner, Integer id_owner, Integer idReason, Integer idBatch) {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DispatchMove dispatch = new DispatchMove(id_type_owner, id_owner, currentTimestamp, idReason, idBatch, currentTimestamp);
        session.save(dispatch);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        //createDispatchOrderLine(dispatch.getId_dispatch_move());
    }

    /*private void createDispatchOrderLine(int codDispathMove) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (int i = 0; i < num; i++) {
            BatchDisplay newBatch= batchDepartureTable.getItems().get(i);
            
        }
    }*/
    private Integer getIntMeasure(int id_product) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class
        );
        criteria.add(Restrictions.eq("id_product", id_product));
        List<Product> list = criteria.list();
        Integer meas = list.get(0).getId_unit_of_measure();
        session.close();
        sessionFactory.close();
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
        criteria.add(Restrictions.eq("status", "EN PROGRESO"));
        ObservableList<DispatchOrder> returnable = FXCollections.observableArrayList();
        List list = criteria.list();
        for (int i = 0; i < list.size(); i++) {
            returnable.add((DispatchOrder) list.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }

}
