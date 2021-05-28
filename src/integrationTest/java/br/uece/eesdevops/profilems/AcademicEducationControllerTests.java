package br.uece.eesdevops.profilems;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.uece.eesdevops.profilems.domain.entity.AcademicEducation;
import br.uece.eesdevops.profilems.domain.entity.Profile;
import br.uece.eesdevops.profilems.repository.AcademicEducationRepository;
import br.uece.eesdevops.profilems.repository.ExperienceRepository;
import br.uece.eesdevops.profilems.repository.ProfileRepository;
import br.uece.eesdevops.profilems.util.BodyRequests;
import br.uece.eesdevops.profilems.util.FakeAcademicEducation;
import br.uece.eesdevops.profilems.util.FakeProfile;
import br.uece.eesdevops.profilems.util.Util;
import br.uece.eesdevops.profilems.web.entity.AcademicEducationDTO;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;


@AutoConfigureMockMvc
@SpringBootTest(classes = ProfileMsApplication.class)
@DisplayName("Runs all tests for academic education registration")
@AutoConfigureEmbeddedDatabase
public class AcademicEducationControllerTests {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ProfileRepository profileRepository;
    
    @Autowired
    private ExperienceRepository experienceRepository;
    
    @Autowired
    private AcademicEducationRepository academicEducationRepository;
    
    @BeforeEach
    void beforeEach() {
    	experienceRepository.deleteAllInBatch();
    	academicEducationRepository.deleteAllInBatch();
    	profileRepository.deleteAllInBatch();
    }
    
    // region GET /academiceducations

