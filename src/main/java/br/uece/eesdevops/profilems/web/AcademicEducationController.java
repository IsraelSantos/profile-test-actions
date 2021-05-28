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

import br.uece.eesdevops.profilems.domain.entity.AcademicEducation;
import br.uece.eesdevops.profilems.domain.service.AcademicEducationService;
import br.uece.eesdevops.profilems.web.entity.AcademicEducationDTO;

@RestController
@RequestMapping("/v1.0/academiceducations")
public class AcademicEducationController implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6549845454454651L;
	
	private AcademicEducationService academicEducationService;
	
	@Autowired
	AcademicEducationController(
			AcademicEducationService academicEducationService
			){
		this.academicEducationService = academicEducationService;
	}
    
	//Para setar página e comprimento na chamada: http://localhost:8081/v1.0/academiceducations?pageNumber=0&pageSize=5
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AcademicEducation>> getAll(Pageable pageable) {
    	return ResponseEntity.ok(academicEducationService.getAll(pageable));
    }
    
	//Para setar página e comprimento na chamada: http://localhost:8081/v1.0/academiceducations/findbyidprofile/{idProfile}?pageNumber=0&pageSize=5
    @GetMapping(value = "/findbyidprofile/{idProfile}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AcademicEducation>> findByIdProfile(@PathVariable Integer idProfile, Pageable pageable) {
    	return ResponseEntity.ok(academicEducationService.findByIdProfile(idProfile, pageable));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AcademicEducation> getById(@PathVariable Integer id) {
    	return ResponseEntity.ok(academicEducationService.getById(id));
    }
    
    @PostMapping(consumes = APPLICATION_JSON_VALUE, 
    		produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AcademicEducation> save(@RequestBody AcademicEducationDTO profile) {
    	AcademicEducation entity = profile.toDomain();
        AcademicEducation res = academicEducationService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
    
    @PutMapping(value = "/{id}", 
    		consumes = APPLICATION_JSON_VALUE, 
    		produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AcademicEducation> change(@PathVariable Integer id, @RequestBody AcademicEducationDTO profile) {
    	AcademicEducation entity = profile.toDomain();
    	entity.setId(id);
        AcademicEducation res = academicEducationService.save(entity);
        return ResponseEntity.ok(res);
    }
    
    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Integer id) {
		academicEducationService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
