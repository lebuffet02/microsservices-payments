package api.pedidos.repository;

import api.pedidos.constantes.StatusPedido;
import api.pedidos.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    Optional<PedidoEntity> findByEmail(String email);

    @Query("SELECT p from PedidoEntity p WHERE p.status = ?1 ")
    List<PedidoEntity> buscaStatusAtivo(StatusPedido status);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE PedidoEntity p SET p.status =:status WHERE p.id =:id")
    void atualizaStatusPedido(StatusPedido status, Long id);
}