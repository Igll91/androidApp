package com.linuxzasve.mobile.adapters;

import java.util.List;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.linuxzasve.mobile.R;
import com.linuxzasve.mobile.emote.EmoticonDrawables;
import com.linuxzasve.mobile.wp_comment.Komentar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class KomentariArrayAdapter extends ArrayAdapter<Komentar> {

	private final Context context;
	private final List<Komentar> values;

	private TextView neki_tekst;
	private TextView datum;
	private TextView autor;
	private ImageView thumbnail;
	
	public KomentariArrayAdapter(Context context, List<Komentar> naslovi) {
		super(context, R.layout.komentar_redak, naslovi);
		this.context = context;
		this.values = naslovi;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		if(convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.komentar_redak, parent, false);
		}
		
		neki_tekst 	= (TextView) convertView.findViewById(R.id.tekst_komentar );
		datum 		= (TextView) convertView.findViewById(R.id.datum_komentar);
		autor 		= (TextView) convertView.findViewById(R.id.autor_komentar);
		thumbnail 	= (ImageView)convertView.findViewById(R.id.komentarThumbnail);
		
		neki_tekst.setMovementMethod(LinkMovementMethod.getInstance());
		datum.setText(values.get(values.size() - position - 1).getPublishDate());
		autor.setText(values.get(values.size() - position - 1).getCreator());
		
		
		neki_tekst.setText(Html.fromHtml(
				values.get(values.size() - position - 1).getContent(),
				new ImageGetter() {
					@Override
					public Drawable getDrawable(String source) {
						
						/* Dohvacam ID slike. Ako nema takovg smajla, vracam null */
						Integer id = EmoticonDrawables.getDrawableId(source);
						
						if (id == null) {
							return null;
						}

						Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);

						Drawable d = new BitmapDrawable(context.getResources(), bitmap);
						int dWidth = d.getIntrinsicWidth();
						int dHeight = d.getIntrinsicHeight();

						d.setBounds(0, -dHeight, dWidth, 0);
						return d;
					}
				}, null));
		
		UrlImageViewHelper
		.setUrlDrawable(thumbnail, values.get(values.size() - position - 1)
				.getThumbnail());
		
		return convertView;
	}
}
