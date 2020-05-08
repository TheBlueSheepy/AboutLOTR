package fr.sheepy.aboutlotr.presentation.model;

import java.util.List;

public class LOTRAPIResponse {
    private List<Character> docs;
    private Page pages;
    private Item items;

    public LOTRAPIResponse() {
    }

    public LOTRAPIResponse(List<Character> docs, Page pages, Item items) {
        this.docs = docs;
        this.pages = pages;
        this.items = items;
    }

    public List<Character> getDocs() {
        return docs;
    }

    public Page getPages() {
        return pages;
    }

    public Item getItems() {
        return items;
    }
}
