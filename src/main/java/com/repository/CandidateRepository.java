package com.example.votingapp.repository;

import com.example.votingapp.model.Candidate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CandidateRepository {
    private static final RowMapper<Candidate> CANDIDATE_MAPPER = (resultSet, rowNumber) -> {
        Candidate candidate = new Candidate();
        candidate.setId(resultSet.getLong("id"));
        candidate.setName(resultSet.getString("name"));
        candidate.setVotes(resultSet.getInt("votes"));
        return candidate;
    };

    private final JdbcTemplate jdbcTemplate;

    public CandidateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Candidate> findAll() {
        return jdbcTemplate.query("SELECT id, name, votes FROM candidate", CANDIDATE_MAPPER);
    }

    public int incrementVotes(Long id) {
        return jdbcTemplate.update("UPDATE candidate SET votes = votes + 1 WHERE id = ?", id);
    }

    public Optional<Candidate> findById(Long id) {
        List<Candidate> candidates = jdbcTemplate.query(
                "SELECT id, name, votes FROM candidate WHERE id = ?", CANDIDATE_MAPPER, id);
        return candidates.stream().findFirst();
    }
}
