package fr.sheepy.aboutlotr;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LOTRAPI {

    @GET("character")
    Call<LOTRAPIResponse> getAllCharacter();
}
