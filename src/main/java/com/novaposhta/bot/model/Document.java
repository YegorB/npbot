package com.novaposhta.bot.model;

import com.google.gson.annotations.SerializedName;

public class Document {

    @SerializedName("DocumentNumber")
    private String documentNumber;

    @SerializedName("Phone")
    private String phone = "";

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getPhone() {
        return phone;
    }
}
