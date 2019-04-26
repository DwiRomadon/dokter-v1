package com.example.drgigi_appv1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drgigi_appv1.img.ApiConfig;
import com.example.drgigi_appv1.img.ServerResponse;
import com.example.drgigi_appv1.network.InitRetrofit;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterGambarMahasiswa extends AppCompatActivity {

    EditText edUserName;
    Button uploadKtp, uploadKtm, uploadStr, uploadKta, uploadData;
    TextView txtKtp, txtKtm, txtstr, txtKta;

    String username;
    String profesi;

    String mediaPathKtp, mediaPathKtm, mediaPathStr, mediaPathKta;

    String[] mediaColumns = {MediaStore.Video.Media._ID};
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_gambar_mhs);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");

        edUserName  = findViewById(R.id.username);
        uploadKtp   = findViewById(R.id.uploadktp);
        uploadKtm   = findViewById(R.id.uploadKtm);
        uploadData  = findViewById(R.id.uploadImg);

        txtKtm      = findViewById(R.id.txtktm);
        txtKtp      = findViewById(R.id.txtktp);

        Intent a    = getIntent();
        username    = a.getStringExtra("username");
        profesi     = a.getStringExtra("profesi");
        edUserName.setText(username);

        edUserName.setEnabled(false);

        uploadKtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });


        uploadKtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 1);
            }
        });

        uploadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadMultipleFiles();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPathKtp = cursor.getString(columnIndex);
                txtKtp.setText(mediaPathKtp);
                // Set the Image in ImageView for Previewing the Media
                //imgKtp.setImageBitmap(BitmapFactory.decodeFile(mediaPathKtp));
                cursor.close();

            } else if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

                // Get the Video from data
                Uri selectedVideo = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedVideo, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

                mediaPathKtm = cursor.getString(columnIndex);
                txtKtm.setText(mediaPathKtm);
                // Set the Video Thumb in ImageView Previewing the Media
                //imgKtm.setImageBitmap(BitmapFactory.decodeFile(mediaPathKtm));
                cursor.close();

            }else {
                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    // Uploading Image/Video
    private void uploadMultipleFiles() {
        progressDialog.show();

        // Map is used to multipart the file using okhttp3.RequestBody
        File ktp = new File(mediaPathKtp);
        File ktm = new File(mediaPathKtm);

        // Parsing any Media type file
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/jpeg"), ktp);
        RequestBody requestBody4 = RequestBody.create(MediaType.parse("image/jpeg"), ktm);

        MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("file", ktp.getName(), requestBody1);
        MultipartBody.Part fileToUpload4 = MultipartBody.Part.createFormData("file", ktm.getName(), requestBody4);

        RequestBody profesis = RequestBody.create(MultipartBody.FORM, profesi);
        RequestBody user = RequestBody.create(MultipartBody.FORM, username);

        ApiConfig getResponse = InitRetrofit.getRetrofit().create(ApiConfig.class);
        Call<ServerResponse> call = getResponse.uploadMulFile(fileToUpload1, fileToUpload4, profesis, user);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (serverResponse != null) {
                    if (serverResponse.getSuccess() == false) {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    assert serverResponse != null;
                    Log.v("Response", serverResponse.toString());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }
}
