package com.example.oturum_sayfasi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spes.example.oop_proje.SecondActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin, buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        buttonLogin.setOnClickListener(view -> loginUser());
    }

    private void loginUser() {
        /*
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show();
            return;
        }

        SupabaseService service = ApiClient.getClient().create(SupabaseService.class);

        // Supabase'de kullanıcıyı sorgulamak için filtreli GET isteği yapacağız
        service.getUsersByUsername(username).enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    UserModel user = response.body().get(0);
                    if (user.getPassword().equals(password)) {
                        Toast.makeText(MainActivity.this, "Giriş başarılı!", Toast.LENGTH_SHORT).show();
                        // Şimdilik sadece mesaj gösteriyoruz, yönlendirme yok
                    } else {
                        Toast.makeText(MainActivity.this, "Şifre yanlış!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Kullanıcı bulunamadı!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Hata: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });  */

        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show();
            return;
        }

        SupabaseService service = ApiClient.getClient().create(SupabaseService.class);

        // Supabase'de kullanıcıyı sorgulamak için filtreli GET isteği
        service.getUsersByUsername("eq." + username, "*").enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    UserModel user = response.body().get(0);
                    if (user.getPassword().equals(password)) {
                        Toast.makeText(MainActivity.this, "Giriş başarılı!", Toast.LENGTH_SHORT).show();
                        // Şimdilik sadece mesaj gösteriyoruz, yönlendirme yok


                        // SecondActivity'e geçiş ve id aktarımı
                        //Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        //intent.putExtra("user_id", user.getId());  // Supabase ID
                        //startActivity(intent);
                        //finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Şifre yanlış!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Kullanıcı bulunamadı! Hata kodu: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Hata: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
