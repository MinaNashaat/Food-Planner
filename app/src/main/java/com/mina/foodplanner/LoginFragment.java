package com.mina.foodplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    TextView signUpText;
    private Button loginBtn;
    private EditText emailET, passwordET;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
//            reload();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signUpText = view.findViewById(R.id.signUpText);
        loginBtn = view.findViewById(R.id.loginBtn);
        emailET = view.findViewById(R.id.emailET);
        passwordET = view.findViewById(R.id.passwordET);




        // 1. Initialize FirebaseAuth (if not already done in onCreate)
        mAuth = FirebaseAuth.getInstance();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 2. Call sign in (assuming email and password strings are available)
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success
                                    Log.d("minanashaat", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
//                                updateUI(user);
                                    Intent intent = new Intent(requireActivity(), HomeActivity.class);
                                    intent.putExtra("uid", user.getUid());
                                    intent.putExtra("email", user.getEmail());
                                    intent.putExtra("name", user.getDisplayName());
                                    startActivity(intent);
                                } else {
                                    // If sign in fails
                                    Log.d("minanashaat", "signInWithEmail:failure", task.getException());

                                    // 3. Use getContext() or requireContext() for the Toast
                                    Toast.makeText(requireContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
//                                updateUI(null);
                                }
                            }
                        });
            }
        });


        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment();

                Navigation.findNavController(view).navigate(action);
//                startActivity(new Intent(view.getContext(),HomeActivity.class));

            }
        });
    }
}