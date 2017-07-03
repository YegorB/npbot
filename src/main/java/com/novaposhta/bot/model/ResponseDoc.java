package com.novaposhta.bot.model;

import com.google.gson.annotations.SerializedName;

public class ResponseDoc {

    @SerializedName("ScheduledDeliveryDate")
    private String deliveryDate;

    @SerializedName("WarehouseRecipient")
    private String warehouse;

    @SerializedName("CityRecipient")
    private String city;

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
