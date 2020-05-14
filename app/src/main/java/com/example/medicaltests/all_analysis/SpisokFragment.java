package com.example.medicaltests.all_analysis;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medicaltests.R;
import com.example.medicaltests.saveAtateSQLite.DatabaseHelper;

import java.util.ArrayList;


public class SpisokFragment extends Fragment {
    public ArrayList<AnalysCardView> cardViewArrayList;
    AnalysisAdapter adapter;

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
        SQLiteOpenHelper allTestsBdHelper = new DatabaseHelper(getContext());
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
            db = allTestsBdHelper.getReadableDatabase();
            cursor = db.query(DatabaseHelper.TABLE_NAME,
                    new String[]{DatabaseHelper.TEST_NAME, DatabaseHelper.TEST_IMAGE},
                    null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    cardViewArrayList.add(new AnalysCardView(cursor.getString(0), cursor.getInt(1)));
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            Toast.makeText(getContext(), "ОЙ!", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
                db.close();
            }
        }
    }
}
