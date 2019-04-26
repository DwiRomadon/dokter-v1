package com.example.drgigi_appv1;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.drgigi_appv1.adapter.HandonAdapter;
import com.example.drgigi_appv1.data.DataHandon;
import com.example.drgigi_appv1.network.InitRetrofit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class detailacara extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    Context context;
    // Deklarasi
    ImageView ivGambarBerita;
    TextView tvTglTerbit, tvPenulis,speaker,alamat,kecamatan,mm,
            cost,person,cp,email,materi,kouta,bts_ealy,biaya_erli,bts_late,biaya_latet,tgl_mulai,tgl_selesai,
            judul,jmmulai,jamfinis,almt,Email,kuota,nama_handon;
    WebView wvKontenBerita;
    ImageButton back;
    SliderLayout sliderLayout;
    private RecyclerView recyclerView;

    HandonAdapter adapter;
    ListView list;

    ArrayList<DataHandon> newsList = new ArrayList<DataHandon>();


    String foto, kategori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailacara);



       back=(ImageButton)findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toback();
            }
        });
        tvPenulis = (TextView) findViewById(R.id.judul);
        cost=(TextView)findViewById(R.id.biaya);
        person=(TextView)findViewById(R.id.kouta);
        sliderLayout = (SliderLayout)findViewById(R.id.slider);
        bts_ealy=(TextView)findViewById(R.id.batasearly);
        biaya_erli=(TextView)findViewById(R.id.costearly);
        bts_late=(TextView)findViewById(R.id.bataslate);
        biaya_latet=(TextView)findViewById(R.id.costlate);
       tgl_mulai=(TextView)findViewById(R.id.tgl_mulai);
       tgl_selesai=(TextView)findViewById(R.id.tgl_selesai);
       judul=(TextView)findViewById(R.id.judulseminar);
       speaker=(TextView)findViewById(R.id.narasumber);
        jmmulai=(TextView)findViewById(R.id.jam_start);
        jamfinis=(TextView)findViewById(R.id.jam_finis);
        kuota=(TextView)findViewById(R.id.partisipan);
        Email=(TextView)findViewById(R.id.Email);
        almt=(TextView)findViewById(R.id.ALamat);

        materi=(TextView)findViewById(R.id.handon);
