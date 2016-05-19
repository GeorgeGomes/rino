package com.seismaismais.jsf;

import javax.faces.context.FacesContext;

import org.junit.Test;

public class JSFVersion {

	@Test
	public void test() {
		String version = FacesContext.class.getPackage().getImplementationVersion();
		System.out.println(version);
	}

}
