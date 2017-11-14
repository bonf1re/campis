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
import campis.dp1.models.SaleCondition;
import com.jfoenix.controls.JFXComboBox;
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
    float totalAmount = 0;
    float baseTotalAmount = 0;
    float discountTotal = 0;
    float freightTotal = 0;
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
    private JFXTextField subtotalField;
    @FXML
    private JFXTextField discountField;
    @FXML
    private JFXTextField clientField;
    @FXML
    private JFXTextField priorityField;
    @FXML
    private Label messageField1;
    @FXML
    private Label messageField2;
    @FXML
    private JFXTextField addressField;
    @FXML
    private JFXTextField districtField;
    @FXML
    private JFXTextField freightField;
    @FXML
    private JFXTextField stateField;

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
        this.creationDate.setValue(request.getCreation_date().toLocalDateTime().toLocalDate());
        this.deliveryDate.setValue(request.getDelivery_date().toLocalDateTime().toLocalDate());
        this.stateField.setText(request.getStatus());
        this.priorityField.setText(Integer.toString(request.getPriority()));
        this.districtField.setText(distr);
        this.addressField.setText(request.getAddress());
        idColumn.setCellValueFactory(cellData -> cellData.getValue().codProdProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asObject());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().cStockProperty().asObject());
        unitaryAmountColumn.setCellValueFactory(cellData -> cellData.getValue().precioBProperty().asObject());
        finalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().pesoProperty().asObject());
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
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

    private ObservableList<SaleCondition> getDiscount(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SaleCondition.class);
        criteria.add(Restrictions.eq("id_to_take", cod));
        List<SaleCondition> list = criteria.list();
        ObservableList<SaleCondition> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            returnable.add(list.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private Float verifyConditions(ObservableList<SaleCondition> discounts, Product prod, int quant) {
        Float returnable = Float.valueOf(0);
        for (int i = 0; i < discounts.size(); i++) {
            int type = discounts.get(i).getId_sale_condition_type();
            if (type == 1) {
                //int maxQ = discounts.get(i).getLimits();
                //if (maxQ < quant) {
                returnable = returnable + discounts.get(i).getAmount() / 100;
                //}
            } else if (type == 2) {
                int type_prod = prod.getId_product_type();
                if (type_prod == type) {
                    //int maxQ = discounts.get(i).getLimits();
                    //if (maxQ < quant) {
                    returnable = returnable + discounts.get(i).getAmount() / 100;
                    //}
                }
            }
        }
        return returnable;
    }
    
    private Float getFreight(String cad) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT freight FROM campis.district WHERE name = '" + cad + "';";
        SQLQuery query = session.createSQLQuery(qryStr);
        List list = query.list();
        Float returnable = ((Double) list.get(0)).floatValue();
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private void loadData(List<RequestOrderLine> list) {
        products = FXCollections.observableArrayList();
        productsView = ContextFX.getInstance().getTempList();
        for (int i = 0; i < list.size(); i++) {
            products = getProduct(list.get(i).getId_product());
            ObservableList<SaleCondition> discounts = getDiscount(list.get(i).getId_product());
            Float disc = verifyConditions(discounts, products.get(0), list.get(i).getQuantity());
            Float base_amount = list.get(i).getQuantity() * list.get(i).getCost();
            String state = "ENTREGA";
            baseTotalAmount = ContextFX.getInstance().getBaseTotAmount();
            baseTotalAmount = baseTotalAmount + base_amount;
            discountTotal = ContextFX.getInstance().getTotAmount();
            discountTotal = discountTotal + base_amount * disc;
            totalAmount = baseTotalAmount - discountTotal;
            float f = getFreight(distr);
            freightTotal = freightTotal + baseTotalAmount * f;
            totalAmount = totalAmount + freightTotal;
            this.freightField.setText(Float.toString(freightTotal));
            this.amountField.setText(Float.toString(totalAmount));
            ContextFX.getInstance().setBaseTotAmount(baseTotalAmount);
            ContextFX.getInstance().setTotAmount(totalAmount);
            //this.amountField.setText(Float.toString(totalAmount));
            this.subtotalField.setText(Float.toString(baseTotalAmount));
            this.discountField.setText(Float.toString(discountTotal));
            this.amountField.setText(Float.toString(totalAmount));

            ProductDisplay prod = new ProductDisplay(products.get(0).getId_product(), products.get(0).getName(),
                    products.get(0).getDescription(), products.get(0).getP_stock(), list.get(i).getQuantity(),
                    base_amount, state, products.get(0).getBase_price(),
                    products.get(0).getId_unit_of_measure(),
                    products.get(0).getId_product_type(), products.get(0).getMax_qt());
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
        session.close();
        sessionFactory.close();
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
        session.close();
        sessionFactory.close();
        return rsRequestOrderLine;
    }

    @FXML
    private void goListRequestOrder() throws IOException {
        main.showListRequestOrder();
    }


}
