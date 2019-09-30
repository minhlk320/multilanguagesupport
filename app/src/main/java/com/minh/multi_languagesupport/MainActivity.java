package com.minh.multi_languagesupport;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);
    btn = findViewById(R.id.button2);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showChangeLanguageDialog();
        }
    });
    }

    private void showChangeLanguageDialog() {
        final String[] listItem={"English","France","Tiếng Việt"};

        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("Select one Language");
        b.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:{
                        setLocale("en");
                        recreate();
                        break;
                    }
                    case 1:{
                        setLocale("fr");
                        recreate();
                        break;
                    }
                    case 2:{
                        setLocale("vi");
                        recreate();
                        break;
                    }
                    default: dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = b.create();
        alertDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Setting",MODE_PRIVATE).edit();
        editor.putString("my_lang",lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences sharedPreferences = getSharedPreferences("Setting", Activity.MODE_PRIVATE);
        String language = sharedPreferences.getString("my_lang","");
        setLocale(language);


    }


}
