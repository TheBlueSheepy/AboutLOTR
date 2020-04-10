package fr.sheepy.aboutlotr;

import java.util.List;

public class LOTRAPIResponse {
    List<Character> docs;
    Page pages;
    Item items;

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
