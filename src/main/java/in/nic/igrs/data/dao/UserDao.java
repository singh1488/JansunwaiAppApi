package in.nic.igrs.data.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.nic.igrs.data.model.AddUserModel;

@Repository
public interface UserDao extends CrudRepository<AddUserModel, Integer> {
	
	AddUserModel findByUsername(String username);
	
}
