/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Gina Bustamante
 */

@Entity
@Table(name = "batch")
public class BatchEntry {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
   
    private Integer id_batch;
    private Integer quantity;
    private Double batch_cost;
    private Timestamp arrival_date;
    private Timestamp expirantion_date;
   
    
    
    
}
