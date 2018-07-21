package com.example.vimuk.breathsrilanka;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vimuk.breathsrilanka.helper.InputValidation;
import com.example.vimuk.breathsrilanka.sql.DatabaseHelper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = MainActivity.this;

    private View view;

    private TextInputLayout textInputLayoutLoginUserName;
    private TextInputLayout textInputLayoutLoginPassword;

    private EditText textInputEditTextLoginUserName;
    private EditText textInputEditTextLoginPassword;

    private Button appCompatButtonButtonSignIn;

    private TextView textViewLinkCreateAccount;

    private InputValidation inputvalidation;
    private DatabaseHelper databaseHelper;
    private int snackbar_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        initViews();
        initListners();
        initObjects();

    }

    private void initViews(){

        view = (View) findViewById(R.id.LoginView);

        textInputLayoutLoginUserName = (TextInputLayout) findViewById(R.id.LoginUserNameTextLayout);
        textInputLayoutLoginPassword = (TextInputLayout) findViewById(R.id.LoginPasswordTextLayout);

        textInputEditTextLoginUserName = (EditText) findViewById(R.id.LoginUserName);
        textInputEditTextLoginPassword = (EditText) findViewById(R.id.LoginPassword);

        appCompatButtonButtonSignIn = (Button) findViewById(R.id.ButtonSignIn);

        textViewLinkCreateAccount = (TextView) findViewById(R.id.LinkCreateAccount);

    }

    public void initListners(){

        appCompatButtonButtonSignIn.setOnClickListener(this);
        textViewLinkCreateAccount.setOnClickListener(this);

    }

    public void initObjects(){

        databaseHelper = new DatabaseHelper(activity);
        inputvalidation = new InputValidation(activity);

    }

    @Override
    public void onClick(View view){

        switch (view.getId()){

            case R.id.ButtonSignIn :
                verifyFromSQLite();
                break;
            case R.id.LinkCreateAccount :
                Intent intentRegister = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intentRegister);
                break;

        }

    }

    private void verifyFromSQLite(){

        if(!inputvalidation.IsInputEditTextFilled(textInputEditTextLoginUserName, textInputLayoutLoginUserName, getString(R.string.error_message_username))){
            return;
        }

        if(!inputvalidation.IsInputEditTextEmail(textInputEditTextLoginUserName, textInputLayoutLoginUserName, getString(R.string.error_message_email))){
            return;
        }

        if(!inputvalidation.IsInputEditTextFilled(textInputEditTextLoginPassword, textInputLayoutLoginPassword, getString(R.string.error_message_password))){
            return;
        }

        if(databaseHelper.checkUser(textInputEditTextLoginUserName.getText().toString().trim(), textInputEditTextLoginPassword.getText().toString().trim())){

            Intent accountIntent = new Intent(activity, HomePageActivity.class);
            accountIntent.putExtra("EMAIL", textInputEditTextLoginUserName.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountIntent);

        }else{

            Snackbar.make(view, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
        }

    }

    private void emptyInputEditText(){

        textInputEditTextLoginUserName.setText(null);
        textInputEditTextLoginPassword.setText(null);

    }

}
