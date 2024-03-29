package vn.edu.stu.project_chat_group.SettingMainOptions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import vn.edu.stu.project_chat_group.R;
import vn.edu.stu.project_chat_group.utilities.Constant;
import vn.edu.stu.project_chat_group.utilities.PreferencesManager;

public class FullNameSettingActivity extends AppCompatActivity {
    private PreferencesManager preferencesManager;
    MaterialButton btnCommit, btnCancel, btnBack;
    EditText etFirst, etLast;


    String firstname = "", famname ="", fullname="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_name_setting);
        preferencesManager = new PreferencesManager(getApplicationContext());

        addControls();
        addEvents();
    }



    private void addControls() {
        btnCommit = findViewById(R.id.btnCommitChange);
        btnCancel = findViewById(R.id.btnCancelChange);
        btnBack = findViewById(R.id.btnBack);
        etFirst = findViewById(R.id.etFirstNameInSetting);
        etLast = findViewById(R.id.etFamilyNameInSetting);

        fullname=  preferencesManager.getString(Constant.KEY_NAME);
        etFirst.setText(fullname.substring(0, fullname.lastIndexOf(' '))); //tách tên riêng ra khỏi full name
        etLast.setText(fullname.split(" (?!.* )")[1]); //tách họ khỏi full name
    }
    private void addEvents() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commitChange();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void openPersonalInfoSetting() {
        Intent intent = new Intent(FullNameSettingActivity.this, PersonalInformationSettingActivity.class);
        startActivity(intent);
    }

    private void commitChange() {
        getNameFromEditText();
        //lockButtonFor15Days();
        preferencesManager.putString(Constant.KEY_NAME, fullname); //đã thay đổi tên


    }

    private void lockButtonFor15Days() { //hàm khoá nút 15 ngày nếu đã bấm đổi tên thành công
        LocalDate localDate = LocalDate.now();
        Date thisDay = new Date();
        thisDay = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar cal = Calendar.getInstance();
        cal.setTime(thisDay);
        cal.add(Calendar.DAY_OF_YEAR, 15);
        Date futureDate = new Date();
        futureDate = cal.getTime();
        if(thisDay.before(futureDate)){
            btnCommit.setEnabled(false);
        }else
            btnCommit.setEnabled(true);
    }

    private void getNameFromEditText() {
        firstname = etFirst.getText().toString();
        famname = etLast.getText().toString();
        fullname = firstname + " " + famname;
    }
}