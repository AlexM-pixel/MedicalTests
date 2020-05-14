package com.example.medicaltests.all_analysis;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicaltests.R;

import java.util.List;

public class AnalysisAdapter extends RecyclerView.Adapter<AnalysisAdapter.AnalysisHolder> {
    private List<AnalysCardView> cardViewList;

    public AnalysisAdapter(List<AnalysCardView> cardViews) {
        this.cardViewList = cardViews;
    }

    @NonNull
    @Override
    public AnalysisHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardV = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_analys, parent, false);

        return new AnalysisHolder(cardV);
    }

    @Override
    public void onBindViewHolder(@NonNull AnalysisHolder holder, int position) {
        Drawable drawable = ContextCompat.getDrawable(holder.cardView.getContext(),cardViewList.get(position).getImage());
        holder.imageView.setImageDrawable(drawable);
        holder.title.setText(cardViewList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return cardViewList.size();
    }

    static class AnalysisHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView imageView;
        private TextView title;

        public AnalysisHolder(CardView cardV) {
            super(cardV);
            cardView = cardV;
            imageView = cardView.findViewById(R.id.imageTest);
            title = cardView.findViewById(R.id.test_title);
        }
    }
}
