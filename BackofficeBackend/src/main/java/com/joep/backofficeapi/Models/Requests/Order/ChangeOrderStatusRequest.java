package com.joep.backofficeapi.Models.Requests.Order;

import com.joep.backofficeapi.Models.Orderstatus;
import org.bson.types.ObjectId;

public class ChangeOrderStatusRequest {
    private ObjectId orderId;
    private Orderstatus status;

    public ChangeOrderStatusRequest(ObjectId orderId, Orderstatus status) {
        this.orderId = orderId;
        this.status = status;
    }

    public Orderstatus getStatus() {
        return status;
    }

    public ObjectId getOrderId() {
        return orderId;
    }
}
