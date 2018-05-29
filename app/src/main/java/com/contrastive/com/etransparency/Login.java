package com.contrastive.com.etransparency;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
//import com.trickbd.app.Viewpost.UserComments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

public class Login extends AppCompatActivity {
    CircularProgressView progressView;
    JsonObjectRequest jsonObjReq;
    private String TAG = Login.class.getSimpleName();
    EditText nameText;
    EditText passText;
    Button login;

    //ids
//    JSONObject authorId;

    String name;
    String pass;

    //json response
    String status;
    String cookie;
    String cookie_encoded;
    String cookie_name;

    //nonce
    String nonceerror;
    String contollerMethod;
    String postCreateNonce = "sgds";
    String postUpdateNonce = "sdns";

//    TextView passReset;

    //user info
    String id;
    public  static String username;
    String nicename;
    String email;
    String urlString;
    String registered;
    String displayname;
    String firstname;
    String lastname;
    String nickname;
    String description;
    String capabilities;
    String avatar;
//    Button signUp;

    TextInputLayout namelayout;
    TextInputLayout passlayout;

    RelativeLayout register;

    //if error
    String error;


    public static boolean isLogged = false;
    public boolean fromComment= false;
    String commentID = "";


    public static final String USER = "user";
    public static final String Nonce = "nonce";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //from comment
        final Intent intent = getIntent();
        commentID = intent.getStringExtra("commentId");
        fromComment = intent.getBooleanExtra("fromcomment",false);

        progressView = (CircularProgressView) findViewById(R.id.loginprogress);
        progressView.setVisibility(View.GONE);
        nameText = (EditText) findViewById(R.id.name);
        passText = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginbtn);
//        signUp = (Button) findViewById(R.id.signup);

//        signUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Uri uri = Uri.parse("http://trickbd.com/wp-login.php?action=register"); // missing 'http://' will cause crashed
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//            }
//        });
        //pull up layout with keyboard
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //layout
        namelayout = (TextInputLayout) findViewById(R.id.input_layout_name);
        passlayout = (TextInputLayout) findViewById(R.id.input_layout_password);
        register = (RelativeLayout) findViewById(R.id.footerLayout);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameText.getText().toString();
                pass = passText.getText().toString();

                if (!name.isEmpty()&&!pass.isEmpty()) {
                    String id="12";
                    String pas="67";
                    if (name.equals(id)&& pass.equals(pas)) {
                        Intent i =new Intent(Login.this,MainActivity.class);
                        startActivity(i);
                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    }else {
                        Toast.makeText(Login.this,"User name or password error",Toast.LENGTH_SHORT);
                    }
                }else {
                    if (name.isEmpty()) {
                        nameText.setError("username required");
                    }else {
                        passText.setError("password required");
                    }

                }



            }
        });



//        passReset =(TextView)  (findViewById(R.id.resetPass));
//        passReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                reset();
//            }
//        });


