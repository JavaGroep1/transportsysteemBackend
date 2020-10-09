package com.joep.backofficeapi.Models.Requests.Order;

import org.bson.types.ObjectId;

public class GetOrderRequest {

    private ObjectId customerId;
    private ObjectId orderId;

    public GetOrderRequest(ObjectId customerId, ObjectId orderId) {
        this.customerId = customerId;
        this.orderId = orderId;
    }

    public ObjectId getOrderId() {
        return orderId;
    }


    public ObjectId getCustomerId() {
        return customerId;
    }
}
