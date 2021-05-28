package br.uece.eesdevops.profilems.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.uece.eesdevops.profilems.domain.entity.Experience;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer>{
	
	@Query(value = " select exp from Experience exp where exp.profile.id = :idProfile order by exp.startDate ")
	Page<Experience> findByIdProfileOrderByDateStart(@Param("idProfile") Integer idProfile, Pageable pageable);
	
}
