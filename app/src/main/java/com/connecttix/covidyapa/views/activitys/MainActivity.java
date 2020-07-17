package com.connecttix.covidyapa.views.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import com.connecttix.covidyapa.R;
import com.connecttix.covidyapa.adapters.ProductAdapter;
import com.connecttix.covidyapa.database.SqliteClass;
import com.connecttix.covidyapa.models.ProductModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ProductModel> products;
    TextView empty;
    Context context;
    SearchView editsearch;
    ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context= this;

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        products = SqliteClass.getInstance(context).databasehelp.productSql.getAllSites();

        getList(products);

    }

    @Override
    public void onResume() {
        super.onResume();
        products = SqliteClass.getInstance(context).databasehelp.productSql.getAllSites();
        adapter = new ProductAdapter(context, products);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    public void getList(ArrayList<ProductModel>list){
        if(list.size()>0){
            adapter = new ProductAdapter(context, list);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }else {
            empty.setVisibility(recyclerView.VISIBLE);
        }
    }

    public void resetSearch() {
        products = SqliteClass.getInstance(context).databasehelp.productSql.getAllSites();
        getList(products);
    }
}
