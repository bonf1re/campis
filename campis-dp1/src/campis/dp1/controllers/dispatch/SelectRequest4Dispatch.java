/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.dispatch;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.controllers.warehouse.EntryMoveListController;
import campis.dp1.models.Area_;
import campis.dp1.models.Client;
import campis.dp1.models.Product;
import campis.dp1.models.ProductDisplay;
import campis.dp1.models.ProductWH_Move;
import campis.dp1.models.RequestOrder;
import campis.dp1.models.RequestOrderLine;
import campis.dp1.models.RequestOrderLineDisplay;
import campis.dp1.models.SaleCondition;
import campis.dp1.models.utils.GraphicsUtils;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javax.persistence.Tuple;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;


/**
 *
 * @author sergio
 */
public class SelectRequest4Dispatch implements Initializable{

    Main main;
    Integer id;
    String distr;
    float totalAmount = 0;
    float baseTotalAmount = 0;
    float discountTotal = 0;
    float freightTotal = 0;
    Integer n_discount = 1;
    Integer n_tocount = 1;
    float IGV = 0.0f;
    
    private ObservableList<RequestOrderLineDisplay> rqLineView;

    
    @FXML
    private JFXTextField nameClientField;
    @FXML
    private JFXDatePicker creationDate;
    @FXML
    private JFXDatePicker deliveryDate;
    @FXML
    private TableView<RequestOrderLineDisplay> tablaProd;
    @FXML
    private TableColumn<RequestOrderLineDisplay, Integer> idColumn;
    @FXML
    private TableColumn<RequestOrderLineDisplay, String> nameColumn;
    @FXML
    private TableColumn<RequestOrderLineDisplay, String> typeColumn;
    @FXML
    private TableColumn<RequestOrderLineDisplay, Integer> totalQtColumn;
    @FXML
    private TableColumn<RequestOrderLineDisplay, Integer> dspQtColumn;
    @FXML
    private TableColumn<RequestOrderLineDisplay, Integer> missQtColumn;
    @FXML
    private TableColumn<RequestOrderLineDisplay, Float> finalAmountColumn;
    @FXML
    private JFXComboBox<Integer> cbRequestOrderId;  
    private ArrayList<RequestOrder> listRequestOrder;
    
    @FXML
    private TableColumn<RequestOrder, String> stateColumn;
    @FXML
    private JFXTextField subtotalField;
    @FXML
    private JFXTextField discountField;
    @FXML
    private JFXTextField clientField;
    @FXML
    private JFXTextField priorityField;
    
