package com.dd.culture.places.models;

/**
 * Created by dds86 on 23-Nov-17.
 */

public class ModelCityListDetails {
    String city_name, cinema_name, cinema_address, cinema_phoneNumber;


    public ModelCityListDetails(String city_name, String cinema_name, String cinema_address, String cinema_phoneNumber) {
        this.city_name = city_name;
        this.cinema_name = cinema_name;
        this.cinema_address = cinema_address;
        this.cinema_phoneNumber = cinema_phoneNumber;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCinema_name() {
        return cinema_name;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }

    public String getCinema_address() {
        return cinema_address;
    }

    public void setCinema_address(String cinema_address) {
        this.cinema_address = cinema_address;
    }

    public String getCinema_phoneNumber() {
        return cinema_phoneNumber;
    }

    public void setCinema_phoneNumber(String cinema_phoneNumber) {
        this.cinema_phoneNumber = cinema_phoneNumber;
    }
}
