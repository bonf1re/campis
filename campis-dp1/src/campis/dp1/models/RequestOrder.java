/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import java.sql.Timestamp;
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
@Table (name="request_order")

public class RequestOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_request_order;
    Timestamp creation_date;
    Timestamp delivery_date;
    String status;
    Float base_amount;
    Float total_amount;
    Integer id_client;
    Integer priority;
    Integer id_district;
    String address;

    public RequestOrder() {
        super();
    }
    
    public RequestOrder(Timestamp creation_date, Timestamp delivery_date,
                        float base_amount, float total_amount, String status, int id_client,int priority) {
        this.creation_date = creation_date;
        this.delivery_date = delivery_date;
        this.status = status;
        this.base_amount = base_amount;
        this.total_amount = total_amount;
        this.id_client = id_client;
        this.priority = priority;
    }
    
    public RequestOrder(Timestamp creation_date, Timestamp delivery_date,
                        float base_amount, float total_amount, String status, 
                        int id_client,int priority,int id_district,String address) {
        this.creation_date = creation_date;
        this.delivery_date = delivery_date;
        this.status = status;
        this.base_amount = base_amount;
        this.total_amount = total_amount;
        this.id_client = id_client;
        this.priority = priority;
        this.id_district = id_district;
        this.address =  address;
    }
    
    public Integer getId_request_order() {
        return id_request_order;
    }

    public void setId_request_order(Integer id_request_order) {
        this.id_request_order = id_request_order;
    }

    public Timestamp getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Timestamp creation_date) {
        this.creation_date = creation_date;
    }

    public Timestamp getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Timestamp delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getBase_amount() {
        return base_amount;
    }

    public void setBase_amount(Float base_amount) {
        this.base_amount = base_amount;
    }

    public Float getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Float total_amount) {
        this.total_amount = total_amount;
    }

    public Integer getId_client() {
        return id_client;
    }

    public void setId_client(Integer id_client) {
        this.id_client = id_client;
    }
    
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    
    public Integer getId_district() {
        return id_district;
    }

    public void setId_district(Integer id_district) {
        this.id_district = id_district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    

    public static List<RequestOrderLine> getRequestOrderLines(Integer id) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RequestOrderLine.class)
                .add(Restrictions.eq("id_request_order", id));
        List<RequestOrderLine> request_ordes_lines = criteria.list();
        sessionFactory.close();

        return request_ordes_lines;
    }
}