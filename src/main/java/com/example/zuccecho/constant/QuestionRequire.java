package com.example.zuccecho.constant;

public enum QuestionRequire {
    REQUIRE("REQUIRE"),
    NONE_REQUIRE("NONE_REQUIRE");

    private String necessary;

    QuestionRequire(String necessary) {
        this.necessary = necessary;
    }

    public String getNecessary() {
        return necessary;
    }

    public void setNecessary(String necessary) {
        this.necessary = necessary;
    }
}
