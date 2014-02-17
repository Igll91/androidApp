package com.linuxzasve.mobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.linuxzasve.mobile.adapters.KomentariArrayAdapter;
import com.linuxzasve.mobile.wp_comment.Komentar;
import com.linuxzasve.mobile.wp_comment.WordpressCommentParser;


public class ListaKomentara extends SherlockActivity {
	
	private ListView listView;
	private MenuItem refresh;

	LinearLayout komentariProgressLayout;
	String message;
	String post_id;
	String akismet;
	
	private List<Komentar> listOfComments;
	private KomentariArrayAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_komentara);

		listView = (ListView) findViewById(R.id.komentari);
		komentariProgressLayout = (LinearLayout) findViewById(R.id.komentariProgressLayout);
		
		Intent intent = getIntent();
		message = intent.getStringExtra("komentari");
		komentariProgressLayout.setVisibility(View.VISIBLE);
		
		listOfComments = new ArrayList<Komentar>();
		adapter = new KomentariArrayAdapter(this, listOfComments);
		listView.setAdapter(adapter); 
		
		ActionBar ab = getSupportActionBar();
		String naslov = intent.getStringExtra("naslov");
		ab.setSubtitle(getResources().getString(R.string.title_activity_lista_komentara) + naslov);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if(listOfComments.isEmpty())
			fetchArticles();
		else
			cleanScreen();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putParcelableArray(Val.KEY_VALUES_BUNDLE_SESSION, listOfComments.toArray(new Komentar[listOfComments.size()]));
	}
	
	// The system calls onRestoreInstanceState() only if there is a saved state to restore.
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		for(Parcelable p: savedInstanceState.getParcelableArray(Val.KEY_VALUES_BUNDLE_SESSION))
		{
			Komentar tempKomentar = (Komentar) p;
			listOfComments.add(tempKomentar);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.lista_komentara, menu);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		refresh = menu.findItem(R.id.menu_refresh);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
			
		case R.id.menu_refresh:
			refresh.setActionView(R.layout.actionbar_indeterminate_progress);
			fetchArticles();
			return true;
			
		case R.id.menu_new_comment:
			Intent intent = new Intent(this, NoviKomentar.class);
			
			intent.putExtra("post_id", post_id);
			intent.putExtra("akismet", akismet);
			intent.putExtra("orig_url", message);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private class DownloadRssFeed extends AsyncTask<String, Void, WordpressCommentParser> {
		@Override
		protected void onPreExecute() {
		}
		
		@Override
		protected WordpressCommentParser doInBackground(String... urls) {
			WordpressCommentParser lzs_feed=null;
			try {
				lzs_feed = new WordpressCommentParser(urls[0]);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return lzs_feed;
		}

		@Override
		protected void onPostExecute(WordpressCommentParser lzs_feed) {
			cleanScreen();
			listOfComments.clear();
			
			// OVO DORADITI NULL POINTER EXCEPTION NEKAD BACI !!! .... !!! .... !!! .... !!! 
			
			if(lzs_feed == null || lzs_feed.getPosts() == null) 
			{
				Toast.makeText(ListaKomentara.this, "TEST - NEMA KOMENTARA ZA OVAJ ÈLANAK!", Toast.LENGTH_LONG).show();
				finish();
			}
			
			listOfComments.addAll(lzs_feed.getPosts());
			adapter.notifyDataSetChanged();
		}
	}
	
	public void fetchArticles() 
	{
		if (ActivityHelper.isOnline(this)) 
			new DownloadRssFeed().execute(message);
		else 
		{
			Toast.makeText(getBaseContext(), R.string.nedostupan_internet, Toast.LENGTH_LONG).show();
			cleanScreen();
		}
	}
	
	private void cleanScreen()
	{
		if (komentariProgressLayout != null) 	komentariProgressLayout.setVisibility(View.GONE);
		if (refresh != null) 				refresh.setActionView(null);  
	}
}

