package ru.javawebinar.topjava.db;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDAO {
    List<Meal> getMeals();

    void addMeal(Meal meal);

    void deleteMeal(int mealId);

    void updateMeal(Meal user);

    void getMealById(int mealId);
}
