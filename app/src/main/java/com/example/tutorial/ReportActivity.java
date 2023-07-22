package com.example.tutorial;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final String userId = "userId";
    private static final String P = "MyPrefs";
    public static final String DATA_TITLE = "TITLE";
    public static final int REQUEST_PICK_PHOTO = 1;
    int REQ_CAMERA = 101;
    File fileDirectoty, imageFilename;
    String strTitle, strTimeStamp, strImageName, strFilePath, strBase64Photo;
//    InputDataViewModel inputDataViewModel;
    Toolbar toolbar;
    TextView tvTitle;
    ImageView imageLaporan;
    LinearLayout layoutImage;
    ExtendedFloatingActionButton fabSend;
    EditText inputTelepon, inputTanggal, inputLaporan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        sharedPreferences = getSharedPreferences(P, MODE_PRIVATE);

        int id = sharedPreferences.getInt(userId, 0);

        setStatusBar();
        setInitLayout();
        setSendLaporan();
    }



    private void setInitLayout() {
        toolbar = findViewById(R.id.toolbar);
        tvTitle = findViewById(R.id.tvTitle);
        imageLaporan = findViewById(R.id.imageLaporan);
        layoutImage = findViewById(R.id.layoutImage);
        fabSend = findViewById(R.id.fabSend);
        inputTelepon = findViewById(R.id.inputTelepon);
        inputTanggal = findViewById(R.id.inputTanggal);
        inputLaporan = findViewById(R.id.inputLaporan);

        //get data intent
        strTitle = getIntent().getExtras().getString(DATA_TITLE);
        if (strTitle != null) {
            tvTitle.setText(strTitle);
            Log.e("title", strTitle);
        }

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

//        inputDataViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider
//                .AndroidViewModelFactory
//                .getInstance(this.getApplication()))
//                .get(InputDataViewModel.class);

        layoutImage.setOnClickListener(v -> {
            AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
            pictureDialog.setTitle("Upload Foto Bukti Laporan");
            String[] pictureDialogItems = {"Pilih foto dari galeri", "Ambil foto lewat kamera"};

            pictureDialog.setItems(pictureDialogItems,
                    (dialog, which) -> {
                        switch (which) {
                            case 0:
                                Dexter.withContext(ReportActivity.this)
                                        .withPermissions(Manifest.permission.CAMERA,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                                Manifest.permission.ACCESS_FINE_LOCATION)
                                        .withListener(new MultiplePermissionsListener() {
                                            @Override
                                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                                if (report.areAllPermissionsGranted()) {
                                                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                                    startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                                }
                                            }

                                            @Override
                                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                                                token.continuePermissionRequest();
                                            }
                                        }).check();
                                break;
                            case 1:
                                Dexter.withContext(ReportActivity.this)
                                        .withPermissions(Manifest.permission.CAMERA,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                                Manifest.permission.ACCESS_FINE_LOCATION)
                                        .withListener(new MultiplePermissionsListener() {
                                            @Override
                                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                                if (report.areAllPermissionsGranted()) {
                                                    try {
                                                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(ReportActivity.this, BuildConfig.APPLICATION_ID + ".provider", createImageFile()));
                                                        startActivityForResult(intent, REQ_CAMERA);
                                                    } catch (IOException ex) {
                                                        Toast.makeText(ReportActivity.this, "Gagal membuka kamera!", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                                                token.continuePermissionRequest();
                                            }
                                        }).check();
                                break;
                        }
                    });
            pictureDialog.show();
        });

        inputTanggal.setOnClickListener(view -> {
            Calendar tanggalJemput = Calendar.getInstance();
            DatePickerDialog.OnDateSetListener date = (view1, year, monthOfYear, dayOfMonth) -> {
                tanggalJemput.set(Calendar.YEAR, year);
                tanggalJemput.set(Calendar.MONTH, monthOfYear);
                tanggalJemput.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String strFormatDefault = "d MMMM yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormatDefault, Locale.getDefault());
                inputTanggal.setText(simpleDateFormat.format(tanggalJemput.getTime()));
            };

            new DatePickerDialog(ReportActivity.this, date,
                    tanggalJemput.get(Calendar.YEAR),
                    tanggalJemput.get(Calendar.MONTH),
                    tanggalJemput.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    private void setSendLaporan() {
        fabSend.setOnClickListener(v -> {
            String strTelepon = inputTelepon.getText().toString();
            String strTanggal = inputTanggal.getText().toString();
            String strLaporan = inputLaporan.getText().toString();

            if (strFilePath == null || strTelepon.isEmpty() || strTanggal.isEmpty() || strLaporan.isEmpty()) {
                Log.d("CEK TITLE",DATA_TITLE);
                Toast.makeText(ReportActivity.this, "Data tidak boleh ada yang kosong!", Toast.LENGTH_SHORT).show();
            } else {
                int id = sharedPreferences.getInt(userId, 0);
                File file = new File(strFilePath);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form=data"), file);
//                inputDataViewModel.addLaporan(strTitle, strBase64Photo, strTanggal, strLaporan, strTelepon);

                RequestBody title =  RequestBody.create(MediaType.parse("multipart/form-data"), strTitle);
                RequestBody content =  RequestBody.create(MediaType.parse("multipart/form-data"), strLaporan);
                RequestBody phone =  RequestBody.create(MediaType.parse("multipart/form-data"), strTelepon);
                RequestBody date =  RequestBody.create(MediaType.parse("multipart/form-data"), strTanggal);
                MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), requestFile );
                ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
                Call<PostFormResponse> call = apiInterface.uploadReport(title, content, phone, date, image,id);
                call.enqueue(new Callback<PostFormResponse>() {
                    @Override
                    public void onResponse(Call<PostFormResponse> call, Response<PostFormResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ReportActivity.this, "Laporan Anda terkirim, tunggu info selanjutnya ya!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Log.e("TAG", "onResponse: Response not successful");
                            Toast.makeText(ReportActivity.this, "Get data failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PostFormResponse> call, Throwable t) {
                        Log.e("TAG", "onFailure: " + t.getMessage());
                    }
                });

            }
        });
    }

    @SuppressLint("SimpleDateFormat")
    private File createImageFile() throws IOException {
        strTimeStamp = new SimpleDateFormat("dd MMMM yyyy HH:mm").format(new Date());
        strImageName = "IMG_";
        fileDirectoty = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "");
        imageFilename = File.createTempFile(strImageName, ".jpg", fileDirectoty);
        strFilePath = imageFilename.getAbsolutePath();
        return imageFilename;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CAMERA && resultCode == RESULT_OK) {
            convertImage(strFilePath);
        } else if (requestCode == REQUEST_PICK_PHOTO && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            assert selectedImage != null;
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String mediaPath = cursor.getString(columnIndex);
            cursor.close();
            strFilePath = mediaPath;
            convertImage(mediaPath);
        }
    }

    private void convertImage(String imageFilePath) {
        File imageFile = new File(imageFilePath);
        if (imageFile.exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            final Bitmap bitmapImage = BitmapFactory.decodeFile(strFilePath, options);
            Bitmap bitmap = Bitmap.createScaledBitmap(bitmapImage, 1024,1024,true);
            Glide.with(this)
                    .load(bitmap)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_image_upload)
                    .into(imageLaporan);

//            strBase64Photo = BitmapManager.bitmapToBase64(bitmap);
        }
    }

    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
