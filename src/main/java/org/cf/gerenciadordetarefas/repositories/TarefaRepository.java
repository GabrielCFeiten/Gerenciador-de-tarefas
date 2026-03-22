package org.cf.gerenciadordetarefas.repositories;

import org.cf.gerenciadordetarefas.models.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<TarefaModel, Long> {
}
