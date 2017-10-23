package campis.dp1;

public class Product {
    String codProd;
    String nombre;
    String descripcion;
    int phy_stock;
    int comm_stock;
    float peso;
    String marca;
    float precio_base;

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPhy_stock() {
        return phy_stock;
    }

    public void setPhy_stock(int phy_stock) {
        this.phy_stock = phy_stock;
    }

    public int getComm_stock() {
        return comm_stock;
    }

    public void setComm_stock(int comm_stock) {
        this.comm_stock = comm_stock;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecio_base() {
        return precio_base;
    }

    public void setPrecio_base(float precio_base) {
        this.precio_base = precio_base;
    }

    public Product(String codProd, String nombre, String descripcion, int phy_stock, int comm_stock,
                    float peso, String marca, float precio_base) {
        this.codProd = codProd;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.phy_stock = phy_stock;
        this.comm_stock = comm_stock;
        this.peso = peso;
        this.marca = marca;
        this.precio_base = precio_base;
    }
}
