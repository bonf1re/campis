/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1;

import campis.dp1.models.BatchDisplay;
import campis.dp1.models.ProductDisplay;
import campis.dp1.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eddy
 */
public class ContextFX {

    private final static ContextFX instance = new ContextFX();

    public static ContextFX getInstance() {
        return instance;
    }

    Integer id = null;
    private User user;
    List aux_list = null;   // Abstract so it can take any kind of arraylist
    Integer quantity = null;
    Float totAmount = 0.0f;
    Integer num = 0; // Useful to save any integer variable.
    Integer var = 0;
    String word = null;
    ObservableList<ProductDisplay> tempList = FXCollections.observableArrayList();
    ObservableList<BatchDisplay> tempBatchList = FXCollections.observableArrayList();

    public Integer getNum() {
        Integer returnable = new Integer(this.num);
        return returnable;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getWord() {
        String returnable = new String(this.word);
        this.word = null;
        return returnable;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getVar() {
        Integer returnable = new Integer(this.var);
        return returnable;
    }

    public void setVar(Integer var) {
        this.var = var;
    }

    public List getList() {
        List returnable = new ArrayList<>(this.aux_list);
        this.aux_list = null;
        return returnable;
    }

    public void setList(List aux_list) {
        this.aux_list = new ArrayList<>(aux_list);
    }

    public Integer getId() {
        Integer returnable = new Integer(this.id);
        this.id = null;
        return returnable;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        Integer returnable = new Integer(this.quantity);
        this.quantity = null;
        return returnable;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getTotAmount() {
        Float returnable = new Float(this.totAmount);
        return returnable;
    }

    public void setTotAmount(Float totAmount) {
        this.totAmount = totAmount;
    }

    public ObservableList<ProductDisplay> getTempList() {
        ObservableList<ProductDisplay> returnable = this.tempList;
        return returnable;
    }

    public void setTempList(ObservableList<ProductDisplay> tempList) {
        this.tempList = tempList;
    }

    public ObservableList<BatchDisplay> getTempBatchList() {
        ObservableList<BatchDisplay> returnable = this.tempBatchList;
        return returnable;
    }

    public void setTempBatchList(ObservableList<BatchDisplay> tempList) {
        this.tempBatchList = tempList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