    @Test
    @DisplayName("should get all academic educations with no results")
    void should_get_all_academic_educations_with_no_results() throws Exception {
        mockMvc.perform(get("/v1.0/academiceducations").header("Origin","*"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)));
    }
    
    @Test
    @DisplayName("should get all academic educations with one result")
    void should_get_all_academic_educations_with_one_result() throws Exception {
    	AcademicEducation academicEducation = FakeAcademicEducation.fakeAcademicEducationIfNoId();
    	Profile profile = FakeProfile.fakeProfileIfNoId();

        profile = profileRepository.save(profile);
        academicEducation.setProfile(profile);
    	academicEducation = academicEducationRepository.save(academicEducation);
        
        mockMvc.perform(get("/v1.0/academiceducations").header("Origin","*"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is(academicEducation.getId())))
                .andExpect(jsonPath("$.content[0].educationalInstitution", is(academicEducation.getEducationalInstitution())))
                .andExpect(jsonPath("$.content[0].formation", is(academicEducation.getFormation())))
                .andExpect(jsonPath("$.content[0].studyArea", is(academicEducation.getStudyArea())))
                .andExpect(jsonPath("$.content[0].note", is(academicEducation.getNote())))
                .andExpect(jsonPath("$.content[0].activityAndGroups", is(academicEducation.getActivityAndGroups())))
                .andExpect(jsonPath("$.content[0].startDate", is(Util.formatDateIntegrationTests(academicEducation.getStartDate()))))
                .andExpect(jsonPath("$.content[0].endDate", is(Util.formatDateIntegrationTests(academicEducation.getEndDate()))))
                .andExpect(jsonPath("$.content[0].abstractM", is(academicEducation.getAbstractM())))
                .andExpect(jsonPath("$.content[0].profile.id", is(profile.getId())));
    }
    
    @Test
    @DisplayName("should get academic educations by id profile with one result")
    void should_get_all_academic_educations_by_id_profile_with_one_result() throws Exception {
    	AcademicEducation academicEducation = FakeAcademicEducation.fakeAcademicEducationIfNoId();
    	Profile profile = FakeProfile.fakeProfileIfNoId();

        profile = profileRepository.save(profile);
        academicEducation.setProfile(profile);
    	academicEducation = academicEducationRepository.save(academicEducation);
        
        mockMvc.perform(get("/v1.0/academiceducations/findbyidprofile/"+profile.getId()).header("Origin","*"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is(academicEducation.getId())))
                .andExpect(jsonPath("$.content[0].educationalInstitution", is(academicEducation.getEducationalInstitution())))
                .andExpect(jsonPath("$.content[0].formation", is(academicEducation.getFormation())))
                .andExpect(jsonPath("$.content[0].studyArea", is(academicEducation.getStudyArea())))
                .andExpect(jsonPath("$.content[0].note", is(academicEducation.getNote())))
                .andExpect(jsonPath("$.content[0].activityAndGroups", is(academicEducation.getActivityAndGroups())))
                .andExpect(jsonPath("$.content[0].startDate", is(Util.formatDateIntegrationTests(academicEducation.getStartDate()))))
                .andExpect(jsonPath("$.content[0].endDate", is(Util.formatDateIntegrationTests(academicEducation.getEndDate()))))
                .andExpect(jsonPath("$.content[0].abstractM", is(academicEducation.getAbstractM())))
                .andExpect(jsonPath("$.content[0].profile.id", is(profile.getId())));
    }
    
    @Test
    @DisplayName("should get a academic education")
    void should_get_a_academic_education() throws Exception {
    	AcademicEducation academicEducation = FakeAcademicEducation.fakeAcademicEducationIfNoId();
    	Profile profile = FakeProfile.fakeProfileIfNoId();

        profile = profileRepository.save(profile);
        academicEducation.setProfile(profile);
    	academicEducation = academicEducationRepository.save(academicEducation);
        
        mockMvc.perform(get("/v1.0/academiceducations/"+academicEducation.getId()).header("Origin","*"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(academicEducation.getId())))
                .andExpect(jsonPath("$.educationalInstitution", is(academicEducation.getEducationalInstitution())))
                .andExpect(jsonPath("$.formation", is(academicEducation.getFormation())))
                .andExpect(jsonPath("$.studyArea", is(academicEducation.getStudyArea())))
                .andExpect(jsonPath("$.note", is(academicEducation.getNote())))
                .andExpect(jsonPath("$.activityAndGroups", is(academicEducation.getActivityAndGroups())))
                .andExpect(jsonPath("$.startDate", is(Util.formatDateIntegrationTests(academicEducation.getStartDate()))))
                .andExpect(jsonPath("$.endDate", is(Util.formatDateIntegrationTests(academicEducation.getEndDate()))))
                .andExpect(jsonPath("$.abstractM", is(academicEducation.getAbstractM())))
                .andExpect(jsonPath("$.profile.id", is(profile.getId())));
    }
    
    // endregion

    // region POST /academiceducations

    @Test
    @DisplayName("should save a new academic education successfully")
    void should_save_new_academic_education_successfully() throws Exception {
    	AcademicEducation academicEducation = mapper.readValue(BodyRequests.newAcademicEducationRequest(), AcademicEducation.class);
    	Profile profile = mapper.readValue(BodyRequests.newProfileRequest(), Profile.class);
    	
    	profile = profileRepository.save(profile);
    	
    	academicEducation.setProfile(profile);
    	
    	AcademicEducationDTO resq = (new AcademicEducationDTO()).toDTO(academicEducation);
    	
    	String json = Util.buildJson(resq);

        MockHttpServletRequestBuilder request = post("/v1.0/academiceducations")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Origin","*");

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.educationalInstitution", is(academicEducation.getEducationalInstitution())))
                .andExpect(jsonPath("$.formation", is(academicEducation.getFormation())))
                .andExpect(jsonPath("$.studyArea", is(academicEducation.getStudyArea())))
                .andExpect(jsonPath("$.note", is(academicEducation.getNote())))
                .andExpect(jsonPath("$.activityAndGroups", is(academicEducation.getActivityAndGroups())))
                .andExpect(jsonPath("$.startDate", is(Util.formatDateIntegrationTests(academicEducation.getStartDate()))))
                .andExpect(jsonPath("$.endDate", is(Util.formatDateIntegrationTests(academicEducation.getEndDate()))))
                .andExpect(jsonPath("$.abstractM", is(academicEducation.getAbstractM())))
                .andExpect(jsonPath("$.profile.id", is(profile.getId())));
    }
    
    //endregion

    // region PUT /academiceducations/{id}

    @Test
    @DisplayName("should update a academic_education successfully")
    void should_update_a_profile_successfully() throws Exception {
    	AcademicEducation academicEducation = mapper.readValue(BodyRequests.newAcademicEducationRequest(), AcademicEducation.class);
    	Profile profile = mapper.readValue(BodyRequests.newProfileRequest(), Profile.class);
    	
    	profile = profileRepository.save(profile);
    	
    	academicEducation.setProfile(profile);
    	
    	academicEducation = academicEducationRepository.save(academicEducation);
        
        Integer id = academicEducation.getId();
        
        academicEducation = mapper.readValue(BodyRequests.updateAcademicEducationRequest(), AcademicEducation.class);
        
        academicEducation.getProfile().setId(profile.getId());
        
        AcademicEducationDTO resq = (new AcademicEducationDTO()).toDTO(academicEducation);
    	
    	String json = Util.buildJson(resq);

        MockHttpServletRequestBuilder request = put("/v1.0/academiceducations/" + id)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Origin","*");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.educationalInstitution", is(academicEducation.getEducationalInstitution())))
                .andExpect(jsonPath("$.formation", is(academicEducation.getFormation())))
                .andExpect(jsonPath("$.studyArea", is(academicEducation.getStudyArea())))
                .andExpect(jsonPath("$.note", is(academicEducation.getNote())))
                .andExpect(jsonPath("$.activityAndGroups", is(academicEducation.getActivityAndGroups())))
                .andExpect(jsonPath("$.startDate", is(Util.formatDateIntegrationTests(academicEducation.getStartDate()))))
                .andExpect(jsonPath("$.endDate", is(Util.formatDateIntegrationTests(academicEducation.getEndDate()))))
                .andExpect(jsonPath("$.abstractM", is(academicEducation.getAbstractM())))
                .andExpect(jsonPath("$.profile.id", is(profile.getId())));
    }
    
  //endregion
    
    // region delete /academiceducations/{id}

    @Test
    @DisplayName("should delete a academic education successfully")
    void should_delete_a_academic_education_successfully() throws Exception {
    	AcademicEducation academicEducation = mapper.readValue(BodyRequests.newAcademicEducationRequest(), AcademicEducation.class);
    	Profile profile = mapper.readValue(BodyRequests.newProfileRequest(), Profile.class);
    	
    	profile = profileRepository.save(profile);
    	
    	academicEducation.setProfile(profile);
    	
    	academicEducation = academicEducationRepository.save(academicEducation);
        
        Integer id = academicEducation.getId();

        MockHttpServletRequestBuilder request = delete("/v1.0/academiceducations/" + id)
                .header("Origin","*");

        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }
    
  //endregion
    
}
