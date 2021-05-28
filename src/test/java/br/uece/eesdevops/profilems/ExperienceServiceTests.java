package br.uece.eesdevops.profilems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.uece.eesdevops.profilems.domain.entity.Experience;
import br.uece.eesdevops.profilems.domain.exception.NotFoundException;
import br.uece.eesdevops.profilems.domain.service.ExperienceService;
import br.uece.eesdevops.profilems.domain.service.impl.ExperienceServiceImpl;
import br.uece.eesdevops.profilems.repository.ExperienceRepository;
import br.uece.eesdevops.profilems.util.FakeExperience;


@DisplayName("Runs all tests for domain service class responsible for change a experience")
public class ExperienceServiceTests {
	
	private final ExperienceRepository experienceRepository = 
			mock(ExperienceRepository.class);

    private ExperienceService service;

    @BeforeEach
    void beforeEach() {
        service = new ExperienceServiceImpl(this.experienceRepository);
    }

    @Test
    @DisplayName("should save a experience to returned status successfully")
    void should_save_a_experience_to_returned_status_successfully() {
    	Experience experience = FakeExperience.fakeExperienceIfNoId();
        
        when(experienceRepository.save(experience))
        		.thenReturn(FakeExperience.fakeExperience());

        Experience tmp = service.save(experience);

        assertNotEquals(null, tmp.getId());

        assertEquals(experience.getAbstractM(), tmp.getAbstractM());
        assertEquals(experience.getPositionM(), tmp.getPositionM());
        assertEquals(experience.getJobType(), tmp.getJobType());
        assertEquals(experience.getCompany(), tmp.getCompany());
        assertEquals(experience.getLocality(), tmp.getLocality());
        assertEquals(experience.getCurrentJob(), tmp.getCurrentJob());
        assertEquals(experience.getStartDate(), tmp.getStartDate());
        assertEquals(experience.getEndDate(), tmp.getEndDate());
        assertEquals(experience.getAbstractM(), tmp.getAbstractM());
        assertEquals(experience.getProfile().getId(), tmp.getProfile().getId());

        verify(experienceRepository).save(experience);
    }
    
    @Test
    @DisplayName("should return not found exception to returned status successfully")
    void should_return_not_found_exception_to_returned_status_successfully() {
        Experience experience = FakeExperience.fakeExperience();
        
        when(experienceRepository.findById(experience.getId()))
        		.thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
        	service.save(experience);
        });

        verify(experienceRepository).findById(experience.getId());
    }
    

}
