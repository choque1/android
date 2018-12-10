package com.example.deivi.pedidosonline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Editar1 extends AppCompatActivity {
    ImageView image1;
    EditText title2,descripcion1,precio1;
    String titulo,descripcion,precio;
    Button aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar1);
        aceptar = findViewById(R.id.aceptar1);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Editar1.this,EditarMenu.class));
            }
        });
        image1 = findViewById (R.id.fotomenu1);
        title2 = findViewById (R.id.producto1);
        precio1 = findViewById(R.id.precio1);
        descripcion1 = findViewById (R.id.descripcion1);
        informacion();
    }
    public void informacion(){
        Intent intent = getIntent();
        titulo = intent.getStringExtra("nombre");
        descripcion = intent.getStringExtra("descripcion");
        precio = intent.getStringExtra("precio");
        title2.setText(titulo);
        descripcion1.setText(descripcion);
        precio1.setText(precio);

        //image1.setImageURI(Uri.parse(img));
    }
    String mCurrentPhotoPath;
    @SuppressLint("NewApi")
    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = null;

        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String imageFileName = "BACKUP_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,".jpg", storageDir  );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    static final int REQUEST_TAKE_PHOTO = 1;

    public void tomarFoto (View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }



    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            image1.setImageBitmap(imageBitmap);
        }
    }

    public void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

}
