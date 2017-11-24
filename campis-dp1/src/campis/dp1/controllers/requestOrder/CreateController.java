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
import campis.dp1.models.Parameters;
import campis.dp1.models.Product;
import campis.dp1.models.ProductDisplay;
import campis.dp1.models.RequestLineDisplay;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
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
public class CreateController implements Initializable {

    Main main;
    Integer id, quantity, selected_id;
    Integer num = 0;
    float totalAmount = 0;
    float baseTotalAmount = 0;
    float discountTotal = 0;
    float igvTotal = 0;
    float freightTotal = 0;
    Integer n_discount = 1;
    Integer n_tocount = 1;
    float IGV = 0.0f;
    Parameters param = new Parameters();
    String message = "";
    private ObservableList<Product> products;
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

    @FXML
    private TableColumn<RequestLineDisplay, String> stateColumn;
    @FXML
    private JFXComboBox<String> nameClientField;
    @FXML
    private JFXDatePicker deliveryDate;
    @FXML
    private JFXTextField statesField;
    @FXML
    private JFXComboBox<String> priorityField;

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
    private Label igvField;
    
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
    private void goAddItem() throws IOException {
        main.showAddItem();
    }

    @FXML
    private void goListRequestOrder() throws IOException {
        requestLineView.clear();
        ContextFX.getInstance().setReqList(requestLineView);
        ContextFX.getInstance().setBaseTotAmount(0f);
        ContextFX.getInstance().setDiscount(0f);
        ContextFX.getInstance().setTotAmount(0f);
        ContextFX.getInstance().setDiscount(0f);
        ContextFX.getInstance().setIgvTot(0f);
        
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
        Boolean verify = verifyDates(currentTimestamp,date_delivery);
        if (verify) {
            int prior = Integer.parseInt(priorityField.getValue());
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            RequestOrder requestOrder = new RequestOrder(
                    Timestamp.valueOf(formatIn.format(currentTimestamp)),
                    Timestamp.valueOf((String) formatIn.format(date_delivery)),
                    Float.parseFloat(subtotalField.getText()),
                    Float.parseFloat(amountField.getText()),
                    Float.parseFloat(IGVField.getText()),
                    Float.parseFloat(freightField.getText()),
                    Float.parseFloat(discountField.getText()),                   
                    (String) statesField.getText(),
                    clientField.getValue(), prior, idDist);
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

    private ObservableList<SaleCondition> getDiscount() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SaleCondition.class);
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
        Integer type, maxQ, taken_id, n_d_aux, n_c_aux;
        Float returnable = Float.valueOf(0);
        
        n_discount = 1;
        n_tocount = 1;
        
        for (int i = 0; i < discounts.size(); i++) {
            maxQ = discounts.get(i).getLimits();
            if (maxQ > quant) continue; 
            type = discounts.get(i).getId_sale_condition_type();
            taken_id = discounts.get(i).getId_to_take();
            n_d_aux = discounts.get(i).getN_discount();
            n_c_aux = discounts.get(i).getN_tocount();
            
            if (type == 1) {
                if (Objects.equals(prod.getId_product(), taken_id))
                    returnable = returnable + discounts.get(i).getAmount() / 100;

            } else if (type == 2) {
                if (Objects.equals(prod.getId_product_type(), taken_id)) {
                    returnable = returnable + discounts.get(i).getAmount() / 100;
                }
            }
            
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
        float noIGVamount;
        freightTotal = getFreight(this.districtField.getValue());
        noIGVamount = baseTotalAmount - discountTotal + freightTotal;
        igvTotal = noIGVamount * (IGV);
        totalAmount = noIGVamount * (IGV+1);
        this.freightField.setText(String.format("%.2f", freightTotal));
        this.amountField.setText(String.format("%.2f", totalAmount));
    }

    private void loadData(int cod, int quant) {
        products = FXCollections.observableArrayList();
        requestLineView = ContextFX.getInstance().getReqList();
        products = getProduct(cod);
        ObservableList<SaleCondition> discounts = getDiscount();
        
        //porcentaje de descuento
        Float disc = verifyConditions(discounts, products.get(0), quant);
        //
        Float base_amount = quant * products.get(0).getBase_price();
        String state = "POR ENTREGAR";
        baseTotalAmount = ContextFX.getInstance().getBaseTotAmount();
        baseTotalAmount = baseTotalAmount + base_amount;
        discountTotal = ContextFX.getInstance().getDiscount();
        
        // cantidad neta de descuento por promocion
        Float promo = (base_amount - ((quant/n_discount * n_tocount) * products.get(0).getBase_price()));
        //
        discountTotal = discountTotal + base_amount * disc + promo;
        freightTotal = ContextFX.getInstance().getFreight();
        igvTotal = (baseTotalAmount - discountTotal + freightTotal) * IGV;
        totalAmount = (baseTotalAmount - discountTotal + freightTotal)*(IGV+1);
        ContextFX.getInstance().setDiscount(discountTotal);
        ContextFX.getInstance().setBaseTotAmount(baseTotalAmount);
        ContextFX.getInstance().setIgvTot(igvTotal);
        ContextFX.getInstance().setTotAmount(totalAmount);
        this.subtotalField.setText(String.format("%.2f",baseTotalAmount));
        this.discountField.setText(String.format("%.2f",discountTotal));
        this.freightField.setText(String.format("%.2f",freightTotal));
        this.IGVField.setText(String.format("%.2f",igvTotal));
        this.amountField.setText(String.format("%.2f",totalAmount));
        RequestLineDisplay prod = new RequestLineDisplay(products.get(0).getId_product(), products.get(0).getName(),
                quantity,
                products.get(0).getBase_price(),
                base_amount,
                (base_amount * disc + promo),
                state);
        requestLineView.add(prod);
        ContextFX.getInstance().setReqList(requestLineView);
        
        tablaProd.setItems(null);
        tablaProd.setItems(requestLineView);
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
        
        
        subtotalField.setText(String.format("%.2f", baseTotalAmount));
        discountField.setText(String.format("%.2f", discountTotal));
        freightField.setText(String.format("%.2f", freightTotal));
        IGVField.setText(String.format("%.2f", igvTotal));
        amountField.setText(String.format("%.2f", totalAmount));
        
        
        
        this.selected_id = 0;
        tablaProd.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    this.selected_id = newValue.getId_product().getValue().intValue();
                });
        try {
            IGV = ContextFX.getInstance().getIGV();
            igvField.setText(String.format("%.2f", IGV));
            
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
            for (int i = 0; i < clients.size(); i++) {
                nameClientField.getItems().add(clients.get(i).getName());
            }
            id = ContextFX.getInstance().getId();
            quantity = ContextFX.getInstance().getQuantity();
            num = ContextFX.getInstance().getNum();
            this.igvField.setText(Float.toString((ContextFX.getInstance().getIGV()*100)/100));
            num = num + 1;
            ContextFX.getInstance().setNum(num);
            idColumn.setCellValueFactory(cellData -> cellData.getValue().getId_product().asObject());
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
            quantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
            unitaryAmountColumn.setCellValueFactory(cellData -> cellData.getValue().getBase_price().asObject());
            finalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().getFinal_price().asObject());
            stateColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus());
            discountColumn.setCellValueFactory(cellData -> cellData.getValue().getDiscount().asObject());
            loadData(id, quantity);
        } catch (NullPointerException e) {
            idColumn.setCellValueFactory(cellData -> cellData.getValue().getId_product().asObject());
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
            quantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
            unitaryAmountColumn.setCellValueFactory(cellData -> cellData.getValue().getBase_price().asObject());
            finalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().getFinal_price().asObject());
            stateColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus());
            discountColumn.setCellValueFactory(cellData -> cellData.getValue().getDiscount().asObject());
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
            RequestLineDisplay req = tablaProd.getItems().get(i);
           
            RequestOrderLine reqOrdLine = new RequestOrderLine(
                    req.getQuantity().getValue(),
                    req.getBase_price().getValue(),
                    codReqOrd,
                    req.getId_product().getValue(),
                    req.getDiscount().getValue());
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
            RequestLineDisplay req = tablaProd.getItems().get(i);
            
            DispatchOrderLine dispOrdLine = new DispatchOrderLine(codDispOrd, 
                                                req.getId_product().getValue(), 
                                                req.getQuantity().getValue());
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
        DispatchOrder dispatch = new DispatchOrder(codReqOrd, 
                Integer.valueOf(this.priorityField.getValue()), this.statesField.getText());
        session.save(dispatch);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        createDispatchOrderLine(dispatch.getId_dispatch_order());
    }

    @FXML
    private void setNameClientAction(ActionEvent event) {
        int codCli = clientField.getValue();
        String name = getClientById(codCli);
        nameClientField.setValue(name);
    }
    
    @FXML
    private void setIDClientAction(ActionEvent event) {
        String nameCli = nameClientField.getValue();
        int id = getClientByName(nameCli);
        clientField.setValue(id);
    }

    private String getClientById(int codCli) {
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
    
    private int getClientByName(String nameCli) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Client.class);
        criteria.add(Restrictions.eq("name", nameCli));
        List<Client> list = criteria.list();
        Integer returnable = list.get(0).getId_client();
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private Boolean verifyDates(Date creation, Date delivery) {
        //Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        
        /*
        if (creation.compareTo(currentTimestamp) <= 0) {
            flag = FALSE;
            message = "Fecha debe ser mayor a la actual";
        }
        */
        if (delivery.compareTo(creation) < 0) {
            message = "Fecha debe ser mayor a la fecha de creación";
            return false;
        }
        return true;
    }

    private void deleteItems(RequestLineDisplay rd) {
        
               
        discountTotal = discountTotal - rd.getDiscount().getValue();
        baseTotalAmount = baseTotalAmount - 
                (rd.getBase_price().getValue() * rd.getQuantity().getValue());
        igvTotal = (baseTotalAmount - discountTotal + freightTotal) * (IGV);
        totalAmount = (baseTotalAmount - discountTotal + freightTotal) * (IGV+1);
        
        
        ContextFX.getInstance().setBaseTotAmount(baseTotalAmount);
        ContextFX.getInstance().setDiscount(discountTotal);
        ContextFX.getInstance().setTotAmount(totalAmount);
        baseTotalAmount = param.roundingMethod(baseTotalAmount, 2);
        discountTotal = param.roundingMethod(discountTotal, 2);
        totalAmount = param.roundingMethod(totalAmount, 2);
        this.subtotalField.setText(Float.toString(baseTotalAmount));
        this.discountField.setText(Float.toString(discountTotal));
        this.IGVField.setText(Float.toString(discountTotal));
        //this.amountField.setText(Float.toString(totalAmount));
    }

    @FXML
    private void deleteItem(ActionEvent event) {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            Integer id_prod = ContextFX.getInstance().getId();
            for (int i = 0; i < requestLineView.size(); i++) {
                if (requestLineView.get(i).getId_product().getValue().compareTo(id_prod) == 0) {
                    deleteItems(requestLineView.get(i));
                    requestLineView.remove(i);
                }
            }
            tablaProd.setItems(null);
            tablaProd.setItems(requestLineView);
        }
    }

}
