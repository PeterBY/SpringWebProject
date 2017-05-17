package ru.javawebinar.topjava.web.meal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.web.meal.MealRestController.REST_URL;

//import org.springframework.util.Assert;

public class MealRestControllerTest extends AbstractControllerTest {
    @Test
    public void testGet() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL + "/" + MEAL1_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + "/" + MEAL1_ID))
                .andExpect(status().isOk());
        Assert.assertFalse(mealService.getAll(USER_ID).contains(MEAL1));
    }

    @Test
    public void testCreate() throws Exception {
        Meal meal = new Meal(LocalDateTime.now(), "test meal", 666);
        mockMvc.perform(post(REST_URL).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.writeValue(meal)))
                .andDo(print())
                .andExpect(status().isOk());
        Assert.assertFalse(mealService.getAll(USER_ID).contains(meal));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal meal = new Meal(LocalDateTime.now(), "test meal", 1488);
        mockMvc.perform(put(REST_URL + "/" + MEAL1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(meal)))
                .andDo(print())
                .andExpect(status().isOk());
        Assert.assertEquals(1488, mealService.get(MEAL1_ID, USER_ID).getCalories());
    }

    @Test
    public void testGetBetween() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL
                + "/?startDate=2012-01-01&endDate=2017-05-05&startTime=09:00:00&endTime=15:00:00"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)));
    }

}