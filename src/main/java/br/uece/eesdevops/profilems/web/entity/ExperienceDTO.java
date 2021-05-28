package br.uece.eesdevops.profilems.web.entity;

import java.time.LocalDate;

import br.uece.eesdevops.profilems.domain.entity.Experience;
import br.uece.eesdevops.profilems.domain.entity.Profile;

public class ExperienceDTO implements DTO<Experience, ExperienceDTO>{

    private String positionM;
    
    private String jobType;
    
    private String company;
    
    private String locality;
    
    private Boolean currentJob;
    
	private LocalDate startDate;
    
	private LocalDate endDate;
    
    private String abstractM;
    
    private Profile profile;

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

	@Override
	public Experience toDomain() {
		Experience res = new Experience();
		res.setPositionM(this.positionM);
		res.setJobType(this.jobType);
		res.setCompany(this.company);
		res.setLocality(this.locality);
		res.setCurrentJob(this.currentJob);
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
	public ExperienceDTO toDTO(Experience entity) {
		ExperienceDTO res = new ExperienceDTO();
		res.setPositionM(entity.getPositionM());
		res.setJobType(entity.getJobType());
		res.setCompany(entity.getCompany());
		res.setLocality(entity.getLocality());
		res.setCurrentJob(entity.getCurrentJob());
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
