package e.welcome.lajecommerceapp;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {
    EditText name,number,email,password,confirmpassword;
    Button signup;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    private String emailpattern="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    public SignupFragment() {

        // Required empty public constructor
    }

    private TextView alreadyhaveanaccount;
    private FrameLayout parentFrameLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_signup, container, false);
        alreadyhaveanaccount=view.findViewById(R.id.alreadyhaveanaccount);
        name=view.findViewById(R.id.et_namesignup);
        number=view.findViewById(R.id.et_numbersignup);
        email=view.findViewById(R.id.et_emailsignup);
         password=view.findViewById(R.id.et_passwordsignup);
        confirmpassword=view.findViewById(R.id.et_passwordconfirmsignup);
        progressBar=view.findViewById(R.id.progressBar2);
        signup=view.findViewById(R.id.btn_signup);
        firebaseAuth=FirebaseAuth.getInstance();
        parentFrameLayout=getActivity().findViewById(R.id.fragmenthold);
                return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alreadyhaveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SigninFragment());
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        confirmpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkemailandpassword();

            }
        });
    }

    private void checkemailandpassword() {
        Drawable custom=getResources().getDrawable(R.drawable.ic_error_black_24dp);
        custom.setBounds(0,0,custom.getIntrinsicWidth(),custom.getIntrinsicHeight());
        if(email.getText().toString().matches(emailpattern))
        {
            if(password.getText().toString().equals(confirmpassword.getText().toString()))
            {    progressBar.setVisibility(View.VISIBLE);
                 signup.setEnabled(false);
                firebaseAuth.createUserWithEmailAndPassword
                        (email.getText().toString(),password.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Intent mainIntent=new Intent(getActivity(),MainActivity.class);
                                    startActivity(mainIntent);
                                    getActivity().finish();
                                }
                                else
                                {    progressBar.setVisibility(View.INVISIBLE);
                                signup.setEnabled(true);
                                    String error=task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            else
            {
                confirmpassword.setError("Password doesn't match",custom);
            }
        }
        else
        {
            email.setError("Invalid email",custom);
        }



    }

    private void checkInputs() {
        if(!TextUtils.isEmpty(email.getText()))
        { if(!TextUtils.isEmpty(name.getText()))
            { if(!TextUtils.isEmpty(number.getText()))
                {  if(!TextUtils.isEmpty(password.getText()) && password.length() >=8)
                { if(!TextUtils.isEmpty(confirmpassword.getText()))
                    {    signup.setEnabled(true);
                    }
                    else
                    {signup.setEnabled(false);
                    }
                }
                    else
                {  signup.setEnabled(false);
                }
                }
                else{  signup.setEnabled(false);

              }
            }
                else
                {  signup.setEnabled(false);

                }

        }
        else
        {
            signup.setEnabled(false);

        }
    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slide_out_from_exit);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();

    }
}
