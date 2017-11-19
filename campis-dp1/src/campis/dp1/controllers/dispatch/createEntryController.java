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
import campis.dp1.models.Batch;
import campis.dp1.models.BatchDisplay;
import campis.dp1.models.DispatchMove;
import campis.dp1.models.Product;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author david
 */

/*Type Owner de tipo 3 es para entradas por proveedores*/
public class createEntryController implements Initializable {

    private Main main;
    Integer id_batch;
    String provider;
    String reason;
    ObservableList<Batch> batch = FXCollections.observableArrayList();
    ObservableList<BatchDisplay> batchView = FXCollections.observableArrayList();

    @FXML
    private TableView<BatchDisplay> batchTable;
    @FXML
    private TableColumn<BatchDisplay, Integer> idBatchColumn;
    @FXML
    private TableColumn<BatchDisplay, String> prodColumn;
    @FXML
    private TableColumn<BatchDisplay, Integer> quantityColumn;
    @FXML
    private TableColumn<BatchDisplay, String> measureColumn;
    @FXML
    private TableColumn<BatchDisplay, String> expColumn;
    @FXML
    private JFXComboBox<String> providerField;
    @FXML
    private JFXComboBox<String> reasonField;

    @FXML
    private void goListEntries() throws IOException {
        main.showListEntries();
    }

    @FXML
    private void goNewBatch() throws IOException {
        ContextFX.getInstance().setWord(provider);
        main.showNewBatch();
    }

    private List<String> getSupNames() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT name FROM campis.supplier;";
        SQLQuery qry = session.createSQLQuery(qryStr);
        List<Object> rows = qry.list();
        List<String> returnable = FXCollections.observableArrayList();
        for (Object row : rows) {
            returnable.add(row.toString());
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> names = getSupNames();
        for (int i = 0; i < names.size(); i++) {
            providerField.getItems().add(names.get(i));
        }
        reasonField.getItems().addAll("Hallazgo", "Devolucion");
        try {
            id_batch = ContextFX.getInstance().getId();
            batchView = ContextFX.getInstance().getTempBatchList();
            Batch b = getBatch(id_batch);
            idBatchColumn.setCellValueFactory(cellData -> cellData.getValue().getId_batch().asObject());
            prodColumn.setCellValueFactory(cellData -> cellData.getValue().getHeritage());
            quantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
            measureColumn.setCellValueFactory(cellData -> cellData.getValue().getArrival_date());
            expColumn.setCellValueFactory(cellData -> cellData.getValue().getExpiration_date());
            loadData(b);
        } catch (NullPointerException e) {
            idBatchColumn.setCellValueFactory(cellData -> cellData.getValue().getId_batch().asObject());
            prodColumn.setCellValueFactory(cellData -> cellData.getValue().getHeritage());
            quantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
            measureColumn.setCellValueFactory(cellData -> cellData.getValue().getArrival_date());
            expColumn.setCellValueFactory(cellData -> cellData.getValue().getExpiration_date());
        }
    }

    private Batch getBatch(int id_batch) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Batch.class);
        criteria.add(Restrictions.eq("id_batch", id_batch));
        List<Batch> list = criteria.list();
        Batch b = list.get(0);
        session.close();
        sessionFactory.close();
        return b;
    }

    private void loadData(Batch b) {
        String nomProd = getNameProd(b.getId_product());
        String measure = getDescripMesaure(b.getId_product());
        BatchDisplay bd = new BatchDisplay(b.getId_batch(), b.getQuantity(), b.getBatch_cost(), measure,
                b.getExpiration_date().toString(), b.getId_product(), b.getType_batch(),
                b.getLocation(), Boolean.toString(b.isState()), nomProd);
        batchView.add(bd);
        ContextFX.getInstance().setTempBatchList(batchView);
        batchTable.setItems(null);
        batchTable.setItems(batchView);
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
        List<Product> list = criteria.list();
        String returnable = list.get(0).getName();
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private String getDescripMesaure(int id_product) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product", id_product));
        List<Product> list = criteria.list();
        int id = list.get(0).getId_unit_of_measure();
        session.close();
        sessionFactory.close();
        String returnable = getMeasure(id);
        return returnable;
    }

    @FXML
    private void saveBatchEntries(ActionEvent event) throws IOException {
        /*
            idTypeOwner = 3 [Provedor genérico]
            idTypeOwner = 4 [Viene de un almacen]
            idTypeOwner = 5 [Ladrillos REX]
            idTypeOwner = 6 [Cementos SOL]
            idTypeOwner = 7 [Ceramicos CELIMA]
            idTypeOwner = 8 [Constructores PEPITO]
         */

 /* 
            idReason = 3 [Proveedores/compra]
            idReason = 7 [Hallazgo]
            idReason = 8 [Devolucion]
         */
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        provider = providerField.getValue();
        reason = reasonField.getValue();

        if (provider == null) {
            int idTypeOwner = 4;
            int idReason = 4; // Razón de que ha entrado por parte de hallazgo o devolucion

            if (reason.compareTo("Hallazgo") == 0) {
                idReason = 7;
            } else if (reason.compareTo("Devolucion") == 0) {
                idReason = 8;
            }

            //reasonField.getItems().addAll("Hallazgo", "Devolucion");
            for (int i = 0; i < batchTable.getItems().size(); i++) {
                int idBatch = batchTable.getItems().get(i).getId_batch().get();
                Batch b = getBatch(idBatch);
                int newType = 1;
                updateBatch(idBatch, newType);
                DispatchMove dispMove = new DispatchMove(idTypeOwner, 1, currentTimestamp, idReason, id_batch, b.getArrival_date());
                insertDispatchMove(dispMove);
            }

        } else {
            int idTypeOwner = 3; //proveedor genérico

            if (provider.compareTo("Ladrillos REX") == 0) {
                idTypeOwner = 5; //provedor Ladrillos REX
            } else if (provider.compareTo("Cementos SOL") == 0) {
                idTypeOwner = 6; //Provedor Cementos SQL
            } else if (provider.compareTo("Ceramicos CELIMA") == 0) {
                idTypeOwner = 7;  //Provedor ceraicos Celima
            } else if (provider.compareTo("Constructores PEPITO") == 0) {
                idTypeOwner = 8; //Provedor constructores PEPITO
            }

            int idReason = 3; // Razón de que ha entrado por parte de un proveedor - COMPRA

            for (int i = 0; i < batchTable.getItems().size(); i++) {
                int idBatch = batchTable.getItems().get(i).getId_batch().get();
                Batch b = getBatch(idBatch);
                int newType = 1;
                updateBatch(idBatch, newType);
                DispatchMove dispMove = new DispatchMove(idTypeOwner, 0, currentTimestamp, idReason, id_batch, b.getArrival_date());
                insertDispatchMove(dispMove);
            }
        }
        this.goListEntries();
    }

    private void updateBatch(int idBatch, int newType) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Batch set type_batch = :newTypeBatch where id_batch = :oldIdBatch");
        query.setParameter("newTypeBatch", newType);
        query.setParameter("oldIdBatch", idBatch);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    private void insertDispatchMove(DispatchMove dispMove) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(dispMove);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
