package com.uo.springfinalproject.services;

import com.uo.springfinalproject.models.IMainModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
@Getter
@Setter
public abstract class GenericService<M extends IMainModel, R extends JpaRepository<M, Long>> {
    @Autowired
    private R repo;

    public M add(M t){
        return repo.save(t);
    }

    public M getById(Long id){
        return repo.findById(id).orElse(null);
    }

    public M edit (M t){
        Long id = t.getId();
        if(id != null & repo.findById(id) != null){
            return repo.save(t);
        }
        return null;
    }

    public boolean delete(M t) {
        Long id = t.getId();
        try{
            repo.deleteById(id);
            return true;
        }catch(Error err){
            return false;
        }
    }
}
