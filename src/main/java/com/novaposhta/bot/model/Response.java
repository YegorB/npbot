package com.novaposhta.bot.model;

import java.util.List;

public class Response {

    private boolean success;

    private List<ResponseDoc> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ResponseDoc> getData() {
        return data;
    }

    public void setData(List<ResponseDoc> data) {
        this.data = data;
    }

    public String toString() {
        return "Ваше отправление следует в "
                + data.get(0).getCity()
                + ": "
                + data.get(0).getWarehouse()
                + ". Ожидаемая дата прибытия: " + data.get(0).getDeliveryDate();
    }
}
