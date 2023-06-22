package com.example.forumbiznes.controller;

import com.example.forumbiznes.Dao.CommentDao;
import com.example.forumbiznes.Model.Comment;
import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.bean.UserBean;
import com.example.forumbiznes.service.CommentService;
import com.example.forumbiznes.service.CommentServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
@Named
@SessionScoped
public class CommentController implements Serializable {
    @EJB
    private CommentService commentService;
    @Inject
    PostController postController;

    @Inject
    UserBean userBean;

    private List<Comment> comments;
    private Comment editedComment;
//    private Comment currentComment;

    @PostConstruct
    private void init() {
        this.comments = commentService.findAll();
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void onAddComment() {
        this.editedComment = new Comment();
        this.editedComment.setUser(userBean.getUser());
    }

    public void onEditComment(Comment c) {
        this.editedComment = c;
    }

    public void onSaveComment(Post p) {


        this.comments.add(this.editedComment);

        this.postController.addComment(p, editedComment);


        this.editedComment = null;
    }

    public void onRemoveComment(Comment c) {
        this.commentService.delete(c);
        comments.remove(c);
    }

    public void onCancelComment() {
        this.editedComment = null;
    }

//    public void onReportComment(Comment c) {
//        commentService.report(c);
//    }


    public Comment getEditedComment() {
        return editedComment;
    }

    public void setEditedComment(Comment editedComment) {
        this.editedComment = editedComment;
    }
}
