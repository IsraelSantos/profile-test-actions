package br.uece.eesdevops.profilems.util;

import java.util.ArrayList;
import java.util.List;

import br.uece.eesdevops.profilems.domain.entity.Profile;

public class FakeProfile {
	
	public static Profile fakeProfileIfNoId() {
		Profile m = new Profile();
		m.setId(null);
		m.setAbstractM("Desenvolvidor Java desde 2000.");
		m.setIdUser(1);
		return m;
	}

	public static Profile fakeProfile() {
		Profile m = new Profile();
		m.setId(new Integer(1));
		m.setAbstractM("Desenvolvidor Java desde 2000.");
		m.setIdUser(1);
		return m;
	}
	
	public static List<Profile> fakeProfileList() {
		List<Profile> list = new ArrayList<Profile>();
		list.add(fakeProfile());
		return list;
	}
}
