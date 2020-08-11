package net.confirmo.appexample.model;

import java.util.StringJoiner;

public class PaginationData {
    private int page = 0;
    private int size = 100;

    private String sortColumn;
    private Order order = Order.none;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PaginationData.class.getSimpleName() + "[", "]")
                .add("page=" + page)
                .add("size=" + size)
                .add("sortColumn='" + sortColumn + "'")
                .add("order=" + order)
                .toString();
    }

    public static enum Order {
        asc,
        desc,
        none
    }
}
