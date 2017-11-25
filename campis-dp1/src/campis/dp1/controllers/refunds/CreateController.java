/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.refunds;

import campis.dp1.Main;
import campis.dp1.models.DispatchOrder;
import campis.dp1.models.Invoice;
import campis.dp1.models.InvoiceLine;
import campis.dp1.models.InvoiceLineDisplay;
import campis.dp1.models.Refund;
import campis.dp1.models.RefundLine;
import campis.dp1.models.RefundLineDisplay;
import campis.dp1.models.RequestOrder;
import campis.dp1.models.RequestOrderLine;
import campis.dp1.models.RequestOrderLineDisplay;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

/**
 *
 * @author Eddy
 */
public class CreateController implements Initializable {

    Main main;
    private ArrayList<Invoice> listInvoice;
    Integer id;
    String distr;
    float totalAmount = 0;
    float baseTotalAmount = 0;
    float discountTotal = 0;
    float freightTotal = 0;
    Integer n_discount = 1;
    Integer n_tocount = 1;
    float IGV = 0.0f;
    private Invoice invoice;
    private ObservableList<InvoiceLineDisplay> rqLineView;
    int selected_quantity;

    @FXML
    private JFXTextField nameClientField;
    @FXML
    private JFXDatePicker creationDate;
    @FXML
    private JFXDatePicker deliveryDate;
    @FXML
    private TableView<InvoiceLineDisplay> tablaProd;
    @FXML
    private TableColumn<InvoiceLineDisplay, Integer> idColumn;
    @FXML
    private TableColumn<InvoiceLineDisplay, String> nameColumn;
    @FXML
    private TableColumn<InvoiceLineDisplay, String> typeColumn;
    @FXML
    private TableColumn<InvoiceLineDisplay, Integer> totalQtColumn;
    @FXML
    private TableColumn<InvoiceLineDisplay, Integer> refundQtColumn;
    @FXML
    private TableColumn<InvoiceLineDisplay, Integer> refundMaxQtColumn;
    @FXML
    private Label messageField1;
    @FXML
    private Label messageField2;
    @FXML
    private JFXTextField clientField;
    @FXML
    private JFXTextField districtField;
    @FXML
    private JFXTextField stateField;
    @FXML
    private JFXTextField priorityField;
    @FXML
    private JFXTextField igvField;
    @FXML
    private JFXComboBox<Integer> cbRequestOrderId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Configuration conf2 = new Configuration();
        conf2.configure("hibernate.cfg.xml");
        conf2.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionF2 = conf2.buildSessionFactory();
        Session s2 = sessionF2.openSession();
        s2.beginTransaction();
        Criteria criteria = s2.createCriteria(Invoice.class);
        this.listInvoice = new ArrayList<>(criteria.list());
        try {
            setupCbRefundId();
            setupFields(s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        s2.close();
        sessionF2.close();
    }

    private void setupCbRefundId() {
        ArrayList<Integer> ro_ids = new ArrayList<>();
        for (Invoice invoiceOrder1 : this.listInvoice) {
            ro_ids.add(invoiceOrder1.getId_invoice());
        }
        ObservableList rq_olist = FXCollections.observableArrayList(ro_ids);
        cbRequestOrderId.setItems(null);
        cbRequestOrderId.setItems(rq_olist);
        cbRequestOrderId.getSelectionModel().selectFirst();
        this.id = this.listInvoice.get(0).getId_invoice();
        this.invoice = this.listInvoice.get(0);

        cbRequestOrderId.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    int index_rq = cbRequestOrderId.getSelectionModel().getSelectedIndex();
                    this.invoice = listInvoice.get(index_rq);
                    this.id = invoice.getId_invoice();
                    Configuration conf2 = new Configuration();
                    conf2.configure("hibernate.cfg.xml");
                    conf2.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
                    SessionFactory sessionF2 = conf2.buildSessionFactory();
                    Session s2 = sessionF2.openSession();
                    s2.beginTransaction();
                    List<InvoiceLine> list = getInvoiceLine(id, s2);
                    loadData(list, s2);
                    s2.close();
                    sessionF2.close();
                }
        );
    }

    private String getNameDistric(int id_dispatch_order, Session session) {
        Query query = session.createSQLQuery("SELECT id_request_order FROM campis.dispatch_order WHERE id_dispatch_order = " + id_dispatch_order);
        Integer id_request_order = (Integer) query.list().get(0);
        Query query2 = session.createSQLQuery("SELECT id_district FROM campis.request_order WHERE id_request_order = " + id_request_order);
        Integer id_district = (Integer) query2.list().get(0);
        String qryStr = "SELECT name FROM campis.district WHERE id_district=" + id_district;
        SQLQuery query3 = session.createSQLQuery(qryStr);
        List list = query3.list();
        String returnable = (String) list.get(0);
        return returnable;
    }

    private String getNameCli(Integer id_dispatch_order, Session session) {
        Query query = session.createSQLQuery("SELECT id_request_order FROM campis.dispatch_order WHERE id_dispatch_order = " + id_dispatch_order);
        Integer id_request_order = (Integer) query.list().get(0);
        Query query2 = session.createSQLQuery("SELECT id_client FROM campis.request_order WHERE id_request_order = " + id_request_order);
        Integer id_client = (Integer) query2.list().get(0);
        Query query3 = session.createSQLQuery("SELECT name FROM campis.client WHERE id_client = " + id_client);
        String name = (String) query3.list().get(0);
        return name;
    }

    private Integer getIdCli(Integer id_dispatch_order, Session session) {
        Query query = session.createSQLQuery("SELECT id_request_order FROM campis.dispatch_order WHERE id_dispatch_order = " + id_dispatch_order);
        Integer id_request_order = (Integer) query.list().get(0);
        Query query2 = session.createSQLQuery("SELECT id_client FROM campis.request_order WHERE id_request_order = " + id_request_order);
        Integer id_client = (Integer) query2.list().get(0);
        return id_client;
    }

    private List<InvoiceLine> getInvoiceLine(Integer id, Session session) {
        Criteria criteria = session.createCriteria(InvoiceLine.class);
        criteria.add(Restrictions.eq("id_invoice", id));
        List<InvoiceLine> rsInvoiceLine = criteria.list();
        return rsInvoiceLine;
    }

    private void loadData(List<InvoiceLine> list, Session session) {
        this.rqLineView = FXCollections.observableArrayList();
        Query query = session.createSQLQuery("SELECT * FROM campis.invoice_line WHERE id_invoice = " + this.id);
        ArrayList<Object[]> rows = new ArrayList<>(query.list());
        for (Object[] row : rows) {
            InvoiceLine rq_line = new InvoiceLine(Integer.parseInt(row[0].toString()), Integer.parseInt(row[1].toString()), Integer.parseInt(row[2].toString()),
                    Integer.parseInt(row[3].toString()), Float.parseFloat(row[4].toString()), Float.parseFloat(row[5].toString()),
                    Integer.parseInt(row[6].toString()), Float.parseFloat(row[7].toString()));
            Query query_n = session.createSQLQuery("SELECT name FROM campis.product WHERE id_product = " + rq_line.getId_product());
            String p_name = (String) query_n.list().get(0);
            Query query_t = session.createSQLQuery("SELECT id_product_type FROM campis.product WHERE id_product = " + rq_line.getId_product());
            int id_pd_t = (int) query_t.list().get(0);
            query_t = session.createSQLQuery("SELECT description FROM campis.product_type WHERE id_product_type = " + id_pd_t);
            String p_t_name = (String) query_t.list().get(0);
            int miss_qt = rq_line.getQuantity();
            try {
                Query query_d_o = session.createSQLQuery("SELECT id_dispatch_order FROM campis.invoice WHERE id_invoice = " + this.id);
                ArrayList<Integer> dispatch_order_list = new ArrayList<>(query_d_o.list());
                for (Integer dispatch_id : dispatch_order_list) {
                    Query query_d_o_l = session.createSQLQuery("SELECT quantity FROM campis.dispatch_order_line WHERE id_product = " + rq_line.getId_product()
                            + " AND id_dispatch_order = " + dispatch_id);
                    ArrayList<Integer> qt_s = new ArrayList<>(query_d_o_l.list());
                    for (Integer qt_ : qt_s) {
                        miss_qt -= qt_;
                    }
                }
            } catch (Exception e) {

            }
            Query query_r = session.createSQLQuery("SELECT * FROM campis.refund WHERE id_invoice = " + rq_line.getId_invoice());
            List<Object[]> refs = query_r.list();
            List<Refund> retu = FXCollections.observableArrayList();
            for (Object[] ref : refs) {
                Refund refaux = new Refund(Integer.parseInt(ref[1].toString()));
                retu.add(refaux);
            }
            Integer maxqtTo = 0;
            for (int i = 0; i < retu.size(); i++) {
                Query query_qt = session.createSQLQuery("SELECT quantity FROM campis.invoice_line WHERE id_product=" + rq_line.getId_product());
                int maxqt = (Integer) query_qt.list().get(0);
                maxqtTo = maxqtTo + maxqt;
            }
            maxqtTo = rq_line.getQuantity() - maxqtTo;
            rqLineView.add(new InvoiceLineDisplay(rq_line.getId_invoice_line(), rq_line.getId_invoice(),
                    rq_line.getId_product(), p_name, p_t_name, rq_line.getCost(), rq_line.getDiscount(),
                    rq_line.getQuantity_ro(), rq_line.getFinal_cost(), rq_line.getQuantity(), 0, maxqtTo));
        }

        tablaProd.setItems(null);
        tablaProd.setItems(rqLineView);
    }

    private List<RequestOrder> getRequestOrder(int cod, Session session) {
        Query query = session.createSQLQuery("SELECT id_request_order FROM campis.dispatch_order WHERE id_dispatch_order = " + cod);
        Integer id_request_order = (Integer) query.list().get(0);
        Criteria criteria = session.createCriteria(RequestOrder.class);
        criteria.add(Restrictions.eq("id_request_order", id_request_order));
        List<RequestOrder> rsRequestLine = criteria.list();
        return rsRequestLine;
    }

    private void setupFields(Session session) {
        this.selected_quantity = 1;
        tablaProd.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue == null) {
                    return;
                }
                this.selected_quantity = newValue.getQuantity().getValue().intValue();
                System.out.println("kshakdjaskjasdsadas"+selected_quantity);
                }
            );
        List<InvoiceLine> list = getInvoiceLine(id, session);
        Invoice invo = this.invoice;
        List<RequestOrder> request2 = getRequestOrder(invo.getId_dispatch_order(), session);
        RequestOrder request = request2.get(0);
        String nameCli = getNameCli(invo.getId_dispatch_order(), session);
        distr = getNameDistric(invo.getId_dispatch_order(), session);
        Integer idCli = getIdCli(invo.getId_dispatch_order(), session);
        this.igvField.setText("19%");
        this.nameClientField.setText(nameCli);
        this.clientField.setText(Integer.toString(idCli));
        this.creationDate.setValue(request.getCreation_date().toLocalDateTime().toLocalDate());
        this.deliveryDate.setValue(request.getDelivery_date().toLocalDateTime().toLocalDate());
        this.stateField.setText(request.getStatus());
        this.priorityField.setText(Integer.toString(request.getPriority()));
        this.districtField.setText(distr);
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getId_product().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameproduct());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().getTypeproduct());
        totalQtColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
        refundQtColumn.setCellValueFactory(cellData -> cellData.getValue().getQtRef().asObject());
        refundMaxQtColumn.setCellValueFactory(cellData -> cellData.getValue().getQtRefMax().asObject());
        refundQtColumn.setCellFactory(
                TextFieldTableCell.<InvoiceLineDisplay,Integer>forTableColumn(new StringConverter<Integer>(){
                    @Override
                    public String toString(Integer value) {
                        if ((selected_quantity > -1 && selected_quantity < value) || (value < 0))
                            return "0";
                        return value.toString();
                    }
                    @Override
                    public Integer fromString(String string) {
                        return Integer.parseInt(string);
                    }
                }));

                refundQtColumn.setCellValueFactory(
                    cellData->cellData.getValue().getQtRef().asObject()
                );
        this.tablaProd.setEditable(true);
        loadData(list, session);
    }

    @FXML
    private void goListDepartureMove(ActionEvent event) throws IOException {
        main.showListRefund();
    }

    @FXML
    private void goCreateRefund(ActionEvent event) throws IOException {
        Configuration conf2 = new Configuration();
        conf2.configure("hibernate.cfg.xml");
        conf2.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionF2 = conf2.buildSessionFactory();
        Session s2 = sessionF2.openSession();
        s2.beginTransaction();
        Criteria criteria = s2.createCriteria(Refund.class);
        Refund ref = new Refund(cbRequestOrderId.getValue());
        /*Query qry = s2.createSQLQuery("INSERT INTO campis.refund VALUES(DEFAULT,"++
                ",'COMPLETO',DEFAULT)");
        int idref = (int)qry.executeUpdate();*/
        s2.save(ref);
        
        int idref = ref.getId_refund();
        for (int i = 0; i < tablaProd.getItems().size(); i++) {
            /*Query qry2 = s2.createSQLQuery("INSERT INTO campis.refund_line VALUES(DEFAULT,"+idref+","+tablaProd.getItems().get(i).getQtRef().getValue()
                +")");
        int idref2 = qry2.executeUpdate();*/
            RefundLine refLine = new RefundLine(idref,tablaProd.getItems().get(i).getQtRef().getValue());
            s2.save(refLine);
        }
        s2.getTransaction().commit();
        s2.close();
        sessionF2.close();
        this.goListDepartureMove(event);
    }
}
