package org.cf.gerenciadordetarefas.services;

import org.cf.gerenciadordetarefas.models.TarefaModel;
import org.cf.gerenciadordetarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public TarefaModel save(TarefaModel tarefaModel) {
        return tarefaRepository.save(tarefaModel);
    }

    public List<TarefaModel> findAll(){
        return tarefaRepository.findAll();
    }

    public void delete(Long id) {
        tarefaRepository.deleteById(id);
    }

    public TarefaModel update(Long id, TarefaModel tarefaModel) {
        Optional<TarefaModel> tarefaBuscado = tarefaRepository.findById(id);

        if(tarefaBuscado.isPresent()){
            TarefaModel tarefaExistente = tarefaBuscado.get();

            tarefaExistente.setDescricao(tarefaModel.getDescricao());
            tarefaExistente.setConcluida(tarefaModel.getConcluida());
            tarefaExistente.setDataVencimento(tarefaModel.getDataVencimento());
            return tarefaRepository.save(tarefaExistente);
        }
        return null;
    }

    public TarefaModel findById(Long id) {
        return tarefaRepository.findById(id).orElse(null);
    }
}
