package br.com.icaro.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.icaro.agenda.domain.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}
