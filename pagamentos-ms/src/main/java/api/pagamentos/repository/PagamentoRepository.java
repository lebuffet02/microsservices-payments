package api.pagamentos.repository;

import api.pagamentos.constantes.StatusPedido;
import api.pagamentos.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {

    @Query("SELECT p from PagamentoEntity p WHERE p.status = ?1")
    List<PagamentoEntity> buscaStatusAtivo(StatusPedido status);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE PagamentoEntity p SET p.status =:status, p.isPagamentoAceito =:aceito WHERE p.id =:id")
    void atualizaStatusPedido(StatusPedido status, boolean aceito, Long id);
}
