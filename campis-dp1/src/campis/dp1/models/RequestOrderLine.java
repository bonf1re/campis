/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Eddy
 */
@Entity
@Table (name = "request_order_line")
public class RequestOrderLine {

    /**
     * @return the discount
     */
    public Float getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(Float discount) {
        this.discount = discount;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_request_order_line;
    Integer quantity;
    Float cost;
    Integer id_request_order;
    Integer id_product;

    Float discount;
    
    public RequestOrderLine() {
        super();
    }
    
    public RequestOrderLine(int quantity, float costo,
                            int idReqOrd, int idProd) {
        this.quantity = quantity;
        this.cost = costo;
        this.id_request_order = idReqOrd;
        this.id_product = idProd;
    }
    
    public RequestOrderLine(int quantity, float costo,
                            int idReqOrd, int idProd, float discount) {
        this.quantity = quantity;
        this.cost = costo;
        this.id_request_order = idReqOrd;
        this.id_product = idProd;
        this.discount = discount;
    }
    
    public Integer getId_request_order_line() {
        return id_request_order_line;
    }

    public void setId_request_order_line(Integer id_request_order_line) {
        this.id_request_order_line = id_request_order_line;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Integer getId_request_order() {
        return id_request_order;
    }

    public void setId_request_order(Integer id_request_order) {
        this.id_request_order = id_request_order;
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public static RequestOrderLine getRequestOrderLine(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RequestOrderLine.class);
        criteria.add(Restrictions.eq("id_request_order_line", cod));
        List rsMeasure = criteria.list();
        RequestOrderLine result = (RequestOrderLine)rsMeasure.get(0);
        session.close();
        sessionFactory.close();

        return result;
    }
}
