package com.example.novopay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TabContent extends Fragment implements AsyncResponse {
    int position;
    RecyclerView r1;

    public static TabContent newInstance(int position) {
        TabContent tabContent = new TabContent();
        Bundle args = new Bundle();
        args.putInt("position", position);
        tabContent.setArguments(args);
        return tabContent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.tabcontent, container, false);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar last_day = Calendar.getInstance();
        last_day.add(Calendar.DATE, -1);
        Calendar last_month = Calendar.getInstance();
        last_month.add(Calendar.DATE, -30);

        String website = null;
        switch(position)
        {
            case 0:
                website = "https://newsapi.org/v2/everything?q=bitcoin&from=" + sdf.format(last_month.getTime()) + "&sortBy=publishedAt&apiKey=" + getString(R.string.api_key);
                break;
            case 1:
                website = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=" + getString(R.string.api_key);
                break;
            case 2:
                website = "https://newsapi.org/v2/everything?q=apple&from=" + sdf.format(last_day.getTime()) + "&to=" + sdf.format(last_day.getTime()) + "&sortBy=popularity&apiKey=" + getString(R.string.api_key);
                break;
            case 3:
                website = "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=" + getString(R.string.api_key);
                break;
            case 4:
                website = "https://newsapi.org/v2/everything?domains=wsj.com&apiKey=" + getString(R.string.api_key);
                break;
        }

        r1 = view1.findViewById(R.id.recyclerview1);

        Viewdata v1 = new Viewdata();
        v1.delegate = this;
        v1.setId(1);
        v1.execute("GET", website);

        return view1;
    }

    @Override
    public void processFinish(String output, int viewdata) {
        try {
            final List<Article> articleList = new ArrayList<>();

            JSONObject jsonObject = new JSONObject(output);
            JSONArray articles = jsonObject.getJSONArray("articles");

            for(int i = 0; i < articles.length(); i++)
            {
                Article a = new Article();
                JSONObject article = articles.getJSONObject(i);

                a.setImage(article.getString("urlToImage"));
                a.setTitle(article.getString("title"));
                a.setTimestamp(article.getString("publishedAt"));
                a.setUrl(article.getString("url"));

                articleList.add(a);
            }

            r1.setHasFixedSize(true);
            r1.setLayoutManager(new LinearLayoutManager(getContext()));

            ArticleAdapter articleAdapter = new ArticleAdapter(getContext(), R.layout.article, articleList);
            r1.setAdapter(articleAdapter);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}