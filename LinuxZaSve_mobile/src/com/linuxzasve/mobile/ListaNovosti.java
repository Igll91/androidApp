package com.linuxzasve.mobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.google.gson.Gson;
import com.linuxzasve.mobile.adapters.MySimpleArrayAdapter;
import com.linuxzasve.mobile.rest.LzsRestApi;
import com.linuxzasve.mobile.rest.LzsRestResponse;
import com.linuxzasve.mobile.rest.Post;

public class ListaNovosti extends SherlockActivity implements OnItemClickListener{

	private static final String HTTP_FEEDS_FEEDBURNER_COM_LINUXZASVE = "http://feeds.feedburner.com/linuxzasve";
	private static final String KEY_VALUES_BUNDLE_SESSION = "valuesOfPosts";
	
	private LinearLayout novostiProgressLayout;
	private MenuItem refresh;

	public static List<Post> values;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_novosti);

		ListView listaClanaka = (ListView)findViewById(R.id.mylist);
		listaClanaka.setOnItemClickListener(this);
		
		values = new ArrayList<Post>();
		
		MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, values);
		listaClanaka.setAdapter(adapter);
		
		novostiProgressLayout = (LinearLayout)findViewById(R.id.novostiProgressLayout);
		novostiProgressLayout.setVisibility(View.VISIBLE);

		ActionBar ab = getSupportActionBar();
		ab.setSubtitle(R.string.subtitle_activity_lista_novosti); 

	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.lista_novosti, menu);
		refresh = menu.findItem(R.id.menu_refresh);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {

		switch (item.getItemId()) {

		case R.id.menu_refresh:
			refresh.setActionView(R.layout.actionbar_indeterminate_progress);
			fetchArticles();
			return true;

		case R.id.action_search:
			onSearchRequested();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		Log.i("test_tag", "onSaveInstance");
		// spremi listu novosti .... parcelable sve kaj se tam nalazi !
		outState.putParcelableArray(KEY_VALUES_BUNDLE_SESSION, values.toArray(new Post[values.size()]));
		
	}
	
	// The system calls onRestoreInstanceState() only if there is a saved state to restore.
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		Log.i("test_tag", "onRestoreInstance");
		// restore liste, pa onda provjera dali je lista prazna, ako nije znaèi nemoj skidati s neta !
		for(Parcelable p: savedInstanceState.getParcelableArray(KEY_VALUES_BUNDLE_SESSION))
		{
			Post tempPost = (Post) p;
			values.add(tempPost);
		}
	}
	
	@Override
	protected void onResume() 
	{
		super.onResume();

		if(values.isEmpty()) 	fetchArticles();	
		else 					cleanScreen();
	}
	
	private class DownloadRssFeed extends AsyncTask<String, Void, LzsRestResponse> {
		@Override
		protected void onPreExecute() {
		}

		@Override
		protected LzsRestResponse doInBackground(final String... urls) {
			LzsRestResponse obj2 = null;
			LzsRestApi api = null;

			try 
			{
				api = new LzsRestApi();

				String json = api.getRecentPosts();

				Gson gson = new Gson();
				obj2 = gson.fromJson(json, LzsRestResponse.class);
			}
			catch (IllegalStateException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}

			return obj2;
		}

		@Override
		protected void onPostExecute(final LzsRestResponse lzs_feed) {
			cleanScreen();
			values.clear();
			values.addAll(lzs_feed.getPosts());
		}
	}

	public void fetchArticles() 
	{
		if (ActivityHelper.isOnline(this)) 
			new DownloadRssFeed().execute(HTTP_FEEDS_FEEDBURNER_COM_LINUXZASVE);
		else 
		{
			Toast.makeText(getBaseContext(), R.string.nedostupan_internet, Toast.LENGTH_LONG).show();
			cleanScreen();
		}
	}

	@Override
	public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) 
	{
		Intent i = new Intent(getApplicationContext(), Clanak.class);

		// ove key Stringove treba spremiti u neke vrijednosti...
		i.putExtra("naslov", values.get(position).getTitle());
		i.putExtra("sadrzaj", values.get(position).getContent());
		i.putExtra("komentari", values.get(position).getUrl());
		i.putExtra("origLink", values.get(position).getUrl());
		
		startActivity(i);
	}
	
	private void cleanScreen()
	{
		if (novostiProgressLayout != null) 	novostiProgressLayout.setVisibility(View.GONE);
		if (refresh != null) 				refresh.setActionView(null);  
	}
	
}
