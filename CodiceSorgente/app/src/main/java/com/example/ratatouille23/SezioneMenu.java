package com.example.ratatouille23;

public class SezioneMenu implements Prodotto{
    private final String title;

    public SezioneMenu(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean isSection() {
        return true;
    }
}
