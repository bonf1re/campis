/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.hibernate.SQLQuery;
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
    private void executeClean (ActionEvent actionEvent) throws SQLException, IOException {
        executeAction("select campis.clean_campis()", "Limpieza de datos");
    }
    
     @FXML
    private void executeBulk (ActionEvent actionEvent) throws SQLException, IOException {
        executeAction("select campis.bulk_campis()", "Carga masiva");
    }
    
    private void executeAction(String command, String action) throws SQLException, IOException {
        String txt= action + " finalizada con éxito.";
        
        
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction(); 

        
                try
                {
                        SQLQuery query = session.createSQLQuery(command);
                        //System.out.printf("Got result %d from '%s'\n", result, sql);
                        List<Object[]> rows = query.list();
                        //rows++;
                }
                catch (Exception e)
                {
                        System.err.printf("Error executing sql command");
                        txt= action + " falló en su ejecución.";;
                }
        
        
        
        
        session.close();
        sessionFactory.close();

        lblResults.setText(txt);
        
    
        
    }
    
    
    
}
