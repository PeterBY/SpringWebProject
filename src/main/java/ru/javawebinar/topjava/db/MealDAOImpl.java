package ru.javawebinar.topjava.db;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MealDAOImpl implements MealDAO{
    private final static List<Meal> mealList = new CopyOnWriteArrayList<>();
    private final static MealDAO instance = new MealDAOImpl();

    static {
        mealList.addAll( Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        ));
    }

    private MealDAOImpl() {
    }

    public static MealDAO getInstance(){
        return instance;
    }

    @Override
    public List<Meal> getMeals() {
        return Collections.unmodifiableList(mealList);
    }

    @Override
    public void addMeal(Meal meal) {
        mealList.add(meal);
    }

    @Override
    public void deleteMeal(int mealId) {
        mealList.remove(mealId);
    }

    @Override
    public void updateMeal(Meal meal) {
        mealList.set(meal.getId(), meal);
    }

    @Override
    public void getMealById(int mealId) {

    }
}
