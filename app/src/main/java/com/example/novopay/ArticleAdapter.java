package com.example.novopay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    Context con;
    int _resource;
    List<Article> lival;

    View listItem;
    ImageView ima;
    TextView tx1;
    TextView tx2;

    public ArticleAdapter(Context context, int resource, List<Article> li) {
        // TODO Auto-generated constructor stub
        con = context;
        _resource = resource;
        lival = li;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);

            ima = itemView.findViewById(R.id.imageview1);
            tx1 = itemView.findViewById(R.id.textview1);
            tx2 = itemView.findViewById(R.id.textview2);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        listItem = layoutInflater.inflate(_resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Article article = lival.get(position);

        String image = article.getImage();
        String title = article.getTitle();
        String timestamp = article.getTimestamp();
        final String url = article.getUrl();

        image.replaceAll("http://", "https://");
        url.replaceAll("http://", "https://");
        timestamp = timestamp.replaceAll("T", " ").replaceAll("Z", "");

        new ImageLoadTask(image, ima).execute();
        tx1.setText(title);
        tx2.setText(timestamp);

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(con, News.class);
                intent1.putExtra("url", url);

                con.startActivity(intent1);
                ((Activity) con).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lival.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}