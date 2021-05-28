package br.uece.eesdevops.profilems.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import br.uece.eesdevops.profilems.ProfileMsApplication;

public class BodyRequests {
	
    private static ClassLoader classLoader;

    static {
        classLoader = ProfileMsApplication.class.getClassLoader();
    }

    public static String newProfileRequest() throws IOException {
        InputStream stream = classLoader.getResourceAsStream("new-profile-request.json");
        if (stream != null) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException("File new-profile-request.json could not be loaded.");
        }
    }

    public static String updateProfileRequest() throws IOException {
        InputStream stream = classLoader.getResourceAsStream("update-profile-request.json");
        if (stream != null) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException("File update-profile-request.json could not be loaded.");
        }
    }
    
    public static String newExperienceRequest() throws IOException {
        InputStream stream = classLoader.getResourceAsStream("new-experience-request.json");
        if (stream != null) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException("File new-experience-request.json could not be loaded.");
        }
    }

    public static String updateExperienceRequest() throws IOException {
        InputStream stream = classLoader.getResourceAsStream("update-experience-request.json");
        if (stream != null) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException("File update-experience-request.json could not be loaded.");
        }
    }
    
    public static String newAcademicEducationRequest() throws IOException {
        InputStream stream = classLoader.getResourceAsStream("new-academic-education-request.json");
        if (stream != null) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException("File new-academic-education-request.json could not be loaded.");
        }
    }

    public static String updateAcademicEducationRequest() throws IOException {
        InputStream stream = classLoader.getResourceAsStream("update-academic-education-request.json");
        if (stream != null) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException("File update-academic-education-request.json could not be loaded.");
        }
    }
   
}
