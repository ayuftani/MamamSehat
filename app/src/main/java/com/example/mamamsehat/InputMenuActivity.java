package com.example.mamamsehat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class InputMenuActivity extends AppCompatActivity {

    private ImageButton button_add_image;
    Button button_post;
    ImageView img;
    Bitmap selectedImage;
    EditText nama,harga,deskripsi;
    CardView loading_bar;

    private static int RESULT_LOAD_IMG = 1;

    private StorageReference mStorageRef;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseFirestore firebaseFirestore;



    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_menu);

        button_add_image = findViewById(R.id.button_add_image);
        button_post = findViewById(R.id.button_post);
        img = findViewById(R.id.img);
        nama = findViewById(R.id.nama);
        harga = findViewById(R.id.harga);
        deskripsi = findViewById(R.id.deskripsi);
        loading_bar = findViewById(R.id.loading_bar);

        //firebase storage reference
        mStorageRef = FirebaseStorage.getInstance().getReference();
        //dekalari firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("menu");
        firebaseFirestore = FirebaseFirestore.getInstance();


        button_add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalery();
            }
        });
        button_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getNama = nama.getText().toString();
                String getHarga = harga.getText().toString();
                String getDeskripsi = deskripsi.getText().toString();

                if(getNama.equals("") || getHarga.equals("") || getDeskripsi.equals("")){
                    Toast.makeText(InputMenuActivity.this, "Lengkapi Data", Toast.LENGTH_SHORT).show();
                }else{
                    uploadFirebase();
                }

            }
        });

    }

    public void openGalery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    public void uploadFirebase(){
        loading_bar.setVisibility(View.VISIBLE);
        final String getNama = nama.getText().toString();
        final String getHarga = harga.getText().toString();
        final String getDeskripsi = deskripsi.getText().toString();

        StorageReference ref = mStorageRef.child(getNama);
        ref.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        loading_bar.setVisibility(View.GONE);
                        // Get a URL to the uploaded content
//                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Uri downloadUrl = taskSnapshot.getUploadSessionUri();


                        String getDownloadUrl = downloadUrl.toString();

                        MenuData menu = new MenuData
                                (getNama,getHarga,getDeskripsi, getDownloadUrl);

                        Map<String, String> inputMenu = new HashMap<>();
                        inputMenu.put("name", getNama);
                        inputMenu.put("harga", getHarga);
                        inputMenu.put("imgUrl", getDownloadUrl);
                        inputMenu.put("deskripsi", getDeskripsi);

                        firebaseFirestore.collection("menu").add(inputMenu)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(InputMenuActivity.this, "Berhasil mengunggah", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(InputMenuActivity.this, MenuActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        InputMenuActivity.this.startActivity(intent);
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(InputMenuActivity.this, "Gagal mengunggah.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
                img.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(InputMenuActivity.this, "Gagal.", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(InputMenuActivity.this, "Jangan Lupa Pilih Gambar.",Toast.LENGTH_LONG).show();
        }
    }


}
