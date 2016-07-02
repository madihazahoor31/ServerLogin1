package com.madiha.serverlogin;

import android.app.Activity;
import android.app.AlertDialog;
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
        Contact contact=new Contact(username,pass);
        authenticate(contact);
        }
private void authenticate(Contact contact){
    ServerRequest serverRequest=new ServerRequest(this);
    serverRequest.fetchDataInBackground(contact, new GetUserCallBack() {
        @Override
        public void done(Contact returnedContact) {
            if(returnedContact==null){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Username and Password dont match");
                builder.setPositiveButton("OK", null);
                builder.show();
            }
            else{
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);

            }
        }
    });
}
}
