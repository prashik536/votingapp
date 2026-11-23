package com.example.votingapp.controller;

import com.example.votingapp.model.Candidate;
import com.example.votingapp.service.VoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vote")
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/candidates")
    public List<Candidate> getCandidates() {
        return voteService.getAllCandidates();
    }

    @PostMapping("/{id}")
    public Candidate vote(@PathVariable Long id) {
        return voteService.vote(id);
    }
}
