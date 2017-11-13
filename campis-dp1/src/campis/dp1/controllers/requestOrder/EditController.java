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
import campis.dp1.models.SaleCondition;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.naming.Context;
import javax.persistence.Query;
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
public class EditController implements Initializable {

    Main main;
    Integer id, idProd, quantity, selected_id;
    Integer codGen = 0;
    Integer num = 0;
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
    private JFXComboBox<String> statesField;
    @FXML
    private JFXTextField subtotalField;
    @FXML
    private JFXComboBox<Integer> priorityField;
    @FXML
    private JFXTextField discountField;
    @FXML
    private Label messageField1;
    @FXML
    private Label messageField2;
    @FXML
    private JFXTextField addressField;
    @FXML
    private JFXComboBox<String> districtField;
    @FXML
    private JFXTextField freightField;
    @FXML
    private JFXTextField clientField;

    private List<Object[]> getDistricts() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT * FROM campis.district;";
        SQLQuery query = session.createSQLQuery(qryStr);
        List<Object[]> rows = query.list();
        session.close();
        sessionFactory.close();
        return rows;
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
        return returnable;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        codGen = ContextFX.getInstance().getVar();
        List<Object[]> dists = getDistricts();
        for (Object[] dist : dists) {
            districtField.getItems().add(dist[1].toString());
        }
        this.selected_id = 0;
        tablaProd.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    this.selected_id = newValue.codProdProperty().getValue().intValue();
                });
        try {
            if (codGen == 0) {
                id = ContextFX.getInstance().getId();
                ContextFX.getInstance().setVar(id);
                statesField.getItems().addAll("ENTREGADO", "CANCELADO", "EN PROGRESO");
                priorityField.getItems().addAll(1, 2, 3);
                List<RequestOrderLine> list = getReqOrdLine(id);
                RequestOrder request = getRequestOrder(id);
                String nameCli = getNameCli(request.getId_client());
                distr = getNameDistric(request.getId_district());
                this.nameClientField.setText(nameCli);
                this.clientField.setText(Integer.toString(request.getId_client()));
                this.creationDate.setValue(request.getCreation_date().toLocalDateTime().toLocalDate());
                this.deliveryDate.setValue(request.getDelivery_date().toLocalDateTime().toLocalDate());
                this.statesField.setValue(request.getStatus());
                this.priorityField.setValue(request.getPriority());
                this.districtField.setValue(distr);
                this.addressField.setText(request.getAddress());
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
                String distr = getNameDistric(request.getId_district());
                this.nameClientField.setText(nameCli);
                this.clientField.setText(Integer.toString(request.getId_client()));
                this.creationDate.setValue(request.getCreation_date().toLocalDateTime().toLocalDate());
                this.deliveryDate.setValue(request.getDelivery_date().toLocalDateTime().toLocalDate());
                this.statesField.setValue(request.getStatus());
                this.priorityField.setValue(request.getPriority());
                this.districtField.setValue(distr);
                this.addressField.setText(request.getAddress());
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

    private Integer getDistrict(String cad) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT id_district FROM campis.district WHERE name = '" + cad + "';";
        SQLQuery query = session.createSQLQuery(qryStr);
        List list = query.list();
        Integer returnable = (Integer) list.get(0);
        return returnable;
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
        int id_dist = getDistrict(districtField.getValue());
        Query query = session.createQuery("UPDATE RequestOrder SET creation_date=:newCreation, delivery_date=:newDelivery, base_amount=:newBase, total_amount=:newTotal, "
                + "status=:newStatus, priority=:newPrior, id_district=:newDist, address=:newAddress "
                + "WHERE id_request_order = :odlIdRequest");
        query.setParameter("newCreation", date_creation);
        query.setParameter("newDelivery", date_delivery);
        query.setParameter("newBase", Float.parseFloat(amountField.getText()));
        query.setParameter("newTotal", Float.parseFloat(amountField.getText()));
        query.setParameter("newStatus", (String) statesField.getValue());
        query.setParameter("newPrior", priorityField.getValue());
        query.setParameter("id_district", id_dist);
        query.setParameter("address", addressField.getText());
        query.setParameter("odlIdRequest", codGen);
        int result = query.executeUpdate();

        session.getTransaction().commit();

        session.close();
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
        session.close();
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

    @FXML
    private void setDistrictAction() {
        Float freight = getFreight(this.districtField.getValue());
        freightTotal = freightTotal + baseTotalAmount * freight;
        totalAmount = totalAmount + freightTotal;
        this.freightField.setText(Float.toString(freightTotal));
        this.amountField.setText(Float.toString(totalAmount));
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
            discountTotal = ContextFX.getInstance().getDiscount();
            discountTotal = discountTotal + baseTotalAmount * disc;
            totalAmount = baseTotalAmount - discountTotal;
            float f = getFreight(distr);
            freightTotal = freightTotal + baseTotalAmount * f;
            totalAmount = totalAmount + freightTotal;
            this.freightField.setText(Float.toString(freightTotal));
            this.amountField.setText(Float.toString(totalAmount));
            ContextFX.getInstance().setBaseTotAmount(baseTotalAmount);
            ContextFX.getInstance().setTotAmount(totalAmount);
            ContextFX.getInstance().setDiscount(discountTotal);
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
                int maxQ = discounts.get(i).getLimits();
                if (maxQ < quant) {
                    returnable = returnable + discounts.get(i).getAmount() / 100;
                }
            } else if (type == 2) {
                int type_prod = prod.getId_product_type();
                if (type_prod == type) {
                    int maxQ = discounts.get(i).getLimits();
                    if (maxQ < quant) {
                        returnable = returnable + discounts.get(i).getAmount() / 100;
                    }
                }
            }
        }
        return returnable;
    }

    private void loadData2(int cod, int quant) {
        products = FXCollections.observableArrayList();
        productsView = ContextFX.getInstance().getTempList();
        products = getProduct(cod);
        ObservableList<SaleCondition> discounts = getDiscount(cod);
        Float disc = verifyConditions(discounts, products.get(0), quant);
        Float base_amount = quant * products.get(0).getBase_price();
        String state = "ENTREGA";
        baseTotalAmount = ContextFX.getInstance().getBaseTotAmount();
        baseTotalAmount = baseTotalAmount + base_amount;
        discountTotal = ContextFX.getInstance().getDiscount();
        discountTotal = discountTotal + baseTotalAmount * disc;
        totalAmount = baseTotalAmount - discountTotal;
        ContextFX.getInstance().setBaseTotAmount(baseTotalAmount);
        ContextFX.getInstance().setTotAmount(totalAmount);
        ContextFX.getInstance().setDiscount(discountTotal);
        this.subtotalField.setText(Float.toString(baseTotalAmount));
        this.discountField.setText(Float.toString(discountTotal));
        this.amountField.setText(Float.toString(totalAmount));

        ProductDisplay prod = new ProductDisplay(products.get(0).getId_product(), products.get(0).getName(),
                products.get(0).getDescription(), products.get(0).getP_stock(), quantity,
                base_amount, state, products.get(0).getBase_price(),
                products.get(0).getId_unit_of_measure(),
                products.get(0).getId_product_type(), products.get(0).getMax_qt());
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

    private void deleteItems(int codProd, int quantity, float price) {
        float base_price = quantity * price;
        ObservableList<SaleCondition> discounts = getDiscount(codProd);
        Float disc = verifyConditions(discounts, products.get(0), quantity);
        discountTotal = discountTotal - base_price * disc;
        baseTotalAmount = baseTotalAmount - base_price;
        totalAmount = baseTotalAmount + discountTotal + freightTotal;
        ContextFX.getInstance().setBaseTotAmount(baseTotalAmount);
        ContextFX.getInstance().setTotAmount(totalAmount);
        this.subtotalField.setText(Float.toString(baseTotalAmount));
        this.discountField.setText(Float.toString(discountTotal));
        this.amountField.setText(Float.toString(totalAmount));
    }

    @FXML
    private void deleteItem(ActionEvent event) {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            Integer id_prod = ContextFX.getInstance().getId();
            for (int i = 0; i < productsView.size(); i++) {
                if (productsView.get(i).codProdProperty().getValue().compareTo(id_prod) == 0) {
                    deleteItems(productsView.get(i).codProdProperty().getValue(),
                            productsView.get(i).cStockProperty().getValue(), productsView.get(i).precioBProperty().getValue());
                    productsView.remove(i);
                }
            }
            tablaProd.setItems(null);
            tablaProd.setItems(productsView);
        }
    }

}
