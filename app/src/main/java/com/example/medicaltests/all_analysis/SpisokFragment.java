package com.example.medicaltests.all_analysis;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicaltests.R;
import com.example.medicaltests.account.UserDao;
import com.example.medicaltests.saveAtateSQLite.MyAppDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class SpisokFragment extends Fragment {
    private ArrayList<AnalysCardView> cardViewArrayList;
    private AnalysisAdapter adapter;

    public static SpisokFragment newInstance() {
        return new SpisokFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spisok, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_for_analysis_spisok);
        cardViewArrayList = new ArrayList<>();
        getItemForSpisok();
        adapter = new AnalysisAdapter(cardViewArrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void getItemForSpisok() {
        //  List<Test> testList = MyAppDatabase.getInstance().testDao().getAll();
        UserDbAsync userDbAsync = new UserDbAsync(MyAppDatabase.getInstance());
        userDbAsync.execute();
        List<Test> testList= null;
        try {
            testList = userDbAsync.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < testList.size(); i++) {
            String testName = testList.get(i).testName;                    // получил название теста из базы
            int imageRes = AnalysTypes.getImageForItem(testName);            // сравнил со значением в enum, нашел нужный и взял у него ресурс на изображение
            cardViewArrayList.add(new AnalysCardView(testName, imageRes));
        }

    }

    private static class  UserDbAsync extends AsyncTask<Void, Void, List<Test>> {
        private final TestDao testDao;

        private UserDbAsync(MyAppDatabase instance) {
            this.testDao = instance.testDao();
        }

        @Override
        protected List<Test> doInBackground(Void... v) {
            return testDao.getAll();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // тут крутелку загрузки можно добавить
        }
    }
}
