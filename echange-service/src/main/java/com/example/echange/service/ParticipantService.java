package com.example.echange.service;

import com.example.echange.model.Echange;
import com.example.echange.model.Participant;
import com.example.echange.model.Status;
import com.example.echange.repo.EchangeRepository;
import com.example.echange.repo.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {
    private EchangeRepository echangeRepository;


    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public Participant updateParticipant(Long id, Participant participant) {
        if (participantRepository.existsById(id)) {
            participant.setId(id);
            return participantRepository.save(participant);
        } else {
            // Gérer l'erreur : participant avec cet ID non trouvé
            return null;
        }
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }
    public Participant updateParticipantStatus(Long id, Status newStatus) {
        Optional<Participant> optionalParticipant = participantRepository.findById(id);

        if (optionalParticipant.isPresent()) {
            Participant participant = optionalParticipant.get();
            participant.setStatut(newStatus);
            return participantRepository.save(participant);
        } else {
            // Gérer l'erreur : participant avec cet ID non trouvé
            return null;
        }
    }

}
