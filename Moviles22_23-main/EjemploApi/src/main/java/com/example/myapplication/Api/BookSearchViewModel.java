package com.example.myapplication.Api;



import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.myapplication.Api.BookRepository;
import com.example.myapplication.Data.VolumesResponse;

public class BookSearchViewModel extends AndroidViewModel {
    private BookRepository bookRepository;
    private LiveData<VolumesResponse> volumesResponseLiveData;

    public BookSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        bookRepository = new BookRepository();
        volumesResponseLiveData = bookRepository.getVolumesResponseLiveData();
    }

    public void searchVolumes(String keyword, String author) {
        bookRepository.searchVolumes(keyword, author);
    }

    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }
}