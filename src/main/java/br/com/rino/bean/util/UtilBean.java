package br.com.rino.bean.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
	
	public void deleteAllCoockies(){
		ChromeOptions options = new ChromeOptions();
		options.setBinary("/Users/george.gomes/AppData/Local/Google/Chrome/Application/chrome.exe");
		System.setProperty("webdriver.chrome.driver", "/rino/chromedriver.exe");
	  
		WebDriver driver = new ChromeDriver(options);
		
		Dimension windowMinSize = new Dimension(0,0);
		driver.manage().window().setSize(windowMinSize);
		driver.manage().deleteAllCookies();
		driver.quit();
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
