package br.uece.eesdevops.profilems.domain.service.impl;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uece.eesdevops.profilems.domain.entity.AcademicEducation;
import br.uece.eesdevops.profilems.domain.exception.InternalServerErrorException;
import br.uece.eesdevops.profilems.domain.exception.NotFoundException;
import br.uece.eesdevops.profilems.domain.service.AcademicEducationService;
import br.uece.eesdevops.profilems.repository.AcademicEducationRepository;


@Service
public class AcademicEducationServiceImpl implements Serializable, AcademicEducationService{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 18965465465454654L;
	
	AcademicEducationRepository academicEducationRepository;

	@Autowired
	public AcademicEducationServiceImpl(
			AcademicEducationRepository academicEducationRepository 
	){
		this.academicEducationRepository = academicEducationRepository;
	}
	
	@Transactional
	public AcademicEducation save(AcademicEducation academicEducation) throws RuntimeException {

		try {
			
			//Checagens
			if (academicEducation.getId() != null) {
				Optional<AcademicEducation> academicEducationTeste = this.academicEducationRepository.findById(academicEducation.getId());
				if (!academicEducationTeste.isPresent()) {
					throw new NotFoundException(AcademicEducation.class, "Academic education does not exist!");
				}
			}
			
			return academicEducationRepository.save(academicEducation);

		}catch (NotFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new InternalServerErrorException(AcademicEducation.class,
					e.getMessage()); 
		}
	}

	@Override
	public Page<AcademicEducation> getAll(Pageable pageable) throws RuntimeException {
		try {

			Page<AcademicEducation> academicEducations = academicEducationRepository.findAll(pageable);
			
			return academicEducations;

		} catch (Exception e) {
			throw new InternalServerErrorException(AcademicEducation.class,
					e.getMessage()); 
		}
	}

	@Override
	public AcademicEducation getById(Integer id) throws RuntimeException {
    	try {
			Optional<AcademicEducation> academicEducation = academicEducationRepository.findById(id);
	        if (!academicEducation.isPresent()) {
	            throw new NotFoundException(AcademicEducation.class, id);
	        } else {
	        	return academicEducation.get();
	        }
		} catch (NotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalServerErrorException(AcademicEducation.class,
					e.getMessage()); 
		}
	}

	@Override
	public void delete(Integer id) throws RuntimeException {
		Optional<AcademicEducation> academicEducation = academicEducationRepository.findById(id);
		
		if(academicEducation.isPresent()) {
			AcademicEducation tmp = academicEducation.get();
			academicEducationRepository.delete(tmp);
		}else {
			throw new NotFoundException(AcademicEducation.class, "The academic education could not be deleted because it does not exist!");
		}
	}

	@Override
	public Page<AcademicEducation> findByIdProfile(Integer idProfile, Pageable pageable) {
		try {

			Page<AcademicEducation> academicEducations = academicEducationRepository.findByIdProfileOrderByDateStart(idProfile, pageable);
			
			return academicEducations;

		} catch (Exception e) {
			throw new InternalServerErrorException(AcademicEducation.class,
					e.getMessage()); 
		}
	}
	
}

