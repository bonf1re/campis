/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

/**
 *
 * @author Gina Bustamante
 */
public class Invoice {
    
    private Integer id_invoice;
    private Integer id_dispatch_order;
    private Integer id_type;
    private Double freight;
    private Double igv;
    private Double total;
    
     public Invoice(){
        super();
    }
    
    public Invoice(Integer id_invoice, Integer id_dispatch_order, Integer id_type, 
                   Double freight, Double igv, Double total){
        this.id_invoice = id_invoice;
        this.id_dispatch_order = id_dispatch_order;
        this.id_type = id_type;
        this.freight = freight;
        this.igv = igv;
        this.total = total;
    }
      
    public Invoice(Integer id_dispatch_order, Integer id_type, Double freight, 
                   Double igv, Double total){
        this.id_dispatch_order = id_dispatch_order;
        this.id_type = id_type;
        this.freight = freight;
        this.igv = igv;
        this.total = total;
    }

    /**
     * @return the id_invoice
     */
    public Integer getId_invoice() {
        return id_invoice;
    }

    /**
     * @param id_invoice the id_invoice to set
     */
    public void setId_invoice(Integer id_invoice) {
        this.id_invoice = id_invoice;
    }

    /**
     * @return the id_dispatch_order
     */
    public Integer getId_dispatch_order() {
        return id_dispatch_order;
    }

    /**
     * @param id_dispatch_order the id_dispatch_order to set
     */
    public void setId_dispatch_order(Integer id_dispatch_order) {
        this.id_dispatch_order = id_dispatch_order;
    }

    /**
     * @return the id_type
     */
    public Integer getId_type() {
        return id_type;
    }

    /**
     * @param id_type the id_type to set
     */
    public void setId_type(Integer id_type) {
        this.id_type = id_type;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }
    
      /**
     * @return the freight
     */
    public Double getFreight() {
        return freight;
    }

    /**
     * @param freight the freight to set
     */
    public void setFreight(Double freight) {
        this.freight = freight;
    }

    /**
     * @return the igv
     */
    public Double getIgv() {
        return igv;
    }

    /**
     * @param igv the igv to set
     */
    public void setIgv(Double igv) {
        this.igv = igv;
    }
    
}
