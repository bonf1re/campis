package campis.dp1.models;

import java.util.ArrayList;
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
@Table (name="refund")
public class Refund { 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_refund;
    Integer id_invoice;
    String status;

    public Refund() {
        super();
    }

    public Refund(Integer id_invoice) {
        this.status = "Por Ingresar";
        this.id_invoice = id_invoice;
    }
    
    public Refund(Integer idrefund,Integer id_invoice) {
        this.status = "Por Ingresar";
        this.id_invoice = id_invoice;
        this.id_refund = idrefund;
    }

    /**
     * @return the id_refund
     */
    public Integer getId_refund() {
        return id_refund;
    }

    /**
     * @param id_refund the id_refund to set
     */
    public void setId_refund(Integer id_refund) {
        this.id_refund = id_refund;
    }

    /**
     * @return the id_complaint
     */
    public Integer getId_invoice() {
        return id_invoice;
    }

    /**
     * @param id_complaint the id_complaint to set
     */
    public void setId_invoice(Integer id_invoice) {
        this.id_invoice = id_invoice;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public static Integer getIdInvoice(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String queryStr = "select id_invoice\n" +
                            "from campis.refund\n" +
                            " WHERE id_refund =" + cod;
        SQLQuery query = session.createSQLQuery(queryStr);
        List list = query.list();
        Integer returnable = (Integer) list.get(0);

        session.close();
        sessionFactory.close();

        return returnable;
    }

    public static List<RefundLine> getRefundLines(Integer id) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RefundLine.class)
                .add(Restrictions.eq("id_refund", id));

        ArrayList<RefundLine> request_ordes_lines = new ArrayList<>(criteria.list());
        session.close();
        sessionFactory.close();

        return request_ordes_lines;
    }
}