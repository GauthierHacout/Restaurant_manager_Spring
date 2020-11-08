package api.dto;

public class OrderItemDTO {

    private long id;

    private double totalPrice;

    private int amount;

    private String product;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public OrderItemDTO(long id, double totalPrice, int amount, String product) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.amount = amount;
        this.product = product;
    }
}
