package br.uece.eesdevops.profilems.web.entity;

import java.io.Serializable;

import br.uece.eesdevops.profilems.domain.entity.Profile;

public class ProfileDTO implements DTO<Profile, ProfileDTO>, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5640361436173465668L;

	private String abstractM;
	
	private Integer idUser;
	
	public String getAbstractM() {
		return abstractM;
	}

	public void setAbstractM(String abstractM) {
		this.abstractM = abstractM;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	@Override
	public Profile toDomain() {
		Profile res = new Profile();
		res.setAbstractM(this.abstractM);
		res.setIdUser(this.idUser);
		return res;
	}

	@Override
	public ProfileDTO toDTO(Profile entity) {
		ProfileDTO res = new ProfileDTO();
		res.setAbstractM(entity.getAbstractM());
		res.setIdUser(entity.getIdUser());
		return res;
	}
}
