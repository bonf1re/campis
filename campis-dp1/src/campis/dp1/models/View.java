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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Marco
 */
@Entity
@Table(name = "view")
public class View {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_view;
    String description;
    
    public View() {
        super();
    }

    public View(String description) {
        this.description = description;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the id_role
     */
    public Integer getId_view() {
        return this.id_view;
    }

    /**
     * @param id_role the id_role to set
     */
    public void setId_view(Integer id_role) {
        this.id_view = id_role;
    }

    public static List<View> getViews() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(View.class);
        List<View> types = criteria.list();
        session.close();
        sessionFactory.close();
        return types;
    }

    public static View getView(String description) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(View.class);
        criteria.add(Restrictions.eq("description",description));
        String descrip;
        List rsMeasure = criteria.list();
        View result = (View)rsMeasure.get(0);
        session.close();
        sessionFactory.close();

        return result;
    }
}
