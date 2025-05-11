package com.example.oturum_sayfasi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextUsernameRegister, editTextPasswordRegister;
    private Button buttonRegisterSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsernameRegister = findViewById(R.id.editTextUsernameRegister);
        editTextPasswordRegister = findViewById(R.id.editTextPasswordRegister);
        buttonRegisterSubmit = findViewById(R.id.buttonRegisterSubmit);

        buttonRegisterSubmit.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {
        String username = editTextUsernameRegister.getText().toString().trim();
        String password = editTextPasswordRegister.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show();
            return;
        }

        UserModel user = new UserModel(username, password);

        SupabaseService service = ApiClient.getClient().create(SupabaseService.class);
        Call<Void> call = service.registerUser(user);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Kayıt başarılı!", Toast.LENGTH_SHORT).show();
                    finish(); // Ana ekrana döner
                } else {
                    Toast.makeText(RegisterActivity.this, "Kayıt başarısız. Tekrar deneyin.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Hata: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

