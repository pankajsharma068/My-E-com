package pankaj.unknowndeveloper.e_com.Model;

import java.io.Serializable;

public class ShowAllModel implements Serializable {

    String img_url;
    String discription;
    String  name;
    String  rating;
    int  price;
    String type;

    public ShowAllModel() {
    }

    public ShowAllModel(String img_url, String discription, String name, String rating, int price, String type) {
        this.img_url = img_url;
        this.discription = discription;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.type = type;

    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
