package fr.sheepy.aboutlotr.data;

import fr.sheepy.aboutlotr.presentation.model.LOTRAPIResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface LOTRAPI {
    @GET("character")
    Call<LOTRAPIResponse> getAllCharacters(@Header("Authorization") String credentials);
}
