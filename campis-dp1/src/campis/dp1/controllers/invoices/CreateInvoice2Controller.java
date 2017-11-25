/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.invoices;

import campis.dp1.Main;
import campis.dp1.ContextFX;
import campis.dp1.controllers.suppliers.ListController;
import campis.dp1.models.Client;
import campis.dp1.models.DispatchOrder;
import campis.dp1.models.DispatchOrderLine;
import campis.dp1.models.InvoiceLineDisplay;
import campis.dp1.models.District;
import campis.dp1.models.Invoice;
import campis.dp1.models.InvoiceLine;
import campis.dp1.models.Parameters;
import campis.dp1.models.Product;
import campis.dp1.models.RequestOrder;
import campis.dp1.models.RequestOrderLine;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author Gina Bustamante
 */
public class CreateInvoice2Controller implements Initializable {
    private Main main;
    
    private List<DispatchOrderLine> do_lines;
    private List<RequestOrderLine> ro_lines;
    private ObservableList<InvoiceLineDisplay> invoiceView;
    
    @FXML
    private JFXTextField clienteField;
    @FXML
    private JFXTextField typeField;
    @FXML
    private JFXTextField IGVField;
    @FXML
    private JFXTextField fleteField;
    @FXML
    private JFXTextField disttricField;
    @FXML
    private JFXTextField creationField;
    @FXML
    private JFXTextField deliveryField;
    
    @FXML
    private TableView<InvoiceLineDisplay> depreTable;
    @FXML
    private TableColumn<InvoiceLineDisplay, String> nombCol;
    @FXML
    private TableColumn<InvoiceLineDisplay, String> tradeCol;
    @FXML
    private TableColumn<InvoiceLineDisplay, Integer> qtCol;
    @FXML
    private TableColumn<InvoiceLineDisplay, Float> m_unitCol;
    @FXML
    private TableColumn<InvoiceLineDisplay, Float> m_subCol;
    @FXML
    private TableColumn<InvoiceLineDisplay, Float> descCol;
    @FXML
    private TableColumn<InvoiceLineDisplay, Float> m_finalCol;
    
    private Integer selected_del; 
    private Integer selected_do; 
    private Integer selected_ro; 
    private String selected_type; 
    private Integer aux_id_invoice;
    private Integer id_client;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupInvoiceTable();
        
