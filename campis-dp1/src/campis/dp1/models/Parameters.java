/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Eddy
 */
public class Parameters {

    Float igv;
    Float dollar;
    Float pound;
    Float euro;

    private Parameters() {
        super();
    }

    private Parameters(Float igv, Float dollar, Float euro) {
        this.igv = igv;
        this.dollar = dollar;
        this.euro = euro;
    }

    public Float getIgv() {
        return igv;
    }

    public void setIgv(Float igv) {
        this.igv = igv;
    }

    public Float getDollar() {
        return dollar;
    }

    public void setDollar(Float dollar) {
        this.dollar = dollar;
    }

    public Float getPound() {
        return pound;
    }

    public void setPound(Float pound) {
        this.pound = pound;
    }

    public Float getEuro() {
        return euro;
    }

    public void setEuro(Float euro) {
        this.euro = euro;
    }

}
