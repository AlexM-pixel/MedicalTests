package com.example.medicaltests.all_analysis;

import androidx.annotation.DrawableRes;

import com.example.medicaltests.R;

public enum AnalysTypes {
    GENERAL_BLOOD_ANALYSIS(R.drawable.true_blood, "Общий анализ крови"),
    THYROID_HORMONES(R.drawable.butterfly, "Гормоны щитовидной железы"),
    BLOOD_CHEMISTRY(R.drawable.icons_blood48, "Биохимический анализ крови"),
    GENERAL_URINE_ANALYSIS(R.drawable.mocha, "Общий анализ мочи"),
    CARBOHYDRATE_METABOLISM(R.drawable.uglevodi, "Углеводный обмен"),
    KIDNEY_TESTS(R.drawable.pochka, "Почечные пробы"),
    LIPIDOGRAM(R.drawable.lipidogramma, "Липидограмма"),
    BONE_MARKERS(R.drawable.bone, "Маркеры костной ткани");


    private int imageR;
    private String nameTest;


    AnalysTypes(@DrawableRes int imageRes, String nameTest) {
        this.imageR = imageRes;
        this.nameTest = nameTest;
    }

    static int getImageForItem(String nameTest) {
        AnalysTypes[] analysTypes = AnalysTypes.values();       //получил массив значений хранящихся в данном классе
        AnalysTypes analysType = null;
        for (AnalysTypes type : analysTypes) {
            if (nameTest.equals(type.nameTest)) {
                analysType = type;
                break;
            }
        }
        return analysType.imageR;
    }
}
