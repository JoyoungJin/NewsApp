package com.example.newsapp;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<NewsData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView TextView_title;
        public TextView TextView_Content;
        public SimpleDraweeView ImageView_title;
        public MyViewHolder(View v) {
            super(v);
            TextView_title = v.findViewById(R.id.TextView_title);
            TextView_Content = v.findViewById(R.id.TextView_Content);
            ImageView_title = v.findViewById(R.id.ImageView_title);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<NewsData> myDataset, Context context) {
        mDataset = myDataset;
        Fresco.initialize(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        //새로운 뷰를 생성한다.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    // 뷰의 내용을 바꿔준다.
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        NewsData news = mDataset.get(position);

        holder.TextView_title.setText(news.getTitle());
        holder.TextView_Content.setText(news.getContent());

        Uri uri = Uri.parse(news.getUrlToImage());
        holder.ImageView_title.setImageURI(uri);


    }

    // Return the size of your dataset (invoked by the layout manager)
    // 데이터셋의 사이즈를 리턴해준다.
    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }
}
