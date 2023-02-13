package com.example.ratatouille23;

public class ProdottoMenu implements Prodotto{
        public final String title;

        public ProdottoMenu(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public boolean isSection() {
            return false;
        }
}
