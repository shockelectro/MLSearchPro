package com.electroshock.mlsearch;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.electroshock.mlsearch.model.Producto;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ProductAdapter extends ArrayAdapter {

    private List<Producto> productoList;
    private int resource;
    private LayoutInflater inflater;
    public ProductAdapter(Context context, int resource, List<Producto> objects) {
        super(context, resource, objects);
        productoList = objects;
        this.resource = resource;
        inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(resource, null);
            holder.ivProductIcon = (ImageView)convertView.findViewById(R.id.ivIcon);
            holder.tvTitle = convertView.findViewById(R.id.tvTitle);
            holder.tvPrice = convertView.findViewById(R.id.tvPrice);
            holder.tvLocalidad = convertView.findViewById(R.id.tvTagline);
            holder.tvVendedor = convertView.findViewById(R.id.tvVendedor);
            holder.tvFin = convertView.findViewById(R.id.tvFin);
            holder.tvLink = convertView.findViewById(R.id.tvLink);
            holder.tvLink.setMovementMethod(LinkMovementMethod.getInstance());
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar);

        // Then later, when you want to display image
        final ViewHolder finalHolder = holder;
        ImageLoader.getInstance().displayImage(productoList.get(position).getThumbnail(), holder.ivProductIcon, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
                finalHolder.ivProductIcon.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progressBar.setVisibility(View.GONE);
                finalHolder.ivProductIcon.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(View.GONE);
                finalHolder.ivProductIcon.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                progressBar.setVisibility(View.GONE);
                finalHolder.ivProductIcon.setVisibility(View.INVISIBLE);
            }
        });

        holder.tvTitle.setText(productoList.get(position).getTitle());
        holder.tvPrice.setText("Precio:" + productoList.get(position).getPrice().toString());
        holder.tvLocalidad.setText("Lugar:" + productoList.get(position).getAddress().getCityName());
        // TODO NIckname ususario https://api.mercadolibre.com/users/74127646?attributes=nickname
        holder.tvVendedor.setText("Vendedor:" + productoList.get(position).getSeller().getId());
        holder.tvFin.setText("Fin:" + productoList.get(position).getStopTime() );
        holder.tvLink.setText("Link:" + productoList.get(position).getPermalink() );
        return convertView;
    }


    private class ViewHolder{
        private ImageView ivProductIcon;
        private TextView tvTitle;
        private TextView tvPrice;
        private TextView tvLocalidad;
        private TextView tvVendedor;
        private TextView tvFin;
        private TextView tvLink;
    }
}
