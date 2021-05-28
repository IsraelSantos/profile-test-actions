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
public class Experience implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1587942168559958293L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "position_m", nullable = false)
    private String positionM;
    
    @Column(name = "job_type", nullable = false)
    private String jobType;
    
    @Column(name = "company", nullable = false)
    private String company;
    
    @Column(name = "locality", nullable = false)
    private String locality;
    
    @Column (name = "current_job", nullable = false)
    private Boolean currentJob;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "end_date", nullable = true)
	private LocalDate endDate;
    
    @Column(name = "abstract_m", nullable = false, columnDefinition="TEXT")
    private String abstractM;
    
    @ManyToOne
	@JoinColumn(name = "id_profile", nullable = false, foreignKey = @ForeignKey(name = "co_id_profile"))
	private Profile profile = new Profile();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPositionM() {
		return positionM;
	}

	public void setPositionM(String positionM) {
		this.positionM = positionM;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public Boolean getCurrentJob() {
		return currentJob;
	}

	public void setCurrentJob(Boolean currentJob) {
		this.currentJob = currentJob;
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

	public String getAbstractM() {
		return abstractM;
	}

	public void setAbstractM(String abstractM) {
		this.abstractM = abstractM;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
    
}
