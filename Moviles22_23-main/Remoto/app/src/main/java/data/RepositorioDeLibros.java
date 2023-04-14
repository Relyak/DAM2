package data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositorioDeLibros {
    //url de api para hacer el fetch
    private static final String BOOK_SEARCH_SERVICE_BASE_URL = "https://www.googleapis.com/";

    private BookSearchService bookSearchService; //instancia de interfaz
    private MutableLiveData<VolumesResponse> volumesResponseLiveData;

    public RepositorioDeLibros() {
        volumesResponseLiveData = new MutableLiveData<>();

        bookSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookSearchService.class);

    }

    public void searchVolumes(String keyword, String author, String totalItems) {
        bookSearchService.searchVolumes(keyword, author, totalItems)
                .enqueue(new Callback<VolumesResponse>() {
                    @Override
                    public void onResponse(Call<VolumesResponse> call, Response<VolumesResponse> response) {
                        if (response.body() != null) {
                            volumesResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<VolumesResponse> call, Throwable t) {
                        volumesResponseLiveData.postValue(null);
                    }
                });
    }

    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }
}
