package spes.example.oop_proje;

import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oturum_sayfasi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class UpdateInfoActivity extends AppCompatActivity {
    private EditText editTextName, editTextSurname, editTextDay, editTextMonth, editTextYear;
    private Spinner genderSpinner;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextDay = findViewById(R.id.editTextDay);
        editTextMonth = findViewById(R.id.editTextMonth);
        editTextYear = findViewById(R.id.editTextYear);
        genderSpinner = findViewById(R.id.spinnerGender);
        saveButton = findViewById(R.id.buttonSave);

        //Spinner verilerini yükle
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Erkek", "Kadın", "Diğer"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        // JSON'dan gelen verileri al
        String name = getIntent().getStringExtra("name");
        String surname = getIntent().getStringExtra("surname");
        int day = getIntent().getIntExtra("birthday", 0);
        int month = getIntent().getIntExtra("birthmonth", 0);
        int year = getIntent().getIntExtra("birthyear", 0);
        String gender = getIntent().getStringExtra("gender");

        // Verileri editText'lere yerleştir
        editTextName.setText(name);
        editTextSurname.setText(surname);
        editTextDay.setText(String.valueOf(day));
        editTextMonth.setText(String.valueOf(month));
        editTextYear.setText(String.valueOf(year));
        genderSpinner.setSelection(getGenderPosition(gender));

// Kaydet butonuna tıklama işlemi
        saveButton.setOnClickListener(v -> {
            // Yeni bilgileri al
            String updatedName = editTextName.getText().toString().trim();
            String updatedSurname = editTextSurname.getText().toString().trim();
            int updatedDay = Integer.parseInt(editTextDay.getText().toString().trim());
            int updatedMonth = Integer.parseInt(editTextMonth.getText().toString().trim());
            int updatedYear = Integer.parseInt(editTextYear.getText().toString().trim());
            String updatedGender = genderSpinner.getSelectedItem().toString();

            String username = getIntent().getStringExtra("username"); // Profile'den gelen
            // Log ekleyelim: verileri kontrol edelim
            Log.d("UpdateInfoActivity", "Updated Data: " +
                    "Username:" + username +
                    "  Name: " + updatedName +
                    ", Surname: " + updatedSurname +
                    ", Day: " + updatedDay +
                    ", Month: " + updatedMonth +
                    ", Year: " + updatedYear +
                    ", Gender: " + updatedGender);
            updateUserInfoOnSupabase(username, updatedName, updatedSurname, updatedGender, updatedDay, updatedMonth, updatedYear);
        });

    }

    private int getGenderPosition(String gender) {
        if (gender.equals("Kadın")) {
            return 1;
        } else if (gender.equals("Diğer")) {
            return 2;
        }
        return 0; // Erkek
    }
    private void updateUserInfoOnSupabase(String username, String name, String surname, String gender, int day, int month, int year) {

        String url = "https://imguaxpadnlpwxsneuwc.supabase.co/rest/v1/zsk_deneme?username=eq." + username;

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("name", name);
            jsonBody.put("surname", surname);
            jsonBody.put("gender", gender);
            jsonBody.put("birthday", day);
            jsonBody.put("birthmonth", month);
            jsonBody.put("birthyear", year);
            // Log ekleyelim: JSON verisini kontrol edelim
            Log.d("UpdateInfoActivity", "JSON Body: " + jsonBody.toString());

        } catch (JSONException e) {
            // JSON hatasını logla
            Log.e("UpdateInfoActivity", "JSON Exception: " + e.getMessage());
            e.printStackTrace();
        }

        StringRequest request = new StringRequest(Request.Method.PATCH, url,
                response -> {
                    Log.d("UpdateInfoActivity", "Güncelleme başarılı, sunucudan yanıt beklenmiyor.");
                    Toast.makeText(this, "Güncelleme başarılı!", Toast.LENGTH_SHORT).show();

                    // ProfileActivity'ye geri dön
                    Intent intent = new Intent(UpdateInfoActivity.this, ProfileActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("name", name);
                    intent.putExtra("surname", surname);
                    intent.putExtra("gender", gender);
                    intent.putExtra("birthday", day);
                    intent.putExtra("birthmonth", month);
                    intent.putExtra("birthyear", year);
                    startActivity(intent);
                    finish();
                },
                error -> {
                    Log.e("Volley Error", "Error Message: " + error.getMessage());
                    if (error.networkResponse != null) {
                        // Hata yanıtı varsa onu da logla
                        Log.e("Volley Error", "Error Response: " + new String(error.networkResponse.data));
                    }
                    Toast.makeText(this, "Güncelleme Hatası: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }) {
            @Override
            public byte[] getBody() {
                return jsonBody.toString().getBytes(StandardCharsets.UTF_8);
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImltZ3VheHBhZG5scHd4c25ldXdjIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NjA1NDIwNywiZXhwIjoyMDYxNjMwMjA3fQ.JyznK6xm5vPW0I2egCRInJVkXKA4Izs4J7YpE9Nef-4");
                headers.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImltZ3VheHBhZG5scHd4c25ldXdjIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NjA1NDIwNywiZXhwIjoyMDYxNjMwMjA3fQ.JyznK6xm5vPW0I2egCRInJVkXKA4Izs4J7YpE9Nef-4");
                headers.put("Content-Type", "application/json");
                headers.put("Prefer", "return=representation");
                return headers;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}
