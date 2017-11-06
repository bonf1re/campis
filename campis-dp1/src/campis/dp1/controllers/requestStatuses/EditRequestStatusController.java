/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.requestStatuses;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.RequestStatus;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
/**
 *
 * @author joseamz
 */
public class EditRequestStatusController implements Initializable{
    Integer id;
    Main main;
    @FXML
    private JFXTextField descriptionField;
    
    @FXML
    private void goListRequestStatuses() throws IOException {
        main.showListRequestStatuses();
    }

    @FXML
    private void editRequestStatus (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update RequestStatus set description = :newDescription"
                + " where id_request_status = :oldIdRequestStatus");
        query.setParameter("newDescription", descriptionField.getText());
        query.setParameter("oldIdRequestStatus", id);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        this.goListRequestStatuses();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id = ContextFX.getInstance().getId();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RequestStatus.class);
        criteria.add(Restrictions.eq("id_request_status",id));
        List rsType = criteria.list();
        RequestStatus result = (RequestStatus)rsType.get(0);
        this.descriptionField.setText(result.getDescription());
        sessionFactory.close();
    }
}
