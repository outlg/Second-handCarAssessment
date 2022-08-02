package com.example.carevaluate.MyUtil;

public class Car {
    int id;
    String brand;
    String model;
    double price;
    double age;
    double distance;
    String description;
    int image;

    public Car(){}
    public Car(int id, String brand, String model, double price, double age, double distance, String description, int img) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.age = age;
        this.distance = distance;
        this.description = description;
        this.image = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
