package br.uece.eesdevops.profilems.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.uece.eesdevops.profilems.domain.entity.AcademicEducation;

@Repository
public interface AcademicEducationRepository extends JpaRepository<AcademicEducation, Integer>{
	
	@Query(value = " select ae from AcademicEducation ae where ae.profile.id = :idProfile order by ae.startDate ")
	Page<AcademicEducation> findByIdProfileOrderByDateStart(@Param("idProfile") Integer idProfile, Pageable pageable);
	
}
