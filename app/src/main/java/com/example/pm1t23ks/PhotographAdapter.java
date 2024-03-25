package com.example.pm1t23ks;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import Models.Photograh;

public class PhotographAdapter extends ArrayAdapter<Photograh> {

    private Context mContext;
    private int mLayoutResourceId;
    private List<Photograh> mData;

    public PhotographAdapter(Context context, int layoutResourceId, List<Photograh> data) {
        super(context, layoutResourceId, data);
        this.mContext = context;
        this.mLayoutResourceId = layoutResourceId;
        this.mData = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.imageView = row.findViewById(R.id.foto);
            holder.txtDescripcion = row.findViewById(R.id.txtSubItems);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Photograh photograh = mData.get(position);
        holder.txtDescripcion.setText(photograh.getDescripcion());

        // Convertir el byte[] a Bitmap
        byte[] byteArray = photograh.getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        // Establecer la imagen en el ImageView
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView txtDescripcion;
    }
}