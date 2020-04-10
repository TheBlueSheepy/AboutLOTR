package fr.sheepy.aboutlotr;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface LOTRAPI {

    @GET("character")
    Call<LOTRAPIResponse> getAllCharacter(@Header("Authorization") String credentials);
}
