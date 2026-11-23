package com.example.votingapp.service;

import com.example.votingapp.model.Candidate;
import com.example.votingapp.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VoteService {
    private final CandidateRepository repository;

    public VoteService(CandidateRepository repository) {
        this.repository = repository;
    }

    public List<Candidate> getAllCandidates() {
        return repository.findAll();
    }

    public Candidate vote(Long id) {
        if (repository.incrementVotes(id) == 0) {
            throw new NoSuchElementException();
        }
        return repository.findById(id).orElseThrow();
    }
}
