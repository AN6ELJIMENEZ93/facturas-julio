package com.facturacion.facturacion_julio.models.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.facturacion.facturacion_julio.models.entity.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ClienteDAO implements IClienteDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            em.remove(this.findOne(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Cliente> findAll() {
        return em.createQuery("from Cliente").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return em.find(Cliente.class, id);
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        if(cliente.getId() != null && cliente.getId() > 0){
            em.merge(cliente);
        } else {
            em.persist(cliente);
        }
        
    }
}
