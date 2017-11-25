/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.invoices;

import campis.dp1.Main;
import campis.dp1.controllers.suppliers.ListController;
import campis.dp1.models.Invoice;
import campis.dp1.models.InvoiceLine;
import campis.dp1.models.Delivery;
import campis.dp1.models.Product;
import campis.dp1.models.UnitOfMeasure;
import campis.dp1.models.DispatchOrderLine;
import campis.dp1.models.DispatchOrderLineDisplay;
import campis.dp1.models.RequestOrderLine;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author Gina Bustamante
 */
public class CreateInvoiceController implements Initializable {
    private Main main;
    
    @FXML
    private Label type_message;
    @FXML
    private Label del_message;

    @FXML
    private JFXComboBox<String> typeCb;
    @FXML
    private JFXComboBox<String> deliveryCb;
    
    private ObservableList<Delivery> deliveries;
    private ObservableList<DispatchOrderLine> do_lines;
    private ObservableList<DispatchOrderLineDisplay>  delieveriesView;
    private Integer selected_del; 
    private Integer selected_do; 
    private String selected_type; 
    
    @FXML
    private TableView<DispatchOrderLineDisplay> tableDelivery;
    @FXML
    private TableColumn<DispatchOrderLineDisplay, Integer> idCol;
    @FXML
    private TableColumn<DispatchOrderLineDisplay, String> prodCol;
    @FXML
    private TableColumn<DispatchOrderLineDisplay, Integer> cantCol;
    @FXML
    private TableColumn<DispatchOrderLineDisplay, String> unitCol;
    @FXML
    private TableColumn<DispatchOrderLineDisplay, Float> weigthCol;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUpDeliveryCb();      
        setupDeliveriesTable();

        typeCb.getItems().add("Boleta");
        typeCb.getItems().add("Factura");
        
