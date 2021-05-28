package br.uece.eesdevops.profilems.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.uece.eesdevops.profilems.domain.entity.Experience;
import br.uece.eesdevops.profilems.domain.entity.Profile;

public class FakeExperience {
	
	public static Experience fakeExperienceIfNoId() {
		Experience res = new Experience();
		res.setPositionM("Analista de Sistemas");
		res.setJobType("Tempo integral");
		res.setCompany("Nubank");
		res.setLocality("Atlanta - USA");
		res.setCurrentJob(true);
		LocalDate now = LocalDate.of(2020, 1, 9);
		res.setStartDate(now);
		res.setEndDate(null);
		res.setAbstractM("Desenvolvedor Java desde 1969.");
		Profile pr = new Profile();
		pr.setId(1);
		res.setProfile(pr);
		return res;
	}

	public static Experience fakeExperience() {
		Experience res = new Experience();
		res.setId(1);
		res.setPositionM("Analista de Sistemas");
		res.setJobType("Tempo integral");
		res.setCompany("Nubank");
		res.setLocality("Atlanta - USA");
		res.setCurrentJob(true);
		LocalDate now = LocalDate.of(2020, 1, 9);
		res.setStartDate(now);
		res.setEndDate(null);
		res.setAbstractM("Desenvolvedor Java desde 1969.");
		Profile pr = new Profile();
		pr.setId(1);
		res.setProfile(pr);
		return res;
	}
	
	public static List<Experience> fakeExperienceList() {
		List<Experience> list = new ArrayList<Experience>();
		list.add(fakeExperience());
		return list;
	}
}
