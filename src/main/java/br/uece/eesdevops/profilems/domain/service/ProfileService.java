package br.uece.eesdevops.profilems.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.uece.eesdevops.profilems.domain.entity.Profile;

public interface ProfileService {
	public Profile save(Profile profile);
	public Page<Profile> getAll(Pageable pageable);
	public Profile findByIdUser(Integer idUser);
	public Profile getById(Integer id);
	public void delete(Integer id);
}