        deliveryCb.getSelectionModel().selectedItemProperty().addListener( (ObservableValue<? extends String> options, String oldValue, String newValue) -> {
            System.out.println(newValue);
            this.selected_del = Integer.parseInt(newValue);     
            
            try {
                loadData_deliveries();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
        
        typeCb.getSelectionModel().selectedItemProperty().addListener( (ObservableValue<? extends String> options, String oldValue, String newValue) -> {
            System.out.println(newValue);
            this.selected_type = newValue;         
        }); 
        
    }
    
    private void setupDeliveriesTable() {
        idCol.setCellValueFactory(cellData -> cellData.getValue().id_dispatch_order_lineProperty().asObject());
        prodCol.setCellValueFactory(cellData -> cellData.getValue().product_nameProperty());
        cantCol.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        unitCol.setCellValueFactory(cellData -> cellData.getValue().unit_of_measure_nameProperty());
        weigthCol.setCellValueFactory(cellData -> cellData.getValue().weigthProperty().asObject());       
    }
    
    private void loadData_deliveries() throws SQLException, ClassNotFoundException {
        //Obtenemos el dispatch order
        for (int i = 0; i < this.deliveries.size(); i++) {
            if (Objects.equals(this.selected_del, this.deliveries.get(i).getId_delivery())) {
                this.selected_do = this.deliveries.get(i).getId_dispatch_order();
            }
        }
       
        //Obtenemos las lineas de las ordenes de despacho
        this.do_lines = getDispatchOrderLines(this.selected_do);
        
        //llenamos la tabla con las líneas de la orden de despacho
        delieveriesView = FXCollections.observableArrayList();
        
        for (int i = 0; i < this.do_lines.size(); i++) {
            DispatchOrderLine aux = this.do_lines.get(i);
            
            Product aux_prod = Product.getProduct(aux.getId_prod());
            
            String nombProd = aux_prod.getName();
            Float w = aux_prod.getWeight()*aux.getQuantity();
            Integer unid  = aux_prod.getId_unit_of_measure();
            String unid_name = UnitOfMeasure.getName(unid);
            
            DispatchOrderLineDisplay d = new DispatchOrderLineDisplay(aux.getId_dispatch_order_line(), 
                                                                     aux.getId_dispatch_order(), 
                                                                     aux.getId_prod(), nombProd, aux.getQuantity(), 
                                                                     unid, unid_name, w, aux.isDelivered());

            
            delieveriesView.add(d);
        }
        
        this.tableDelivery.setItems(null);
        this.tableDelivery.setItems(delieveriesView);
    }    
    
    
     private ObservableList<DispatchOrderLine> getDispatchOrderLines(int aux_id_do) {
         //Enlistamos las dispatch order lines
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr3 = "SELECT * FROM campis.dispatch_order_line WHERE id_dispatch_order = " + aux_id_do;
        SQLQuery qry3 = session.createSQLQuery(qryStr3);
        List<Object[]> rows3 = qry3.list();
        ObservableList<DispatchOrderLine> do_lines = FXCollections.observableArrayList();

        for (Object[] row : rows3) {
            DispatchOrderLine sup = new DispatchOrderLine(Integer.parseInt(row[0].toString()),
                                        Integer.parseInt(row[1].toString()),
                                        Integer.parseInt(row[2].toString()),
                                        Integer.parseInt(row[3].toString()));
            do_lines.add(sup);

        }
        
        session.close();
        sessionFactory.close();
        
        return do_lines;
     }
     
    @FXML
    private void setUpDeliveryCb() {
       this.deliveries = getDeliveries();
       
       System.out.println("campis.dp1.controllers.invoices.CreateInvoiceController.setUpDeliveryCb()");
       System.out.println(deliveries.size());
        
       for (int i = 0; i < deliveries.size(); i++) {
           deliveryCb.getItems().add(this.deliveries.get(i).getId_delivery().toString());
       }

    }
    
    private ObservableList<Delivery> getDeliveries() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT * FROM campis.delivery;";
        SQLQuery qry = session.createSQLQuery(qryStr);
        List<Object[]> rows = qry.list();
        ObservableList<Delivery> returnable = FXCollections.observableArrayList();
        
        for (Object[] row : rows) {
            // Verify that it does not have a invoice already created
            int id_dispatch_order = (int) row[3];
            
            if (session.createSQLQuery("SELECT  * FROM campis.invoice WHERE id_dispatch_order = "+id_dispatch_order).list().size()>0)
                continue;

            Delivery sup = new Delivery(Integer.parseInt(row[0].toString()),
                                        row[1].toString(),
                                        row[2].toString(),
                                        Integer.parseInt(row[3].toString()));
            returnable.add(sup);
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }
    
    public boolean validation() {

        boolean delValid = this.selected_del == 0;
        boolean typeValid = this.selected_type.length() == 0;
        
        del_message.setText(" ");
        type_message.setText(" ");

        if (delValid) {
            del_message.setText("Campo obligatorio");
        }

        if (typeValid) {
            type_message.setText("Campo obligatorio");
        }


        return (!delValid && !typeValid);
    }
    
     @FXML
    private void insertInvoice(ActionEvent event) throws IOException {
        
        //Cuando creas la factura de verias marcar como facturada esa guia de remision
           
        if (validation()) {
            
            //Previamente, hacer los calculos
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();    
            
            //Obetenemos el id de la orden de despacho
            String qryStr = "SELECT * FROM campis.delivery WHERE id_delivery = " + this.selected_del;
            SQLQuery qry = session.createSQLQuery(qryStr);
            List<Object[]> rows = qry.list();
            
            System.out.println("campis.dp1.controllers.invoices.CreateInvoiceController.insertInvoice()");          
            Integer aux_id_do = 0;
            for (Object[] row : rows) {
                aux_id_do = Integer.parseInt(row[3].toString());
                System.out.println("Orden de despacho: " + row[3].toString());
            }
            
            //Listamos las lineas de la orden de despacho
            String qryStr3 = "SELECT * FROM campis.dispatch_order_line WHERE id_dispatch_order = " + aux_id_do;
            SQLQuery qry3 = session.createSQLQuery(qryStr3);
            List<Object[]> rows3 = qry3.list();
            ObservableList<DispatchOrderLine> do_lines = FXCollections.observableArrayList();
            
            for (Object[] row : rows3) {
                DispatchOrderLine sup = new DispatchOrderLine(Integer.parseInt(row[0].toString()),
                                            Integer.parseInt(row[1].toString()),
                                            Integer.parseInt(row[2].toString()),
                                            Integer.parseInt(row[3].toString()));
                do_lines.add(sup);

            }

            //Obtenemos el id de la orden de venta
            String qryStr2 = "SELECT * FROM campis.dispatch_order WHERE id_dispatch_order = " + aux_id_do;
            SQLQuery qry2 = session.createSQLQuery(qryStr2);
            List<Object[]> rows2 = qry2.list();
            
            System.out.println("campis.dp1.controllers.invoices.CreateInvoiceController.insertInvoice()");          
            Integer aux_id_ro = 0;
            for (Object[] row : rows2) {
                aux_id_ro = Integer.parseInt(row[1].toString());
                System.out.println("Orden de venta: " + row[1].toString());
            }
            
            //Listamos las lineas de la orden de venta
            String qryStr4 = "SELECT * FROM campis.request_order_line WHERE id_request_order = " + aux_id_ro;
            SQLQuery qry4 = session.createSQLQuery(qryStr4);
            List<Object[]> rows4 = qry4.list();
            ObservableList<RequestOrderLine> ro_lines = FXCollections.observableArrayList();
            
            System.out.println("campis.dp1.controllers.invoices.CreateInvoiceController.insertInvoice()");
            System.out.println(rows4.size());
            
            for (Object[] row : rows4) {
                System.out.println(Integer.parseInt(row[0].toString()));
                System.out.println(Integer.parseInt(row[1].toString()));
                System.out.println(Float.parseFloat(row[2].toString()));
                System.out.println(Integer.parseInt(row[3].toString()));
                System.out.println(Integer.parseInt(row[4].toString()));
                System.out.println(Float.parseFloat(row[5].toString()));
                
                RequestOrderLine sup = new RequestOrderLine(Integer.parseInt(row[0].toString()),
                                            Integer.parseInt(row[1].toString()),
                                            Float.parseFloat(row[2].toString()),
                                            Integer.parseInt(row[3].toString()),
                                            Integer.parseInt(row[4].toString()),
                                            Float.parseFloat(row[5].toString()));
                                
                ro_lines.add(sup);
            }
            
            //Una vez que tenemos todo, insertamos la factura en la tabla factura
            Integer type = getType(this.selected_type);
            Float freigth  = getFreigth();
              
            Invoice i = new Invoice(aux_id_do, type, 0.0); //El total inicialmente en 0
            
            Double igv = i.getTotal()*0.18;
            
            String qryStr5 = "INSERT INTO campis.invoice VALUES(DEFAULT,'" + i.getId_dispatch_order()+ "',"
                    + "'"+i.getId_type()+"',"
                    + "'"+freigth+"',"
                    + "'"+igv+"',"
                    + "'"+i.getTotal()+"')";
            SQLQuery qry5 = session.createSQLQuery(qryStr5);
            qry5.executeUpdate();
            
            //Obtenemos el id de la factura ingresada 
            String qryStr6 = "SELECT * FROM campis.invoice WHERE id_dispatch_order = " +aux_id_do;
            SQLQuery qry6 = session.createSQLQuery(qryStr6);
            List<Object[]> rows6 = qry6.list();
            
            System.out.println("campis.dp1.controllers.invoices.CreateInvoiceController.insertInvoice()");          
            Integer aux_id_invoice = 0;
            for (Object[] row : rows6) {
                aux_id_invoice = Integer.parseInt(row[0].toString());
                System.out.println("Factura: " + row[0].toString());
            }
            
            //Insertamos las lineas
            float total = 0;
            for (DispatchOrderLine line: do_lines) {
                //hallamos la reques order line correspondiente
                
                RequestOrderLine aux_ro = null;
                
                for (RequestOrderLine ro_line: ro_lines){
                    if (line.getId_prod().intValue() == ro_line.getId_product().intValue()){
                        
                        System.out.println("P L S");
                        
                        System.out.println(ro_line.getDiscount());
                        
                        aux_ro = new RequestOrderLine(ro_line.getId_request_order_line(), 
                                                     ro_line.getQuantity(),
                        ro_line.getCost(), ro_line.getId_request_order(), ro_line.getId_product(), ro_line.getDiscount());
                        
                        break;
                    }
                } 
                
                if (aux_ro != null) {
                    System.out.println("PLS x2");
                }
         
                System.out.println("campis.dp1.controllers.invoices.CreateInvoiceController.insertInvoice()");
                System.out.println(aux_ro.getDiscount());
                System.out.println(line.getQuantity());
                System.out.println(aux_ro.getQuantity());
                
                try{
                    float try_line = 1*line.getQuantity();
                }catch(Exception e){
                    System.out.println("error in line");
                    e.printStackTrace();
                }
                
                try{
                    float try_dcto = 1*aux_ro.getQuantity();
                }catch(Exception e){
                    System.out.println("error in aux_ro");
                    e.printStackTrace();
                }
                
                float desc = 0*line.getQuantity()/aux_ro.getQuantity();
                float final_cost  = (aux_ro.getCost()*line.getQuantity()) - desc;
                total += final_cost;
                
                InvoiceLine i_line = new InvoiceLine( aux_id_invoice, line.getId_prod(), 
                                                    line.getQuantity(), aux_ro.getCost(),
                                                    desc, aux_ro.getQuantity(),
                                                    final_cost);
                
                String qryStr7 = "INSERT INTO campis.invoice_line VALUES(DEFAULT," + i_line.getId_invoice() + ","
                    + i_line.getId_product()+ ","
                    + i_line.getQuantity()+ ","
                    + i_line.getCost()+ ","
                    + i_line.getDiscount() + ","
                    + i_line.getQuantity_ro()+ ","
                    + i_line.getFinal_cost()+ ")";
                
                SQLQuery qry7 = session.createSQLQuery(qryStr7);
                qry7.executeUpdate();
                
            }
            
            //Actulizamos el costo de la factura
            String qryStr8 = "UPDATE campis.invoice SET total = " + total + "WHERE id_invoice = " + aux_id_invoice ;
            SQLQuery qry8 = session.createSQLQuery(qryStr8);
            qry8.executeUpdate();
            
            //Actulizamos la guia de remision
//            String qryStr9 = "UPDATE delivery SET invoiced = " + true + "WHERE id_delivery = " + this.selected_del;
//            SQLQuery qry9 = session.createSQLQuery(qryStr8);
//            qry9.executeUpdate();
            
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            
            this.goListInvoices(event);
        }
    }
    
    private float getFreigth(){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();  
        
        //Obetenemos el id de la orden de despacho
        String qryStr = "SELECT * FROM campis.delivery WHERE id_delivery = " + this.selected_del;
        SQLQuery qry = session.createSQLQuery(qryStr);
        List<Object[]> rows = qry.list();

        System.out.println("campis.dp1.controllers.invoices.CreateInvoiceController.insertInvoice()");          
        Integer aux_id_do = 0;
        for (Object[] row : rows) {
            aux_id_do = Integer.parseInt(row[3].toString());
            System.out.println("Orden de despacho: " + row[3].toString());
        }

        //Obtenemos el id de la orden de venta
        String qryStr2 = "SELECT * FROM campis.dispatch_order WHERE id_dispatch_order = " + aux_id_do;
        SQLQuery qry2 = session.createSQLQuery(qryStr2);
        List<Object[]> rows2 = qry2.list();

        System.out.println("campis.dp1.controllers.invoices.CreateInvoiceController.insertInvoice()");          
        Integer aux_id_ro = 0;
        Integer freigth = 0;
        for (Object[] row : rows2) {
            aux_id_ro = Integer.parseInt(row[1].toString());
            System.out.println("Orden de venta: " + row[1].toString());
        }
        
         //Listamos las lineas de la orden de venta
        String qryStr4 = "SELECT * FROM campis.request_order WHERE id_request_order = " + aux_id_ro;
        SQLQuery qry4 = session.createSQLQuery(qryStr4);
        List<Object[]> rows4 = qry4.list();
//        ObservableList<RequestOrderLine> ro_lines = FXCollections.observableArrayList();
//
//        System.out.println("campis.dp1.controllers.invoices.CreateInvoiceController.insertInvoice()");
//        System.out.println(rows4.size());
        
        float fre = 0;
        
        for (Object[] row : rows4) {
            fre = Float.parseFloat(row[11].toString());
            System.out.println("freight: " + row[11].toString());
        }
            
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        
        return fre;
    }
    
    private int getType(String type){
        if (type.compareTo("Boleta") == 0){
            return 0;
        } else if (type.compareTo("Factura") == 0){
            return  1;
        }
        
        return -1;
    }
    
 
    
    @FXML
    private void goListInvoices(ActionEvent event) throws IOException {
        main.showListInvoice();
    }
    
}
