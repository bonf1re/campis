/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author joseamz
 */
@Entity
@Table(name = "request_status")
public class RequestStatus {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id_request_status;
    String description;
    
    public RequestStatus() {
        super();
    }
    
    public RequestStatus(String description) {
        this.description = description;
    }

    public int getId_request_status() {
        return id_request_status;
    }

    public void setId_request_status(int id_request_status) {
        this.id_request_status = id_request_status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
