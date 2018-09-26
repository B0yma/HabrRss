package com.boyma.habrrsstitles.ui.MainActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.boyma.habrrsstitles.R;
import com.boyma.habrrsstitles.models.Category;
import com.boyma.habrrsstitles.models.Item;

import java.util.ArrayList;
import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.NewsViewHolder> {

    private List<Item> data;

    public RecAdapter() {
        data = new ArrayList<>();
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recitem, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.time.setText(data.get(position).getPubDate());
        holder.tags.setText("Tэги:");
        for (Category c : data.get(position).getTags()){
            holder.tags.append(c.getValue()+", ");
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addAll(List<Item> list) {
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void clearData() {
        data.clear();
    }


    public List<Item> getData() {
        return data;
    }


    class NewsViewHolder extends RecyclerView.ViewHolder {

        private TextView title,time,tags;


        public NewsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
            tags = itemView.findViewById(R.id.tagi);
        }
    }

}