//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
//    String cate = "";
//    private void catarray() {
//        jsonObjReq = new JsonObjectRequest(Request.Method.GET,
//                "http://trickbd.com/apixyzv1/get_category_index/", null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            // Parsing json array response
//                            // loop through each json object
//                            JSONArray cateories = response.getJSONArray("categories");
//
//                            for (int i = 0;i<cateories.length();i++){
//
//                                JSONObject cat = (JSONObject) cateories
//                                        .get(i);
//
//                                if (i>0){
//                                    cate+="\n";
//                                }
//                                cate+=  ("<item>"+cat.getString("slug")+"</item>");
//                            }
//                            Log.d("cat",cate);
//
//
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(Login.this,
//                                    "Error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//
//            }
//        });
//
//
//        // Adding request to request queue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(jsonObjReq);
//        // Cancelling request
//        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
//    }
    //collecting data from json
    private void login(final String url) {
        namelayout.setVisibility(View.GONE);
        passlayout.setVisibility(View.GONE);
        register.setVisibility(View.GONE);
        login.setVisibility(View.GONE);
        progressView.setVisibility(View.VISIBLE);
//        passReset.setVisibility(View.GONE);
        progressView.startAnimation();
        jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parsing json array response
                            // loop through each json object
                            status = response.getString("status");
                            if (status.equals("error")){
                                namelayout.setVisibility(View.VISIBLE);
                                passlayout.setVisibility(View.VISIBLE);
                                register.setVisibility(View.VISIBLE);
                                login.setVisibility(View.VISIBLE);
//                                passReset.setVisibility(View.VISIBLE);
                                error = response.getString("error");
                                Toast.makeText(Login.this, error, Toast.LENGTH_SHORT).show();
                                isLogged = false;

                                // Check if no view has focus:

                            }else {
                                //get cookie
                                cookie = response.getString("cookie");

                                cookie_encoded = URLEncoder.encode(cookie);
                                Nonce("http://trickbd.com/apixyzv1/get_nonce/?controller=Posts&method=create_post&cookie="+cookie_encoded);
                                Nonce("http://trickbd.com/apixyzv1/get_nonce/?controller=Posts&method=update_post&cookie="+cookie_encoded);
//                                Log.d("nonce",postCreateNonce);
//                                Log.d("nonce",postUpdateNonce);
                                String cookies = cookie_encoded.replace("%7C","1o7");

//                                Log.d("cookie replaced",replaced);
//
//                                String oldc = replaced.replace("1o7","%7C");
//                                Log.d("cookie encoded",oldc);




                                cookie_name = response.getString("cookie_name");

                                JSONObject user = response.getJSONObject("user");
                                id = user.getString("id");
                                username = user.getString("username");
                                nicename = user.getString("nicename");
                                email = user.getString("email");
                                urlString = user.getString("url");
                                registered = user.getString("registered");
                                displayname = user.getString("displayname");
                                firstname =  user.getString("firstname");
                                lastname = user.getString("lastname");
                                nickname = user.getString("nickname");
                                description = user.getString("description");
                                capabilities = user.getString("capabilities");
                                avatar = user.getString("avatar");

                                Log.d("avatar",avatar);
                                if (avatar.equals("null")){
                                    Log.d("avatar","is empty");
                                    getAuthorImage("http://trickbd.com/apixyzv1/trickbd/get_avatar_url/?id="+id);

                                }else {
                                    avatar = user.getString("avatar");

                                }

                                Log.d("author_avatar",avatar);

                                Toast.makeText(Login.this, "Welcome "+username, Toast.LENGTH_SHORT).show();


                                storeInfo(cookies,id,username,nicename,email,urlString,registered,displayname,firstname,lastname,nickname,description,capabilities,avatar);
                                getAuthorPostIDs("http://trickbd.com/apixyzv1/get_author_posts/?slug="+username+"&include=id&post_status=draft,publish,pending");
                                isLogged = true;
                                if (fromComment){
//                                    Intent comment = new Intent(Login.this, UserComments.class);
//                                    comment.putExtra("postID",commentID);
//                                    comment.putExtra("logForComment",true);
//                                    startActivity(comment);
                                }else {

                                    Intent main = new Intent(Login.this, MainActivity.class);
                                    startActivity(main);
                                }

                            }


                            progressView.setVisibility(View.GONE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login.this,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                progressView.setVisibility(View.GONE);

            }
        });


        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
    }


    private void getAuthorImage(String url) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            if (response.getString("status").equals("ok")) {
                                avatar = response.getString("url");
                            }else {

                                avatar = "https://trickbd.com/wp-content/uploads/2016/11/19/trickbd-logo.png";
                            }

                            SharedPreferences prefs = getSharedPreferences(USER, MODE_PRIVATE);

                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("avatar",avatar);
                            editor.commit();




                            Log.d("author_avatar",prefs.getString("avatar","ss"));
                } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login.this,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });


        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
        requestQueue.add(jsonObjReq);
        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);

    }

    private void getAuthorPostIDs(String url) {
        jsonObjReq = new JsonObjectRequest(Request.Method.GET,
            url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        // Parsing json array response
                        // loop through each json object

                        status = response.getString("status");
                        if (status.equals("error")){

                            // Check if no view has focus:

                        }else {
                            //get cookie

                            Log.d("idss",response.toString());

                            int total_page = Integer.parseInt(response.getString("pages"));
//                            removeAuthirpostIds();


                                if (total_page>1) {
                                    getAuthorPostIDsResponse("http://trickbd.com/apixyzv1/get_author_posts/?slug=" + username + "&include=id&post_status=draft,publish,pending&count="+(total_page*30));
                                }else {
                                    JSONArray jsonArray = response.getJSONArray("posts");
                                    for (int i = 0; i < jsonArray.length(); i++) {

                                        JSONObject ids = (JSONObject) jsonArray
                                                .get(i);

//                                        insertAuthorPostIDs(ids.getString("id"));


                                    }
                                }

                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Login.this,
                                "Error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            VolleyLog.d(TAG, "Error: " + error.getMessage());

        }
    });


        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
    }

