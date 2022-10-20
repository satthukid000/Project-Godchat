package vn.edu.stu.project_chat_group;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class PersonalInformationSettingActivity extends AppCompatActivity {
    MaterialButton btnChangeName, btnBack, btnIDConfirm;
    TextView tvFullName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information_setting);
        
        addControls();
        addEvents();
    }



    private void addControls() {
        btnChangeName = findViewById(R.id.btnFullNameChange);
        tvFullName = findViewById(R.id.tvFullNameSetting);
        btnBack = findViewById(R.id.btnBack);
        btnIDConfirm = findViewById(R.id.btnIDConfirm);
    }
    private void addEvents() {
        btnChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFullNameChangeSetting();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnIDConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openIDConfirmationSetting();
            }
        });
    }

    private void openIDConfirmationSetting() {
        Intent intent = new Intent(PersonalInformationSettingActivity.this, IdentityConfirmationSettingActivity.class);
        startActivity(intent);
    }


    private void openFullNameChangeSetting() {
        Intent intent = new Intent(PersonalInformationSettingActivity.this, FullNameSettingActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode == 2){
                String name ="";
                name = data.getStringExtra("fullname"); //tên name từ activity fullname
                tvFullName.setText(name);
            }
        }

    }
}