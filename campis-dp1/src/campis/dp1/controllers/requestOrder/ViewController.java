/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.requestOrder;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Client;
import campis.dp1.models.Product;
import campis.dp1.models.ProductDisplay;
import campis.dp1.models.RequestOrder;
import campis.dp1.models.RequestOrderLine;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Eddy
 */
public class ViewController implements Initializable {

    Main main;
    Integer id;
    float totalAmount = 0;
    private ObservableList<Product> products;
    private ObservableList<ProductDisplay> productsView;

    @FXML
    private JFXTextField amountField;
    @FXML
    private JFXTextField nameClientField;
    @FXML
    private JFXDatePicker creationDate;
    @FXML
    private JFXDatePicker deliveryDate;
    @FXML
    private TableView<ProductDisplay> tablaProd;
    @FXML
    private TableColumn<ProductDisplay, Integer> idColumn;
    @FXML
    private TableColumn<ProductDisplay, String> nameColumn;
    @FXML
    private TableColumn<ProductDisplay, Integer> typeColumn;
    @FXML
    private TableColumn<ProductDisplay, Integer> quantityColumn;
    @FXML
    private TableColumn<ProductDisplay, Float> unitaryAmountColumn;
    @FXML
    private TableColumn<ProductDisplay, Float> finalAmountColumn;
    @FXML
    private TableColumn<ProductDisplay, String> stateColumn;
    @FXML
    private JFXTextField codClientField;
    @FXML
    private JFXTextField stateField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id = ContextFX.getInstance().getId();
        ContextFX.getInstance().setId(id);
        List<RequestOrderLine> list = getReqOrdLine(id);
        RequestOrder request = getRequestOrder(id);
        String nameCli = getNameCli(request.getId_client());
        this.nameClientField.setText(nameCli);
        this.codClientField.setText(Integer.toString(request.getId_client()));
        this.creationDate.setValue(request.getCreation_date().toLocalDateTime().toLocalDate());
        this.deliveryDate.setValue(request.getDelivery_date().toLocalDateTime().toLocalDate());
        this.stateField.setText(request.getStatus());
        idColumn.setCellValueFactory(cellData -> cellData.getValue().codProdProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asObject());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().cStockProperty().asObject());
        unitaryAmountColumn.setCellValueFactory(cellData -> cellData.getValue().precioBProperty().asObject());
        finalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().pesoProperty().asObject());
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        loadData(list);
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
        return returnable;
    }

    private void loadData(List<RequestOrderLine> list) {
        products = FXCollections.observableArrayList();
        productsView = ContextFX.getInstance().getTempList();
        for (int i = 0; i < list.size(); i++) {
            products = getProduct(list.get(i).getId_product());
            Float amount = list.get(i).getQuantity() * list.get(i).getCost();
            String state = "ENTREGA";
            totalAmount = ContextFX.getInstance().getTotAmount();
            totalAmount = totalAmount + amount;
            ContextFX.getInstance().setTotAmount(totalAmount);
            this.amountField.setText(Float.toString(totalAmount));

            ProductDisplay prod = new ProductDisplay(products.get(0).getId_product(), products.get(0).getName(),
                    products.get(0).getDescription(), products.get(0).getP_stock(), list.get(i).getQuantity(),
                    amount, state, products.get(0).getBase_price(),
                    products.get(0).getId_unit_of_measure(), products.get(0).getId_product_type());
            productsView.add(prod);
        }
        ContextFX.getInstance().setTempList(productsView);
        productsView = ContextFX.getInstance().getTempList();
        tablaProd.setItems(null);
        tablaProd.setItems(productsView);
    }

    private ObservableList<Product> getProduct(Integer id_product) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product", id_product));
        List<Product> list = criteria.list();
        ObservableList<Product> returnable;
        returnable = FXCollections.observableArrayList();
        returnable.add(list.get(0));
        return returnable;
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
        return rsRequestOrderLine;
    }

    @FXML
    private void goListRequestOrder() throws IOException {
        main.showListRequestOrder();
    }

}
