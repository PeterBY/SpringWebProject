package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(path = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/meals";

    @Autowired(required = false)
    private ConversionService conversionService;

    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    public Meal create(@RequestBody Meal meal) {
        return super.create(meal);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        super.update(meal, id);
    }

//    @GetMapping
//    public List<MealWithExceed> getAllOrWithFilter(
//        @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//        @RequestParam(value = "startTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
//        @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
//        @RequestParam(value = "endTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {
//            if (startDate == null && startTime == null && startTime == null && startTime == null)
//                return super.getAll();
//            return super.getBetween(startDate, startTime, endDate, endTime);
//    }

    @GetMapping
    public List<MealWithExceed> getAllOrWithFilter(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "endTime", required = false) String endTime) {
        if (startDate == null && startTime == null && startTime == null && startTime == null)
            return super.getAll();

        return super.getBetween(
                startDate != null ? conversionService.convert(startDate, LocalDate.class) : null,
                startTime != null ? conversionService.convert(startTime, LocalTime.class) : null,
                endDate != null ? conversionService.convert(endDate, LocalDate.class) : null,
                endTime != null ? conversionService.convert(endTime, LocalTime.class) : null);
    }
}