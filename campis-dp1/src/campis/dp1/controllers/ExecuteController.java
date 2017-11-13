/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author david
 */
public class ExecuteController implements Initializable {

    
    @FXML
    private Label lblResults;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblResults.setText("");
    }

    
    
    @FXML
    private void executeBulk (ActionEvent actionEvent) throws SQLException, IOException {
        Integer rows=0;
        FileReader fr = new FileReader("db/seed_test.sql");
        BufferedReader br = new BufferedReader(fr);
        
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction(); 

        while (true){
                String sql = br.readLine();
                if (sql == null)
                {
                        break;
                }
                try
                {
                        int result = session.createSQLQuery(sql).executeUpdate();
                        System.out.printf("Got result %d from '%s'\n", result, sql);
                        lblResults.setText(sql);
                        rows++;
                }
                catch (Exception e)
                {
                        System.err.printf("Error executing '%s'\n", sql);
                }
        }
        session.getTransaction().commit();
        fr.close();
        
        session.close();
        sessionFactory.close();

        lblResults.setText("Carga masiva finalizada. "+ rows.toString() + " filas afectadas.");
        
    }
    
}
