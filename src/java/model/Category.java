
package model;

import java.util.List;
import perfumeshopDAO.ProductDAO;


public class Category {
    private int id;
    private String name, describe;

    public Category() {
    }

    public Category(int id, String name, String describe) {
        this.id = id;
        this.name = name;
        this.describe = describe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getTotalProduct() {
        int total = 0;
        ProductDAO p = new ProductDAO();
        List<Product> list = p.getProductsByCategoryid(this.id);
        for (int i = 0; i < list.size(); i++) {
            total += 1;
        }
        return total;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + ", describe=" + describe + '}';
    }
}
