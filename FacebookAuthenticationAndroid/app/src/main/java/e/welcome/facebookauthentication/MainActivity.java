package e.welcome.facebookauthentication;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {
    private LoginButton login;
    private CircleImageView circleImageView;
    private TextView txtname,txtemail;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.login_btn);
        txtname=findViewById(R.id.profile_name);
        txtemail=findViewById(R.id.profile_email);

        circleImageView=findViewById(R.id.profile_pic);

        callbackManager=CallbackManager.Factory.create();
        login.setReadPermissions(Arrays.asList("email","public_profile"));
        checkLogInstatus();
        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                Toast.makeText(MainActivity.this, "Login anshul", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel()
            {

            }

            @Override
            public void onError(FacebookException error)
            {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    AccessTokenTracker tokenTracker =new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                   AccessToken currentAccessToken)
        {
            if(currentAccessToken==null)
            {
                txtemail.setText("");
                txtname.setText("");
                circleImageView.setImageResource(0);
                Toast.makeText(MainActivity.this, "User logged out", Toast.LENGTH_SHORT).show();
            }
            else
            {
                loadUserprofile(currentAccessToken);
            }
        }
    };
    private void loadUserprofile(AccessToken newAccesstoken)
    {
        GraphRequest request=GraphRequest.newMeRequest(newAccesstoken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {


                try {
                    String first_name =object.getString("first_name");
                    String last_name=object.getString("last_name");
                    String email=object.getString("email");
                    String id=object.getString("id");

                    String image_url="https://graph.facebook.com/"+id+"/picture?type=normal";
                    txtemail.setText(email);
                    txtname.setText(first_name+" "+last_name);
                    RequestOptions requestOptions=new RequestOptions();
                    requestOptions.dontAnimate();
                    Glide.with(MainActivity.this).load(image_url).into(circleImageView);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        Bundle parameters=new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }
    private void checkLogInstatus()
    {

        if(AccessToken.getCurrentAccessToken()!=null)
        {
            loadUserprofile(AccessToken.getCurrentAccessToken());
        }
    }
}