//    public void insertAuthorPostIDs(String postID) {
//            ContentValues values = new ContentValues();
//            values.put(DBopenHelper.AUTHOR_POST_ID,postID);
//            getContentResolver().insert(AuthorPostIDProvider.CONTENT_URI, values);
//    }
//
//    private void removeAuthirpostIds() {
//        Cursor cursorAll = getContentResolver().query(AuthorPostIDProvider.CONTENT_URI, null,
//                null, null, null);
//        int count=0;
//        ArrayList<String> list= new ArrayList<>();
//
//        if (cursorAll .moveToFirst()) {
//
//            while (!cursorAll.isAfterLast() ) {
//                String postID = cursorAll.getString(cursorAll
//                        .getColumnIndex(DBopenHelper.AUTHOR_POST_ID));
//
//                list.add(postID);
//                cursorAll.moveToNext();
//                count++;
//            }
//        }
//        int n=0;
//
//        while(n<count) {
//            String id = DBopenHelper.AUTHOR_POST_ID + "=" + list.get(n);
//
//            Log.d("author post id",id);
//            getContentResolver().delete(AuthorPostIDProvider.CONTENT_URI, id, null);
//            n++;
//        }
//    }


    private void Nonce(String url) {
        jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parsing json array response
                            // loop through each json object

                            status = response.getString("status");
                            if (status.equals("error")){
                                nonceerror = response.getString("error");
                                Toast.makeText(Login.this, nonceerror, Toast.LENGTH_SHORT).show();

                                // Check if no view has focus:

                            }else {
                                //get cookie
                                contollerMethod = response.getString("method");
                                if (contollerMethod.equals("create_post")) {
                                    postCreateNonce = response.getString("nonce");
                                    Log.d("nonce create",postCreateNonce);
                                    storeNonce(postCreateNonce,postUpdateNonce);
                                }else {
                                    postUpdateNonce = response.getString("nonce");
                                    storeNonce(postCreateNonce,postUpdateNonce);

                                    Log.d("nonce update",postUpdateNonce);
                                }

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login.this,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                progressView.setVisibility(View.GONE);

            }
        });


        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                    onBackPressed();
                break;
        }




        return super.onOptionsItemSelected(item);
    }

    void storeInfo(String cookie, String id, String username, String nicename, String email, String urlString, String registered,
                   String displayname, String firstname, String lastname, String nickname , String description, String capabilities, String avatar){
        SharedPreferences prefs = getSharedPreferences(USER, MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("cookie",cookie);
        editor.putString("id",id);
        editor.putString("username",username);
        editor.putString("nicename",nicename);
        editor.putString("email",email);
        editor.putString("urlString",urlString);
        editor.putString("registered",registered);
        editor.putString("displayname",displayname);
        editor.putString("firstname",firstname);
        editor.putString("lastname",lastname);
        editor.putString("nickname",nickname);
        editor.putString("description",description);
        editor.putString("capabilities",capabilities);
        editor.putString("avatar",avatar);
        editor.putBoolean("login",true);
        editor.commit();
    }

    void storeNonce(String postCreateNonce, String postUpdateNonce){
        SharedPreferences prefs = getSharedPreferences(Nonce, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("postCreateNonce",postCreateNonce);
        editor.putString("postUpdateNonce",postUpdateNonce);
        editor.commit();
    }

    void  reset(){
        new MaterialDialog.Builder(this)
                .content("Reset Password")
                .inputType(InputType.TYPE_CLASS_TEXT )
                .positiveText("Reset")
                .input("user name or email","", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        resetPass("https://trickbd.com/apixyzv1/user/retrieve_password/?user_login="+input);
                    }
                }).show();
    }

    private void resetPass(String url) {
        jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", "onResponse: "+response);
                        try {
                            // Parsing json array response
                            // loop through each json object


                            status = response.getString("status");
                            if (status.equals("ok")){
                                Toast.makeText(Login.this, response.getString("msg"), Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(Login.this, "Invaild user name or email", Toast.LENGTH_SHORT).show();

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login.this,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });


        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }

    private void getAuthorPostIDsResponse(String url) {

        jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parsing json array response
                            // loop through each json object

                            status = response.getString("status");
                            if (status.equals("error")){


                            }else {
                                //get cookie
                                JSONArray jsonArray = response.getJSONArray("posts");
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject ids = (JSONObject) jsonArray
                                            .get(i);

//                                    insertAuthorPostIDs(ids.getString("id"));


                                }

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login.this,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });


        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isLogged){
            Intent backToMain = new Intent(Login.this,MainActivity.class);
            startActivity(backToMain);

        }
    }
}
