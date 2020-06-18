package org.confirmo.client.restapi.invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceList {

    private long totalCount;
    private long offset;
    private long limit;
    private String order;
    private String query;

    private List<Invoice> data = new ArrayList<>();

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Invoice> getData() {
        return data;
    }

    public void setData(List<Invoice> data) {
        this.data = data;
    }
}
