/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.refunds;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Invoice;
import campis.dp1.models.InvoiceLine;
import campis.dp1.models.InvoiceLineDisplay;
import campis.dp1.models.Refund;
import campis.dp1.models.RefundLine;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
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
import org.hibernate.query.Query;

/**
 *
 * @author Eddy
 */
public class ViewController implements Initializable {

    Main main;
    private ArrayList<Invoice> listInvoice;
    Integer id, idref;
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
    private JFXTextField cbRequestOrderId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idref = ContextFX.getInstance().getId();
        Configuration conf2 = new Configuration();
        conf2.configure("hibernate.cfg.xml");
        conf2.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionF2 = conf2.buildSessionFactory();
        Session s2 = sessionF2.openSession();
        s2.beginTransaction();
        Criteria criteria = s2.createCriteria(Invoice.class);
        this.listInvoice = new ArrayList<>(criteria.list());
        try {
            setupCbRefundId(idref);
            //setupFields(s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        s2.close();
        sessionF2.close();
    }
    
    private Invoice getInvoice(int idref){
        Configuration conf2 = new Configuration();
        conf2.configure("hibernate.cfg.xml");
        conf2.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionF2 = conf2.buildSessionFactory();
        Session s2 = sessionF2.openSession();
        s2.beginTransaction();
        SQLQuery qry = s2.createSQLQuery("SELECT * FROM campis.invoice WHERE id_invoice = "+idref);
        List<Object[]> rows = qry.list();
        Invoice returnable =  new Invoice();
        for (Object[] row : rows) {
            returnable = new Invoice(Integer.parseInt(row[0].toString()),Integer.parseInt(row[1].toString()),
                    Integer.parseInt(row[2].toString()), Double.parseDouble(row[3].toString()), 
                    Double.parseDouble(row[4].toString()), Double.parseDouble(row[5].toString()));
        }
        return returnable;
    }
    
    private void setupCbRefundId(int idref) {
        ArrayList<Integer> ro_ids = new ArrayList<>();
        for (Invoice invoiceOrder1 : this.listInvoice) {
            ro_ids.add(invoiceOrder1.getId_invoice());
        }
        ObservableList rq_olist = FXCollections.observableArrayList(ro_ids);
        cbRequestOrderId.setText(Integer.toString(idref));
        //cbRequestOrderId.getSelectionModel().selectFirst();
        //this.id = this.listInvoice.get(0).getId_invoice();
        //this.invoice = this.listInvoice.get(0);
        this.invoice = getInvoice(idref);
        this.id = invoice.getId_invoice();
        Configuration conf2 = new Configuration();
        conf2.configure("hibernate.cfg.xml");
        conf2.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionF2 = conf2.buildSessionFactory();
        Session s2 = sessionF2.openSession();
        s2.beginTransaction();
        List<InvoiceLine> list = getInvoiceLine(id, s2);
        loadData(s2);
        s2.close();
        sessionF2.close();
    }
    
    private void loadData(Session session) {
        this.rqLineView = FXCollections.observableArrayList();
        Query query = session.createSQLQuery("SELECT * FROM campis.invoice_line WHERE id_invoice = " + this.id);
        ArrayList<Object[]> rows = new ArrayList<>(query.list());
        int j = 0;
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
            List<RefundLine> refLine = getRefLine(rq_line.getId_invoice());
            rqLineView.add(new InvoiceLineDisplay(rq_line.getId_invoice_line(), rq_line.getId_invoice(),
                    rq_line.getId_product(), p_name, p_t_name, rq_line.getCost(), rq_line.getDiscount(),
                    rq_line.getQuantity_ro(), rq_line.getFinal_cost(), rq_line.getQuantity(),refLine.get(j).getQuantity(), maxqtTo));
            j++;
        }
        
        tablaProd.setItems(null);
        tablaProd.setItems(rqLineView);
    }
    
    private List<RefundLine> getRefLine(int idInvoice) {
        Configuration conf2 = new Configuration();
        conf2.configure("hibernate.cfg.xml");
        conf2.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionF2 = conf2.buildSessionFactory();
        Session s2 = sessionF2.openSession();
        s2.beginTransaction();
        SQLQuery qry = s2.createSQLQuery("SELECT id_refund FROM campis.refund WHERE id_invoice ="+idInvoice);
        Integer rows = (Integer)qry.list().get(0);
        SQLQuery qry2 = s2.createSQLQuery("SELECT * FROM campis.refund_line WHERE id_refund="+rows);
        List<Object[]> refs = qry2.list();
        List<RefundLine> returnable = FXCollections.observableArrayList();
        for (Object[] ref : refs) {
            RefundLine refaux = new RefundLine(Integer.parseInt(ref[1].toString()),Integer.parseInt(ref[2].toString()));
            returnable.add(refaux);
        }
        return returnable;
    }
    
    private List<InvoiceLine> getInvoiceLine(Integer id, Session session) {
        Criteria criteria = session.createCriteria(InvoiceLine.class);
        criteria.add(Restrictions.eq("id_invoice", id));
        List<InvoiceLine> rsInvoiceLine = criteria.list();
        return rsInvoiceLine;
    }
    
    @FXML
    private void goListDepartureMove(ActionEvent event) {
    }

}
