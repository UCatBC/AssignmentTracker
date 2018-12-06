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

    public void setModule(String pModule) {
        this.module = pModule;
    }

    public String getModule(){
        return module;
    }

    public void setTitle(String pTitle) {
        this.title = pTitle;
    }

    public String getTitle(){
        return title;
    }

    public void setDeadline(String pDeadline) {
        this.deadline = pDeadline;
    }

    public String getDeadline(){
        return deadline;
    }

    public void setWeighting(String pWeighting) {
        this.weighting = pWeighting;
    }

    public String getWeighting(){
        return weighting;
    }

    public void setGrade(String pGrade) {
        this.grade = pGrade;
    }

    public String getGrade(){
        return grade;
    }

    public void setResources(String pResources) {
        this.resources = pResources;
    }

    public String getResources(){
        return resources;
    }

    public void setCompleted(String pCompleted) {
        this.completed = pCompleted;
    }

    public String getCompleted(){
        return completed;
    }

    public void setType(String pType) {
        this.type = pType;
    }

    public String getType(){
        return type;
    }

    public void setIssue(String pIssue) {
        this.issue = pIssue;
    }

    public String getIssue(){
        return issue;
    }

}