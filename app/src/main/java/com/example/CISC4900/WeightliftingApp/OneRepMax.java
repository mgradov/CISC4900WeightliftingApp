package com.example.CISC4900.WeightliftingApp;

public class OneRepMax {

    public static final String EPLEY = "EPLEY";
    public static final String BRZYCKI = "BRZYCKI";
    public static final String LANDER = "LANDER";
    public static final String MAYHEW = "MAYHEW";
    public static final String LOMBARDI = "LOMBARDI";
    public static final String WATHEN = "WATHEN";
    public static final String OCONNER = "OCONNER";

    private double weight;
    private double reps;
    private String formula;

    public OneRepMax(double weight, double reps, String formula) {
        this.weight = weight;
        this.reps = reps;
        this.formula = formula;
    }

    public OneRepMax(double weight, double reps) {
        this.weight = weight;
        this.reps = reps;
    }

    public OneRepMax() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getReps() {
        return reps;
    }

    public void setReps(double reps) {
        this.reps = reps;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public double epley() {
        return weight * (1 + reps / 30);
    }

    public double brzycki() {
        return weight * (36 / (37 - reps));
    }

    public double lander() {
        return weight / (1.013 - 0.0267123 * reps);
    }

    public double mayhew() {
        return 100 * weight / (52.2 + 41.9 * Math.exp(-0.055 * reps));
    }

    public double lombardi() {
        return weight * Math.pow(reps, 0.10);
    }

    public double wathen() {
        return (100 * weight) / (48.8 + 53.8 * Math.exp(-0.075 * reps));
    }

    public double oConner() {
        return weight * (1 + 0.025 * reps);
    }
}
