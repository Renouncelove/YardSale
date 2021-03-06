package com.YardSale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.YardSale.utils.AccountUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A login screen that offers login via email/password.
 */
public class AccountSettingsActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    private static final String TAG = "Sign In";

    /**
     * Firebase Connection
     */
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mConfirmPasswordEditText;
    private EditText mPostalCodeEditText;
    private Button mUpdateSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        // Text Inputs
        mEmailEditText = findViewById(R.id.editTextEmail);
        mPasswordEditText = findViewById(R.id.editTextPassword);
        mConfirmPasswordEditText = findViewById(R.id.editTextConfirmPassword);
        mPostalCodeEditText = findViewById((R.id.editTextPostalCode));

        // Buttons
        mUpdateSettings = findViewById(R.id.buttonUpdateSettings);

        mUpdateSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Set errors to null
                mPasswordEditText.setError(null);
                mEmailEditText.setError(null);

                if(mPasswordEditText.getText().toString().equals(mConfirmPasswordEditText.getText().toString()))
                    AccountUtil.updateUserSettings(mEmailEditText.getText().toString().trim(), mPasswordEditText.getText().toString(), mPostalCodeEditText.getText().toString().trim());

                else {
                    mPasswordEditText.setError("Passwords do not match");
                    mConfirmPasswordEditText.setError("Passwords do not match");
                }

            }
        });

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }



}

