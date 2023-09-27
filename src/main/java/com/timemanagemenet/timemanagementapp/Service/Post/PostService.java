package com.timemanagemenet.timemanagementapp.Service.Post;

import com.timemanagemenet.timemanagementapp.Entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(Long id);
    Post createPost(Post post);
    void deletePost(Long id);
    Post updatePost(Long id, Post post);
}
