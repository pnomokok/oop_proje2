package spes.example.oop_proje;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
// biktim
public class ProfileActivity extends AppCompatActivity {
    private TextView textViewName,textViewSurname,textViewBirthDay,textViewBirthMonth,textViewBirthYear,textViewGender;
    private Button updateInfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // TextView'leri bağla
        textViewName = findViewById(R.id.textViewName);
        textViewSurname = findViewById(R.id.textViewSurname);
        textViewBirthDay = findViewById(R.id.textViewBirthDay);
        textViewBirthMonth = findViewById(R.id.textViewBirthMonth);
        textViewBirthYear = findViewById(R.id.textViewBirthYear);
        textViewGender = findViewById(R.id.textViewGender);

        // Güncelleme butonunu bağla
        updateInfoButton = findViewById(R.id.buttonUpdateInfo);

        // SecondActivity'den gelen verileri al
        String username = getIntent().getStringExtra("username");
        String name = getIntent().getStringExtra("name");
        String surname = getIntent().getStringExtra("surname");
        int day = getIntent().getIntExtra("birthday", 0);
        int month = getIntent().getIntExtra("birthmonth", 0);
        int year = getIntent().getIntExtra("birthyear", 0);
        String gender = getIntent().getStringExtra("gender");

        // Verileri ekranda göster
        textViewName.setText(name);
        textViewSurname.setText(surname);
        textViewBirthDay.setText(String.valueOf(day));
        textViewBirthMonth.setText(String.valueOf(month));
        textViewBirthYear.setText(String.valueOf(year));
        textViewGender.setText(gender);

        // Bilgileri güncelleme butonuna tıklama işlemi
        updateInfoButton.setOnClickListener(v -> {
            // UpdateInfoActivity sayfasına geçiş
            Intent intent = new Intent(ProfileActivity.this, UpdateInfoActivity.class);

            // Mevcut bilgileri intent ile geç
            intent.putExtra("username",username);
            intent.putExtra("name", name);
            intent.putExtra("surname", surname);
            intent.putExtra("birthday", day);
            intent.putExtra("birthmonth", month);
            intent.putExtra("birthyear", year);
            intent.putExtra("gender", gender);

            startActivity(intent);
        });
    }
}

