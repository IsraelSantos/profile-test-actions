package br.uece.eesdevops.profilems;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

import br.uece.eesdevops.profilems.domain.entity.Experience;
import br.uece.eesdevops.profilems.domain.entity.Profile;
import br.uece.eesdevops.profilems.repository.AcademicEducationRepository;
import br.uece.eesdevops.profilems.repository.ExperienceRepository;
import br.uece.eesdevops.profilems.repository.ProfileRepository;
import br.uece.eesdevops.profilems.util.BodyRequests;
import br.uece.eesdevops.profilems.util.FakeExperience;
import br.uece.eesdevops.profilems.util.FakeProfile;
import br.uece.eesdevops.profilems.util.Util;
import br.uece.eesdevops.profilems.web.entity.ExperienceDTO;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;


@AutoConfigureMockMvc
@SpringBootTest(classes = ProfileMsApplication.class)
@DisplayName("Runs all tests for experience registration")
@AutoConfigureEmbeddedDatabase
public class ExperienceControllerTests {
	
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
    
    // region GET /experiences

    @Test
    @DisplayName("should get all experiences with no results")
    void should_get_all_experiences_with_no_results() throws Exception {
        mockMvc.perform(get("/v1.0/experiences").header("Origin","*"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)));
    }
    
    @Test
    @DisplayName("should get all experiences with one result")
    void should_get_all_experiences_with_one_result() throws Exception {
    	Experience experience = FakeExperience.fakeExperienceIfNoId();
    	Profile profile = FakeProfile.fakeProfileIfNoId();

        profile = profileRepository.save(profile);
        experience.setProfile(profile);
    	experience = experienceRepository.save(experience);
        
        mockMvc.perform(get("/v1.0/experiences").header("Origin","*"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is(experience.getId())))
                .andExpect(jsonPath("$.content[0].positionM", is(experience.getPositionM())))
                .andExpect(jsonPath("$.content[0].jobType", is(experience.getJobType())))
                .andExpect(jsonPath("$.content[0].company", is(experience.getCompany())))
                .andExpect(jsonPath("$.content[0].locality", is(experience.getLocality())))
                .andExpect(jsonPath("$.content[0].currentJob", is(experience.getCurrentJob())))
                .andExpect(jsonPath("$.content[0].startDate", is(Util.formatDateIntegrationTests(experience.getStartDate()))))
                .andExpect(jsonPath("$.content[0].endDate", is(Util.formatDateIntegrationTests(experience.getEndDate()))))
                .andExpect(jsonPath("$.content[0].abstractM", is(experience.getAbstractM())))
                .andExpect(jsonPath("$.content[0].profile.id", is(profile.getId())));
    }
    
    @Test
    @DisplayName("should get experiences by id profile with one result")
    void should_get_all_experiences_by_id_profile_with_one_result() throws Exception {
    	Experience experience = FakeExperience.fakeExperienceIfNoId();
    	Profile profile = FakeProfile.fakeProfileIfNoId();

        profile = profileRepository.save(profile);
        experience.setProfile(profile);
    	experience = experienceRepository.save(experience);
        
        mockMvc.perform(get("/v1.0/experiences/findbyidprofile/"+profile.getId()).header("Origin","*"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is(experience.getId())))
                .andExpect(jsonPath("$.content[0].positionM", is(experience.getPositionM())))
                .andExpect(jsonPath("$.content[0].jobType", is(experience.getJobType())))
                .andExpect(jsonPath("$.content[0].company", is(experience.getCompany())))
                .andExpect(jsonPath("$.content[0].locality", is(experience.getLocality())))
                .andExpect(jsonPath("$.content[0].currentJob", is(experience.getCurrentJob())))
                .andExpect(jsonPath("$.content[0].startDate", is(Util.formatDateIntegrationTests(experience.getStartDate()))))
                .andExpect(jsonPath("$.content[0].endDate", is(Util.formatDateIntegrationTests(experience.getEndDate()))))
                .andExpect(jsonPath("$.content[0].abstractM", is(experience.getAbstractM())))
                .andExpect(jsonPath("$.content[0].profile.id", is(profile.getId())));
    }
    
    @Test
    @DisplayName("should get a experience")
    void should_get_a_experience() throws Exception {
    	Experience experience = FakeExperience.fakeExperienceIfNoId();
    	Profile profile = FakeProfile.fakeProfileIfNoId();

        profile = profileRepository.save(profile);
        experience.setProfile(profile);
    	experience = experienceRepository.save(experience);
        
        mockMvc.perform(get("/v1.0/experiences/"+experience.getId()).header("Origin","*"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(experience.getId())))
                .andExpect(jsonPath("$.positionM", is(experience.getPositionM())))
                .andExpect(jsonPath("$.jobType", is(experience.getJobType())))
                .andExpect(jsonPath("$.company", is(experience.getCompany())))
                .andExpect(jsonPath("$.locality", is(experience.getLocality())))
                .andExpect(jsonPath("$.currentJob", is(experience.getCurrentJob())))
                .andExpect(jsonPath("$.startDate", is(Util.formatDateIntegrationTests(experience.getStartDate()))))
                .andExpect(jsonPath("$.endDate", is(Util.formatDateIntegrationTests(experience.getEndDate()))))
                .andExpect(jsonPath("$.abstractM", is(experience.getAbstractM())))
                .andExpect(jsonPath("$.profile.id", is(profile.getId())));
    }
    
    // endregion

    // region POST /experiences

    @Test
    @DisplayName("should save a new experience successfully")
    void should_save_new_experience_successfully() throws Exception {
    	Experience experience = mapper.readValue(BodyRequests.newExperienceRequest(), Experience.class);
    	Profile profile = mapper.readValue(BodyRequests.newProfileRequest(), Profile.class);
    	
    	profile = profileRepository.save(profile);
    	
    	experience.setProfile(profile);
    	
    	ExperienceDTO resq = (new ExperienceDTO()).toDTO(experience);
    	
    	String json = Util.buildJson(resq);

        MockHttpServletRequestBuilder request = post("/v1.0/experiences")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Origin","*");

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.positionM", is(experience.getPositionM())))
                .andExpect(jsonPath("$.jobType", is(experience.getJobType())))
                .andExpect(jsonPath("$.company", is(experience.getCompany())))
                .andExpect(jsonPath("$.locality", is(experience.getLocality())))
                .andExpect(jsonPath("$.currentJob", is(experience.getCurrentJob())))
                .andExpect(jsonPath("$.startDate", is(Util.formatDateIntegrationTests(experience.getStartDate()))))
                .andExpect(jsonPath("$.endDate", is(Util.formatDateIntegrationTests(experience.getEndDate()))))
                .andExpect(jsonPath("$.abstractM", is(experience.getAbstractM())))
                .andExpect(jsonPath("$.profile.id", is(profile.getId())));
    }
    
    //endregion

    // region PUT /experiences/{id}

    @Test
    @DisplayName("should update a experience successfully")
    void should_update_a_profile_successfully() throws Exception {
    	Experience experience = mapper.readValue(BodyRequests.newExperienceRequest(), Experience.class);
    	Profile profile = mapper.readValue(BodyRequests.newProfileRequest(), Profile.class);
    	
    	profile = profileRepository.save(profile);
    	
    	experience.setProfile(profile);
    	
    	experience = experienceRepository.save(experience);
        
        Integer id = experience.getId();
        
        experience = mapper.readValue(BodyRequests.updateExperienceRequest(), Experience.class);
        
        experience.getProfile().setId(profile.getId());
        
        ExperienceDTO resq = (new ExperienceDTO()).toDTO(experience);
    	
    	String json = Util.buildJson(resq);

        MockHttpServletRequestBuilder request = put("/v1.0/experiences/" + id)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Origin","*");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.positionM", is(experience.getPositionM())))
                .andExpect(jsonPath("$.jobType", is(experience.getJobType())))
                .andExpect(jsonPath("$.company", is(experience.getCompany())))
                .andExpect(jsonPath("$.locality", is(experience.getLocality())))
                .andExpect(jsonPath("$.currentJob", is(experience.getCurrentJob())))
                .andExpect(jsonPath("$.startDate", is(Util.formatDateIntegrationTests(experience.getStartDate()))))
                .andExpect(jsonPath("$.endDate", is(Util.formatDateIntegrationTests(experience.getEndDate()))))
                .andExpect(jsonPath("$.abstractM", is(experience.getAbstractM())))
                .andExpect(jsonPath("$.profile.id", is(profile.getId())));
    }
    
  //endregion
    
    // region delete /experiences/{id}

    @Test
    @DisplayName("should delete a experience successfully")
    void should_delete_a_experience_successfully() throws Exception {
    	Experience experience = mapper.readValue(BodyRequests.newExperienceRequest(), Experience.class);
    	Profile profile = mapper.readValue(BodyRequests.newProfileRequest(), Profile.class);
    	
    	profile = profileRepository.save(profile);
    	
    	experience.setProfile(profile);
    	
    	experience = experienceRepository.save(experience);
        
        Integer id = experience.getId();

        MockHttpServletRequestBuilder request = delete("/v1.0/experiences/" + id)
                .header("Origin","*");

        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }
    
  //endregion
    
}
