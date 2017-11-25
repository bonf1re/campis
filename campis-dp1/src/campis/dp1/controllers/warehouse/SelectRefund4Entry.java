package campis.dp1.controllers.warehouse;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Product;
import campis.dp1.models.ProductWH_Move;
import campis.dp1.models.Refund;
import campis.dp1.models.RefundLine;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
    private ObservableList<ProductWH_Move> rfLineView;
    private Main main;
    
    @FXML
    void goListEntryMove() throws IOException {
        main.showWhEntryMoveList();
    }

    @FXML
    void goRefundMoveCreate() throws IOException {
        // Should take the refund ID for further save
        // Should take the the list of ProductWH_Move
        ArrayList<Object> sendable = new ArrayList<>();
        sendable.add(this.refund.getId_refund());
        sendable.add(new ArrayList<>(this.rfLineView));
        ContextFX.getInstance().setPolymorphic_list(sendable);
        Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
        int principal_wh_id = (int) session.createSQLQuery("SELECT id_warehouse FROM campis.wh_config WHERE wh_type = 1").list().get(0);
        ContextFX.getInstance().setId(principal_wh_id);
        session.close();
        sessionFactory.close();
        main.showRefundMoveCreate();
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
        criteria.add(Restrictions.eq("status", "Por Ingresar"));
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
        this.id = this.listRefund.get(0).getId_refund();
        this.refund = this.listRefund.get(0);
        cbRefundId.getSelectionModel().selectFirst();
        
        
        
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

    private void setupFields(Session session) {
        List<RefundLine> list = getRefundLine(id,session);
        Refund ref = this.refund;
        int idCli = getIdCli(ref.getId_invoice(),session);
        String nameCli = getNameCli(ref.getId_invoice(),session);
        this.nameClientField.setText(nameCli);
        this.clientField.setText(String.valueOf(idCli));
        this.requestField.setText(String.valueOf((int)session
                                    .createSQLQuery("SELECT id_dispatch_order FROM campis.invoice WHERE id_invoice = "+ref.getId_invoice())
                                    .list()
                                    .get(0)));
        this.invoiceField.setText(String.valueOf(ref.getId_invoice()));
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_product()).asObject());
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId_product_type().toString()));
        qtColumn.setCellValueFactory(cellData -> cellData.getValue().getNum().asObject());
        
        
        this.tablaProd.setEditable(true);
        loadData(list,session);
    }

    private List<RefundLine> getRefundLine(Integer id, Session s2) {
        Criteria criteria = s2.createCriteria(RefundLine.class);
        criteria.add(Restrictions.eq("id_refund", id));
        List<RefundLine> aux = criteria.list();
        ArrayList<RefundLine> returnable = new ArrayList<>(aux);
        return returnable;
    }

    private void loadData(List<RefundLine> list, Session session) {
        this.rfLineView = FXCollections.observableArrayList();
        Query query = session.createSQLQuery("SELECT * FROM campis.refund_line WHERE id_refund = "+this.refund.getId_refund());
        ArrayList<Object[]> rows = new ArrayList<>(query.list());
        for (Object[] row : rows){
            RefundLine aux_rf = new RefundLine((int)row[1],(int)row[2]);
            aux_rf.setId_product((int)row[3]);
            System.out.println(row[0].toString());
            System.out.println(row[1].toString());
            System.out.println(row[2].toString());
            System.out.println(row[3].toString());
            System.out.println("Quantity in rf_line is:"+aux_rf.getQuantity());
            Product aux_prod = (Product) session.createCriteria(Product.class)
                                .add(Restrictions.eq("id_product", aux_rf.getId_product())).list().get(0);
            //cantLote.setCellValueFactory(cellData -> cellData.getValue().getQtLt().asObject());
            //cant_x_lote.setCellValueFactory(cellData -> cellData.getValue().getNum().asObject());
            rfLineView.add(new ProductWH_Move(aux_prod,0,1,(int)row[2]));
        }
        tablaProd.setItems(null);
        tablaProd.setItems(rfLineView);
    }

    private String getNameCli(int id_invoice, Session session) {
        int id_client = getIdCli(id_invoice, session);
        return (String) session.createSQLQuery("SELECT name FROM campis.client WHERE id_client = "+id_client)
                                            .list()
                                            .get(0);
    }

    private int getIdCli(Integer id_invoice, Session session) {
        int id_dispatch_order = (int) session.createSQLQuery("SELECT id_dispatch_order FROM campis.invoice WHERE id_invoice = "+id_invoice)
                                        .list()
                                        .get(0);
        int id_request_order = (int) session.createSQLQuery("SELECT id_request_order FROM campis.dispatch_order WHERE id_dispatch_order = "+id_dispatch_order)
                                        .list()
                                        .get(0);
        
        return (int) session.createSQLQuery("SELECT id_client FROM campis.request_order WHERE id_request_order = "+id_request_order)
                    .list()
                    .get(0);
    }

}
