package br.uece.eesdevops.profilems.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class AcademicEducation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1654654564548948L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
    @Column(name = "educational_institution", nullable = false)
    private String educationalInstitution;
    
    @Column(name = "formation", nullable = true)
    private String formation;
    
    @Column(name = "study_area", nullable = true)
    private String studyArea;
    
    @Column(name = "note", nullable = true)
    private String note;
    
    @Column(name = "activity_and_groups", nullable = true)
    private String activityAndGroups;
    
    @Column(name = "abstract_m", nullable = false, columnDefinition="TEXT")
    private String abstractM;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "end_date", nullable = true)
	private LocalDate endDate;
    
    @ManyToOne
	@JoinColumn(name = "id_profile", nullable = false, foreignKey = @ForeignKey(name = "co_id_profile_a_e"))
	private Profile profile = new Profile();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

}
