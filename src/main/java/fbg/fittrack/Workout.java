package fbg.fittrack;

import java.util.List;

public class Workout {
    private String name;
    private List<Exercise> exercises;
    private boolean completed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Workout(String name, List<Exercise> exercises, boolean completed) {
        this.name = name;
        this.exercises = exercises;
        this.completed = completed;
    }
}
