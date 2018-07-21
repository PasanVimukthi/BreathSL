package com.example.vimuk.breathsrilanka;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vimuk.breathsrilanka.helper.InputValidation;
import com.example.vimuk.breathsrilanka.model.User;
import com.example.vimuk.breathsrilanka.sql.DatabaseHelper;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = SignUp.this;
    private View view;

    private TextInputLayout textInputLayoutUserName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutPasswordconfirm;

    private EditText editTextUserName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;

    private Button buttonSignUp;

    private InputValidation inputvalidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        initViews();
        initListners();
        initObjects();

    }

    private void initViews(){

        view = (View) findViewById(R.id.ViewSignUp);

        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.SignUpLayoutUserName);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.SignUpLayoutPassword);
        textInputLayoutPasswordconfirm = (TextInputLayout) findViewById(R.id.SignUpLayoutConfirmPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.SignUpLayoutEmail);

        editTextUserName = (EditText) findViewById(R.id.SignUpEditTextUserName);
        editTextPassword = (EditText) findViewById(R.id.SignUpEditTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.SignUpEditTextConfirmPassword);
        editTextEmail = (EditText) findViewById(R.id.SignUpEditTextEmail);

        buttonSignUp = (Button) findViewById(R.id.ButtonSignUp);

    }

    private void initListners(){

        buttonSignUp.setOnClickListener(this);

    }

    private void initObjects(){

        inputvalidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }

    @Override
    public void onClick(View view){

        switch (view.getId()){
            case R.id.ButtonSignUp :
                postDataToSQLite();
                break;
        }

    }

    private void postDataToSQLite(){

        if(!inputvalidation.IsInputEditTextFilled(editTextUserName, textInputLayoutUserName, getString(R.string.error_message_username))){
            return;
        }

        if(!inputvalidation.IsInputEditTextFilled(editTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }

        if(!inputvalidation.IsInputEditTextEmail(editTextEmail, textInputLayoutEmail, getString(R.string.error_email_exists))){
            return;
        }

        if(!inputvalidation.IsInputEditTextFilled(editTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))){
            return;
        }

        if(!inputvalidation.IsInputEditTextMatches(editTextPassword, editTextConfirmPassword,
                textInputLayoutPasswordconfirm, getString(R.string.error_password_match))){
            return;
        }

        if(!databaseHelper.checkUser(editTextEmail.getText().toString().trim())){

            user.setUsername(editTextUserName.getText().toString().trim());
            user.setEmail(editTextEmail.getText().toString().trim());
            user.setPassword(editTextPassword.getText().toString().trim());
            System.out.println("add kare naa dan thama");
            databaseHelper.addUser(user);

            //Intent mainpage = new Intent(activity, MainActivity.class);
            //startActivity(mainpage);
            System.out.println("Hereeeee");
            Snackbar.make(view, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();

        }else{

            Snackbar.make(view, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }

    }

    private void emptyInputEditText(){

        editTextUserName.setText(null);
        editTextEmail.setText(null);
        editTextPassword.setText(null);
        editTextConfirmPassword.setText(null);

    }

}
