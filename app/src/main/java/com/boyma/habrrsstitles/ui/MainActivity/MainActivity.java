package com.boyma.habrrsstitles.ui.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.boyma.habrrsstitles.R;
import com.boyma.habrrsstitles.models.Item;
import com.boyma.habrrsstitles.ui.NewsActivity.NewsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainActivityView {

    private MainActivityPresenter mpresenter;

    private SwipeRefreshLayout mSwipeLayout;
    private RecyclerView rec_view;
    private LinearLayoutManager mLayoutManager;
    private RecAdapter mAdapter;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mpresenter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        mpresenter = new MainActivityPresenter(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mpresenter.onSaveInstanceState(outState);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initUI() {

        mSwipeLayout = findViewById(R.id.swipeRefreshLayout);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mpresenter.onSwipeRefresh();
            }
        });

        rec_view = findViewById(R.id.rec_view);
        initrecView();
    }

    private void initrecView() {
        rec_view.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        rec_view.setLayoutManager(mLayoutManager);
        mAdapter = new RecAdapter();
        rec_view.setAdapter(mAdapter);
        rec_view.addOnItemTouchListener(new RecyclerTouchListener(this, rec_view, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                mpresenter.onItemClick(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    @Override
    public void setRefreshing() {
        mSwipeLayout.setRefreshing(true);
    }

    @Override
    public void stopRefreshing() {
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void addItemsToList(ArrayList<Item> items) {
        mAdapter.clearData();
        mAdapter.addAll(items);
    }

    @Override
    public void startNewsActivity(String link) {
        Intent intent = new Intent(this, NewsActivity.class);
        intent.putExtra("link",link);
        startActivity(intent);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    //-----------------------------------------
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public interface ClickListener{
        void onClick(View view,int position);
        void onLongClick(View view,int position);
    }
    //-----------------------------------------
}
