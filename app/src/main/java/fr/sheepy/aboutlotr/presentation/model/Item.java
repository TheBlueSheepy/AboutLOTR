package fr.sheepy.aboutlotr.presentation.model;

class Item {
    private String begin;
    private String end;
    private int total;

    public Item() {
    }

    public Item(String begin, String end, int total) {
        this.begin = begin;
        this.end = end;
        this.total = total;
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    public int getTotal() {
        return total;
    }
}
