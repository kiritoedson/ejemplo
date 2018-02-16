package com.example.usuario.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button boton1;
    private Bitmap imagenDescargada;
    private ImageView miImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton1=findViewById(R.id.button1);
        miImagen=findViewById(R.id.imageView);
        boton1.setText("presiona");

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new DescargarImagen().execute(new URL("http://icons.iconarchive.com/icons/th3-prophetman/game/256/Gears-of-War-Skull-2-icon.png"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.mimenu,menu);
        return true;
    }

    class DescargarImagen extends AsyncTask<URL,Integer,Bitmap>{

        @Override
        protected Bitmap doInBackground(URL... urls) {
            try {
                imagenDescargada= BitmapFactory.decodeStream(urls[0].openConnection().getInputStream());

            }catch (Exception e){
                Log.e("diplo", "error"+e.toString());
            }
            return imagenDescargada;
        }

        @Override
        protected void onPostExecute (Bitmap bitmap){
            //establecer el ImagenView sobre el layout
            miImagen.setImageBitmap(imagenDescargada);
            super.onPostExecute(bitmap);
        }
    }
}
