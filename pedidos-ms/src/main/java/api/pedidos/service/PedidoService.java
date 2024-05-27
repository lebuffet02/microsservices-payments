package api.pedidos.service;

import api.pedidos.dto.PedidoDTO;
import api.pedidos.dto.PedidoStatusDTO;
import api.pedidos.entity.PedidoEntity;
import api.pedidos.exception.PedidosException;
import api.pedidos.exception.ResponseEnum;
import api.pedidos.mapper.MapperPedido;
import api.pedidos.repository.PedidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.Optional;

public interface PedidoService {

    Page<PedidoStatusDTO> getAllService(int page, int size);

    Optional<PedidoStatusDTO> saveService(PedidoDTO pedidoDTO);

    Optional<PedidoStatusDTO> updateService(Long id, PedidoDTO pedidoDTO);

    Optional<PedidoStatusDTO> getByIdService(Long id);

    void deleteByIdService(Long id);
}