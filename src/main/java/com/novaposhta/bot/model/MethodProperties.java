package com.novaposhta.bot.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MethodProperties {

    @SerializedName("Documents")
    private List<Document> documents;

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
