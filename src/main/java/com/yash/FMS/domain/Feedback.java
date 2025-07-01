package com.yash.FMS.domain;

import java.util.Date;

public class Feedback {
    private int feedbackId;
    private int userId;
    private int categoryId;
    private Date submissionDate;

    private int question1Answer;
    private int question2Answer;
    private int question3Answer;
    private int question4Answer;
    private int question5Answer;
    private int question6Answer;
    private int question7Answer;

    public Feedback() {
    }

    public Feedback(int feedbackId, int userId, int categoryId, Date submissionDate, int question1Answer, int question2Answer, int question3Answer, int question4Answer, int question5Answer, int question6Answer, int question7Answer) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.submissionDate = submissionDate;
        this.question1Answer = question1Answer;
        this.question2Answer = question2Answer;
        this.question3Answer = question3Answer;
        this.question4Answer = question4Answer;
        this.question5Answer = question5Answer;
        this.question6Answer = question6Answer;
        this.question7Answer = question7Answer;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public int getQuestion1Answer() {
        return question1Answer;
    }

    public void setQuestion1Answer(int question1Answer) {
        this.question1Answer = question1Answer;
    }

    public int getQuestion2Answer() {
        return question2Answer;
    }

    public void setQuestion2Answer(int question2Answer) {
        this.question2Answer = question2Answer;
    }

    public int getQuestion3Answer() {
        return question3Answer;
    }

    public void setQuestion3Answer(int question3Answer) {
        this.question3Answer = question3Answer;
    }

    public int getQuestion4Answer() {
        return question4Answer;
    }

    public void setQuestion4Answer(int question4Answer) {
        this.question4Answer = question4Answer;
    }

    public int getQuestion5Answer() {
        return question5Answer;
    }

    public void setQuestion5Answer(int question5Answer) {
        this.question5Answer = question5Answer;
    }

    public int getQuestion6Answer() {
        return question6Answer;
    }

    public void setQuestion6Answer(int question6Answer) {
        this.question6Answer = question6Answer;
    }

    public int getQuestion7Answer() {
        return question7Answer;
    }

    public void setQuestion7Answer(int question7Answer) {
        this.question7Answer = question7Answer;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", submissionDate=" + submissionDate +
                ", question1Answer=" + question1Answer +
                ", question2Answer=" + question2Answer +
                ", question3Answer=" + question3Answer +
                ", question4Answer=" + question4Answer +
                ", question5Answer=" + question5Answer +
                ", question6Answer=" + question6Answer +
                ", question7Answer=" + question7Answer +
                '}';
    }
}