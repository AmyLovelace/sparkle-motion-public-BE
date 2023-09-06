package com.sparklemotion.repositories;
import com.sparklemotion.entities.Action;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends CrudRepository<Action,Long>{
}
