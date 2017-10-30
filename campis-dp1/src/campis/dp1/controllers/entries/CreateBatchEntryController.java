/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.entries;

import campis.dp1.Main;
import campis.dp1.models.Rack;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author david
 */
public class CreateBatchEntryController implements Initializable {

    
    private Main main;
    
    @FXML
    private void goNewEntry() throws IOException {
        main.showNewEntry();
    }
    
    @FXML
    private void insertBatchEntry() throws IOException {
        /*
        Rack r = new Rack(1, Integer.parseInt(x_Field.getText()), Integer.parseInt(y_Field.getText()), 
                            Integer.parseInt(columnsField.getText()), Integer.parseInt(floorsField.getText()),
                            0);
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(r);
        session.getTransaction().commit();

        sessionFactory.close();
        */
        
        this.goNewEntry();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
