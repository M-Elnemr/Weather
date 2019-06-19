package com.example.weather;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.model.Adapter;
import com.example.weather.model.Model;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ECity) EditText ECity;
    @BindView(R.id.recycler) RecyclerView recyclerView;
    Adapter adapter;
    String city;
    ArrayList<Model> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        models = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        adapter = new Adapter(models);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickL(new Adapter.OnItemClickL() {
            @Override
            public void onItemDelete(int position) {
                models.remove(position);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.button)
    public void submit(){

        if(ECity.getText().toString().equals(null)){
            ECity.setError("Write The City Name");
            return;
        }
        city = ECity.getText().toString().trim();
        new Test(MainActivity.this).execute();
    }

    private class Test extends AsyncTask<String, Void, Model>{

        ProgressDialog progressDialog;
        Context context;

        public Test(Context context){
            this.context = context;
            progressDialog = new ProgressDialog(context);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
             progressDialog.setTitle("Loading ... please wait");
             progressDialog.show();
        }

        @Override
        protected Model doInBackground(String... strings) {

            Model model = new Model();
            String data = JsonClient.getWeather(city);
            try{
               model = JsonParser.parsing(data);

            }catch (Throwable e){
                e.printStackTrace();
            }
            return model;
        }

        @Override
        protected void onPostExecute(Model model) {
            super.onPostExecute(model);

            ECity.setText("");

            String m = model.getCityName();
            if(m == null){
                Toast.makeText(MainActivity.this, "The City Name Is Incorrect", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                return;
            }
            models.add(model);
            adapter.notifyDataSetChanged();

            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }
}
