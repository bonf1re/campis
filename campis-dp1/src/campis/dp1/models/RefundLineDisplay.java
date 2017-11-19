package campis.dp1.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Marco
 */
public class RefundLineDisplay {
    final IntegerProperty id_refund_line;
    final IntegerProperty order_request_quantity;
    final IntegerProperty quantity;
    final IntegerProperty id_request_order_line;
    final StringProperty product_name;
    Integer id_product;

    public RefundLineDisplay(int id, int id_request_order_line, int quantity) {
        RequestOrderLine rq = RequestOrderLine.getRequestOrderLine(id_request_order_line);
        this.id_product = rq.getId_product();
        Product product = Product.getProduct(id_product);
        this.product_name = new SimpleStringProperty(product.getName());
        this.order_request_quantity = new SimpleIntegerProperty(rq.getQuantity());
        this.id_refund_line = new SimpleIntegerProperty(id);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.id_request_order_line = new SimpleIntegerProperty(id_request_order_line);
    }

    /**
     * @return the id_refund_line
     */
    public IntegerProperty getId_refund_line() {
        return id_refund_line;
    }

    /**
     * @return the order_request_quantity
     */
    public IntegerProperty getOrder_request_quantity() {
        return order_request_quantity;
    }

    /**
     * @return the quantity
     */
    public IntegerProperty getQuantity() {
        return quantity;
    }

    /**
     * @return the id_request_order_line
     */
    public IntegerProperty getId_request_order_line() {
        return id_request_order_line;
    }

    /**
     * @return the product_name
     */
    public StringProperty getProduct_name() {
        return product_name;
    }

    /**
     * @return the id_product
     */
    public Integer getId_product() {
        return id_product;
    }

    /**
     * @param id_product the id_product to set
     */
    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }
}