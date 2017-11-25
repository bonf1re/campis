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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Eddy
 */
@Entity
@Table(name = "dispatch_order")
public class DispatchOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_dispatch_order;
    Integer id_request_order;
    Integer priority;
    String status;
    
    public DispatchOrder() {
        super();
    }
    
    public DispatchOrder(int id_request_order, int priority, String status) {
        this.id_request_order = id_request_order;
        this.priority = priority;
        this.status = status;
    }
    
    public DispatchOrder(int id_dispatch_order, int id_request_order, int priority, String status) {
        this.id_dispatch_order = id_dispatch_order;
        this.id_request_order = id_request_order;
        this.priority = priority;
        this.status = status;
    }
    
    public Integer getId_dispatch_order() {
        return id_dispatch_order;
    }

    public void setId_dispatch_order(Integer id_dispatch_order) {
        this.id_dispatch_order = id_dispatch_order;
    }

    public Integer getId_request_order() {
        return id_request_order;
    }

    public void setId_request_order(Integer id_request_order) {
        this.id_request_order = id_request_order;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static List<DispatchOrderLine> getDispatchOrderLines(Integer id) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(DispatchOrderLine.class)
                .add(Restrictions.eq("id_dispatch_order", id));
        List<DispatchOrderLine> dispatch_ordes_lines = criteria.list();
        session.close();
        sessionFactory.close();

        return dispatch_ordes_lines;
    }
    
    public static Integer getRequestOrder(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String queryStr = "select id_request_order\n" +
                            "from campis.dispatch_order\n" +
                            " WHERE id_dispatch_order =" + cod;
        SQLQuery query = session.createSQLQuery(queryStr);
        List list = query.list();
        Integer returnable = (Integer) list.get(0);

        session.close();
        sessionFactory.close();

        return returnable;
    }
}
