package com.example.asus.rahmadinaarifazhari_1202154341_studycase4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import static android.view.View.GONE;

public class ListMahasiswa extends AppCompatActivity {
    //    Deklarasi variabel
    private ListView mListView;
    private ProgressBar mProgressBar;
    private String[] mNamaMahasiswa = {
            "Rifa",
            "Ivana",
            "Jafar",
            "Farhan",
            "Hans",
            "Ica",
            "Karin",
            "Ariq",
            "Posma",
            "Brian",
            "Fajar",
            "Tesar",
            "Ical",
            "Ardi",
            "Hakim",
            "Yuzar",
            "Arya"
    };
    private ItemListView itemListView;
    private Button mBtnFind;

    ListMahasiswaFragment fragmentMhs;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);
//        referensi id
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mListView = (ListView) findViewById(R.id.lv_listnama);
        mBtnFind = (Button) findViewById(R.id.btn_find);

        mListView.setVisibility(GONE);

        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        mBtnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListView = new ItemListView(activity);
                itemListView.execute();
            }
        });
        if (savedInstanceState == null){
            fragmentMhs = new ListMahasiswaFragment();
            getSupportFragmentManager().beginTransaction().add(fragmentMhs,"task").commit();
        }else{ //activity created for subscquent time
            fragmentMhs = (ListMahasiswaFragment) getSupportFragmentManager().findFragmentByTag("task");
        }

        if (fragmentMhs != null){
            if (fragmentMhs.itemListView != null && fragmentMhs.itemListView.getStatus() == AsyncTask.Status.RUNNING){
                // progressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    class ItemListView extends AsyncTask<Void, String, Void>{

        private Activity activity;

        public ItemListView(Activity activity){
            this.activity = activity;
        }

        private ArrayAdapter<String> mAdapter;

        private int count = 1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListMahasiswa.this);
        public void onAttach(Activity activity){this.activity = activity;}
        public void onDetach(){activity = null;}

        @Override

        protected void onPreExecute(){
            mAdapter = (ArrayAdapter<String>)mListView.getAdapter();

//      Progress Dialog
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Name");
            mProgressDialog.setMessage("Please Wait");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);

//      Ketika button cancel di klik
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    itemListView.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialogInterface.dismiss();
                }
            });
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for(String item: mNamaMahasiswa){
                publishProgress(item);
                try{
                    Thread.sleep(100);
                }catch(Exception e){
                    e.printStackTrace();
                }
                if (isCancelled()){
                    itemListView.cancel(true);
                }
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);

            Integer current_status = (int) ((count/(float)mNamaMahasiswa.length)*100);
            mProgressBar.setProgress(current_status);

            //set progress only working for horizontal loading
            mProgressDialog.setProgress(current_status);

            //set message will not working when using horizontal loading
            mProgressDialog.setMessage(String.valueOf(current_status+"%"));
            count++;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            mProgressBar.setVisibility(GONE);

            // remove progress dialog
            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);
        }
    }
}
