package com.timemanagemenet.timemanagementapp.Service.Post;

import com.timemanagemenet.timemanagementapp.Entity.Post;
import com.timemanagemenet.timemanagementapp.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImp implements PostService{
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    @Override
    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post getPostByKeycloakUserId(String userId) {
        return postRepository.findByUserIdsContaining(userId);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
