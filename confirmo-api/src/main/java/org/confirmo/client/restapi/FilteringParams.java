package org.confirmo.client.restapi;

import java.util.Map;
import java.util.StringJoiner;

public class FilteringParams {

    private long limit=20L;
    private long offset=0L;
    private Order order = Order.createdAt;
    private Boolean asc = true;
    private Long createdAtFrom = null;
    private Long createdAtTo = null;
    private Status status = null;

    public FilteringParams(long limit, long offset, Order order) {
        this.limit = limit;
        this.offset = offset;
        this.order = order;
    }

    public enum Status {
        active, confirming, paid, expired, error
    }

    public enum Order {
        createdAt, invoiceAmount, cryptoAmount
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Boolean getAsc() {
        return asc;
    }

    public void setAsc(Boolean asc) {
        this.asc = asc;
    }

    public Long getCreatedAtFrom() {
        return createdAtFrom;
    }

    public void setCreatedAtFrom(Long createdAtFrom) {
        this.createdAtFrom = createdAtFrom;
    }

    public Long getCreatedAtTo() {
        return createdAtTo;
    }

    public void setCreatedAtTo(Long createdAtTo) {
        this.createdAtTo = createdAtTo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringJoiner("&")
                .add("limit=" + emptyIfNull(limit))
                .add("offset=" +emptyIfNull(offset))
                .add("order=" + emptyIfNull(order) + "%2C" + (asc?"asc":"desc"))
                .add("createdAtFrom=" + emptyIfNull(createdAtFrom))
                .add("createdAtTo=" + emptyIfNull(createdAtTo))
                .add("status=" + emptyIfNull(status))
                .toString();
    }

    public Map<String, Object> toMap() {
        return Map.of(
                "limit",limit,
                "offset",offset,
                "order", order + "%2C" + (asc?"asc":"desc"),
                "createdAtFrom",createdAtFrom,
                "createdAtTo",createdAtTo,
                "status",status);
    }

    private String emptyIfNull(Object o) {
        return o==null?"":o.toString();
    }
}
