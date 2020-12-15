package com.joep.backofficeapi.Models.Requests.Order;

import com.joep.backofficeapi.Models.Order.Orderstatus;
import org.bson.types.ObjectId;

public class ChangeOrderStatusRequest {
    private final String orderId;
    private final Orderstatus status;

    public ChangeOrderStatusRequest(String orderId, Orderstatus status) {
        this.orderId = orderId;
        this.status = status;
    }

    public Orderstatus getStatus() {
        return status;
    }

    public ObjectId getOrderId() {
        return new ObjectId(orderId);
    }

}
