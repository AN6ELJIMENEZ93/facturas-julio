package com.facturacion.facturacion_julio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.facturacion.facturacion_julio.models.DAO.IClienteDAO;
import com.facturacion.facturacion_julio.models.entity.Cliente;
import com.facturacion.facturacion_julio.validators.ClienteValidator;

@RestController
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private IClienteDAO clienteDAO;

    @Autowired
    private ClienteValidator clienteValidator;

    public void initBinder(DataBinder binder){
        binder.addValidators(clienteValidator);
    }
    
    @RequestMapping(path="/clientes", method=RequestMethod.GET)
    public List<Cliente> clienteList() {
        return clienteDAO.findAll();
    }

    @PostMapping("/guardar-cliente")
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente, BindingResult result) {
        clienteValidator.validate(cliente, result);

        if (result.hasErrors()) {
            result.getAllErrors();
            return ResponseEntity.badRequest().body("Error con la validacion de los datos: " +result.getAllErrors());
        } else {
            clienteDAO.save(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado exitosamente: " +cliente.getNombre());
        }
    
    }

    @GetMapping("/clientes/{id}")
    public Cliente getClienteById(@PathVariable(name="id", required = true) Long id){
       return clienteDAO.findOne(id);
    }

    @DeleteMapping("/delete-cliente/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable(name="id") Long id){
        try {
            clienteDAO.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado el registro");
        } catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El id no coincide");
        } catch(UnexpectedRollbackException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El id no coincide");
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No fue posible eliminar el registro");
        }
    }

}
