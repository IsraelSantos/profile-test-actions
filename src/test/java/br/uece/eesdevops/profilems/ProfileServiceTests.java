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

import br.uece.eesdevops.profilems.domain.entity.Profile;
import br.uece.eesdevops.profilems.domain.exception.NotFoundException;
import br.uece.eesdevops.profilems.domain.service.ProfileService;
import br.uece.eesdevops.profilems.domain.service.impl.ProfileServiceImpl;
import br.uece.eesdevops.profilems.repository.ProfileRepository;
import br.uece.eesdevops.profilems.util.FakeProfile;


@DisplayName("Runs all tests for domain service class responsible for change a profile")
public class ProfileServiceTests {
	
	private final ProfileRepository profileRepository = 
			mock(ProfileRepository.class);

    private ProfileService service;

    @BeforeEach
    void beforeEach() {
        service = new ProfileServiceImpl(this.profileRepository);
    }

    @Test
    @DisplayName("should save a profile to returned status successfully")
    void should_save_a_profile_to_returned_status_successfully() {
        Profile profile = FakeProfile.fakeProfileIfNoId();
        
        when(profileRepository.save(profile))
        		.thenReturn(FakeProfile.fakeProfile());

        Profile tmp = service.save(profile);

        assertNotEquals(null, tmp.getId());

        assertEquals(profile.getAbstractM(), tmp.getAbstractM());
        assertEquals(profile.getIdUser(), tmp.getIdUser());

        verify(profileRepository).save(profile);
    }
    
    @Test
    @DisplayName("should return not found exception to returned status successfully")
    void should_return_not_found_exception_to_returned_status_successfully() {
        Profile profile = FakeProfile.fakeProfile();
        
        when(profileRepository.findById(profile.getId()))
        		.thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
        	service.save(profile);
        });

        verify(profileRepository).findById(profile.getId());
    }
    

}
