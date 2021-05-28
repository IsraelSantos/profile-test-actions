package br.uece.eesdevops.profilems.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.uece.eesdevops.profilems.domain.entity.AcademicEducation;
import br.uece.eesdevops.profilems.domain.entity.Profile;

public class FakeAcademicEducation {
	
	public static AcademicEducation fakeAcademicEducationIfNoId() {
		AcademicEducation res = new AcademicEducation();
		res.setEducationalInstitution("UECE");
		res.setFormation("Mestrado");
		res.setStudyArea("Ciência da Computação");
		res.setNote("Ênfase na área de engenharia de software.");
		res.setActivityAndGroups(null);
		LocalDate now = LocalDate.of(2020, 1, 9);
		res.setStartDate(now);
		res.setEndDate(null);
		res.setAbstractM("Estudos relacionados a engenharia de software e inteligência artificial.");
		Profile pr = new Profile();
		pr.setId(1);
		res.setProfile(pr);
		return res;
	}

	public static AcademicEducation fakeAcademicEducation() {
		AcademicEducation res = new AcademicEducation();
		res.setId(1);
		res.setEducationalInstitution("UECE");
		res.setFormation("Mestrado");
		res.setStudyArea("Ciência da Computação");
		res.setNote("Ênfase na área de engenharia de software.");
		res.setActivityAndGroups(null);
		LocalDate now = LocalDate.of(2020, 1, 9);
		res.setStartDate(now);
		res.setEndDate(null);
		res.setAbstractM("Estudos relacionados a engenharia de software e inteligência artificial.");
		Profile pr = new Profile();
		pr.setId(1);
		res.setProfile(pr);
		return res;
	}
	
	public static List<AcademicEducation> fakeAcademicEducationList() {
		List<AcademicEducation> list = new ArrayList<AcademicEducation>();
		list.add(fakeAcademicEducation());
		return list;
	}
}
