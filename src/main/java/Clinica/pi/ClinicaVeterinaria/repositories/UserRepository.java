package Clinica.pi.ClinicaVeterinaria.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Clinica.pi.ClinicaVeterinaria.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>{

	Optional<UserModel> findByUsername(String username);
	
}
