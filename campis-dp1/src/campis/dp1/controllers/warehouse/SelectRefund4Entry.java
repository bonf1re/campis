package campis.dp1.controllers.warehouse;

import campis.dp1.models.ProductWH_Move;
import campis.dp1.models.Refund;
import campis.dp1.models.RefundLine;
import campis.dp1.models.RequestOrder;
import campis.dp1.models.RequestOrderLine;
import campis.dp1.models.RequestOrderLineDisplay;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

public class SelectRefund4Entry implements Initializable{

    @FXML
    private JFXTextField nameClientField;

    @FXML
    private TableView<ProductWH_Move> tablaProd;

    @FXML
    private TableColumn<ProductWH_Move, Integer> idColumn;

    @FXML
    private TableColumn<ProductWH_Move, String> nameColumn;

    @FXML
    private TableColumn<ProductWH_Move, String> typeColumn;

    @FXML
    private TableColumn<ProductWH_Move, Integer> qtColumn;

    @FXML
    private Label messageField1;

    @FXML
    private Label messageField2;

    @FXML
    private JFXTextField clientField;

    @FXML
    private JFXComboBox<Integer> cbRefundId;

    @FXML
    private JFXTextField invoiceField;

    @FXML
    private JFXTextField requestField;
    private Refund refund;
    private ArrayList<Refund> listRefund;
    private Integer id;
    private ObservableList<RefundLine> rfLineView;

    @FXML
    void goListEntryMove() {

    }

    @FXML
    void goRefundMoveCreate() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Configuration conf2 = new Configuration();
        conf2.configure("hibernate.cfg.xml");
        conf2.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionF2 = conf2.buildSessionFactory();
        Session s2 = sessionF2.openSession();
        s2.beginTransaction();
        Criteria criteria = s2.createCriteria(Refund.class);
        criteria.add(Restrictions.eq("status", "EN PROGRESO"));
        this.listRefund = new ArrayList<>(criteria.list());
        try{
        setupCbRefundId();
        setupFields(s2);
        }catch(Exception e){
            e.printStackTrace();
        }
        s2.close();
        sessionF2.close();
    }

    private void setupCbRefundId() {
        ArrayList<Integer> refund_ids = new ArrayList<>();
        for (Refund refund_ : this.listRefund) {
            refund_ids.add(refund_.getId_refund());
        }
        ObservableList rq_olist = FXCollections.observableArrayList(refund_ids);
        cbRefundId.setItems(null);
        cbRefundId.setItems(rq_olist);
        cbRefundId.getSelectionModel().selectFirst();
        this.id = this.listRefund.get(0).getId_refund();
        this.refund = this.listRefund.get(0);
        
        
        cbRefundId.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            int index_rf=cbRefundId.getSelectionModel().getSelectedIndex();
            this.refund= listRefund.get(index_rf);
            this.id = refund.getId_refund();
            Configuration conf2 = new Configuration();
            conf2.configure("hibernate.cfg.xml");
            conf2.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionF2 = conf2.buildSessionFactory();
            Session s2 = sessionF2.openSession();
            s2.beginTransaction();
            List<RefundLine> list = getRefundLine(id,s2);
            loadData(list,s2);
            setupFields(s2);
            s2.close();
            sessionF2.close();
            }
        );
    }

    private void setupFields(Session s2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<RefundLine> getRefundLine(Integer id, Session s2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void loadData(List<RefundLine> list, Session session) {
        this.rfLineView = FXCollections.observableArrayList();
        Query query = session.createSQLQuery("SELECT * FROM campis.refund_line WHERE id_refund = "+this.id);
        ArrayList<Object[]> rows = new ArrayList<>(query.list());
        for (Object[] row : rows){
            RefundLine aux_rf = new RefundLine((int)row[0],(int)row[0]);
            
            rfLineView.add();
        }
        
        tablaProd.setItems(null);
        tablaProd.setItems(rfLineView);
    }

}
