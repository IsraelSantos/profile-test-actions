package br.uece.eesdevops.profilems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uece.eesdevops.profilems.domain.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>{
	public Optional<Profile> findByIdUser(Integer idUser);
}
