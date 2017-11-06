/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.departures;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import static campis.dp1.controllers.products.EditController.getMeasure;
import campis.dp1.models.Batch;
import campis.dp1.models.BatchDisplay;
import campis.dp1.models.Client;
import campis.dp1.models.DispatchMove;
import campis.dp1.models.Product;
import campis.dp1.models.Warehouse;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.xml.ws.Dispatch;
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
public class ViewDepartureController implements Initializable {

    private Main main;
    private int selected_id;
    ObservableList<Batch> batch;
    ObservableList<BatchDisplay> batchView = FXCollections.observableArrayList();
    
    @FXML
    private TableColumn<BatchDisplay, Integer> idbatchColumn;
    @FXML
    private TableColumn<BatchDisplay, String> prodColumn;
    @FXML
    private TableColumn<BatchDisplay, Integer> quantityColumn;
    @FXML
    private TableColumn<BatchDisplay, String> measureColumn;
    @FXML
    private TableColumn<BatchDisplay, String> expirationColumn;
    @FXML
    private JFXTextField destinyField;
    @FXML
    private JFXTextField departField;
    @FXML
    private TableView<BatchDisplay> batchTable;

    @FXML
    private void goListDepartures() throws IOException {
        main.showListDepartures();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selected_id = ContextFX.getInstance().getId();
        String destinyName = "";
        batch = FXCollections.observableArrayList();
        batch = getBatch(selected_id);
        DispatchMove disp = getDispatch(selected_id);
        if (disp.getType_owner().compareTo(1) == 0) {
            destinyName = getZone(disp.getId_owner());
        } else if (disp.getType_owner().compareTo(2) == 0) {
            destinyName = getNomCli(disp.getId_owner());
        }
        String depart = disp.getMov_date().toString();
        idbatchColumn.setCellValueFactory(cellData -> cellData.getValue().getId_batch().asObject());
        prodColumn.setCellValueFactory(cellData -> cellData.getValue().getHeritage());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
        measureColumn.setCellValueFactory(cellData -> cellData.getValue().getArrival_date());
        expirationColumn.setCellValueFactory(cellData -> cellData.getValue().getExpiration_date());
        destinyField.setText(destinyName);
        departField.setText(depart);
        loadData();
    }

    private DispatchMove getDispatch(int selected_id) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(DispatchMove.class);
        criteria.add(Restrictions.eq("id_group_batch", selected_id));
        List<DispatchMove> list = criteria.list();
        DispatchMove returnable = list.get(0);
        return returnable;
    }

    private ObservableList<Batch> getBatch(int selected_id) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Batch.class);
        criteria.add(Restrictions.eq("id_group_batch", selected_id));
        List<Batch> list = criteria.list();
        ObservableList<Batch> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            returnable.add(list.get(i));
        }
        return returnable;
    }

    private String getNomCli(int selected_id) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Client.class);
        criteria.add(Restrictions.eq("id_client", selected_id));
        List<Client> list = criteria.list();
        Client cli = (Client) list.get(0);
        String nom = cli.getName();
        return nom;
    }

    private String getZone(Integer id_owner) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Warehouse.class);
        criteria.add(Restrictions.eq("id_warehouse", id_owner));
        List<Warehouse> list = criteria.list();
        Warehouse zone = (Warehouse) list.get(0);
        String nom = zone.getName();
        return nom;
    }

    private String getProd(int id_product) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product", id_product));
        List<Product> list = criteria.list();
        Product prod = (Product) list.get(0);
        String nom = prod.getName();
        return nom;
    }

    private void loadData() {
        for (int i = 0; i < batch.size(); i++) {
            String nameProd = getProd(batch.get(i).getId_product());
            int quantity = batch.get(i).getQuantity();
            int idMeasure = batch.get(i).getType_batch();
            String measure = getMeasure(idMeasure);
            String exp = batch.get(i).getExpiration_date().toString();
            BatchDisplay batchdisp = new BatchDisplay(batch.get(i).getId_batch(),quantity,
                                        batch.get(i).getBatch_cost(),measure,exp,batch.get(i).getId_product(),
                                        idMeasure,batch.get(i).getLocation(),
                                        Boolean.toString(batch.get(i).isState()),nameProd);
            batchView.add(batchdisp);
        }
        batchTable.setItems(null);
        batchTable.setItems(batchView);
    }
}
