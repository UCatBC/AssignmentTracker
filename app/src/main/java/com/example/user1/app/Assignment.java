package com.example.user1.app;

public class Assignment {
    public String type;
    public String module;
    public String title;
    public String issue;
    public String deadline;
    public String weighting;
    public String resources;
    public String completed;
    public String grade;


    public Assignment(String module, String title, String issue, String deadline,
                      String weighting) {
        this.module = module;
        this.title = title;
        this.issue = issue;
        this.deadline = deadline;
        this.weighting = weighting;
    }

    public Assignment(String type, String module, String title, String issue, String deadline,
                      String weighting, String resources, String completed, String grade) {
        this.type = type;
        this.module = module;
        this.title = title;
        this.issue = issue;
        this.deadline = deadline;
        this.weighting = weighting;
        this.resources = resources;
        this.completed = completed;
        this.grade = grade;
    }

}