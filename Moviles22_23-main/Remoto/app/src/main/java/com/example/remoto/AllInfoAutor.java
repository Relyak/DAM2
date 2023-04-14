package com.example.remoto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import data.BookSearchViewModel;
import data.Volume;
import data.VolumesResponse;

public class AllInfoAutor extends AppCompatActivity {

    public static final String PAGINAS_MAXIMAS = "40";
    private List<Volume> results = new ArrayList<>();
    BookSearchViewModel vm;
    LiveData<VolumesResponse> data;
    TextView STitulo,SDescripcion,SAutor;
    ImageView SLibro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_info_autor);

        STitulo = findViewById(R.id.STitulo);
        SDescripcion = findViewById(R.id.SDescripcion);
        SAutor = findViewById(R.id.SAutor);
        SLibro = findViewById(R.id.SLibro);

        //Recibimos los datos para realizar una nueva petición
        int position = getIntent().getIntExtra("posicion",0);
        String autor = getIntent().getStringExtra("autor");
        String keyword = getIntent().getStringExtra("keyword");

        //Iniciamos observer
        vm=new ViewModelProvider(this).get(BookSearchViewModel.class);
        vm.init();
        data=vm.getVolumesResponseLiveData();

        //Se hace la petición
        vm.searchVolumes(keyword, autor, PAGINAS_MAXIMAS);
        data.observe(this, (data)->{

            //Obtenemos los valores y utilizamos la variable enviada para posicionar
            results = data.getItems();
            Volume vol = results.get(position);

            STitulo.setText(vol.getVolumeInfo().getTitle());
            SAutor.setText(String.valueOf(vol.getVolumeInfo().getAuthors()));
            SDescripcion.setText(vol.getVolumeInfo().getDescription());

            //Añadir la imagen
            if (vol.getVolumeInfo().getImageLinks() != null) {
                String imageUrl = vol.getVolumeInfo().getImageLinks().getSmallThumbnail()
                        .replace("http://", "https://");

                Glide.with(this).load(imageUrl).into(SLibro);
            }

        });



    }


}