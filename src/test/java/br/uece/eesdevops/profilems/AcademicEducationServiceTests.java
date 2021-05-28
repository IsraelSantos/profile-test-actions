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

import br.uece.eesdevops.profilems.domain.entity.AcademicEducation;
import br.uece.eesdevops.profilems.domain.exception.NotFoundException;
import br.uece.eesdevops.profilems.domain.service.AcademicEducationService;
import br.uece.eesdevops.profilems.domain.service.impl.AcademicEducationServiceImpl;
import br.uece.eesdevops.profilems.repository.AcademicEducationRepository;
import br.uece.eesdevops.profilems.util.FakeAcademicEducation;


@DisplayName("Runs all tests for domain service class responsible for change a academic education")
public class AcademicEducationServiceTests {
	
	private final AcademicEducationRepository academicEducationRepository = 
			mock(AcademicEducationRepository.class);

    private AcademicEducationService service;

    @BeforeEach
    void beforeEach() {
        service = new AcademicEducationServiceImpl(this.academicEducationRepository);
    }

    @Test
    @DisplayName("should save a academic education to returned status successfully")
    void should_save_a_academic_education_to_returned_status_successfully() {
    	AcademicEducation academicEducation = FakeAcademicEducation.fakeAcademicEducationIfNoId();
        
        when(academicEducationRepository.save(academicEducation))
        		.thenReturn(FakeAcademicEducation.fakeAcademicEducation());

        AcademicEducation tmp = service.save(academicEducation);

        assertNotEquals(null, tmp.getId());

        assertEquals(academicEducation.getAbstractM(), tmp.getAbstractM());
        assertEquals(academicEducation.getEducationalInstitution(), tmp.getEducationalInstitution());
        assertEquals(academicEducation.getFormation(), tmp.getFormation());
        assertEquals(academicEducation.getStudyArea(), tmp.getStudyArea());
        assertEquals(academicEducation.getNote(), tmp.getNote());
        assertEquals(academicEducation.getActivityAndGroups(), tmp.getActivityAndGroups());
        assertEquals(academicEducation.getStartDate(), tmp.getStartDate());
        assertEquals(academicEducation.getEndDate(), tmp.getEndDate());
        assertEquals(academicEducation.getAbstractM(), tmp.getAbstractM());
        assertEquals(academicEducation.getProfile().getId(), tmp.getProfile().getId());

        verify(academicEducationRepository).save(academicEducation);
    }
    
    @Test
    @DisplayName("should return not found exception to returned status successfully")
    void should_return_not_found_exception_to_returned_status_successfully() {
        AcademicEducation academicEducation = FakeAcademicEducation.fakeAcademicEducation();
        
        when(academicEducationRepository.findById(academicEducation.getId()))
        		.thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
        	service.save(academicEducation);
        });

        verify(academicEducationRepository).findById(academicEducation.getId());
    }
    

}
