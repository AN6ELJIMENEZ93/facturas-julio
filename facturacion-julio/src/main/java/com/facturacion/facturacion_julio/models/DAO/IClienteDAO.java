package com.facturacion.facturacion_julio.models.DAO;

import java.util.List;

import com.facturacion.facturacion_julio.models.entity.Cliente;

public interface IClienteDAO {
    public List<Cliente> findAll();
    public void save(Cliente cliente);
    public Cliente findOne(Long id);
    public void delete(Long id);
}
