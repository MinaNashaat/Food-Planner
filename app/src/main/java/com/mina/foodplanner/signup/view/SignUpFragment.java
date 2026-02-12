package com.mina.foodplanner.signup.view;

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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.mina.foodplanner.HomeActivity;
import com.mina.foodplanner.R;
//import com.mina.foodplanner.SignUpFragmentDirections;

public class SignUpFragment extends Fragment {

    private FirebaseAuth mAuth;
    private Button signupBtn;
    private EditText nameET, emailET, passwordET;
    private TextView loginTV;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        signupBtn = view.findViewById(R.id.signupBtn);
        nameET = view.findViewById(R.id.nameET);
        emailET = view.findViewById(R.id.emailET);
        passwordET = view.findViewById(R.id.passwordET);
        loginTV = view.findViewById(R.id.loginTV);

        ImageButton googleBtn = view.findViewById(R.id.googleBtn);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);

        googleBtn.setOnClickListener(v -> {

            googleSignInClient.signOut().addOnCompleteListener(task -> {

                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);

            });

        });


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("minanashaat", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            String username = nameET.getText().toString();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();

                            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("minanashaat", "User profile updated.");
                                        nameET.setText("");
                                        emailET.setText("");
                                        passwordET.setText("");
                                        Toast.makeText(requireContext(), "The account created successfully", Toast.LENGTH_SHORT).show();
//                                                        updateUI(user);
                                    }
                                }
                            });

//                                    updateUI(user);
                        } else {
                            Log.w("minanashaat", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(requireContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
            }
        });
        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment();

                Navigation.findNavController(view).navigate(action);

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(requireContext(), "Google sign in failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), task -> {

                    if (task.isSuccessful()) {

                        FirebaseUser user = mAuth.getCurrentUser();

                        Toast.makeText(requireContext(), "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(requireActivity(), HomeActivity.class);
                        intent.putExtra("uid", user.getUid());
                        intent.putExtra("email", user.getEmail());
                        intent.putExtra("name", user.getDisplayName());

                        startActivity(intent);
                        requireActivity().finish();

                    } else {

                        Toast.makeText(requireContext(), "Authentication Failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}