package fr.sheepy.aboutlotr.presentation.model;

class Page {
    private int current;
    private int prev;
    private Boolean hasPrev;
    private int next;
    private Boolean hasNext;
    private int total;

    public Page() {
    }

    public Page(int current, int prev, Boolean hasPrev, int next, Boolean hasNext, int total) {
        this.current = current;
        this.prev = prev;
        this.hasPrev = hasPrev;
        this.next = next;
        this.hasNext = hasNext;
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public int getPrev() {
        return prev;
    }

    public Boolean getHasPrev() {
        return hasPrev;
    }

    public int getNext() {
        return next;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public int getTotal() {
        return total;
    }
}
