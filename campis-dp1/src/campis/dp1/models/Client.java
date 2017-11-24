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

/**
 *
 * @author Eddy
 */
@Entity
@Table (name="client")
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_client;
    String name;
    String dni;
    String ruc;
    Boolean active;
    String address;
    String phone;
    String email;

    public Integer getId_client() {
        return id_client;
    }

    public void setId_client(Integer id_client) {
        this.id_client = id_client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Client() {
        super();
    }
    
    public Client(String nom, String dni, String ruc,
                   String address, String phone, 
                  String email) {
        this.name = nom;
        this.dni = dni;
        this.ruc = ruc;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.active = true;
    }

    public static String getName(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String queryStr = "select name\n" +
                            "from campis.client\n" +
                            " WHERE id_client =" + cod;
        SQLQuery query = session.createSQLQuery(queryStr);
        List list = query.list();
        String returnable = (String) list.get(0);

        session.close();
        sessionFactory.close();

        return returnable;
    }
}
