package fr.sheepy.aboutlotr;

class Page {
    private int current;
    private int prev;
    private Boolean hasPrev;
    private int next;
    private Boolean hasNext;
    private int total;

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
