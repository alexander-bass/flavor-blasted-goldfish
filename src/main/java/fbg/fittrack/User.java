package fbg.fittrack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class User {
    private String name;
    private String goal;
    private double weight;
    private double height;
    private List<Workout> workouts;
    private List<Workout> schedule;

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

    public void addWorkout(Workout workout) {
        workouts.add(workout);
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
