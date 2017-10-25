/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.views;

import campis.dp1.Main;
import campis.dp1.models.Warehouse;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author sergio
 */
public class MainViewController implements Initializable {
    private Main main;
    //private ObservableList<Warehouse> warehouses;
    @FXML
    private TableView<Warehouse> list_table;
    @FXML
    private TableColumn<Warehouse, String> c_name;
    @FXML
    private TableColumn<Warehouse, Integer> c_length;
    @FXML
    private TableColumn<Warehouse, Integer> c_width;
    @FXML
    private TableColumn<Warehouse, Boolean> c_status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void loadData() throws SQLException, ClassNotFoundException{
        try{
            //warehouses = FXCollections.observableArrayList();
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
//        
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Warehouse.class);
            List<Warehouse> warehouses = criteria.list();
            for (int i = 0; i < warehouses.size(); i++) {
                System.out.println(warehouses.get(i).getName());
            }
            session.getTransaction().commit();
//        
            sessionFactory.close();
            
            
        }catch(Exception e){
            throw(e);
            
        }
    }
}
