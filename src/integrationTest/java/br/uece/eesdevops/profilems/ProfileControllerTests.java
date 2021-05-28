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

import br.uece.eesdevops.profilems.domain.entity.Profile;
import br.uece.eesdevops.profilems.repository.AcademicEducationRepository;
import br.uece.eesdevops.profilems.repository.ExperienceRepository;
import br.uece.eesdevops.profilems.repository.ProfileRepository;
import br.uece.eesdevops.profilems.util.BodyRequests;
import br.uece.eesdevops.profilems.util.FakeProfile;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;


@AutoConfigureMockMvc
@SpringBootTest(classes = ProfileMsApplication.class)
@DisplayName("Runs all tests for profile registration")
@AutoConfigureEmbeddedDatabase
public class ProfileControllerTests {
	
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
    
    // region GET /profiles

    @Test
    @DisplayName("should get all profiles with no results")
    void should_get_all_profiles_with_no_results() throws Exception {
        mockMvc.perform(get("/v1.0/profiles").header("Origin","*"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)));
    }
    
    @Test
    @DisplayName("should get all profiles with one result")
    void should_get_all_profiles_with_one_result() throws Exception {
        Profile profile = FakeProfile.fakeProfileIfNoId();

        profile = profileRepository.save(profile);
        
        mockMvc.perform(get("/v1.0/profiles").header("Origin","*"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is(profile.getId())))
                .andExpect(jsonPath("$.content[0].abstractM", is(profile.getAbstractM())))
                .andExpect(jsonPath("$.content[0].idUser", is(profile.getIdUser())));
    }
    
    @Test
    @DisplayName("should get a profile")
    void should_get_a_profile() throws Exception {
        Profile profile = FakeProfile.fakeProfileIfNoId();

        profile = profileRepository.save(profile);
        
        mockMvc.perform(get("/v1.0/profiles/"+profile.getId()).header("Origin","*"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(profile.getId())))
                .andExpect(jsonPath("$.abstractM", is(profile.getAbstractM())))
                .andExpect(jsonPath("$.idUser", is(profile.getIdUser())));
    }
    
    @Test
    @DisplayName("should get a profile by id user")
    void should_get_a_profile_by_id_user() throws Exception {
        Profile profile = FakeProfile.fakeProfileIfNoId();

        profile = profileRepository.save(profile);
        
        mockMvc.perform(get("/v1.0/profiles/findbyiduser/"+profile.getIdUser()).header("Origin","*"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(profile.getId())))
                .andExpect(jsonPath("$.abstractM", is(profile.getAbstractM())))
                .andExpect(jsonPath("$.idUser", is(profile.getIdUser())));
    }
    
    // endregion

    // region POST /profiles

    @Test
    @DisplayName("should save a new profile successfully")
    void should_save_new_profile_successfully() throws Exception {
        Profile profile = mapper.readValue(BodyRequests.newProfileRequest(), Profile.class);

        MockHttpServletRequestBuilder request = post("/v1.0/profiles")
                .content(BodyRequests.newProfileRequest())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Origin","*");

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.abstractM", is(profile.getAbstractM())))
                .andExpect(jsonPath("$.idUser", is(profile.getIdUser())));
    }
    
    //endregion

    // region PUT /profiles/{id}

    @Test
    @DisplayName("should update a profile successfully")
    void should_update_a_profile_successfully() throws Exception {
        Profile profile = mapper.readValue(BodyRequests.newProfileRequest(), Profile.class);

        profile = profileRepository.save(profile);
        
        Integer id = profile.getId();
        
        profile = mapper.readValue(BodyRequests.updateProfileRequest(), Profile.class);

        MockHttpServletRequestBuilder request = put("/v1.0/profiles/" + id)
                .content(BodyRequests.updateProfileRequest())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Origin","*");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.abstractM", is(profile.getAbstractM())))
                .andExpect(jsonPath("$.idUser", is(profile.getIdUser())));
    }
    
  //endregion
    
    // region delete /profiles/{id}

    @Test
    @DisplayName("should delete a profile successfully")
    void should_delete_a_profile_successfully() throws Exception {
        Profile profile = mapper.readValue(BodyRequests.newProfileRequest(), Profile.class);

        profile = profileRepository.save(profile);
        
        Integer id = profile.getId();

        MockHttpServletRequestBuilder request = delete("/v1.0/profiles/" + id)
                .header("Origin","*");

        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }
    
  //endregion
    
}
