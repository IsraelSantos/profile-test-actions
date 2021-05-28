package br.uece.eesdevops.profilems.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Profile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1934302338959958293L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "abstract_m", nullable = false, columnDefinition="TEXT")
    private String abstractM;
    
    @Column(name = "id_user", nullable = false, unique = true)
    private Integer idUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
	
}
