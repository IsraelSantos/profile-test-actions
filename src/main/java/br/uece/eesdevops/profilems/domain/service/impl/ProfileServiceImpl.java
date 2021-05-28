package br.uece.eesdevops.profilems.domain.service.impl;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uece.eesdevops.profilems.domain.entity.Profile;
import br.uece.eesdevops.profilems.domain.exception.InternalServerErrorException;
import br.uece.eesdevops.profilems.domain.exception.NotFoundException;
import br.uece.eesdevops.profilems.domain.service.ProfileService;
import br.uece.eesdevops.profilems.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements Serializable, ProfileService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7302482972333087217L;
	
	ProfileRepository profileRepository;

	@Autowired
	public ProfileServiceImpl(
			ProfileRepository profileRepository 
	){
		this.profileRepository = profileRepository;
	}
	
	@Transactional
	public Profile save(Profile profile) throws RuntimeException {

		try {
			
			//Checagens
			if (profile.getId() != null) {
				Optional<Profile> profileTeste = this.profileRepository.findById(profile.getId());
				if (!profileTeste.isPresent()) {
					throw new NotFoundException(Profile.class, "Perfil não existe!");
				}
			}
			
			return profileRepository.save(profile);

		}catch (NotFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new InternalServerErrorException(Profile.class,
					e.getMessage()); 
		}
	}

	@Override
	public Page<Profile> getAll(Pageable pageable) throws RuntimeException {
		try {

			Page<Profile> profiles = profileRepository.findAll(pageable);
			
			return profiles;

		} catch (Exception e) {
			throw new InternalServerErrorException(Profile.class,
					e.getMessage()); 
		}
	}

	@Override
	public Profile getById(Integer id) throws RuntimeException {
    	try {
			Optional<Profile> profile = profileRepository.findById(id);
	        if (!profile.isPresent()) {
	            throw new NotFoundException(Profile.class, id);
	        } else {
	        	return profile.get();
	        }
		} catch (NotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalServerErrorException(Profile.class,
					e.getMessage()); 
		}
	}

	@Override
	public void delete(Integer id) throws RuntimeException {
		Optional<Profile> profile = profileRepository.findById(id);
		
		if(profile.isPresent()) {
			Profile tmp = profile.get();
			profileRepository.delete(tmp);
		}else {
			throw new NotFoundException(Profile.class, "The profile could not be deleted because it does not exist!");
		}
	}

	@Override
	public Profile findByIdUser(Integer idUser) throws RuntimeException {
		try {

			Optional<Profile> profile = profileRepository.findByIdUser(idUser);
	        if (!profile.isPresent()) {
	            throw new NotFoundException(Profile.class, idUser);
	        } else {
	        	return profile.get();
	        }

		} catch (Exception e) {
			throw new InternalServerErrorException(Profile.class,
					e.getMessage()); 
		}
	}
	
}

