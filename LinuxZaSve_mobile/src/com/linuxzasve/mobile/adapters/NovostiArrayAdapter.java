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

public class NovostiArrayAdapter extends ArrayAdapter<Post> {
	
	private final Context context;
	private List<Post> naslovi;
	
	private TextView neki_tekst;
	private TextView datum;
	private TextView autor;
	private TextView broj_komentara;
	private ImageView thumbnail;
	
	public NovostiArrayAdapter(final Context context, final List<Post> naslovi) {
		super(context, R.layout.novosti_redak, naslovi);
		this.context = context;
		this.naslovi = naslovi;
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		
		if(convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.novosti_redak, parent, false);
		}
		
		neki_tekst = (TextView)convertView.findViewById(R.id.neki_tekst);
		datum = (TextView)convertView.findViewById(R.id.datum);
		autor = (TextView)convertView.findViewById(R.id.autor);
		broj_komentara = (TextView)convertView.findViewById(R.id.broj_komentara);
		thumbnail = (ImageView)convertView.findViewById(R.id.thumbnail);
		
		neki_tekst.setText(naslovi.get(position).getTitle());
		datum.setText(naslovi.get(position).getDate("dd.MM.yyyy"));
		autor.setText(naslovi.get(position).getAuthor().getNickname());
		broj_komentara.setText(Integer.toString(naslovi.get(position).getComment_count()));
		
		String thumbnailUrl = TimThumb.generateTimThumbUrl(naslovi.get(position).getThumbnail_images().getFull().getUrl(), 256, 256, 1);
		UrlImageViewHelper.setUrlDrawable(thumbnail, thumbnailUrl);
		
		return convertView;
	}
}

