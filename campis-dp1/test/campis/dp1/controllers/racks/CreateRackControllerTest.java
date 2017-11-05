/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.racks;

import java.net.URL;
import java.util.ResourceBundle;
import campis.dp1.models.Rack;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gina Bustamante
 */
public class CreateRackControllerTest {
    
    public CreateRackControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

//    /**
//     * Test of initialize method, of class CreateRackController.
//     */
//    @Test
//    public void testInitialize() {
//        System.out.println("initialize");
//        URL url = null;
//        ResourceBundle rb = null;
//        CreateRackController instance = new CreateRackController();
//        instance.initialize(url, rb);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
    
    /**
     * Test of insertRack method, of class CreateRackController.
     */
    @Test
    public void testInsertRack() {
        System.out.println("insert rack");
        CreateRackController instance = new CreateRackController();
        
        // TODO review the generated test code and remove the default call to fail.
        
        //Creamos un nuevo rack
        Rack nRack = new Rack(1, 10, 10, 12, 4, 0);
        
        //Insertamos el nuevo Rack
        try {
            instance.insertRack(nRack);
        } catch (IOException ex) {
            Logger.getLogger(CreateRackControllerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("No se pudo insertar correctamente el rack");
        }     
    }
    
//    private Rack getRack(Integer id){
//        Configuration configuration = new Configuration();
//        configuration.configure("hibernate.cfg.xml");
//        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        Criteria criteria = session.createCriteria(Rack.class);
//        criteria.add(Restrictions.eq("id_rack",id));
//        List rsType = criteria.list();
//        Rack result = (Rack)rsType.get(0);
//        
//        return result;
//    }
    
}
