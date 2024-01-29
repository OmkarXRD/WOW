package com.live.worldsocialintegrationapp.ModelClasses.onlineServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAnswerRoot {


    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private Details details;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public class Details {
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("question")
        @Expose
        private String question;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("answers")
        @Expose
        private Answers answers;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public Answers getAnswers() {
            return answers;
        }

        public void setAnswers(Answers answers) {
            this.answers = answers;
        }
    }

    public class Answers {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("queId")
        @Expose
        private String queId;
        @SerializedName("answer")
        @Expose
        private String answer;
        @SerializedName("created")
        @Expose
        private String created;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQueId() {
            return queId;
        }

        public void setQueId(String queId) {
            this.queId = queId;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

    }
}