        //ID´s selecionados
        this.selected_del = ContextFX.getInstance().getSelected_del();
        this.selected_do = ContextFX.getInstance().getSelected_do();
        this.selected_ro = DispatchOrder.getRequestOrder(this.selected_do);
        
        
        try {
            this.lodData_invoice();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    
    private void setupInvoiceTable() {
        nombCol.setCellValueFactory(cellData -> cellData.getValue().getNameproduct());
        tradeCol.setCellValueFactory(cellData -> cellData.getValue().getTrademark());
        qtCol.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
        m_unitCol.setCellValueFactory(cellData -> cellData.getValue().getCost().asObject());
        m_subCol.setCellValueFactory(cellData -> cellData.getValue().getM_sub().asObject());
        descCol.setCellValueFactory(cellData -> cellData.getValue().getDiscount().asObject());
        m_finalCol.setCellValueFactory(cellData -> cellData.getValue().getFinalCost().asObject());
    }
    
    public void lodData_invoice() throws SQLException, ClassNotFoundException {
        //Obtenemos el request order
        RequestOrder ro = RequestOrder.getRO(this.selected_ro);
        
        this.id_client = ro.getId_client();
        
        String client = Client.getName(ro.getId_client());
        this.clienteField.setText(client);
        
        String district = District.getName(ro.getId_district());
        this.disttricField.setText(district);
        
        String create_date = ro.getCreation_date().toString();
        this.creationField.setText(create_date);
        
        String delivery_date = ro.getDelivery_date().toString();
        this.deliveryField.setText(delivery_date);
        
        int tipo = this.getType(id_client);
        
        if (tipo == 0){
            this.typeField.setText("Boleta");
        } else {
            this.typeField.setText("Factura");
        }

        //listamos las lienas de la orden de despachoe
        this.do_lines = DispatchOrder.getDispatchOrderLines(this.selected_do);
        
        //listamos las lineas de la orden de venta
        this.ro_lines = RequestOrder.getRequestOrderLines(this.selected_ro);
        
        
        this.invoiceView = FXCollections.observableArrayList();
        
        Float total_view = Float.parseFloat("0"); 
        Float total_weigth = Float.parseFloat("0"); 
        Float total_weigth_ro = Float.parseFloat("0");  
        
        for (DispatchOrderLine line: do_lines) {
                //hallamos la reques order line correspondiente
                
                RequestOrderLine aux_ro = null;
                
                for (RequestOrderLine ro_line: ro_lines){
                    if (line.getId_prod().intValue() == ro_line.getId_product().intValue()){
                                               
                        aux_ro = new RequestOrderLine(ro_line.getId_request_order_line(), 
                                                      ro_line.getQuantity(),ro_line.getCost(), 
                                                      ro_line.getId_request_order(), 
                                                      ro_line.getId_product(), ro_line.getDiscount());
                        
                        break;
                    }
                } ;
                
               
                
                //Creamos una invoice line display
                Product p_aux = Product.getProduct(line.getId_prod());
                String nameProd = p_aux.getName();
                String trademark = p_aux.getTrademark();
                
                Float m_sub = line.getQuantity()*aux_ro.getCost();
                
                float desc = aux_ro.getDiscount()*line.getQuantity()/aux_ro.getQuantity();
                float total_cost  = (aux_ro.getCost()*line.getQuantity()) - desc;
                
                //para calculos posteriores
                total_view += total_cost;
                total_weigth += p_aux.getWeight()*line.getQuantity();
                total_weigth_ro += p_aux.getWeight()*aux_ro.getQuantity();
                
                InvoiceLineDisplay i_line = new InvoiceLineDisplay(0, 0, line.getId_prod(), nameProd, "X", trademark, m_sub, 
                                                                    aux_ro.getCost(), aux_ro.getDiscount(), aux_ro.getQuantity(),
                                                                    total_cost, line.getQuantity(), 0, 0);
                

                this.invoiceView.add(i_line);
            }
        
            Float igv = total_view*ContextFX.getInstance().getIGV();
            Float freigth = getFreigth()*total_weigth/total_weigth_ro;
            
            this.IGVField.setText(igv.toString());
            this.fleteField.setText(freigth.toString());
         
            this.depreTable.setItems(null);
            this.depreTable.setItems(invoiceView);
    }
    
        
    @FXML
    private void insertInvoice(ActionEvent event) throws IOException, DocumentException {
        
        //Cuando creas la factura de verias marcar como facturada esa guia de remision
           
            
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
            Integer type = getType(this.id_client);
                         
            Invoice i = new Invoice(aux_id_do, type, 0.0, 0.0, 0.0); //El total inicialmente en 0
                        
            String qryStr5 = "INSERT INTO campis.invoice VALUES(DEFAULT,'" + i.getId_dispatch_order()+ "',"
                    + "'"+i.getId_type()+"',"
                    + "'"+i.getFreight()+"',"
                    + "'"+i.getIgv()+"',"
                    + "'"+i.getTotal()+"')";
            SQLQuery qry5 = session.createSQLQuery(qryStr5);
            qry5.executeUpdate();
            
            //Obtenemos el id de la factura ingresada 
            String qryStr6 = "SELECT * FROM campis.invoice WHERE id_dispatch_order = " +aux_id_do;
            SQLQuery qry6 = session.createSQLQuery(qryStr6);
            List<Object[]> rows6 = qry6.list();
            
            System.out.println("campis.dp1.controllers.invoices.CreateInvoiceController.insertInvoice()");          
            aux_id_invoice = 0;
            for (Object[] row : rows6) {
                aux_id_invoice = Integer.parseInt(row[0].toString());
                System.out.println("Factura: " + row[0].toString());
            }
            
            //Insertamos las lineas
            float total = 0;
            float total_weight = 0;
            float total_weight_ro = 0;
            
            for (DispatchOrderLine line: do_lines) {
                //hallamos la reques order line correspondiente
                
                RequestOrderLine aux_ro = null;
                
                for (RequestOrderLine ro_line: ro_lines){
                    if (line.getId_prod().intValue() == ro_line.getId_product().intValue()){
                        
                        System.out.println("P L S");
                        
                        System.out.println(ro_line.getDiscount());
                        
                        aux_ro = new RequestOrderLine(ro_line.getId_request_order_line(), 
                                                      ro_line.getQuantity(),ro_line.getCost(), 
                                                      ro_line.getId_request_order(), 
                                                      ro_line.getId_product(), ro_line.getDiscount());
                        
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
                
                float desc = aux_ro.getDiscount()*line.getQuantity()/aux_ro.getQuantity();
                float final_cost  = (aux_ro.getCost()*line.getQuantity()) - desc;
                
                InvoiceLine i_line = new InvoiceLine(aux_id_invoice, line.getId_prod(), 
                                                    line.getQuantity(), aux_ro.getCost(),
                                                    desc, aux_ro.getQuantity(),
                                                    final_cost);
                
                
                /**** PARA CALCULOS POSTERIORES ****/
                System.out.println("campis.dp1.controllers.invoices.CreateInvoiceController.insertInvoice()");
                Product p = Product.getProduct(line.getId_prod());
                
                total_weight += line.getQuantity()*p.getWeight();
                System.out.println("PESO OD: " + total_weight);
                
                total_weight_ro += aux_ro.getQuantity()*p.getWeight();
                System.out.println("PESO RO: " + total_weight_ro);
                
                total += final_cost;
                /***********************************/
                
               
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
            Parameters aux = new Parameters();
            
            Float freigth  = getFreigth()*total_weight/total_weight_ro;
            Float igv = total*ContextFX.getInstance().getIGV();
            
            Float final_total = total + igv + freigth;

            
            String qryStr8 = "UPDATE campis.invoice SET" + " freight = " + freigth + ", igv = " + igv + ", total = " + final_total + "WHERE id_invoice = " + aux_id_invoice ;
            SQLQuery qry8 = session.createSQLQuery(qryStr8);
            qry8.executeUpdate();
            
            
            session.getTransaction().commit(); 
            session.close();
            sessionFactory.close();
            createInvoicePdf();
            this.goListInvoices(event);
        
    }
    
    private float getFreigth(){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();  
        
        //Obtenemos el id de la orden de venta
        String qryStr2 = "SELECT * FROM campis.dispatch_order WHERE id_dispatch_order = " + selected_do;
        SQLQuery qry2 = session.createSQLQuery(qryStr2);
        List<Object[]> rows2 = qry2.list();
       
        this.selected_ro = 0;
        for (Object[] row : rows2) {
            selected_ro = Integer.parseInt(row[1].toString());
            System.out.println("Orden de venta: " + row[1].toString());
        }
        
        RequestOrder ro = RequestOrder.getRO(selected_ro);
        System.out.println("campis.dp1.controllers.invoices.CreateInvoiceController.getFreigth()");
        System.out.println(ro.getFreight_amount());
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        
        return ro.getFreight_amount();
    }
    
    private int getType(Integer id_client){
        Client aux_cli = Client.getClient(id_client);
        
        if (aux_cli.getDni().compareTo("--") == 0){
            //es una persona jurídica, asi que se le entrga una factura
            return  1; 
        } else if (aux_cli.getRuc().compareTo("--") == 0) {
            //se entrga una boleta
            return 0;
        }
        
        return -1;
    }
    
     
    public void createInvoicePdf() throws IOException, DocumentException {
        String dest = "reports/invoices/FACTURA_" + aux_id_invoice  + ".pdf";
        File file = new File(dest);

        List<InvoiceLine> invoice_lines =  Invoice.getInvoiceLines(aux_id_invoice);
        Integer id_ro = Invoice.getIdDispatchOrder(aux_id_invoice);
        RequestOrder ro = RequestOrder.getRO(DispatchOrder.getRequestOrder(id_ro));
        String client = Client.getName(ro.getId_client());
        String district = District.getName(ro.getId_district());

        String create_date = ro.getCreation_date().toString();
        String delivery_date = ro.getDelivery_date().toString();
        Double freight_data = Invoice.getFreight(aux_id_invoice);
        createPdf(dest, invoice_lines, client, district, create_date, delivery_date, freight_data);
    }

    public void createPdf(String dest, List<InvoiceLine> invoice_lines, String client, String district_data, String create_date_data, String delivery_date_data, Double freight_data) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font smallfont = new Font(Font.FontFamily.HELVETICA, 10);
        PdfPCell blank = new PdfPCell(new Phrase(""));
        blank.setBorder(Rectangle.NO_BORDER);

        PdfPTable titulo = new PdfPTable(1);
        titulo.setTotalWidth(new float[]{ 160 });
        titulo.setLockedWidth(true);
        PdfPCell cell = new PdfPCell(new Phrase("FACTURA"));
        cell.setFixedHeight(30);
        cell.setBorder(Rectangle.NO_BORDER);
        titulo.addCell(cell);

        PdfPTable info = new PdfPTable(5);

        PdfPCell nombre_cli_label = new PdfPCell(new Phrase("Nombre del Cliente:"));
        nombre_cli_label.setBorder(Rectangle.NO_BORDER);
        PdfPCell address_dispatch_label = new PdfPCell(new Phrase("Dirección de Despacho:"));
        address_dispatch_label.setBorder(Rectangle.NO_BORDER);
        PdfPCell create_date_label = new PdfPCell(new Phrase("Fecha de Creación:"));
        create_date_label.setBorder(Rectangle.NO_BORDER);
        PdfPCell district_label = new PdfPCell(new Phrase("Distrito:"));
        district_label.setBorder(Rectangle.NO_BORDER);
        PdfPCell delivery_date_label = new PdfPCell(new Phrase("Fecha Delivery:"));
        delivery_date_label.setBorder(Rectangle.NO_BORDER);
        PdfPCell igv_label = new PdfPCell(new Phrase("IGV:"));
        igv_label.setBorder(Rectangle.NO_BORDER);
        
        PdfPCell nombre_cli = new PdfPCell(new Phrase(client));
        nombre_cli.setBorder(Rectangle.NO_BORDER);
        PdfPCell create_date = new PdfPCell(new Phrase(create_date_data));
        create_date.setBorder(Rectangle.NO_BORDER);
        PdfPCell district = new PdfPCell(new Phrase(district_data));
        district.setBorder(Rectangle.NO_BORDER);
        PdfPCell delivery_date = new PdfPCell(new Phrase(delivery_date_data));
        delivery_date.setBorder(Rectangle.NO_BORDER);
        PdfPCell igv = new PdfPCell(new Phrase("0.19"));
        igv.setBorder(Rectangle.NO_BORDER);

        info.addCell(nombre_cli_label);
        info.addCell(nombre_cli);
        info.addCell(blank);
        info.addCell(district_label);
        info.addCell(district);

        info.addCell(create_date_label);
        info.addCell(create_date);
        info.addCell(blank);
        info.addCell(delivery_date_label);
        info.addCell(delivery_date);

        info.addCell(igv_label);
        info.addCell(igv);
        info.addCell(blank);
        info.addCell(blank);
        info.addCell(blank);
            
        PdfPTable table = new PdfPTable(7);
        table.addCell("Nombre");
        table.addCell("Marca");
        table.addCell("Cantidad");
        table.addCell("Monto Unitario");
        table.addCell("Monto Subtotal");
        table.addCell("Descuento");
        table.addCell("Desto Final");
        Float discount_counter = 0.0f;
        Float subtotal_counter = 0.0f;
        for(int i = 0; i < invoice_lines.size(); i++) {
            Product pro = Product.getProduct(invoice_lines.get(i).getId_product());
            discount_counter += invoice_lines.get(i).getDiscount();
            table.addCell(pro.getName());
            table.addCell(pro.getTrademark());
            table.addCell(invoice_lines.get(i).getQuantity().toString());
            table.addCell(invoice_lines.get(i).getCost().toString());
            Float cost = invoice_lines.get(i).getQuantity() * invoice_lines.get(i).getCost();
            subtotal_counter += cost;
            table.addCell(cost.toString());
            table.addCell(invoice_lines.get(i).getDiscount().toString());
            table.addCell(invoice_lines.get(i).getFinal_cost().toString());
        }

        PdfPTable totales = new PdfPTable(6);
        PdfPCell subtotal_label = new PdfPCell(new Phrase("Subtotal:"));
        subtotal_label.setBorder(Rectangle.NO_BORDER);
        PdfPCell discount_label = new PdfPCell(new Phrase("Descuento:"));
        discount_label.setBorder(Rectangle.NO_BORDER);
        PdfPCell freight_label = new PdfPCell(new Phrase("Flete:"));
        freight_label.setBorder(Rectangle.NO_BORDER);
        PdfPCell total_label = new PdfPCell(new Phrase("Total:"));
        total_label.setBorder(Rectangle.NO_BORDER);

        PdfPCell subtotal = new PdfPCell(new Phrase("S/. " + subtotal_counter.toString()));
        subtotal.setBorder(Rectangle.NO_BORDER);
        PdfPCell discount = new PdfPCell(new Phrase("S/. " + discount_counter.toString()));
        discount.setBorder(Rectangle.NO_BORDER);
        Double monto_total = subtotal_counter - discount_counter + freight_data;
        PdfPCell freight = new PdfPCell(new Phrase("S/. " + freight_data.toString()));
        freight.setBorder(Rectangle.NO_BORDER);
        PdfPCell total = new PdfPCell(new Phrase("S/. " + monto_total.toString()));
        total.setBorder(Rectangle.NO_BORDER);

        blank.setColspan(4);
        totales.addCell(blank);
        totales.addCell(subtotal_label);
        totales.addCell(subtotal);

        totales.addCell(blank);
        totales.addCell(discount_label);
        totales.addCell(discount);

        totales.addCell(blank);
        totales.addCell(freight_label);
        totales.addCell(freight);

        totales.addCell(blank);
        totales.addCell(total_label);
        totales.addCell(total);

        document.add(titulo);
        document.add(info);
        document.add(table);
        document.add(totales);
        document.close();
    }

    @FXML
    private void goListInvoices(ActionEvent event) throws IOException {
        main.showListInvoice();
    }
  
}
