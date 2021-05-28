package br.uece.eesdevops.profilems.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.uece.eesdevops.profilems.domain.entity.AcademicEducation;

public interface AcademicEducationService {
	public AcademicEducation save(AcademicEducation profile);
	public Page<AcademicEducation> getAll(Pageable pageable);
	public AcademicEducation getById(Integer id);
	public void delete(Integer id);
	public Page<AcademicEducation> findByIdProfile(Integer idProfile, Pageable pageable);
}
