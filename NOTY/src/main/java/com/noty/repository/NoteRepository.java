package com.noty.repository;

import com.noty.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByCandidatIdAndMatiereId(int candidatId, int matiereId);
}
