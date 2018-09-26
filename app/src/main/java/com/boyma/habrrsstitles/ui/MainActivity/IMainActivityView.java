package com.boyma.habrrsstitles.ui.MainActivity;

import com.boyma.habrrsstitles.models.Item;

import java.util.ArrayList;

public interface IMainActivityView {
    void setRefreshing();

    void stopRefreshing();

    void addItemsToList(ArrayList<Item> items);

    void startNewsActivity(String link);

    void showToast(String s);
}
