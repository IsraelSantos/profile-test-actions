package br.uece.eesdevops.profilems.domain.service.impl;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uece.eesdevops.profilems.domain.entity.Experience;
import br.uece.eesdevops.profilems.domain.exception.InternalServerErrorException;
import br.uece.eesdevops.profilems.domain.exception.NotFoundException;
import br.uece.eesdevops.profilems.domain.service.ExperienceService;
import br.uece.eesdevops.profilems.repository.ExperienceRepository;


@Service
public class ExperienceServiceImpl implements Serializable, ExperienceService{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 18965465465454654L;
	
	ExperienceRepository experienceRepository;

	@Autowired
	public ExperienceServiceImpl(
			ExperienceRepository experienceRepository 
	){
		this.experienceRepository = experienceRepository;
	}
	
	@Transactional
	public Experience save(Experience experience) throws RuntimeException {

		try {
			
			//Checagens
			if (experience.getId() != null) {
				Optional<Experience> experienceTeste = this.experienceRepository.findById(experience.getId());
				if (!experienceTeste.isPresent()) {
					throw new NotFoundException(Experience.class, "Experience does not exist!");
				}
			}
			
			return experienceRepository.save(experience);

		}catch (NotFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new InternalServerErrorException(Experience.class,
					e.getMessage()); 
		}
	}

	@Override
	public Page<Experience> getAll(Pageable pageable) throws RuntimeException {
		try {

			Page<Experience> experiences = experienceRepository.findAll(pageable);
			
			return experiences;

		} catch (Exception e) {
			throw new InternalServerErrorException(Experience.class,
					e.getMessage()); 
		}
	}

	@Override
	public Experience getById(Integer id) throws RuntimeException {
    	try {
			Optional<Experience> experience = experienceRepository.findById(id);
	        if (!experience.isPresent()) {
	            throw new NotFoundException(Experience.class, id);
	        } else {
	        	return experience.get();
	        }
		} catch (NotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalServerErrorException(Experience.class,
					e.getMessage()); 
		}
	}

	@Override
	public void delete(Integer id) throws RuntimeException {
		Optional<Experience> experience = experienceRepository.findById(id);
		
		if(experience.isPresent()) {
			Experience tmp = experience.get();
			experienceRepository.delete(tmp);
		}else {
			throw new NotFoundException(Experience.class, "The experience could not be deleted because it does not exist!");
		}
	}

	@Override
	public Page<Experience> findByIdProfile(Integer idProfile, Pageable pageable) {
		try {

			Page<Experience> experiences = experienceRepository.findByIdProfileOrderByDateStart(idProfile, pageable);
			
			return experiences;

		} catch (Exception e) {
			throw new InternalServerErrorException(Experience.class,
					e.getMessage()); 
		}
	}
	
}

