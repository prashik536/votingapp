package com.example.votingapp.service;

import com.example.votingapp.model.Candidate;
import com.example.votingapp.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    @Autowired
    private CandidateRepository repository;

    public List<Candidate> getAllCandidates() {
        return repository.findAll();
    }

    public Candidate vote(Long id) {
        Candidate candidate = repository.findById(id).orElseThrow();
        candidate.setVotes(candidate.getVotes() + 1);
        return repository.save(candidate);
    }
}

