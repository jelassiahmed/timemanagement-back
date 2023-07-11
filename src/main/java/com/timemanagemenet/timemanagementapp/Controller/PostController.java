package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Post;
import com.timemanagemenet.timemanagementapp.Service.Post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Post savePost(@RequestBody Post post) {
        return postService.savePost(post);
    }

    @GetMapping("/{postId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @DeleteMapping("/{postId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deletePostById(@PathVariable Long postId) {
        postService.deletePostById(postId);
    }

    @PutMapping("/{postId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post post) {
        post.setPostId(postId);
        return postService.updatePost(post);
    }

    @GetMapping("/user")
    public List<Post> getPostsForAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        return Collections.singletonList(postService.getPostByKeycloakUserId(userId));
    }


    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
}
