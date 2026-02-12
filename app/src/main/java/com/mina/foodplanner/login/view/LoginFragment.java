package com.mina.foodplanner.login.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mina.foodplanner.HomeActivity;
//import com.mina.foodplanner.LoginFragmentDirections;
import com.mina.foodplanner.R;
import com.mina.foodplanner.data.datasource.sharedprefrences.SharedPrefrencesDataSource;
import com.mina.foodplanner.data.db.AppDatabase;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.data.model.UserPlannedMeal;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginFragment extends Fragment {

    TextView signUpText,guest;
    private Button loginBtn;
    private EditText emailET, passwordET;
    private FirebaseAuth mAuth;
    private GoogleSignInClient googleSignInClient;
    private ImageButton googleBtn;
    private final ActivityResultLauncher<Intent> googleLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == getActivity().RESULT_OK) {

                            Intent data = result.getData();
                            Task<GoogleSignInAccount> task =
                                    GoogleSignIn.getSignedInAccountFromIntent(data);

                            try {
                                GoogleSignInAccount account = task.getResult(ApiException.class);
                                firebaseAuthWithGoogle(account.getIdToken());

                            } catch (ApiException e) {}

                        }
                    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPrefrencesDataSource sharedPrefrencesDataSource = new SharedPrefrencesDataSource(requireContext());

        if (sharedPrefrencesDataSource.isLoggedIn()) {

            Intent intent = new Intent(requireActivity(), HomeActivity.class);

            startActivity(intent);
            requireActivity().finish();
            requireActivity().finish();
        }

        signUpText = view.findViewById(R.id.signUpText);
        loginBtn = view.findViewById(R.id.loginBtn);
        emailET = view.findViewById(R.id.emailET);
        passwordET = view.findViewById(R.id.passwordET);
        googleBtn = view.findViewById(R.id.googleBtn);
        guest = view.findViewById(R.id.guest);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(
                    GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso);

        googleBtn.setOnClickListener(v -> signInWithGoogle());

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefrencesDataSource prefs = new SharedPrefrencesDataSource(requireContext());

                prefs.saveGuest();


                Intent intent = new Intent(requireActivity(), HomeActivity.class);
                intent.putExtra("isGuest", true);
                startActivity(intent);

                requireActivity().finish();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Log.d("minanashaat", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    SharedPrefrencesDataSource sharedPrefrencesDataSource = new SharedPrefrencesDataSource(requireContext());

                                    sharedPrefrencesDataSource.saveUser(
                                            user.getEmail(),
                                            user.getDisplayName()
                                    );
//                                updateUI(user);
                                    restoreFromCloud(user.getEmail());

                                    Intent intent = new Intent(requireActivity(), HomeActivity.class);
                                    intent.putExtra("uid", user.getUid());
                                    intent.putExtra("email", user.getEmail());
                                    intent.putExtra("name", user.getDisplayName());
                                    startActivity(intent);
                                    requireActivity().finish();
                                } else {
                                    Log.d("minanashaat", "signInWithEmail:failure", task.getException());

                                    Toast.makeText(requireContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
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

            }
        });
    }
    private void signInWithGoogle() {

        googleSignInClient.signOut().addOnCompleteListener(task -> {

            Intent signInIntent = googleSignInClient.getSignInIntent();
            googleLauncher.launch(signInIntent);

        });
    }

    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), task -> {

                    if (task.isSuccessful()) {

                        FirebaseUser user = mAuth.getCurrentUser();

                        SharedPrefrencesDataSource sharedPrefrencesDataSource = new SharedPrefrencesDataSource(requireContext());

                        sharedPrefrencesDataSource.saveUser(
                                user.getEmail(),
                                user.getDisplayName()
                        );

                        restoreFromCloud(user.getEmail());

                        Intent intent = new Intent(requireActivity(), HomeActivity.class);

                        intent.putExtra("uid", user.getUid());
                        intent.putExtra("email", user.getEmail());
                        intent.putExtra("name", user.getDisplayName());

                        startActivity(intent);
                        requireActivity().finish();

                    } else {
                        Toast.makeText(requireContext(), "Google authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void restoreFromCloud(String email) {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        AppDatabase database = AppDatabase.getInstance(requireContext());

        firestore.collection("users")
                .document(email)
                .collection("favorites")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {

                        Meal meal = doc.toObject(Meal.class);

                        if (meal != null) {
                            database.mealsDao()
                                    .insertMeal(meal)
                                    .subscribeOn(Schedulers.io())
                                    .subscribe();
                        }
                    }
                });

        firestore.collection("users")
                .document(email)
                .collection("plannedMeals")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    for (DocumentSnapshot document : queryDocumentSnapshots) {

                        UserPlannedMeal meal =
                                document.toObject(UserPlannedMeal.class);

                        if (meal != null) {
                            database.userPlannedMealsDao()
                                    .insert(meal)
                                    .subscribeOn(Schedulers.io())
                                    .subscribe();
                        }
                    }
                });
    }



}