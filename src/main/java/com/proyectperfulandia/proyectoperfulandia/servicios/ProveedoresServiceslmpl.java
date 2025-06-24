package com.proyectperfulandia.proyectoperfulandia.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectperfulandia.proyectoperfulandia.entidades.Proveedores;
import com.proyectperfulandia.proyectoperfulandia.repositorios.ProveedoresRepository;

@Service
public class ProveedoresServiceslmpl implements ProveedoresServices{

    @Autowired
    private ProveedoresRepository proveedoresRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Proveedores> findByAll() {
        return (List<Proveedores>) proveedoresRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Proveedores> findById(Long id){
        Optional<Proveedores> proveedoresOptional = proveedoresRepository.findById(id);
        return proveedoresOptional;
    }

    @Override
    public Proveedores save(Proveedores unProveedores){
        return proveedoresRepository.save(unProveedores); // el problema puede estar en la diferencia de variables
    }

    @Override
    public Optional<Proveedores> delete(Proveedores unProveedores){
        Optional<Proveedores> proveedoresOptional = proveedoresRepository.findById(unProveedores.getId_proveedor());
        proveedoresOptional.ifPresent(provedorDb ->{
            proveedoresRepository.delete(unProveedores);
        });
        return proveedoresOptional;
    }
}
