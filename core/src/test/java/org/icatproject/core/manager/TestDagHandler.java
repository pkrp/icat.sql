package org.icatproject.core.manager;

import java.util.HashSet;
import java.util.Set;

import org.icatproject.core.IcatException;
import org.icatproject.core.entity.Datafile;
import org.icatproject.core.entity.DatafileParameter;
import org.icatproject.core.entity.Dataset;
import org.icatproject.core.entity.DatasetParameter;
import org.icatproject.core.entity.EntityBaseBean;
import org.icatproject.core.entity.Investigation;
import org.icatproject.core.entity.User;
import org.icatproject.core.manager.DagHandler.Step;
import org.junit.Test;


public class TestDagHandler {

	@Test
	public void t1() throws Exception {
		Set<Class<? extends EntityBaseBean>> es = new HashSet<Class<? extends EntityBaseBean>>();
		es.add(Investigation.class);
		es.add(Datafile.class);
		es.add(DatasetParameter.class);
		es.add(DatafileParameter.class);
		Step s = DagHandler.fixes(Dataset.class, es);
		System.out.println(s);
	}

	@Test(expected = IcatException.class)
	public void t2() throws Exception {
		Set<Class<? extends EntityBaseBean>> es = new HashSet<Class<? extends EntityBaseBean>>();
		es.add(Investigation.class);
		es.add(Datafile.class);
		es.add(DatasetParameter.class);
		es.add(User.class);
		DagHandler.fixes(Dataset.class, es);
	}

}