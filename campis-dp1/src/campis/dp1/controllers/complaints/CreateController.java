package campis.dp1.controllers.complaints;

import campis.dp1.Main;
import campis.dp1.models.Complaint;
import campis.dp1.models.RequestOrder;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Marco
 */
public class CreateController implements Initializable {

    private Main main;
    @FXML
    private JFXTextField descriptionField;
    @FXML
    private JFXComboBox requestField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Integer> requestList = getDispatchOrders();
        for (int i = 0; i < requestList.size(); i++) {
            requestField.getItems().addAll(requestList.get(i));
        }
    }

    @FXML
    private void goListComplaint() throws IOException {
        main.showListComplaint();
    }
    
    public static List<Integer> getDispatchOrders(){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT id_dispatch_order FROM campis.dispatch_order;";
        SQLQuery qry = session.createSQLQuery(qryStr);
        List<Object[]> rows = qry.list();
        List<Integer> types = FXCollections.observableArrayList();
        for (Object row : rows) {
            types.add(Integer.parseInt(row.toString()));
        }
        session.close();
        sessionFactory.close();
        return types;

    }
    
    /*public static List<Integer> getRequestOrders() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT id_request_order FROM campis.request_order;";
        SQLQuery qry = session.createSQLQuery(qryStr);
        List<Object[]> rows = qry.list();
        List<Integer> types = FXCollections.observableArrayList();
        for (Object row : rows) {
            types.add(Integer.parseInt(row.toString()));
        }
        session.close();
        sessionFactory.close();
        return types;
    }*/

    @FXML
    public void insertComplaint() throws IOException {
        Complaint complaint = new Complaint(descriptionField.getText(), Integer.parseInt(requestField.getEditor().getText()));

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "INSERT INTO campis.complaint VALUES(DEFAULT,'" + complaint.getDescription() + "',"
                + "'" + complaint.getStatus() + "',"
                + "" + complaint.getId_request_order() + ")";
        SQLQuery qry = session.createSQLQuery(qryStr);
        qry.executeUpdate();
        //session.save(complaint);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        main.showListComplaint();
    }

}
