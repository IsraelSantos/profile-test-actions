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

import br.uece.eesdevops.profilems.domain.entity.Experience;
import br.uece.eesdevops.profilems.domain.service.ExperienceService;
import br.uece.eesdevops.profilems.web.entity.ExperienceDTO;

@RestController
@RequestMapping("/v1.0/experiences")
public class ExperienceController implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6549845454454651L;
	
	private ExperienceService experienceService;
	
	@Autowired
	ExperienceController(
			ExperienceService experienceService
			){
		this.experienceService = experienceService;
	}
    
	//Para setar página e comprimento na chamada: http://localhost:8081/v1.0/experiences?pageNumber=0&pageSize=5
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Experience>> getAll(Pageable pageable) {
    	return ResponseEntity.ok(experienceService.getAll(pageable));
    }
    
	//Para setar página e comprimento na chamada: http://localhost:8081/v1.0/experiences/findbyidprofile/{idProfile}?pageNumber=0&pageSize=5
    @GetMapping(value = "/findbyidprofile/{idProfile}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Experience>> findByIdProfile(@PathVariable Integer idProfile, Pageable pageable) {
    	return ResponseEntity.ok(experienceService.findByIdProfile(idProfile, pageable));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Experience> getById(@PathVariable Integer id) {
    	return ResponseEntity.ok(experienceService.getById(id));
    }
    
    @PostMapping(consumes = APPLICATION_JSON_VALUE, 
    		produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Experience> save(@RequestBody ExperienceDTO profile) {
    	Experience entity = profile.toDomain();
        Experience res = experienceService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
    
    @PutMapping(value = "/{id}", 
    		consumes = APPLICATION_JSON_VALUE, 
    		produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Experience> change(@PathVariable Integer id, @RequestBody ExperienceDTO profile) {
    	Experience entity = profile.toDomain();
    	entity.setId(id);
        Experience res = experienceService.save(entity);
        return ResponseEntity.ok(res);
    }
    
    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Integer id) {
		experienceService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
