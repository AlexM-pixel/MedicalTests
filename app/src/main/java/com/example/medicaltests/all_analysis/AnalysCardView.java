package com.example.medicaltests.all_analysis;


class AnalysCardView {
    private String title;
    private int image;

    AnalysCardView(String title, int image) {
        this.title = title;
        this.image = image;
    }

    String getTitle() {
        return title;
    }

    int getImage() {
        return image;
    }
}
