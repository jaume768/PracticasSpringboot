package com.example.login_spring.Service;

import com.example.login_spring.Model.Permiso;
import com.example.login_spring.Repository.PermisoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoService {

    private final PermisoRepository permisoRepository;
    public PermisoService(PermisoRepository permisoRepository){
        this.permisoRepository = permisoRepository;
    }

    public List<Permiso> getPermisos(){
        return permisoRepository.findAll();
    }

    public Permiso insertPermiso(Permiso permiso){
        return permisoRepository.save(permiso);
    }

}