    @FXML
    private JFXTextField addressField;
    @FXML
    private JFXTextField districtField;
    @FXML
    private JFXTextField freightField;
    @FXML
    private JFXTextField stateField;
    @FXML
    private JFXTextField igvField;
    private RequestOrder requestOrder;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Configuration conf2 = new Configuration();
        conf2.configure("hibernate.cfg.xml");
        conf2.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionF2 = conf2.buildSessionFactory();
        Session s2 = sessionF2.openSession();
        s2.beginTransaction();
        Criteria criteria = s2.createCriteria(RequestOrder.class);
        criteria.add(Restrictions.eq("status", "EN PROGRESO"));
        this.listRequestOrder = new ArrayList<>(criteria.list());
        try{
        setupCbRequestOrderId();
        setupFields(s2);
        }catch(Exception e){
            e.printStackTrace();
        }
        s2.close();
        sessionF2.close();
        
    }
    
    
    private void setupCbRequestOrderId() {
        ArrayList<Integer> ro_ids = new ArrayList<>();
        for (RequestOrder requestOrder1 : this.listRequestOrder) {
            ro_ids.add(requestOrder1.getId_request_order());
        }
        ObservableList rq_olist = FXCollections.observableArrayList(ro_ids);
        cbRequestOrderId.setItems(null);
        cbRequestOrderId.setItems(rq_olist);
        cbRequestOrderId.getSelectionModel().selectFirst();
        this.id = this.listRequestOrder.get(0).getId_request_order();
        this.requestOrder = this.listRequestOrder.get(0);
        
        
        cbRequestOrderId.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            int index_rq=cbRequestOrderId.getSelectionModel().getSelectedIndex();
            this.requestOrder= listRequestOrder.get(index_rq);
            this.id = requestOrder.getId_request_order();
            Configuration conf2 = new Configuration();
            conf2.configure("hibernate.cfg.xml");
            conf2.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionF2 = conf2.buildSessionFactory();
            Session s2 = sessionF2.openSession();
            s2.beginTransaction();
            List<RequestOrderLine> list = getReqOrdLine(id,s2);
            loadData(list,s2);
            s2.close();
            sessionF2.close();
            }
        );
    }

    private String getNameDistric(int cod, Session session) {
        String qryStr = "SELECT name FROM campis.district WHERE id_district=" + cod;
        SQLQuery query = session.createSQLQuery(qryStr);
        List list = query.list();
        String returnable = (String) list.get(0);
        return returnable;
    }

    private String getNameCli(Integer id_client,Session session) {
        Query query = session.createSQLQuery("SELECT name FROM campis.client WHERE id_client = "+id_client);        
        String name = (String)query.list().get(0);
        return name;
    }
   

    private void loadData(List<RequestOrderLine> list, Session session) {
        this.rqLineView = FXCollections.observableArrayList();
        Query query = session.createSQLQuery("SELECT * FROM campis.request_order_line WHERE id_request_order = "+this.id);
        ArrayList<Object[]> rows = new ArrayList<>(query.list());
        for (Object[] row : rows) {
            RequestOrderLine rq_line = new RequestOrderLine((int)row[1], ((BigDecimal)row[2]).floatValue(),(int) row[3],(int) row[4],((BigDecimal)row[5]).floatValue());
            Query query_n = session.createSQLQuery("SELECT name FROM campis.product WHERE id_product = "+rq_line.getId_product());
            String p_name = (String)query_n.list().get(0);
            Query query_t = session.createSQLQuery("SELECT id_product_type FROM campis.product WHERE id_product = "+rq_line.getId_product());
            int id_pd_t = (int)query_t.list().get(0);
            query_t = session.createSQLQuery("SELECT description FROM campis.product_type WHERE id_product_type = "+id_pd_t);
            String p_t_name = (String) query_t.list().get(0);
            int miss_qt = rq_line.getQuantity();
            try{
                Query query_d_o = session.createSQLQuery("SELECT id_dispatch_order FROM campis.dispatch_order WHERE id_request_order = "+this.id);
                ArrayList<Integer> dispatch_order_list = new ArrayList<>(query_d_o.list());
                for (Integer dispatch_id : dispatch_order_list) {
                    Query query_d_o_l = session.createSQLQuery("SELECT quantity FROM campis.dispatch_order_line WHERE id_product = "+rq_line.getId_product()+
                                                                " AND id_dispatch_order = "+dispatch_id);
                    ArrayList<Integer> qt_s = new ArrayList<>(query_d_o_l.list());
                    for (Integer qt_ : qt_s) {
                        miss_qt-=qt_;
                    }
                }
            }catch(Exception e){
                
            }
            rqLineView.add(new RequestOrderLineDisplay(rq_line, p_name, p_t_name, 0, miss_qt));
        }
        
        tablaProd.setItems(null);
        tablaProd.setItems(rqLineView);
        
        
    }


    private List<RequestOrderLine> getReqOrdLine(Integer id,Session session) {
        Criteria criteria = session.createCriteria(RequestOrderLine.class);
        criteria.add(Restrictions.eq("id_request_order", id));
        List<RequestOrderLine> rsRequestOrderLine = criteria.list();
        return rsRequestOrderLine;
    }



    private void setupFields(Session session) {
        List<RequestOrderLine> list = getReqOrdLine(id,session);
        RequestOrder request = this.requestOrder;
        String nameCli = getNameCli(request.getId_client(),session);
        distr = getNameDistric(request.getId_district(),session);
        this.igvField.setText("19%");
        this.nameClientField.setText(nameCli);
        this.clientField.setText(Integer.toString(request.getId_client()));
        this.creationDate.setValue(request.getCreation_date().toLocalDateTime().toLocalDate());
        this.deliveryDate.setValue(request.getDelivery_date().toLocalDateTime().toLocalDate());
        this.stateField.setText(request.getStatus());
        this.priorityField.setText(Integer.toString(request.getPriority()));
        this.districtField.setText(distr);
        this.addressField.setText(request.getAddress());
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_product()).asObject());
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdName()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTypeName()));
        totalQtColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
        dspQtColumn.setCellFactory(
                TextFieldTableCell.<RequestOrderLineDisplay,Integer>forTableColumn(new StringConverter<Integer>(){
                    @Override
                    public String toString(Integer value){
                        return value.toString();
                    }
                    @Override
                    public Integer fromString(String string){
                        return Integer.parseInt(string);
                    }       
            }));    
        dspQtColumn.setCellValueFactory(cellData -> cellData.getValue().getDspQt().asObject());
        missQtColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMissQt()).asObject());
        finalAmountColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getQuantity()*cellData.getValue().getCost()).asObject());
        this.tablaProd.setEditable(true);
        loadData(list,session);
    }
    
    @FXML 
    void goListDepartureMove() throws IOException{
        main.showWhDepartureMoveList();
    }
    
    @FXML
    void goDepartureMove(){
        Configuration conf2 = new Configuration();
            conf2.configure("hibernate.cfg.xml");
            conf2.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionF2 = conf2.buildSessionFactory();
            Session s2 = sessionF2.openSession();
            s2.beginTransaction();
        int principal_wh_id = (int) s2.createSQLQuery("SELECT id_warehouse FROM campis.wh_config WHERE wh_type = 0").list().get(0);
        ContextFX.getInstance().setId(principal_wh_id);
        //  Check quantities
        GraphicsUtils gu = new GraphicsUtils();
        int check_rs = checkQts();
        if (check_rs==-1){
            gu.popupError("Error", "No puede insertar valores negativos.", "Volver");
            return;
        }else if (check_rs==-2){
            gu.popupError("Error", "No puede despachar mayor cantidad de la faltante para esta orden.", "Volver");
            return;
        }
        // Send Id_Request_Order, Id_product Quantity per line
        ArrayList<Object> sendable = new ArrayList<>();
        sendable.add(this.id);
        ArrayList<Object> id_prod_qt_pairs = new ArrayList<>();
        for (RequestOrderLineDisplay requestOrderLineDisplay : rqLineView) {
            ArrayList<Integer> dd_pair = new ArrayList<Integer>();
            dd_pair.add(requestOrderLineDisplay.getId_product());
            dd_pair.add(requestOrderLineDisplay.getDspQt().get());
            id_prod_qt_pairs.add(dd_pair);
        }
        
        sendable.add(id_prod_qt_pairs);
        ContextFX.getInstance().setPolymorphic_list(id_prod_qt_pairs);
        //main.showDispatchMoveCreate();
    }

    private int checkQts() {
        for (RequestOrderLineDisplay requestOrderLineDisplay : rqLineView) {
            if (requestOrderLineDisplay.getDspQt().get()<0){
                return -1;
            }
            if (requestOrderLineDisplay.getDspQt().get()>requestOrderLineDisplay.getMissQt()){
                return -2;
            }
        }
        return 0;
    }
    
}
