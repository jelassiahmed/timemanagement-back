package com.timemanagemenet.timemanagementapp.Service.Post;
import com.timemanagemenet.timemanagementapp.Entity.Post;

import java.util.List;

public interface PostService {
    Post savePost(Post post);
    Post getPostById(Long postId);
    void deletePostById(Long postId);
    Post updatePost(Post post);
    Post getPostByKeycloakUserId(String userId);
    List<Post> getAllPosts();
    // Additional methods for your requirements
}