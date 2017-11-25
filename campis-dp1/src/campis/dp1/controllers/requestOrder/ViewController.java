/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.requestOrder;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Client;
import campis.dp1.models.Parameters;
import campis.dp1.models.Product;
import campis.dp1.models.ProductDisplay;
import campis.dp1.models.RequestLineDisplay;
import campis.dp1.models.RequestOrder;
import campis.dp1.models.RequestOrderLine;
import campis.dp1.models.SaleCondition;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Eddy
 */
public class ViewController implements Initializable {

    Main main;
    Integer id;
    String distr;

    
    private ObservableList<RequestLineDisplay> requestLineView = FXCollections.observableArrayList();

    @FXML
    private TableView<RequestLineDisplay> tablaProd;

    @FXML
    private TableColumn<RequestLineDisplay, Integer> idColumn;

    @FXML
    private TableColumn<RequestLineDisplay, String> nameColumn;

    @FXML
    private TableColumn<RequestLineDisplay, Integer> quantityColumn;

    @FXML
    private TableColumn<RequestLineDisplay, Float> unitaryAmountColumn;

    @FXML
    private TableColumn<RequestLineDisplay, Float> finalAmountColumn;

    @FXML
    private TableColumn<RequestLineDisplay, Float> discountColumn;

  //  @FXML
  //  private TableColumn<RequestLineDisplay, String> stateColumn;
    
    @FXML
    private Label messageField1;

    @FXML
    private Label messageField2;

    @FXML
    private Label subtotalField;

    @FXML
    private Label discountField;

    @FXML
    private Label freightField;

    @FXML
    private Label IGVField;

    @FXML
    private Label amountField;

    @FXML
    private Label nameClientField;

    @FXML
    private Label districtField;

    @FXML
    private Label deliveryDate;

    @FXML
    private Label clientField;

    @FXML
    private Label priorityField;

    @FXML
    private Label statesField;
    
    @FXML
    private Label creationDate;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        id = ContextFX.getInstance().getId();
        ContextFX.getInstance().setId(id);
        List<RequestOrderLine> list = getReqOrdLine(id);
        RequestOrder request = getRequestOrder(id);
        String nameCli = getNameCli(request.getId_client());
        distr = getNameDistric(request.getId_district());
        this.nameClientField.setText(nameCli);
        this.clientField.setText(Integer.toString(request.getId_client()));
        this.creationDate.setText(request.getCreation_date().toLocalDateTime().toLocalDate().toString());
        this.deliveryDate.setText(request.getDelivery_date().toLocalDateTime().toLocalDate().toString());
        this.statesField.setText(request.getStatus());
        this.priorityField.setText(Integer.toString(request.getPriority()));
        this.districtField.setText(distr);
        this.IGVField.setText(request.getIgv_amount().toString());
        this.freightField.setText(request.getFreight_amount().toString());
        this.discountField.setText(request.getDiscount_amount().toString());
        this.subtotalField.setText(request.getBase_amount().toString());
        this.amountField.setText(request.getTotal_amount().toString());
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getId_product().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
        unitaryAmountColumn.setCellValueFactory(cellData -> cellData.getValue().getBase_price().asObject());
        finalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().getFinal_price().asObject());
//        stateColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus());
        discountColumn.setCellValueFactory(cellData -> cellData.getValue().getDiscount().asObject());
        loadData(list);
    }

    private String getNameDistric(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT name FROM campis.district WHERE id_district=" + cod;
        SQLQuery query = session.createSQLQuery(qryStr);
        List list = query.list();
        String returnable = (String) list.get(0);
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private String getNameCli(Integer id_client) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Client.class);
        criteria.add(Restrictions.eq("id_client", id_client));
        List<Client> list = criteria.list();
        String name = list.get(0).getName();
        session.close();
        sessionFactory.close();
        return name;
    }

    private RequestOrder getRequestOrder(Integer id) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RequestOrder.class);
        criteria.add(Restrictions.eq("id_request_order", id));
        List<RequestOrder> list = criteria.list();
        RequestOrder returnable;
        returnable = (RequestOrder) list.get(0);
        session.close();
        sessionFactory.close();
        return returnable;
    }


    private void loadData(List<RequestOrderLine> list) {

        for (int i = 0; i < list.size(); i++) {
            Product p = getProduct(list.get(i).getId_product());
            RequestLineDisplay prod = new RequestLineDisplay(list.get(i).getId_product(), p.getName(),
                list.get(i).getQuantity(),
                list.get(i).getCost(),
                (list.get(i).getQuantity() * list.get(i).getCost()),
                list.get(i).getDiscount(),
                "POR ENTREGAR");
            requestLineView.add(prod);
        }
        tablaProd.setItems(null);
        tablaProd.setItems(requestLineView);
        
    }

    private Product getProduct(Integer id_product) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product", id_product));
        List<Product> list = criteria.list();
        
        return list.get(0);
    }

    private List<RequestOrderLine> getReqOrdLine(int id) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RequestOrderLine.class);
        criteria.add(Restrictions.eq("id_request_order", id));
        List<RequestOrderLine> rsRequestOrderLine = criteria.list();
        session.close();
        sessionFactory.close();
        return rsRequestOrderLine;
    }

    @FXML
    private void goListRequestOrder() throws IOException {

        main.showListRequestOrder();
    }

    private void goListBills() throws IOException {
        main.showBillList();
    }
}
