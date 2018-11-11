package com.electroshock.mlsearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.electroshock.mlsearch.model.Producto;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    private List<Producto> dataSetList;
    private String[] mDataset;
    private List lDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    /*
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public MyViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }*/

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView title,apellido;

        //Recibe una vista
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView =(CardView) itemView.findViewById(R.id.cardview);
            title = (TextView) itemView.findViewById(R.id.title);
            apellido = (TextView) itemView.findViewById(R.id.apellido);
        }
    }


    public MyAdapter(Context context,List<Producto> lProductos){
        this.context=context;
        this.dataSetList=lProductos;
    }
    public MyAdapter(List<Producto> lProductos){
        this.dataSetList=lProductos;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
 /*   public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }*/

    public void setDataSetList(List<Producto> dataSetList) {
        this.dataSetList = dataSetList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View itemView=  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        // create a new view
        //TextView v = (TextView) LayoutInflater.from(parent.getContext())
          //      .inflate(R.layout.my_text_view, parent, false);
        //...
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(dataSetList.get(position).getTitle());
        holder.apellido.setText(dataSetList.get(position).getId());
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mDataset[position]);
        //holder.mTextView.setText(lDataset.get(position).toString());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    //public int getItemCount() {return mDataset.length;     }
    public int getItemCount() {
        if (lDataset != null) {
            return dataSetList.size();
        } else
            return 0;
    }
}