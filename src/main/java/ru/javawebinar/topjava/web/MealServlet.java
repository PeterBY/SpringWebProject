package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.db.MealDAO;
import ru.javawebinar.topjava.db.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private static final MealDAO mealDAO = MealDAOImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MealWithExceed> mealWithExceededList = MealsUtil.getFilteredWithExceeded(mealDAO.getMeals(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
        req.setAttribute("mealWithExceededList", mealWithExceededList);
        req.getRequestDispatcher("meals.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        mealDAO.addMeal(new Meal(
                LocalDateTime.parse(req.getParameter("date")),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories"))));
        resp.sendRedirect(req.getRequestURI());
    }
}
