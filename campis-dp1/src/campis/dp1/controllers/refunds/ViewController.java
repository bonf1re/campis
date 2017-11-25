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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Eddy
 */
public class ViewController implements Initializable{
    
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
    private TableView<InvoiceLine> tablaProd;
    @FXML
    private TableColumn<InvoiceLine, Integer> idColumn;
    @FXML
    private TableColumn<InvoiceLine, String> nameColumn;
    @FXML
    private TableColumn<InvoiceLine, String> typeColumn;
    @FXML
    private TableColumn<InvoiceLine, Integer> totalQtColumn;
    @FXML
    private TableColumn<InvoiceLine, Integer> refundQtColumn;
    @FXML
    private TableColumn<InvoiceLine, Integer> refundMaxQtColumn;
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
        /*idref = ContextFX.getInstance().getId();
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
        cbRequestOrderId.setText(rq_olist.get(0).toString());
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
        );*/
    }
    
    @FXML
    private void goListDepartureMove(ActionEvent event) {
    }
    
}
