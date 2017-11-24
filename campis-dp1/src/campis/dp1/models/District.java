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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="district")
public class District {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)   
    private Integer id_district;
    private String name;
    private Float freight;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id_district
     */
    public int getId_district() {
        return id_district;
    }

    /**
     * @param id_district the id_district to set
     */
    public void setId_district(int id_district) {
        this.id_district = id_district;
    }

    public Float getFreight() {
        return freight;
    }
    
    /**
     * @param freight the freight to set
     */
    public void setFreight(Float freight) {
        this.freight = freight;
    }
    
    public District() {
        super();
    }

    public District(Integer id_district, String name, Float freight) {
        this.id_district = id_district;
        this.name = name;
        this.freight = freight;
    }
    
    public District(String name, Float freight) {
        this.name = name;
        this.freight = freight;
    }

    public static String getName(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String queryStr = "select name\n" +
                            "from campis.district\n" +
                            " WHERE id_district =" + cod;
        SQLQuery query = session.createSQLQuery(queryStr);
        List list = query.list();
        String returnable = (String) list.get(0);

        session.close();
        sessionFactory.close();

        return returnable;
    }
}
