package fbg.fittrack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String goal;
    private double weight;
    private double height;
    private List<Workout> workouts = new ArrayList<>();
    private List<Workout> schedule = new ArrayList<>();
    private List<Exercise> exercises = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public List<Workout> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Workout> schedule) {
        this.schedule = schedule;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addWorkout(Workout workout) {
        if (workouts == null) {
            workouts = new ArrayList<>();
            workouts.add(workout);
        } else {
            workouts.add(workout);
        }
    }

    public void removeWorkout(Workout workout) {
        workouts.removeIf(e -> Objects.equals(e.getName(), workout.getName()));
    }

    public void addExercise(Exercise exercise) {
        if (exercises == null) {
            exercises = new ArrayList<>();
            exercises.add(exercise);
        }
        else {
            exercises.add(exercise);
        }
    }

    public void removeExercise(Exercise exercise) {
        exercises.removeIf(e -> Objects.equals(e.getName(), exercise.getName()));
    }

    public User() {}
    public User(String name, String goal, double weight, double height) {
        this.name = name;
        this.goal = goal;
        this.weight = weight;
        this.height = height;
    }

    public void saveProfile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("userProfile.json")) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static User loadProfile(File userProfile) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileReader reader = new FileReader(userProfile)) {
            return gson.fromJson(reader, User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
