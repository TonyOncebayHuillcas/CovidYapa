package com.connecttix.covidyapa.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.connecttix.covidyapa.R;
import com.connecttix.covidyapa.database.SqliteClass;
import com.connecttix.covidyapa.models.ProductModel;
import com.connecttix.covidyapa.models.TiendaModel;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    List<ProductModel> items;
    View.OnClickListener listener;

    public ProductAdapter(Context context, ArrayList<ProductModel> items){
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.row_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    @SuppressLint("ResourceAsColor")
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        // Set item views based on your views and data model

        holder.bind(items.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Display info " + position, Toast.LENGTH_SHORT).show();
                //Intent i = new Intent(context, DetailContactActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                //context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_product,tv_direccion,tv_stock,tv_precio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_product = (TextView) itemView.findViewById(R.id.tv_product);
            tv_direccion = (TextView) itemView.findViewById(R.id.tv_direccion);
            tv_stock = (TextView) itemView.findViewById(R.id.tv_stock);
            tv_precio = (TextView) itemView.findViewById(R.id.tv_precio);

        }

        public void bind(ProductModel model){

            TiendaModel tiendaModel = SqliteClass.getInstance(context).databasehelp.storeSql.getStore(String.valueOf(model.getId_tienda()));

            tv_product.setText(model.getNombre());
            tv_direccion.setText(tiendaModel.getDirección());
            tv_stock.setText(String.valueOf(model.getStock()));
            tv_precio.setText(model.getPrecio());

        }

    }
}