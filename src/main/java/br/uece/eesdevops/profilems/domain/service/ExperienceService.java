package br.uece.eesdevops.profilems.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.uece.eesdevops.profilems.domain.entity.Experience;

public interface ExperienceService {
	public Experience save(Experience profile);
	public Page<Experience> getAll(Pageable pageable);
	public Experience getById(Integer id);
	public void delete(Integer id);
	public Page<Experience> findByIdProfile(Integer idProfile, Pageable pageable);
}
