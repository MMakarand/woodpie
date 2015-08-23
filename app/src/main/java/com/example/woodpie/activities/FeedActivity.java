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
import com.example.woodpie.listeners.InfiniteScrollListener;
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
        listView.setOnScrollListener(new InfiniteScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount)
            {
                addMoreFeed();
            }
        });

        addFeed();
    }

    private void addFeed()
    {
        for (int i = 0; i < 10; i++)
        {
            FeedItem feedItem = new FeedItem();
            User user = new User();
            user.setUserName("Asmita Metrewar");
            user.setProfilePicURL("http://qph.is.quoracdn.net/main-thumb-9289996-200-jbqkkqweisqhwcvyzhghtqtmvaneprwd.jpeg");
            feedItem.setUser(user);

            Book book = new Book();
            book.setBookName("To Kill A Mockingbird");
            book.setAuthorName("Harper Lee");
            book.setCoverPicURL("http://ecx.images-amazon.com/images/I/51J6fU-Lw%2BL.jpg");
            feedItem.setBook(book);

            feedItem.setId(i);
            feedItem.setAction(ActionType.ADD_TO_COLLECTION);

            feedItems.add(feedItem);
        }
        listAdapter.notifyDataSetChanged();
    }

    private void addMoreFeed()
    {
        for (int i = 0; i < 10; i++)
        {
            FeedItem feedItem = new FeedItem();
            User user = new User();
            user.setUserName("Makarand Muley");
            user.setProfilePicURL("https://goo.gl/OEbewK");
            feedItem.setUser(user);

            Book book = new Book();
            book.setBookName("A Game of Thrones");
            book.setAuthorName("George R R Martin");
            book.setCoverPicURL("http://www.georgerrmartin.com/wp-content/uploads/2013/03/GOTMTI2.jpg");
            feedItem.setBook(book);

            feedItem.setId(feedItems.size());
            feedItem.setAction(ActionType.RATE);
            feedItem.setRating(4);

            feedItems.add(feedItem);
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
