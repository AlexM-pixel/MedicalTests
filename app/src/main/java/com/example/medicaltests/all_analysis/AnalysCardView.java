package com.example.medicaltests.all_analysis;

import com.example.medicaltests.R;

import java.util.Arrays;
import java.util.List;

public class AnalysCardView {
    private String title;
    private int image;

    public AnalysCardView(String title, int image) {
        this.title = title;
        this.image = image;
    }
//        public static List<AnalysCardView> userList = Arrays.asList(new AnalysCardView("общий_анализ_крови",  R.drawable.blood_chemistry),
//            new AnalysCardView("гормоны_щитовидной_железы",  R.drawable.icons_blood48));

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
