package br.com.rino.bean.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.rino.util.JSFUtil;

@ManagedBean(name = "utilBean")
@RequestScoped
public class UtilBean {

//	@ManagedProperty(value="#{sequenceBean}") 
//	SequenceBean sequence;
	
	public void redirect(String url) {
		try {
			JSFUtil.redirect(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void initAppAlone(String url){
//		try {
//			JSFUtil.initAppAlone(url);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void initAppSequence(String url){
//		try {
//			sequence.init();
//			JSFUtil.initAppSequence(url);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public Object getSessionObj(String id){
		return JSFUtil.getSessionObj(id);
	}
	
}
