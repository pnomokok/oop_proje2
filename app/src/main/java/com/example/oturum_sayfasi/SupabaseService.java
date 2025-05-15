package com.example.oturum_sayfasi;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SupabaseService {

    @Headers({
            "Content-Type: application/json",
            "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Ind1dGpqampsYndwbnd6bmJ3Y2tzIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NTM5Mzc0OCwiZXhwIjoyMDYwOTY5NzQ4fQ.FSzyUvY7tKV-kewlTy-84xQk7HpCqirdCelkymoUrMI",
            "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Ind1dGpqampsYndwbnd6bmJ3Y2tzIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NTM5Mzc0OCwiZXhwIjoyMDYwOTY5NzQ4fQ.FSzyUvY7tKV-kewlTy-84xQk7HpCqirdCelkymoUrMI",
            "Prefer: return=minimal"
    })
    @POST("oturum")
    Call<Void> registerUser(@Body UserModel user);

    @Headers({
            "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Ind1dGpqampsYndwbnd6bmJ3Y2tzIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NTM5Mzc0OCwiZXhwIjoyMDYwOTY5NzQ4fQ.FSzyUvY7tKV-kewlTy-84xQk7HpCqirdCelkymoUrMI",
            "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Ind1dGpqampsYndwbnd6bmJ3Y2tzIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NTM5Mzc0OCwiZXhwIjoyMDYwOTY5NzQ4fQ.FSzyUvY7tKV-kewlTy-84xQk7HpCqirdCelkymoUrMI"
    })
    @GET("oturum")
    Call<List<UserModel>> getUsersByUsername(@Query("username") String username, @Query("select") String select);


    @Headers({
            "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Ind1dGpqampsYndwbnd6bmJ3Y2tzIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NTM5Mzc0OCwiZXhwIjoyMDYwOTY5NzQ4fQ.FSzyUvY7tKV-kewlTy-84xQk7HpCqirdCelkymoUrMI",
            "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Ind1dGpqampsYndwbnd6bmJ3Y2tzIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NTM5Mzc0OCwiZXhwIjoyMDYwOTY5NzQ4fQ.FSzyUvY7tKV-kewlTy-84xQk7HpCqirdCelkymoUrMI",
            "Content-Type: application/json"
    })
    @PATCH("oturum")
    Call<Void> updateUserById(@Query("id") String id, @Body UserModel user);
    @Headers({
            "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Ind1dGpqampsYndwbnd6bmJ3Y2tzIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NTM5Mzc0OCwiZXhwIjoyMDYwOTY5NzQ4fQ.FSzyUvY7tKV-kewlTy-84xQk7HpCqirdCelkymoUrMI",
            "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Ind1dGpqampsYndwbnd6bmJ3Y2tzIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NTM5Mzc0OCwiZXhwIjoyMDYwOTY5NzQ4fQ.FSzyUvY7tKV-kewlTy-84xQk7HpCqirdCelkymoUrMI"
    })
    @GET("oturum")
    Call<List<UserModel>> getUserById(@Query("id") String id);

}

