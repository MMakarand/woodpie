package com.example.woodpie.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.woodpie.R;
import com.example.woodpie.adapters.FeedListAdapter;
import com.example.woodpie.data.FeedItem;
import com.example.woodpie.entities.Book;
import com.example.woodpie.entities.User;
import com.example.woodpie.utils.ActionType;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity
{

    private static final String TAG = FeedActivity.class.getSimpleName();
    private ListView listView;
    private FeedListAdapter listAdapter;
    private List <FeedItem> feedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        listView = (ListView) findViewById(R.id.list);

        feedItems = new ArrayList<>();

        listAdapter = new FeedListAdapter(this, feedItems);
        listView.setAdapter(listAdapter);

        addFeed();
    }

    private void addFeed()
    {
        for (int i = 0; i < 10; i++)
        {
            FeedItem feedItem1 = new FeedItem();
            User user = new User();
            user.setUserName("Asmita Metrewar");
            user.setProfilePicURL("http://api.androidhive.info/feed/img/cosmos.jpg");
            feedItem1.setUser(user);

            Book book1 = new Book();
            book1.setBookName("To Kill A Mockingbird");
            book1.setAuthorName("Harper Lee");
            book1.setCoverPicURL("http://api.androidhive.info/feed/img/cosmos.jpg");
            feedItem1.setBook(book1);

            feedItem1.setId(i);
            feedItem1.setAction(ActionType.ADD_TO_COLLECTION);

            feedItems.add(feedItem1);
        }
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
