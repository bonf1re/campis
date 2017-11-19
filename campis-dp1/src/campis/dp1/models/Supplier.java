/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

/**
 *
 * @author Eddy
 */
public class Supplier {
    private Integer id_supplier;
    private String name;
    private String ruc;
    private String address;
    private String email;
    private String phone;

    public Integer getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(Integer id_supplier) {
        this.id_supplier = id_supplier;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public Supplier() {
        super();
    }
    
    public Supplier(Integer id, String name, String ruc, String address, String email, String phone) {
        this.id_supplier = id;
        this.name =  name;
        this.ruc = ruc;
        this.address =  address;
        this.email = email;
        this.phone = phone;
    }
    
    public Supplier(String name, String ruc, String address, String email, String phone) {
        this.name =  name;
        this.ruc = ruc;
        this.address =  address;
        this.email = email;
        this.phone = phone;
    }
    
}
