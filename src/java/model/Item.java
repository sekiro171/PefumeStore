
package model;

public class Item {
    private Product product;
    private int quantity;
    private double price;

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.getSalePrice();
    }

    public Item() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" + "product=" + product + ", quantity=" + quantity + ", price=" + price + '}';
    }
    
}
