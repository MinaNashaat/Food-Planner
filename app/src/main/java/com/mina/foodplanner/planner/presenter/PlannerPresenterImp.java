package com.mina.foodplanner.planner.presenter;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.mina.foodplanner.data.PlannerRepo;
import com.mina.foodplanner.data.SharedPrefrencesRepo;
import com.mina.foodplanner.data.model.PlannerDay;
import com.mina.foodplanner.data.model.UserPlannedMeal;
import com.mina.foodplanner.planner.view.WeeklyPlannerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlannerPresenterImp implements PlannerPresenter{
    PlannerRepo plannerRepo;
    SharedPrefrencesRepo sharedPrefrencesRepo;
    WeeklyPlannerView weeklyPlannerView;
    CompositeDisposable disposable;

    public PlannerPresenterImp(Context context, WeeklyPlannerView weeklyPlannerView) {
        this.plannerRepo = new PlannerRepo(context);
        sharedPrefrencesRepo = new SharedPrefrencesRepo(context);
        this.weeklyPlannerView = weeklyPlannerView;
        disposable = new CompositeDisposable();
    }

    @Override
    public Flowable<List<UserPlannedMeal>> getAllUserPlannedMeals() {
        String email = sharedPrefrencesRepo.getUserEmail();
        return plannerRepo.getAllUserPlannedMeals(email);
    }

    @Override
    public void deleteUserPlannedMeal(UserPlannedMeal userPlannedMeal) {
//        plannerRepo.deleteUserPlannedMeal(userPlannedMeal);

        disposable.add(
                plannerRepo.deleteUserPlannedMeal(userPlannedMeal)
                        .subscribeOn(Schedulers.io())
                        .subscribe()
        );
    }

    @Override
    public void updateMealsBasedonDay(PlannerDay plannerDay) {
        String email = sharedPrefrencesRepo.getUserEmail();
//        List<UserPlannedMeal> userPlannedMeals = plannerRepo.getMealsForUserByDate(email,plannerDay.fullDate);
//        weeklyPlannerView.updateWeeklyPlannerMeals(userPlannedMeals);

        disposable.add(
                plannerRepo.getMealsForUserByDate(email, plannerDay.fullDate)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meals -> weeklyPlannerView.updateWeeklyPlannerMeals(meals)
                        )
        );

    }

    @Override
    public void generateNext7Days() {
        List<PlannerDay> days = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
        SimpleDateFormat dayNumberFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        for (int i = 0; i < 7; i++) {
            Date date = calendar.getTime();

            days.add(new PlannerDay(
                    dayNameFormat.format(date).toUpperCase(),
                    dayNumberFormat.format(date),
                    fullDateFormat.format(date)
            ));

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        weeklyPlannerView.getNextSevenDays(days);
    }

    @Override
    public void onDestroy() {
        disposable.clear();
    }

}
