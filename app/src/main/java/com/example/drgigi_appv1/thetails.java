package com.example.drgigi_appv1;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
public class thetails extends AppCompatActivity {

    // Deklarasi
    ImageView ivGambarBerita;
    TextView tvTglTerbit, tvPenulis,speaker,alamat,kecamatan,kab,cost,person,cp,email,materi,kouta;
    WebView wvKontenBerita;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thetails);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //      setSupportActionBar(toolbar);

//        TextView fab = (TextView) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {z
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // Inisialisasi
        ivGambarBerita = (ImageView) findViewById(R.id.thumb);
        tvTglTerbit = (TextView) findViewById(R.id.time);
        tvPenulis = (TextView) findViewById(R.id.title);
        kecamatan=(TextView)findViewById(R.id.kec);
        kab=(TextView)findViewById(R.id.kab);
        cost=(TextView)findViewById(R.id.cost);
        person=(TextView)findViewById(R.id.participant);
        cp=(TextView)findViewById(R.id.cp);
        email=(TextView)findViewById(R.id.email);
        materi =(TextView)findViewById(R.id.materi);
        speaker=(TextView)findViewById(R.id.speaker);
        alamat=(TextView)findViewById(R.id.alamat);


        //wvKontenBerita = (WebView) findViewById(R.id.);

        // Jalankan method tampil detail berita
   showDetailBerita();

    }

    private void showDetailBerita() {
        // Tangkap data dari intent
        String judul_seminar = getIntent().getStringExtra("JDL_SMR");
        String kota = getIntent().getStringExtra("KOTA_SMR");
        String tanggal = getIntent().getStringExtra("TGL_SMR");
        String kec = getIntent().getStringExtra("KEC_SMR");
        String narasumber = getIntent().getStringExtra("NARASUMBER");
        String foto = getIntent().getStringExtra("FOTO");
        String materii = getIntent().getStringExtra("MATERI");
        String koutaa = getIntent().getStringExtra("KOUTA");
        String biayaa= getIntent().getStringExtra("BIAYA");
        String cpp = getIntent().getStringExtra("CP");
        String emaill = getIntent().getStringExtra("EMAIL");
        String Almt = getIntent().getStringExtra("LOKASI");



        // Set judul actionbar / toolbar
//        getSupportActionBar().setTitle(judul_berita);

        // Set ke widget
        tvPenulis.setText("JUDUL SEMINAR : " + judul_seminar);
        tvTglTerbit.setText("WAKTU SEMINAR: " + tanggal);
        kecamatan.setText("KECAMATAN: " + kec);
        kab.setText("KOTA: " + kota);
        materi.setText("MATERI: " + materii);
        person.setText(koutaa);
        cost.setText(biayaa);
        cp.setText("TELP: " + cpp);
        email.setText("EMAIL: " + emaill);
        speaker.setText("NARASUMBER: " + narasumber);
        alamat.setText("ALAMAYT: " + narasumber);

        //tvTglTerbit.setText(tanggal_berita);
        // Untuk gambar berita
        Picasso.with(this).load(foto).into(ivGambarBerita);
//        // Set isi berita sebagai html ke WebView
//        wvKontenBerita.getSettings().setJavaScriptEnabled(true);
//        wvKontenBerita.loadData(isi_berita, "text/html; charset=utf-8", "UTF-8");
    }
}