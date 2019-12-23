package com.bdqn.entity;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

public class HotelEntity implements Serializable {

    public void setId(String id) {
        this.id = id;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getId() {
        return id;
    }

    public String getHotelName() {
        return hotelName;
    }

    @Field
    private String id;
    @Field
    private String hotelName;

}
