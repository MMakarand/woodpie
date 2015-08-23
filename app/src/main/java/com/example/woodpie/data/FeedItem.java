package com.example.woodpie.data;

import com.example.woodpie.entities.Book;
import com.example.woodpie.entities.User;
import com.example.woodpie.utils.ActionType;

import java.util.List;

/**
 * Created by asmita on 22/8/15.
 */
public class FeedItem
{
    private int id;
    private User user;
    private ActionType action;
    private Book book;
    private Integer rating;
    private String review;
    private List<String> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
