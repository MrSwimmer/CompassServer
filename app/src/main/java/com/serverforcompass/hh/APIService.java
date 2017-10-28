package com.serverforcompass.hh;


import com.serverforcompass.hh.GETPage.PageV;
import com.serverforcompass.hh.GETVacancy.Vacancy;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Севастьян on 26.09.2017.
 */

public interface APIService {

    @GET("/vacancies/{id}")
    Call<Vacancy> getVacancy(@Path("id") String groupId);

    @GET("/vacancies")
    Call<PageV> getListURL(@Query("text") String text, @Query("page") int page);
}