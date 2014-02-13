package com.linuxzasve.mobile.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.linuxzasve.mobile.R;
import com.linuxzasve.mobile.rest.Post;
import com.linuxzasve.mobile.timthumb.TimThumb;

public class MySimpleArrayAdapter extends ArrayAdapter<Post> {
	
	private final Context context;
	private List<Post> naslovi;
	
	public MySimpleArrayAdapter(final Context context, final List<Post> naslovi) {
		super(context, R.layout.novosti_redak, naslovi);
		this.context = context;
		this.naslovi = naslovi;
	}

	static class ViewHolder 
	{
		TextView neki_tekst;
		TextView datum;
		TextView autor;
		TextView broj_komentara;
		ImageView thumbnail;
		boolean isSet = false;
	}
	
	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.novosti_redak, parent, false);
			holder = new ViewHolder();

			holder.neki_tekst = (TextView)convertView.findViewById(R.id.neki_tekst);
			holder.datum = (TextView)convertView.findViewById(R.id.datum);
			holder.autor = (TextView)convertView.findViewById(R.id.autor);
			holder.broj_komentara = (TextView)convertView.findViewById(R.id.broj_komentara);
			holder.thumbnail = (ImageView)convertView.findViewById(R.id.thumbnail);

			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder)convertView.getTag();
		}
		holder.neki_tekst.setText(naslovi.get(position).getTitle());
		holder.datum.setText(naslovi.get(position).getDate("dd.MM.yyyy"));
		holder.autor.setText(naslovi.get(position).getAuthor().getNickname());
		holder.broj_komentara.setText(Integer.toString(naslovi.get(position).getComment_count()));

		String thumbnailUrl = TimThumb.generateTimThumbUrl(naslovi.get(position).getThumbnail_images().getFull().getUrl(), 256, 256, 1);
		UrlImageViewHelper.setUrlDrawable(holder.thumbnail, thumbnailUrl);

		return convertView;
	}
}
