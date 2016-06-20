package ar.com.unlam.soa.controldeluces;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Ariel on 20/06/2016.
 */
public class ImageAdapter extends BaseAdapter{
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }
    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(260, 260));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }


        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
    private Integer[] mThumbIds = {
            R.drawable.imagen,
            R.drawable.imagen5,
            R.drawable.imagen9,
            R.drawable.imagen2,
            R.drawable.imagen6,
            R.drawable.imagen10,
            R.drawable.imagen3,
            R.drawable.imagen7,
            R.drawable.imagen11,
            R.drawable.imagen4,
            R.drawable.imagen8,
            R.drawable.imagen12
    };

}
