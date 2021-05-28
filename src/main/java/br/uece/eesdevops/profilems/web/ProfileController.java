package br.uece.eesdevops.profilems.web;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.eesdevops.profilems.domain.entity.Profile;
import br.uece.eesdevops.profilems.domain.service.ProfileService;
import br.uece.eesdevops.profilems.web.entity.ProfileDTO;

@RestController
@RequestMapping("/v1.0/profiles")
public class ProfileController implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4982526632752626588L;
	
	private ProfileService profileService;
	
	@Autowired
	ProfileController(
				ProfileService profileService
			){
		this.profileService = profileService;
	}
    
	//Para setar página e comprimento na chamada: http://localhost:8081/profiles/list?page=0&size=5
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Profile>> getAll(Pageable pageable) {
    	return ResponseEntity.ok(profileService.getAll(pageable));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> getById(@PathVariable Integer id) {
    	return ResponseEntity.ok(profileService.getById(id));
    }
    
    @GetMapping(value = "/findbyiduser/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> getByIdUser(@PathVariable Integer id) {
    	return ResponseEntity.ok(profileService.findByIdUser(id));
    }
    
    @PostMapping(consumes = APPLICATION_JSON_VALUE, 
    		produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> save(@RequestBody ProfileDTO profile) {
    	Profile entity = profile.toDomain();
        Profile res = profileService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
    
    @PutMapping(value = "/{id}", 
    		consumes = APPLICATION_JSON_VALUE, 
    		produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> change(@PathVariable Integer id, @RequestBody ProfileDTO profile) {
    	Profile entity = profile.toDomain();
    	entity.setId(id);
        Profile res = profileService.save(entity);
        return ResponseEntity.ok(res);
    }
    
    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Integer id) {
		profileService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
