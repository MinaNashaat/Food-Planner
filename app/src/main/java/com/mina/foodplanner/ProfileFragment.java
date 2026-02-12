package com.mina.foodplanner;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;
import com.mina.foodplanner.data.datasource.sharedprefrences.SharedPrefrencesDataSource;
import com.mina.foodplanner.data.db.AppDatabase;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.data.model.UserPlannedMeal;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfileFragment extends Fragment {

    FirebaseFirestore firestore;
    AppDatabase database;
    Button sybcBTN, logoutBtn;
    TextView profileNameTV, profileEmailTV;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firestore = FirebaseFirestore.getInstance();
        database = AppDatabase.getInstance(requireContext());
        sybcBTN = view.findViewById(R.id.sybcBTN);
        logoutBtn = view.findViewById(R.id.logoutBtn);
        profileNameTV = view.findViewById(R.id.profileNameTV);
        profileEmailTV = view.findViewById(R.id.profileEmailTV);

        SharedPrefrencesDataSource sharedPrefrencesDataSource = new SharedPrefrencesDataSource(requireContext());


        if (sharedPrefrencesDataSource.isGuest()) {
            sybcBTN.setEnabled(false);
            sybcBTN.setAlpha(0.5f);
//            sybcBTN.setText("Login required to sync");
        }

        String name = sharedPrefrencesDataSource.getUserName();
        String email = sharedPrefrencesDataSource.getUserEmail();

        profileNameTV.setText(name != null ? name : "Guest");
        profileEmailTV.setText(email != null ? email : "");

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPrefrencesDataSource sharedPrefrencesDataSource = new SharedPrefrencesDataSource(requireContext());
                sharedPrefrencesDataSource.logout();

                FirebaseAuth.getInstance().signOut();

                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(
                        GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

                GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(requireContext(), gso);

                googleSignInClient.signOut();

                disposable.add(
                        AppDatabase.getInstance(requireContext())
                                .mealsDao()
                                .deleteAll()
                                .andThen(
                                        AppDatabase.getInstance(requireContext())
                                                .userPlannedMealsDao()
                                                .deleteAll()
                                )
                                .subscribeOn(Schedulers.io())
                                .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
                                .subscribe(
                                        () -> {
                                            Intent intent = new Intent(requireActivity(), MainActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);

                                            Snackbar.make(requireView(), "Logged out successfully", Snackbar.LENGTH_SHORT).show();

                                        }
                                )
                );

            }
        });

        sybcBTN.setOnClickListener(v -> syncToCloud());

    }
    private void syncToCloud() {

        String email = FirebaseAuth.getInstance()
                .getCurrentUser()
                .getEmail();

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        deleteCollection(firestore, "users/" + email + "/favorites", () -> {

            deleteCollection(firestore, "users/" + email + "/plannedMeals", () -> {

                uploadNewData(email);

            });

        });
    }
    private void deleteCollection(FirebaseFirestore firestore, String path, Runnable onComplete) {

        firestore.collection(path)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    WriteBatch batch = firestore.batch();

                    for (DocumentSnapshot document :
                            queryDocumentSnapshots.getDocuments()) {
                        batch.delete(document.getReference());
                    }

                    batch.commit().addOnSuccessListener(unused -> {
                        onComplete.run();
                    });
                });
    }

    private void uploadNewData(String email) {

        CompositeDisposable compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(
                database.mealsDao()
                        .getAllMeals()
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(meals -> {

                            if (meals == null) return;

                            for (Meal meal : meals) {
                                firestore.collection("users")
                                        .document(email)
                                        .collection("favorites")
                                        .document(meal.getIdMeal())
                                        .set(meal);
                            }

                        }, throwable -> {
                            throwable.printStackTrace();
                        })
        );


        CompositeDisposable disposable = new CompositeDisposable();

        disposable.add(
                database.userPlannedMealsDao()
                        .getAllUserPlannedMeals(email)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(plannedMeals -> {

                            if (plannedMeals == null || plannedMeals.isEmpty()) return;

                            for (UserPlannedMeal meal : plannedMeals) {

                                String safeDate = meal.getDate().replace("/", "-");
                                String docId = meal.getIdMeal() + "_" + safeDate;

                                firestore.collection("users")
                                        .document(email)
                                        .collection("plannedMeals")
                                        .document(docId)
                                        .set(meal);
                            }

                        })
        );


        Snackbar.make(requireView(), "Sync Completed",Snackbar.LENGTH_SHORT).show();

    }

}