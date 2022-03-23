package pankaj.unknowndeveloper.e_com.Model;

public class MyCartModel {
String Date;
String Time;
int TotalPrice;
int TotalQuantity;
String productName;
String productPrice;

    public MyCartModel() {
    }

    public MyCartModel(String date, String time, int totalPrice, int totalQuantity, String productName, String productPrice) {
        Date = date;
        Time = time;
        TotalPrice = totalPrice;
        TotalQuantity = totalQuantity;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    public int getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
