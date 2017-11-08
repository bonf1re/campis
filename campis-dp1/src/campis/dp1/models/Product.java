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
@Table (name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_product;
    String name;
    String description;
    Integer p_stock;
    Integer c_stock;
    Float weight;
    String trademark;
    Float base_price;
    Integer id_unit_of_measure;
    Integer id_product_type;
    
    public Product() {
        super();
    }
    
    public Product(String nombre, String descripcion, int phy_stock, int comm_stock,
                    float peso, String marca, float precio_base, int medida, int type) {
        this.name = nombre;
        this.description = descripcion;
        this.p_stock = phy_stock;
        this.c_stock = comm_stock;
        this.weight = peso;
        this.trademark = marca;
        this.base_price = precio_base;
        this.id_unit_of_measure = medida;
        this.id_product_type = type;
    }
    
    public Product(Integer codProd , String nombre, String descripcion, int phy_stock, int comm_stock,
                    float peso, String marca, float precio_base, int medida, int type) {
        this.id_product = codProd;
        this.name = nombre;
        this.description = descripcion;
        this.p_stock = phy_stock;
        this.c_stock = comm_stock;
        this.weight = peso;
        this.trademark = marca;
        this.base_price = precio_base;
        this.id_unit_of_measure = medida;
        this.id_product_type = type;
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getP_stock() {
        return p_stock;
    }

    public void setP_stock(int p_stock) {
        this.p_stock = p_stock;
    }

    public Integer getC_stock() {
        return c_stock;
    }

    public void setC_stock(int c_stock) {
        this.c_stock = c_stock;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public Float getBase_price() {
        return base_price;
    }

    public void setBase_price(float base_price) {
        this.base_price = base_price;
    }

    public Integer getId_unit_of_measure() {
        return id_unit_of_measure;
    }

    public void setId_unit_of_measure(int id_unit_of_measure) {
        this.id_unit_of_measure = id_unit_of_measure;
    }

    public Integer getId_product_type() {
        return id_product_type;
    }

    public void setId_product_type(Integer id_product_type) {
        this.id_product_type = id_product_type;
    }

    public static Product getProduct(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product",cod));
        String descrip;
        List rsMeasure = criteria.list();
        Product result = (Product)rsMeasure.get(0);
        sessionFactory.close();

        return result;
    }
    
}
