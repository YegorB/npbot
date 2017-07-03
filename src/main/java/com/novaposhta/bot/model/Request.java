package com.novaposhta.bot.model;

public class Request {

    private String apiKey = "bc2b648400040b81d2a91945a007adb7";

    private String modelName = "TrackingDocument";

    private String calledMethod = "getStatusDocuments";

    private MethodProperties methodProperties = new MethodProperties();

    public String getApiKey() {
        return apiKey;
    }

    public String getModelName() {
        return modelName;
    }

    public String getCalledMethod() {
        return calledMethod;
    }

    public MethodProperties getMethodProperties() {
        return methodProperties;
    }

}
