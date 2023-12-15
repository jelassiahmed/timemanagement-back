package com.timemanagemenet.timemanagementapp.service.post;

import com.timemanagemenet.timemanagementapp.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(Long id);
    Post createPost(Post post);
    void deletePost(Long id);
    Post updatePost(Long id, Post post);
}
