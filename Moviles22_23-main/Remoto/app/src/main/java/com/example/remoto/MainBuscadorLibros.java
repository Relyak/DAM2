package com.example.remoto;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import data.BookSearchResultsAdapter;
import data.BookSearchViewModel;
import data.VolumesResponse;

public class MainBuscadorLibros extends AppCompatActivity implements BookSearchResultsAdapter.OnItemClickListener {

    public static int FILAS_LIBROS = 10;
    public static ActivityResultLauncher resultadoLauncher;
    TextView busqueda, autor;
    Button buscar;
    RecyclerView listaAutores;
    BookSearchViewModel vm;
    LiveData<VolumesResponse> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        busqueda= findViewById(R.id.idBusqueda);
        autor=findViewById(R.id.idAutor);
        buscar=findViewById(R.id.idBuscar);
        listaAutores=findViewById(R.id.idList);

        BookSearchResultsAdapter adapter= new BookSearchResultsAdapter();
        listaAutores.setLayoutManager(new LinearLayoutManager(this));
        listaAutores.setAdapter(adapter);

        //Método onClick
        adapter.setOnItemClickListener(this);

        vm=new ViewModelProvider(this).get(BookSearchViewModel.class);
        vm.init();
        data=vm.getVolumesResponseLiveData();

        data.observe(this, (data)->{
            adapter.setResults(data.getItems());
        });

        buscar.setOnClickListener((v)->{
            vm.searchVolumes(busqueda.getText().toString(), autor.getText().toString(), String.valueOf(FILAS_LIBROS));
        });

        //Variable utilizada para obtener información
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) listaAutores.getLayoutManager();

        //Detectar el final del recyclerView
        listaAutores.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //Obtengo el length del último objeto
                int ultimoObjetoVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();

                //Obtengo el total de items
                int allItems = listaAutores.getAdapter().getItemCount();

                //Si llega al final del scroll se suma 10 a la API debe ser menor de 41 o no funciona
                if (ultimoObjetoVisible == allItems-1) {
                    if (FILAS_LIBROS<31) {
                        FILAS_LIBROS+=10;
                    }
                    vm.searchVolumes(busqueda.getText().toString(), autor.getText().toString(), String.valueOf(FILAS_LIBROS));
                }
            }
        });

        //Launcher Results
        resultadoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result ->{



                });
    }

    @Override
    public void onItemClick(int position) {
        envio(position);
    }

    public void envio(int position){
        //Envía a otra actividad
        Intent intent = new Intent(MainBuscadorLibros.this,AllInfoAutor.class);
        intent.putExtra("posicion",position);
        intent.putExtra("keyword",busqueda.getText().toString());
        intent.putExtra("autor",autor.getText().toString());
        resultadoLauncher.launch(intent);
    }

}