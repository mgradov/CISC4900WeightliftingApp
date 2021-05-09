package com.example.CISC4900.WeightliftingApp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "record_table")
public class Record {

    //Constructors
    public Record(Date date, String exercise, double weight, double sets, double reps) {
        this.date = date;
        this.exercise = exercise;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
    }

    public Record() {
        this.date = new Date(0);
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    private Date date;

    private String exercise;

    private double weight;

    private double sets;

    private double reps;

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSets() {
        return sets;
    }

    public void setSets(double sets) {
        this.sets = sets;
    }

    public double getReps() {
        return reps;
    }

    public void setReps(double reps) {
        this.reps = reps;
    }

    public double getOneRepMax() {
        return weight * (1 + reps / 30);
    }

}
