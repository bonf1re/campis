/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Gina Bustamante
 */
@Entity
@Table (name="invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_invoice;
    private Integer id_dispatch_order;
    private Integer id_type;
    private Double freight;
    private Double igv;
    private Double total;

     public Invoice(){
        super();
    }
    
    public Invoice(Integer id_invoice, Integer id_dispatch_order, Integer id_type, 
                   Double freight, Double igv, Double total){
        this.id_invoice = id_invoice;
        this.id_dispatch_order = id_dispatch_order;
        this.id_type = id_type;
        this.freight = freight;
        this.igv = igv;
        this.total = total;
        this.igv = 0.0;
        this.freight = 0.0;
    }
      
    public Invoice(Integer id_dispatch_order, Integer id_type, Double freight, 
                   Double igv, Double total){
        this.id_dispatch_order = id_dispatch_order;
        this.id_type = id_type;
        this.freight = freight;
        this.igv = igv;
        this.total = total;
        this.igv = 0.0;
        this.freight = 0.0;
    }

    /**
     * @return the id_invoice
     */
    public Integer getId_invoice() {
        return id_invoice;
    }

    /**
     * @param id_invoice the id_invoice to set
     */
    public void setId_invoice(Integer id_invoice) {
        this.id_invoice = id_invoice;
    }

    /**
     * @return the id_dispatch_order
     */
    public Integer getId_dispatch_order() {
        return id_dispatch_order;
    }

    /**
     * @param id_dispatch_order the id_dispatch_order to set
     */
    public void setId_dispatch_order(Integer id_dispatch_order) {
        this.id_dispatch_order = id_dispatch_order;
    }

    /**
     * @return the id_type
     */
    public Integer getId_type() {
        return id_type;
    }

    /**
     * @param id_type the id_type to set
     */
    public void setId_type(Integer id_type) {
        this.id_type = id_type;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

      /**
     * @return the freight
     */
    public Double getFreight() {
        return freight;
    }

    /**
     * @param freight the freight to set
     */
    public void setFreight(Double freight) {
        this.freight = freight;
    }

    /**
     * @return the igv
     */
    public Double getIgv() {
        return igv;
    }

    /**
     * @param igv the igv to set
     */
    public void setIgv(Double igv) {
        this.igv = igv;
    }
    
    public static Invoice getInvoice(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Invoice.class);
        criteria.add(Restrictions.eq("id_invoice",cod));
        String descrip;
        List rsMeasure = criteria.list();
        Invoice result = (Invoice)rsMeasure.get(0);
        session.close();
        sessionFactory.close();

        return result;
    }

    public static List<InvoiceLine> getInvoiceLines(Integer id) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(InvoiceLine.class)
                .add(Restrictions.eq("id_invoice", id));

        ArrayList<InvoiceLine> request_ordes_lines = new ArrayList<>(criteria.list());
        session.close();
        sessionFactory.close();

        return request_ordes_lines;
    }

    public static Integer getIdDispatchOrder(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String queryStr = "select id_dispatch_order\n" +
                            "from campis.invoice\n" +
                            " WHERE id_invoice =" + cod;
        SQLQuery query = session.createSQLQuery(queryStr);
        List list = query.list();
        Integer returnable = (Integer) list.get(0);

        session.close();
        sessionFactory.close();

        return returnable;
    }

    public static Double getFreight(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String queryStr = "select freight\n" +
                            "from campis.invoice\n" +
                            " WHERE id_invoice =" + cod;
        SQLQuery query = session.createSQLQuery(queryStr);
        List list = query.list();
        Double returnable = ((BigDecimal) list.get(0)).doubleValue();

        session.close();
        sessionFactory.close();

        return returnable;
    }
}
