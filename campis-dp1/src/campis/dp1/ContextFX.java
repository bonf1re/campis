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
import java.util.Date;
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
    List aux_2nd_list = null;
    List aux_3rd_list = null;
    List whMovesProdList = null;
    Integer quantity = null;
    Float baseTotAmount = 0.0f;
    Float totAmount = 0.0f;
    Float discount = 0.0f;
    Float freight = 0.0f;
    Integer num = 0; // Useful to save any integer variable.
    Integer var = 0;
    String word = null;
    ObservableList<ProductDisplay> tempList = FXCollections.observableArrayList();
    ObservableList<BatchDisplay> tempBatchList = FXCollections.observableArrayList();
    ArrayList<Object> polymorphic_list = new ArrayList<Object>();
    
    public ArrayList<Object> getPolymorphic_list() {
        ArrayList<Object> returnable = new ArrayList<>(polymorphic_list);
        this.polymorphic_list=null;
        return returnable;
    }

    public void setPolymorphic_list(ArrayList<Object> polymorphic_list) {
        this.polymorphic_list = new ArrayList<>(polymorphic_list);
    }
     

    int id_user;
    // Es un desmadre este contexto
    Integer whMoveType = null;
    // why r u like dis
    private Integer id_type;
    private Integer id_objective;
    //usen esto para pasar fechas
    private Date init_date;
    private Date end_date;
    //y para pasar un string cualquiera
    private String labeltoPrint1;
    private String labeltoPrint2;
    private String labeltoPrint3;
    
    
    public Integer getWhMoveType(){
        Integer returnable = new Integer(this.whMoveType);
        this.whMoveType=null;
        return returnable;
    }
    
    public void setWhMoveType(int aux){
        this.whMoveType = new Integer(aux);
    }
   
    public List getWhMovesProdList(){
        List returnable = new ArrayList<>(this.whMovesProdList);
        this.whMovesProdList=null;
        return returnable;
    }
    public void setWhMovesProdList(List aux_list){
        this.whMovesProdList = new ArrayList<>(aux_list);
    }
    
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
    
    public List get2ndList(){
        List returnable = new ArrayList<>(this.aux_2nd_list);
        this.aux_2nd_list = null;
        return returnable;
    }
    
    public void set2ndList(List aux_2nd_list){
        this.aux_2nd_list = new ArrayList<>(aux_2nd_list);
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
    
    public Float getBaseTotAmount() {
        Float returnable = new Float(this.baseTotAmount);
        return returnable;
    }

    public void setBaseTotAmount(Float baseTotAmount) {
        this.baseTotAmount = baseTotAmount;
    }
    
    public Float getTotAmount() {
        Float returnable = new Float(this.totAmount);
        return returnable;
    }

    public void setTotAmount(Float totAmount) {
        this.totAmount = totAmount;
    }
    
    public Float getDiscount() {
        Float returnable = new Float(this.discount);
        return returnable;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }
    
    public Float getFreight() {
        Float returnable = new Float(this.freight);
        return returnable;
    }
    
    public void setFreight(Float freight) {
        this.freight = freight;
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
        setId_User(user.getId_user());
    }
    
    public List get3rdList(){
        List returnable = new ArrayList<>(this.aux_3rd_list);
        this.aux_3rd_list = null;
        return returnable;
    }

    public void set3rdList(List num_list) {
        this.aux_3rd_list=new ArrayList<>(num_list);
    }

    public int getId_User() {
        return this.id_user;
    }
    public void setId_User(int user){
        this.id_user = user;
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
     * @return the id_objective
     */
    public Integer getId_objective() {
        return id_objective;
    }

    /**
     * @param id_objective the id_objective to set
     */
    public void setId_objective(Integer id_objective) {
        this.id_objective = id_objective;
    }

    /**
     * @return the init_date
     */
    public Date getInit_date() {
        return init_date;
    }

    /**
     * @param init_date the init_date to set
     */
    public void setInit_date(Date init_date) {
        this.init_date = init_date;
    }

    /**
     * @return the end_date
     */
    public Date getEnd_date() {
        return end_date;
    }

    /**
     * @param end_date the end_date to set
     */
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    /**
     * @return the labeltoPrint
     */
    public String getLabeltoPrint1() {
        return labeltoPrint1;
    }

    /**
     * @param labeltoPrint the labeltoPrint to set
     */
    public void setLabeltoPrint1(String labeltoPrint1) {
        this.labeltoPrint1 = labeltoPrint1;
    }
    
    
       /**
     * @return the labeltoPrint2
     */
    public String getLabeltoPrint2() {
        return labeltoPrint2;
    }

    /**
     * @param labeltoPrint2 the labeltoPrint2 to set
     */
    public void setLabeltoPrint2(String labeltoPrint2) {
        this.labeltoPrint2 = labeltoPrint2;
    }

    /**
     * @return the labeltoPrint3
     */
    public String getLabeltoPrint3() {
        return labeltoPrint3;
    }

    /**
     * @param labeltoPrint3 the labeltoPrint3 to set
     */
    public void setLabeltoPrint3(String labeltoPrint3) {
        this.labeltoPrint3 = labeltoPrint3;
    }
}
