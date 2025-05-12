// kullanici kayit ekrani
package spes.example.oop_proje;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oturum_sayfasi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {

    private EditText editTextName, editTextSurname, editTextDay, editTextMonth, editTextYear;
    private Spinner genderSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextDay = findViewById(R.id.editTextDay);
        editTextMonth = findViewById(R.id.editTextMonth);
        editTextYear = findViewById(R.id.editTextYear);
        genderSpinner = findViewById(R.id.spinnerGender);

        //KVKK metnini göster
        TextView textViewShowKvkk = findViewById(R.id.textViewShowKvkk);

        textViewShowKvkk.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
            builder.setTitle("KVKK Metni");

            builder.setMessage("Bu uygulama kapsamında kişisel verileriniz, yalnızca afet durumlarında yardım amacıyla kullanılmak üzere işlenecektir. Kullanım koşullarını ve veri işleme politikalarını onayladığınızda bilgileriniz sistemimize kaydedilecektir.");

            builder.setPositiveButton("Kapat", (dialog, which) -> dialog.dismiss());

            AlertDialog dialog = builder.create();
            dialog.show();
        });

        // Spinner verilerini yükle
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Kadın","Erkek", "Diğer"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        Button submitButton = findViewById(R.id.buttonSubmit);
        submitButton.setOnClickListener(v -> {
            saveUserToSupabase();
        });
    }
    private void saveUserToSupabase() {
        String name = editTextName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();
        String gender = genderSpinner.getSelectedItem().toString();
        int day = Integer.parseInt(editTextDay.getText().toString().trim());
        int month = Integer.parseInt(editTextMonth.getText().toString().trim());
        int year = Integer.parseInt(editTextYear.getText().toString().trim());

        CheckBox checkKvkk = findViewById(R.id.checkKvkk);
        if (!checkKvkk.isChecked()) {
            Toast.makeText(this, "KVKK onayını kabul etmeniz gerekmektedir!", Toast.LENGTH_SHORT).show();
            return;
        }

        String username = "905555000000"; // Şimdilik manuel olarak sabit

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("name", name);
            jsonBody.put("surname", surname);
            jsonBody.put("gender", gender);
            jsonBody.put("birthday", day);
            jsonBody.put("birthmonth", month);
            jsonBody.put("birthyear", year);
        } catch (JSONException e) {
            Log.e("JSONError", "JSONException occurred: " + e.getMessage());
            e.printStackTrace();
        }

        String url = "https://imguaxpadnlpwxsneuwc.supabase.co/rest/v1/zsk_deneme?username=eq." + username;

        StringRequest request = new StringRequest(Request.Method.PATCH, url,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        if (jsonArray.length() > 0) {
                            JSONObject user = jsonArray.getJSONObject(0);

                            Toast.makeText(this, "Kayıt başarılı!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SecondActivity.this, ProfileActivity.class);
                            intent.putExtra("username", user.getString("username"));
                            intent.putExtra("name", user.getString("name"));
                            intent.putExtra("surname", user.getString("surname"));
                            intent.putExtra("gender", user.getString("gender"));
                            intent.putExtra("birthday", user.getInt("birthday"));
                            intent.putExtra("birthmonth", user.getInt("birthmonth"));
                            intent.putExtra("birthyear", user.getInt("birthyear"));
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(this, "Sunucudan boş yanıt alındı.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Log.e("ParseError", "JSON parse hatası: " + e.getMessage());
                    }
                },
                error -> {
                    Log.e("VolleyError", "Hata: " + error.toString());
                    Toast.makeText(this, "Bir hata oluştu.", Toast.LENGTH_SHORT).show();
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

