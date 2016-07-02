package com.madiha.serverlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
     EditText uname,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
    }
    public void onRegisterClick(View view){
        Intent intent=new Intent(MainActivity.this,Register.class);
        startActivity(intent);
    }
    public void onLogInClick(View view){
        String username=uname.getText().toString();
        String pass=password.getText().toString();
        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }

}
