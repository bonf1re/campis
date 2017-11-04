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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Criteria;
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
        List<RequestOrder> requestList = getRequestOrders(); 
        for (int i = 0; i < requestList.size(); i++) {
            requestField.getItems().addAll(requestList.get(i).getId_request_order());
        }
    }
    
    @FXML
    private void goListComplaint() throws IOException {
        main.showListComplaint();
    }

    public static List<RequestOrder> getRequestOrders() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RequestOrder.class)
                .setProjection(Projections.projectionList()
                .add(Projections.property("id_request_order"),"id_request_order"))
                .setResultTransformer(Transformers.aliasToBean(RequestOrder.class));
        List<RequestOrder> types = criteria.list();
        return types;
    }
 
    @FXML
    public void insertComplaint() throws IOException {
        Complaint complaint = new Complaint(descriptionField.getText(), Integer.parseInt(requestField.getEditor().getText()));
          
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(complaint);
        session.getTransaction().commit();

        sessionFactory.close();
        main.showListComplaint();      
    }

}