package com.madiha.serverlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
EditText name,email,uname,password,cpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        email=(EditText)findViewById(R.id.email);
        uname=(EditText)findViewById(R.id.username);
        cpassword=(EditText)findViewById(R.id.cpassword);


    }
    public void onRegisterClick(View view){
String sname=name.getText().toString();
String semail=email.getText().toString();
String suname=uname.getText().toString();
        String spassword=password.getText().toString();
        String scpassword=cpassword.getText().toString();
        Contact contact;
        if (scpassword.equals(spassword)){
contact= new Contact(sname,semail,suname,spassword);
        }
        else {
            Toast.makeText(this,"password donot match",Toast.LENGTH_SHORT).show();
        }

    }
public void onLoginClick(View view){
    Intent intent=new Intent(Register.this,MainActivity.class);
    startActivity(intent);
}
}
