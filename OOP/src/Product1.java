public class Product1 {
    private String name;
    private double price;

    public Product1(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " цена: " + price;
    }
}