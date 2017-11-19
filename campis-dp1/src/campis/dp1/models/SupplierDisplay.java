/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Eddy
 */
public class SupplierDisplay {
    private final IntegerProperty id_supplier;
    private final StringProperty name;
    private final StringProperty ruc;
    private final StringProperty address;
    private final StringProperty email;
    private final StringProperty phone;
    
    public SupplierDisplay(int id, String name, String ruc, String address, String email, String phone) {
        this.id_supplier = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.ruc = new SimpleStringProperty(ruc);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
    }

    public IntegerProperty getId_supplier() {
        return id_supplier;
    }

    public StringProperty getName() {
        return name;
    }

    public StringProperty getRuc() {
        return ruc;
    }

    public StringProperty getAddress() {
        return address;
    }

    public StringProperty getEmail() {
        return email;
    }

    public StringProperty getPhone() {
        return phone;
    }    
    
}
