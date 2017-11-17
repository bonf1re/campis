/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.requestOrder;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Client;
import campis.dp1.models.DispatchOrder;
import campis.dp1.models.DispatchOrderLine;
import campis.dp1.models.District;
import campis.dp1.models.Product;
import campis.dp1.models.ProductDisplay;
import campis.dp1.models.RequestOrder;
import campis.dp1.models.RequestOrderLine;
import campis.dp1.models.SaleCondition;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.temporal.TemporalQueries.zoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
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
public class CreateController implements Initializable {

    Main main;
    Integer id, quantity, selected_id;
    Integer num = 0;
    float totalAmount = 0;
    float baseTotalAmount = 0;
    float discountTotal = 0;
    float freightTotal = 0;
    Integer n_discount = 1;
    Integer n_tocount = 1;
    float IGV = 0.0f;
    
    String message = "";
    private ObservableList<Product> products;
    private ObservableList<ProductDisplay> productsView = FXCollections.observableArrayList();

    @FXML
    private JFXTextField amountField;

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
    private JFXTextField nameClientField;
    @FXML
    private JFXDatePicker deliveryDate;
    @FXML
    private JFXTextField statesField;
    @FXML
    private JFXComboBox<String> priorityField;
    @FXML
    private JFXTextField subtotalField;
    @FXML
    private JFXTextField discountField;
    @FXML
    private JFXComboBox<Integer> clientField;
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
    private JFXTextField igvField;

    @FXML
    private void goAddItem() throws IOException {
        main.showAddItem();
    }

    @FXML
    private void goListRequestOrder() throws IOException {
        productsView.clear();
        ContextFX.getInstance().setTempList(productsView);
        ContextFX.getInstance().setBaseTotAmount(0f);
        ContextFX.getInstance().setTotAmount(0f);
        main.showListRequestOrder();
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
        session.close();
        sessionFactory.close();
        return returnable;
    }

    @FXML
    private void createRequestOrder() throws IOException, ParseException {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        SimpleDateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_delivery = getDate(deliveryDate.getValue());
        int idDist = getDistrict(this.districtField.getValue());
        Boolean verify = verifyDates(currentTimestamp, date_delivery);
        if (verify == TRUE) {
            int prior = Integer.parseInt(priorityField.getValue());
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            RequestOrder requestOrder = new RequestOrder(Timestamp.valueOf(formatIn.format(currentTimestamp)),
                    Timestamp.valueOf((String) formatIn.format(date_delivery)),
                    Float.parseFloat(subtotalField.getText()),
                    Float.parseFloat(amountField.getText()),
                    (String) statesField.getText(),
                    clientField.getValue(), prior, idDist, addressField.getText());
            session.save(requestOrder);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            createRequestOrderLine(requestOrder.getId_request_order());
            createDispatchOrder(requestOrder.getId_request_order());
            ContextFX.getInstance().setTempList(null);
            this.goListRequestOrder();
        } else {
            messageField1.setText(message);
        }
    }

