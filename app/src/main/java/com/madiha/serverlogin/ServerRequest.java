package com.madiha.serverlogin;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import org.apache.http.params.*;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.*;
import java.util.ArrayList;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.widget.ListView;
/**
 * Created by mz shah on 07-May-16.
 */
public class ServerRequest {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT=15000;
    public static final String SERVER_ADDRESS=" ";
    public ServerRequest(Context context){
        progressDialog=new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait");

    }
    public void storeDataInBackground(Contact contact,GetUserCallBack callBack){
        progressDialog.show();
        new StoreDataAsyncTask(contact,callBack).execute();
    }
    public void fetchDataInBackground(Contact contact, GetUserCallBack callBack){
        progressDialog.show();
        new FetchDataAsyncTask(contact,callBack).execute();
    }
    public class StoreDataAsyncTask extends AsyncTask<Void,Void,Void>
    {
        Contact contact;
        GetUserCallBack callBack;
        public StoreDataAsyncTask(Contact contact,GetUserCallBack callBack){
            this.contact=contact;
            this.callBack=callBack;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> data_to_send = new ArrayList<NameValuePair>();
            data_to_send.add(new BasicNameValuePair("Name",contact.name));
            data_to_send.add(new BasicNameValuePair("Username",contact.username));
            data_to_send.add(new BasicNameValuePair("Email",contact.email));
            data_to_send.add(new BasicNameValuePair("Password", contact.password));
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpClient client= new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS+"register.php");
            try {
                post.setEntity(new UrlEncodedFormEntity(data_to_send));
                client.execute(post);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void avoid){
            progressDialog.dismiss();
            callBack.done(null);
            super.onPostExecute(avoid);
        }
    }
    public class FetchDataAsyncTask extends AsyncTask<Void, Void, Contact>
    {
        Contact contact;
        GetUserCallBack callBack;
        public FetchDataAsyncTask(Contact contact,GetUserCallBack callBack){
            this.contact=contact;
            this.callBack=callBack;

        }
        @Override
        protected Contact doInBackground(Void... params) {
            ArrayList<NameValuePair> data_to_send = new ArrayList<NameValuePair>();
            //data_to_send.add(new BasicNameValuePair("Name",contact.name));
            data_to_send.add(new BasicNameValuePair("Username",contact.username));
          //  data_to_send.add(new BasicNameValuePair("Email",contact.email));
            data_to_send.add(new BasicNameValuePair("Password", contact.password));
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpClient client= new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS+"FetchUserdata.php");
            Contact retunedContact=null;
            try {
                post.setEntity(new UrlEncodedFormEntity(data_to_send));
               HttpResponse httpResponse = client.execute(post);
                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jsonObject = new JSONObject(result);

                retunedContact =  null;
                if (jsonObject.length() == 0) {
                    retunedContact =  null;
                }
                else
                {
                    String name , email;
                    name = null;
                    email = null;
                    if(jsonObject.has("name")){
                        name=jsonObject.getString("name");
                    }
                    if(jsonObject.has("email")){
                        email=jsonObject.getString("email");
                    }
                    retunedContact=new Contact(name,email,contact.username,contact.password);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return retunedContact;
        }
        @Override
        protected void onPostExecute(Contact returnedContact){
            progressDialog.dismiss();
            callBack.done(returnedContact);
            super.onPostExecute(returnedContact);
        }
}
    }