//        nama_handon=(TextView)findViewById(R.id.namahandon);

        showDetailBerita();

        list = (ListView) findViewById(R.id.namahandon);
        newsList.clear();

        adapter = new HandonAdapter(detailacara.this, newsList);

        list.setAdapter(adapter);

        tampilhandon();

    }






    public void toback(){
        Intent sign = new Intent(this, MainActivity.class);
        startActivity(sign);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }



    private void showDetailBerita() {

            // Tangkap data dari intent
            String judul_seminar = getIntent().getStringExtra("JDL_SMR");
            kategori = getIntent().getStringExtra("KATEGORI");
            String tg_mulai = getIntent().getStringExtra("TGL_MULAI");
            String tg_selesai = getIntent().getStringExtra("TGL_SELESAI");
            String jam_mulai = getIntent().getStringExtra("JAM_MULAI");
            String jam_selesai = getIntent().getStringExtra("JAM_SELESAI");
            String narasumber = getIntent().getStringExtra("narasumber");
            String cpp = getIntent().getStringExtra("CP");
            String emaill = getIntent().getStringExtra("EMAIL");
            String Almt = getIntent().getStringExtra("LOKASI");
            foto = getIntent().getStringExtra("FOTO");
            String batas_early_bird = getIntent().getStringExtra("batas_early_bird");
            String biaya_early_bird = getIntent().getStringExtra("biaya_early_bird");
            String batas_regule = getIntent().getStringExtra("batas_regule");
            String biaya_reguler = getIntent().getStringExtra("biaya_reguler");
            String batas_late = getIntent().getStringExtra("batas_late");
            String biaya_late = getIntent().getStringExtra("biaya_late");
            String koutaa = getIntent().getStringExtra("KUOTA");
            String handon = getIntent().getStringExtra("HANDON");
            String deskripsi = getIntent().getStringExtra("DESKRIPSI");


            JSONArray jsonArray = null;
            String cps;
            try {
                jsonArray = new JSONArray(narasumber);
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject jObjct = jsonArray.getJSONObject(j);
                    cps = jObjct.getString("value");
                    speaker.append("\n" + cps);//
                    //Log.i("woi",cp);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
//
//
            JSONArray sonArray = null;
            String handone;
            try {
                sonArray = new JSONArray(cpp);
                for (int j = 0; j < sonArray.length(); j++) {
                    JSONObject jObjct = sonArray.getJSONObject(j);
                    handone = jObjct.getString("value");
                    materi.append("\n" + handone);
                    //Log.i("woi",cp);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
//
//            JSONArray ray = null;
//            String val1,val2,val3,val4,val5,val6,val7,val8,val9,val10;
//            try {
//                ray = new JSONArray(handon);
//                for (int j = 0; j < ray.length(); j++) {
//                    JSONObject jObjct = ray.getJSONObject(j);
//                    val1 = (String) jObjct.getString("value");
//                val2= (String) jObjct.getString("skp_hand_on");
//                val3= (String) jObjct.getString("kouta");
//                val4= (String) jObjct.getString("batas_early_bird");
//                val5= (String) jObjct.getString("biaya_reguler");
//                val6= (String) jObjct.getString("batas_late_menu");
//                val7= (String) jObjct.getString("biaya_late");
//                val8= (String) jObjct.getString("start_hand_on");
//                val9= (String) jObjct.getString("nd_hand_on");
//                val10= (String) jObjct.getString("biaya_early_bird");
//
//                   nama_handon.append("\n" + val1);
//                    //Log.i("woi",cp);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }


//        mm.setText(handon);

            bts_ealy.setText(batas_early_bird);
            biaya_erli.setText(biaya_early_bird);
            bts_late.setText(batas_late);
            biaya_latet.setText(biaya_late);
            // alamat.setText(Almt);
            tgl_mulai.setText(tg_mulai);
            tgl_selesai.setText(tg_selesai);
            judul.setText(judul_seminar);
            person.setText(batas_regule);
            cost.setText(biaya_reguler);
            kuota.setText(koutaa);
            almt.setText(Almt);
            Email.setText(deskripsi);
            jamfinis.setText(jam_selesai);
            jmmulai.setText(jam_mulai);

            showImageSlide();

    }


    public void tampilhandon(){
        String handon = getIntent().getStringExtra("HANDON");
        JSONArray ray = null;
        try {
            ray = new JSONArray(handon);
            for (int j = 0; j < ray.length(); j++) {
                JSONObject jObjct = ray.getJSONObject(j);
                DataHandon news = new DataHandon();

                news.setValue(jObjct.getString("value"));
                news.setKuota(jObjct.getString("kouta"));
                news.setBatasEarly(jObjct.getString("batas_early_bird"));
                news.setSkpHandon(jObjct.getString("skp_hand_on"));
                news.setBiayaReguler(jObjct.getString("biaya_reguler"));
                news.setBatasLate(jObjct.getString("batas_late_menu"));
                news.setBiayaLate(jObjct.getString("biaya_late"));
                news.setStartHandon(jObjct.getString("start_hand_on"));
                news.setEndHandon(jObjct.getString("end_hand_on"));
                news.setBiayaErli(jObjct.getString("biaya_early_bird"));


                /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // TODO Auto-generated method stub
                        Intent a = new Intent(ListBad.this, ListAbsenNgajar.class);
                        a.putExtra("nidn", nidn);
                        a.putExtra("kodemk", newsList.get(position).getNomk());
                        a.putExtra("kelas", newsList.get(position).getKelas());
                        a.putExtra("kodeHari", newsList.get(position).getKdHari());
                        startActivity(a);
                    }
                });*/

                newsList.add(news);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void showImageSlide(){

        String r1 = foto.replace("[", "");
        String r2 = r1.replace("]", "");
        String[] gambars = r2.split(",");

        for (int i=0; i< gambars.length; i++){
            HashMap<String,String> Hash_file_maps = new HashMap<String, String>();
            String r3 = gambars[i].replace("\"", "");
            String url = InitRetrofit.API_URL + r3;
            Log.v("woi", url);
            Hash_file_maps.put(kategori,url);

            for(String name : Hash_file_maps.keySet()){

                TextSliderView textSliderView = new TextSliderView(detailacara.this);
                textSliderView
                        .description(name)
                        .image(Hash_file_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(detailacara.this);
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra",name);
                sliderLayout.addSlider(textSliderView);
            }
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
            sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            sliderLayout.setCustomAnimation(new DescriptionAnimation());
            sliderLayout.setDuration(6000);
            sliderLayout.addOnPageChangeListener(detailacara.this);
        }

    }



    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}