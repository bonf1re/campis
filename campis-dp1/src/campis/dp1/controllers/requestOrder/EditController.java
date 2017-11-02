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
import campis.dp1.models.RequestDisplay;
import campis.dp1.models.RequestOrder;
import campis.dp1.models.RequestOrderLine;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Eddy
 */
public class EditController implements Initializable {

    Main main;
    Integer id, idProd, quantity;
    Integer codGen = 0;
    Integer num = 0;
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
    private JFXComboBox<String> statesField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        codGen = ContextFX.getInstance().getVar();
        try {
            if (codGen == 0) {
                id = ContextFX.getInstance().getId();
                ContextFX.getInstance().setVar(id);
                statesField.getItems().addAll("ENTREGADO", "CANCELADO", "EN PROGRESO");
                List<RequestOrderLine> list = getReqOrdLine(id);
                RequestOrder request = getRequestOrder(id);
                String nameCli = getNameCli(request.getId_client());
                this.nameClientField.setText(nameCli);
                this.codClientField.setText(Integer.toString(request.getId_client()));
                this.creationDate.setValue(request.getCreation_date().toLocalDateTime().toLocalDate());
                this.deliveryDate.setValue(request.getDelivery_date().toLocalDateTime().toLocalDate());
                this.statesField.setValue(request.getStatus());
                idColumn.setCellValueFactory(cellData -> cellData.getValue().codProdProperty().asObject());
                nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
                typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asObject());
                quantityColumn.setCellValueFactory(cellData -> cellData.getValue().cStockProperty().asObject());
                unitaryAmountColumn.setCellValueFactory(cellData -> cellData.getValue().precioBProperty().asObject());
                finalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().pesoProperty().asObject());
                stateColumn.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
                loadData(list);
            } else {
                idProd = ContextFX.getInstance().getId();
                quantity = ContextFX.getInstance().getQuantity();
                num = ContextFX.getInstance().getNum();
                num = num + 1;
                statesField.getItems().addAll("ENTREGADO", "CANCELADO", "EN PROGRESO");
                List<RequestOrderLine> list = getReqOrdLine(codGen);
                RequestOrder request = getRequestOrder(codGen);
                String nameCli = getNameCli(request.getId_client());
                this.nameClientField.setText(nameCli);
                this.codClientField.setText(Integer.toString(request.getId_client()));
                this.creationDate.setValue(request.getCreation_date().toLocalDateTime().toLocalDate());
                this.deliveryDate.setValue(request.getDelivery_date().toLocalDateTime().toLocalDate());
                this.statesField.setValue(request.getStatus());
                idColumn.setCellValueFactory(cellData -> cellData.getValue().codProdProperty().asObject());
                nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
                typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asObject());
                quantityColumn.setCellValueFactory(cellData -> cellData.getValue().cStockProperty().asObject());
                unitaryAmountColumn.setCellValueFactory(cellData -> cellData.getValue().precioBProperty().asObject());
                finalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().pesoProperty().asObject());
                stateColumn.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
                loadData2(idProd, quantity);
            }
        } catch (NullPointerException e) {
            idColumn.setCellValueFactory(cellData -> cellData.getValue().codProdProperty().asObject());
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asObject());
            quantityColumn.setCellValueFactory(cellData -> cellData.getValue().cStockProperty().asObject());
            unitaryAmountColumn.setCellValueFactory(cellData -> cellData.getValue().precioBProperty().asObject());
            finalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().pesoProperty().asObject());
            stateColumn.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        }
    }

    @FXML
    private void goAddItem() throws IOException {
        main.showAddItem2();
    }

    @FXML
    private void editRequestOrder(ActionEvent event) throws IOException {
        SimpleDateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_creation = getDate(creationDate.getValue());
        Date date_delivery = getDate(deliveryDate.getValue());
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("UPDATE RequestOrder SET creation_date=:newCreation, delivery_date=:newDelivery, base_amount=:newBase, total_amount=:newTotal, "
                + "status=:newStatus WHERE id_request_order = :odlIdRequest");
        query.setParameter("newCreation", date_creation);
        query.setParameter("newDelivery", date_delivery);
        query.setParameter("newBase", Float.parseFloat(amountField.getText()));
        query.setParameter("newTotal", Float.parseFloat(amountField.getText()));
        query.setParameter("newStatus", (String) statesField.getValue());
        query.setParameter("odlIdRequest", codGen);
        int result = query.executeUpdate();

        session.getTransaction().commit();

        sessionFactory.close();
        createRequestOrderLine(codGen);
        this.goListRequestOrder();
    }

    private void createRequestOrderLine(int codReqOrd) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        productsView = ContextFX.getInstance().getTempList();
        for (int i = 0; i < productsView.size(); i++) {
            ProductDisplay prod = tablaProd.getItems().get(i);
            Integer idprod = prod.codProdProperty().getValue();
            Integer quant = prod.cStockProperty().getValue();
            Float cost = prod.precioBProperty().getValue();
            List<RequestOrderLine> list = getReqOrdLineDif();
            boolean flag = false;
            for (int j = 0; j < list.size(); j++) {
                if (idprod.compareTo(list.get(j).getId_product()) == 0) {
                    flag = true;
                }
            }
            if (flag == false) {
                RequestOrderLine reqOrdLine = new RequestOrderLine(quant, cost, codReqOrd, idprod);
                session.save(reqOrdLine);
            }
        }
        session.getTransaction().commit();
        sessionFactory.close();
    }

    private Date getDate(LocalDate value) {

        Calendar calendar = new GregorianCalendar(value.getYear(),
                value.getMonthValue(),
                value.getDayOfMonth());
        return calendar.getTime();
    }

    @FXML
    private void goListRequestOrder() throws IOException {
        main.showListRequestOrder();
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

    private void loadData2(int cod, int quant) {
        products = FXCollections.observableArrayList();
        productsView = ContextFX.getInstance().getTempList();
        products = getProduct(cod);
        Float amount = quant * products.get(0).getBase_price();
        String state = "ENTREGA";
        totalAmount = ContextFX.getInstance().getTotAmount();
        totalAmount = totalAmount + amount;
        ContextFX.getInstance().setTotAmount(totalAmount);
        this.amountField.setText(Float.toString(totalAmount));

        ProductDisplay prod = new ProductDisplay(products.get(0).getId_product(), products.get(0).getName(),
                products.get(0).getDescription(), products.get(0).getP_stock(), quantity,
                amount, state, products.get(0).getBase_price(),
                products.get(0).getId_unit_of_measure(), products.get(0).getId_product_type());
        productsView.add(prod);
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

    private List<RequestOrderLine> getReqOrdLineDif() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RequestOrderLine.class);
        List<RequestOrderLine> request = criteria.list();

        return request;
    }

}
