package Model;

public class Bills {
    private int id;
    private int orderid;
    private double price;
    private double discount;
    private double balance;
    private String status;
      private Orders order; // Add this line

    // Constructors
    public Bills() {
    }
    
    public Bills(int id, int orderid, double price, double discount, double balance, String status) {
        this.id = id;
        this.orderid = orderid;
        this.price = price;
        this.discount = discount;
        this.balance = balance;
        this.status = status;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

 

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
