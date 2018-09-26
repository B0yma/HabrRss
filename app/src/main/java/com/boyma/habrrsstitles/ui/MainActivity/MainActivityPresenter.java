package com.boyma.habrrsstitles.ui.MainActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;

import com.boyma.habrrsstitles.models.Rss;

import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.Scanner;

public class MainActivityPresenter {

    private IMainActivityView view;
    private StringBuilder xmlstring;
    private Rss rss;

    public MainActivityPresenter(IMainActivityView viewinter) {
        this.view = viewinter;
        new FetchFeedTask().execute();
    }

    public void onSwipeRefresh() {
        new FetchFeedTask().execute();
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("xml",xmlstring);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        xmlstring = (StringBuilder) savedInstanceState.getSerializable("xml");
        if (xmlstring!=null){
            Rss rss = getFromXml(xmlstring);
            view.addItemsToList(rss.getChannelObject().getItems());
        }
    }

    private Rss getFromXml(StringBuilder xmlstring) {
        Rss rss = null;
        Reader reader = new StringReader(xmlstring.toString());
        Persister serializer = new Persister();
        try {
            rss = serializer.read(Rss.class, reader, false);
            System.out.println("xmlstring:"+rss.getChannelObject().getItems().get(0).getTitle());
        } catch (Exception e) {
            System.out.println("e.toString():"+e.toString());
        }
        return rss;
    }

    public void onItemClick(int position) {
        //view.startNewsActivity(rss.getChannelObject().getItems().get(position).getGuid().getValue());
        view.showToast("Opening link: "+rss.getChannelObject().getItems().get(position).getGuid().getValue());
    }

    private class FetchFeedTask extends AsyncTask<Void, Void, Boolean> {

        private String urlLink;

        @Override
        protected void onPreExecute() {
            view.setRefreshing();
            urlLink = "https://habr.com/rss/hubs/all/";
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            if (TextUtils.isEmpty(urlLink))
                return false;
            try {
                //-------------------
                URL url = new URL(urlLink);
                InputStream inputStream = url.openConnection().getInputStream();
                xmlstring = parseFeed(inputStream);
                rss = getFromXml(xmlstring);
                //-------------------
                return true;
            } catch (IOException e) {
                System.out.println("e.toString():"+e.toString());
            }

            return false;
        }

        private StringBuilder parseFeed(InputStream inputStream) {
            Scanner s = new Scanner(inputStream).useDelimiter("\\A");
            StringBuilder builder = new StringBuilder();
            builder.append(s.hasNext() ? s.next() : "");
            return builder;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success){
                view.stopRefreshing();
                view.addItemsToList(rss.getChannelObject().getItems());
            }
        }
    }
}
