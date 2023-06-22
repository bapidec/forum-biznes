package com.example.forumbiznes.controller;

import com.example.forumbiznes.Dao.CommentDao;
import com.example.forumbiznes.Model.Comment;
import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.service.CommentService;
import com.example.forumbiznes.service.CommentServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;

import java.util.List;

public class CommentController {
    @EJB
    private CommentService commentService;
    @Inject
    PostController postController;

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
    }

    public void onEditComment(Comment c) {
        this.editedComment = c;
    }

    public void onSaveComment(Post p) {

        Comment saved;

        // jeśli nowy, nie edytowany
        if(this.editedComment.getId() == null) {
            this.comments.add(this.editedComment);
            saved = commentService.save(this.editedComment);
        }
        else {
            saved = commentService.update(this.editedComment);
            // aktualizacja this.topics z bazą
            this.comments.replaceAll(c -> c != editedComment ? c : saved);
        }
        this.postController.addComment(p, saved);

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
