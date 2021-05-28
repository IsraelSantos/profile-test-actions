package br.uece.eesdevops.profilems.web.entity;

import java.io.Serializable;
import java.time.LocalDate;

import br.uece.eesdevops.profilems.domain.entity.AcademicEducation;
import br.uece.eesdevops.profilems.domain.entity.Profile;

public class AcademicEducationDTO implements DTO<AcademicEducation, AcademicEducationDTO>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 16445487457845L;
	
    private String educationalInstitution;
    
    private String formation;
    
    private String studyArea;
    
    private String note;
    
    private String activityAndGroups;
    
    private String abstractM;
	
	private LocalDate startDate;
    
	private LocalDate endDate;
    
	private Profile profile = new Profile();

	public String getEducationalInstitution() {
		return educationalInstitution;
	}

	public void setEducationalInstitution(String educationalInstitution) {
		this.educationalInstitution = educationalInstitution;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public String getStudyArea() {
		return studyArea;
	}

	public void setStudyArea(String studyArea) {
		this.studyArea = studyArea;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getActivityAndGroups() {
		return activityAndGroups;
	}

	public void setActivityAndGroups(String activityAndGroups) {
		this.activityAndGroups = activityAndGroups;
	}

	public String getAbstractM() {
		return abstractM;
	}

	public void setAbstractM(String abstractM) {
		this.abstractM = abstractM;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public AcademicEducation toDomain() {
		AcademicEducation res = new AcademicEducation();
		res.setEducationalInstitution(this.educationalInstitution);
		res.setFormation(this.formation);
		res.setStudyArea(this.studyArea);
		res.setNote(this.note);
		res.setActivityAndGroups(this.activityAndGroups);
		res.setStartDate(this.startDate);
		res.setEndDate(this.endDate);
		res.setAbstractM(this.abstractM);
		Profile pr = new Profile();
		if (profile != null) {
			pr.setId(profile.getId());
		}
		res.setProfile(pr);
		return res;
	}

	@Override
	public AcademicEducationDTO toDTO(AcademicEducation entity) {
		AcademicEducationDTO res = new AcademicEducationDTO();
		res.setEducationalInstitution(entity.getEducationalInstitution());
		res.setFormation(entity.getFormation());
		res.setStudyArea(entity.getStudyArea());
		res.setNote(entity.getNote());
		res.setActivityAndGroups(entity.getActivityAndGroups());
		res.setStartDate(entity.getStartDate());
		res.setEndDate(entity.getEndDate());
		res.setAbstractM(entity.getAbstractM());
		Profile pr = new Profile();
		if (entity.getProfile() != null) {
			pr.setId(entity.getProfile().getId());
		}
		res.setProfile(pr);
		return res;
	}

}
