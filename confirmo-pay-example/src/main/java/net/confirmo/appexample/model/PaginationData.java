package net.confirmo.appexample.model;

public class PaginationData {
    private int page = 0;
    private int size = 100;

    private String sortColumn;
    private boolean asc;

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public boolean isAsc() {
        return asc;
    }
}
