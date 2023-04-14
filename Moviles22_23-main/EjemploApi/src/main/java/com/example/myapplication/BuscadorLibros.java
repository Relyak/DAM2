package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Data.VolumesResponse;
import com.example.myapplication.Api.BookSearchViewModel;

public class BuscadorLibros extends AppCompatActivity {
    TextView busqueda,autor;
    Button buscar;
    RecyclerView listado;

    BookSearchViewModel vm;

    LiveData<VolumesResponse> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_booksearch);
        busqueda = findViewById(R.id.idBusqueda);
        autor = findViewById(R.id.idAutor);
        buscar = findViewById(R.id.idBuscar);
        listado = findViewById(R.id.idList);

        BookSearchResultsAdapter adapter = new BookSearchResultsAdapter();
        listado.setLayoutManager(new LinearLayoutManager(this));
        listado.setAdapter(adapter);

        vm = new ViewModelProvider(this).get(BookSearchViewModel.class);
        vm.init();
        data = vm.getVolumesResponseLiveData();
        data.observe(this, dato -> {
            adapter.setResults(dato.getItems());
        });
        buscar.setOnClickListener(v->{
            vm.searchVolumes(busqueda.getText().toString(),autor.getText().toString());
        });

    }
}