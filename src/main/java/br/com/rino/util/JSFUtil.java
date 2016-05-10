package br.com.rino.util;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class JSFUtil {

	public static Object getSessionObj(String id) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(id);
	}

	public static void setSessionObj(String id, Object obj) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(id, obj);
	}
	
	public static void redirect(String url) {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initAppAlone(String url){
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			setSessionObj("type", "alone");
			context.redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initAppSequence(String url){
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			setSessionObj("type", null);
			context.redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
