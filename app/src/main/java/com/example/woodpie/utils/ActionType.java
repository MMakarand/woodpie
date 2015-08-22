package com.example.woodpie.utils;

/**
 * Created by asmita on 22/8/15.
 */
public enum ActionType
{
    REVIEW("reviewed book"),
    RATE("rated book"),
    ADD_TO_WISHLIST("added book in wishlist"),
    ADD_TO_COLLECTION("added book in collection");

    private String action;

    private ActionType(String action)
    {
        this.action = action;
    }

    public String getActionText()
    {
        return this.action;
    }
}
