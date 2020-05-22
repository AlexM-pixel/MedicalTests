package com.example.medicaltests.all_analysis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicaltests.DaoSession;
import com.example.medicaltests.R;
import com.example.medicaltests.contextApp.GreenDaoApp;

import java.util.ArrayList;
import java.util.List;


public class SpisokFragment extends Fragment {
    private ArrayList<AnalysCardView> cardViewArrayList;

    public static SpisokFragment newInstance() {
        return new SpisokFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spisok, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_for_analysis_spisok);
        cardViewArrayList = new ArrayList<>();
        getItemForSpisok();
        AnalysisAdapter adapter = new AnalysisAdapter(cardViewArrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void getItemForSpisok() {
        DaoSession daoSession = ((GreenDaoApp) getActivity().getApplication()).getDaoSession();
        List<Test> testList = new ArrayList<>(daoSession.getTestDao().loadAll());                 // скачал весь список из базы
        for (int i = 0; i < testList.size(); i++) {
            String testName = testList.get(i).getNameTest();                                      // получил название теста
            int imageRes = AnalysTypes.getImageForItem(testName);            // сравнил со значением в enum, нашел нужный и взял у него ресурс на изображение
            cardViewArrayList.add(new AnalysCardView(testName, imageRes));
        }
    }
}
