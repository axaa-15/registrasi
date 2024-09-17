package com.annisa.registrasi;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.annisa.registrasi.R;

import java.util.Calendar;
public class MainActivity extends AppCompatActivity {

    EditText edtFullName, edtUsername, edtEmail, edtNIM, edtPassword, edttanggallahir, edtalamat, edtnohp;
    Spinner spinnerGender;
    Button btnRegister;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Memuat layout XML

        // Inisialisasi view
        edtFullName = findViewById(R.id.edt_FullName);
        edtUsername = findViewById(R.id.edt_Email);
        edtEmail = findViewById(R.id.edt_Email);
        edtNIM = findViewById(R.id.edt_NIM);
        edtPassword = findViewById(R.id.edt_Password);
        edttanggallahir = findViewById(R.id.edt_tanggallahir);
        edtalamat = findViewById(R.id.edt_alamat);
        edtnohp = findViewById(R.id.edt_nohp);
        spinnerGender = findViewById(R.id.spin_gender);
        btnRegister = findViewById(R.id.btn_Register);

        String[] genders = {"Perempuan", "laki-Laki"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genders);
        spinnerGender.setAdapter(genderAdapter);

        calendar = Calendar.getInstance();
        edttanggallahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                                // Set tanggal yang dipilih ke TextView
                                edttanggallahir.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil data dari EditText
                String fullName = edtFullName.getText().toString();
                String username = edtUsername.getText().toString();
                String email = edtEmail.getText().toString();
                String nim = edtNIM.getText().toString();
                String password = edtPassword.getText().toString();
                String birthDate = edttanggallahir.getText().toString();
                String address = edtalamat.getText().toString();
                String phone = edtnohp.getText().toString();
                String gender = spinnerGender.getSelectedItem().toString();

                if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(username) || TextUtils.isEmpty(email) ||
                        TextUtils.isEmpty(nim) || TextUtils.isEmpty(password) || TextUtils.isEmpty(birthDate) ||
                        TextUtils.isEmpty(address) || TextUtils.isEmpty(phone)) {
                    Toast.makeText(MainActivity.this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(MainActivity.this, "Registered!\nFull Name: " + fullName +
                        "\nUsername: " + username + "\nEmail: " + email + "\nNIM: " + nim +
                        "\nGender: " + gender + "\nDate of Birth: " + birthDate +
                        "\nAddress: " + address + "\nPhone: " + phone, Toast.LENGTH_LONG).show();
            }
        });
    }
}