    private ObservableList<Product> getProduct(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product", cod));
        List<Product> list = criteria.list();
        ObservableList<Product> returnable;
        returnable = FXCollections.observableArrayList();
        returnable.add(list.get(0));
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private ObservableList<SaleCondition> getDiscount(int cod) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SaleCondition.class);
        criteria.add(Restrictions.eq("id_to_take", cod));
        criteria.add(Restrictions.le("initial_date", today.getTime()));
        criteria.add(Restrictions.ge("final_date", today.getTime()));
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
        n_discount = 1;
        n_tocount = 1;
        Integer n_d_aux, n_c_aux;
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
            n_d_aux = discounts.get(i).getN_discount();
            n_c_aux = discounts.get(i).getN_tocount();
            if (n_d_aux != 1 || n_c_aux != 1) {
                n_discount = n_d_aux;
                n_tocount = n_c_aux;
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

    @FXML
    private void setDistrictAction() {
        Float freight = getFreight(this.districtField.getValue());
        freightTotal = freightTotal + baseTotalAmount * freight;
        totalAmount = ((totalAmount + freightTotal)*100)/100;
        this.freightField.setText(Float.toString((freightTotal*100)/100));
        this.amountField.setText(Float.toString((totalAmount*100)/100));
    }

    private void loadData(int cod, int quant) {
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
        
        discountTotal = discountTotal + base_amount * disc + 
                        (base_amount - ((quant/n_discount * n_tocount) * products.get(0).getBase_price()));
        freightTotal = ContextFX.getInstance().getFreight();
        totalAmount = baseTotalAmount - discountTotal;
        ContextFX.getInstance().setDiscount(discountTotal);
        ContextFX.getInstance().setFreight(freightTotal);
        ContextFX.getInstance().setBaseTotAmount(baseTotalAmount);
        ContextFX.getInstance().setTotAmount(totalAmount);
        this.subtotalField.setText(Float.toString((baseTotalAmount*100)/100));
        this.discountField.setText(Float.toString((discountTotal*100)/100));
        totalAmount = (totalAmount*IGV*100)/100;
        this.amountField.setText(Float.toString((totalAmount*100)/100));
        ProductDisplay prod = new ProductDisplay(products.get(0).getId_product(), products.get(0).getName(),
                products.get(0).getDescription(), products.get(0).getP_stock(), quantity,
                base_amount, state, products.get(0).getBase_price(),
                products.get(0).getId_unit_of_measure(), products.get(0).getId_product_type(),
                products.get(0).getMax_qt());
        productsView.add(prod);
        ContextFX.getInstance().setTempList(productsView);
        productsView = ContextFX.getInstance().getTempList();
        tablaProd.setItems(null);
        tablaProd.setItems(productsView);
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
        List<Client> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            returnable.add(list.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        this.selected_id = 0;
        tablaProd.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    this.selected_id = newValue.codProdProperty().getValue().intValue();
                });
        try {
            IGV = ContextFX.getInstance().getIGV() + 1;
            statesField.setText("EN PROGRESO");
            priorityField.getItems().addAll("1", "2", "3");
            List<Object[]> dists = getDistricts();
            for (Object[] dist : dists) {
                districtField.getItems().add(dist[1].toString());
            }
            List<Client> clients = getClients();
            for (int i = 0; i < clients.size(); i++) {
                clientField.getItems().add(clients.get(i).getId_client());
            }
            id = ContextFX.getInstance().getId();
            quantity = ContextFX.getInstance().getQuantity();
            num = ContextFX.getInstance().getNum();
            this.igvField.setText(Float.toString((ContextFX.getInstance().getIGV()*100)/100));
            num = num + 1;
            ContextFX.getInstance().setNum(num);
            idColumn.setCellValueFactory(cellData -> cellData.getValue().codProdProperty().asObject());
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asObject());
            quantityColumn.setCellValueFactory(cellData -> cellData.getValue().cStockProperty().asObject());
            unitaryAmountColumn.setCellValueFactory(cellData -> cellData.getValue().precioBProperty().asObject());
            finalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().pesoProperty().asObject());
            stateColumn.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
            loadData(id, quantity);
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

    private Date getDate(LocalDate value) {

        Calendar calendar = new GregorianCalendar(value.getYear(),
                value.getMonthValue() - 1,
                value.getDayOfMonth());
        return calendar.getTime();
    }

    private void createRequestOrderLine(int codReqOrd) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (int i = 0; i < tablaProd.getItems().size(); i++) {
            ProductDisplay prod = tablaProd.getItems().get(i);
            Integer idprod = prod.codProdProperty().getValue();
            Integer quant = prod.cStockProperty().getValue();
            Float cost = prod.precioBProperty().getValue();
            RequestOrderLine reqOrdLine = new RequestOrderLine(quant, cost, codReqOrd, idprod);
            session.save(reqOrdLine);
        }
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
    
    private void createDispatchOrderLine(int codDispOrd) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (int i = 0; i < tablaProd.getItems().size(); i++) {
            ProductDisplay prod = tablaProd.getItems().get(i);
            Integer idprod = prod.codProdProperty().getValue();
            Integer quant = prod.cStockProperty().getValue();
            DispatchOrderLine dispOrdLine = new DispatchOrderLine(codDispOrd, idprod, quant);
            session.save(dispOrdLine);
        }
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    private void createDispatchOrder(int codReqOrd) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DispatchOrder dispatch = new DispatchOrder(codReqOrd, Integer.valueOf(this.priorityField.getValue()), this.statesField.getText());
        session.save(dispatch);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        createDispatchOrderLine(dispatch.getId_dispatch_order());
    }

    @FXML
    private void setNameClientAction(ActionEvent event) {
        int codCli = clientField.getValue();
        String name = getClient(codCli);
        nameClientField.setText(name);
    }

    private String getClient(int codCli) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Client.class);
        criteria.add(Restrictions.eq("id_client", codCli));
        List<Client> list = criteria.list();
        String returnable = list.get(0).getName();
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private Boolean verifyDates(Date creation, Date delivery) {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        Boolean flag = TRUE;
        if (creation.compareTo(currentTimestamp) <= 0) {
            flag = FALSE;
            message = "Fecha debe ser mayor a la actual";
        }
        if (delivery.compareTo(creation) < 0) {
            flag = FALSE;
            message = "Fecha debe ser mayor a la fecha de creación";
        }
        return flag;
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
