package br.com.rino.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.rino.dao.ConfigGeneralDAO;
import br.com.rino.dao.ConfigPhotoDAO;
import br.com.rino.entity.ConfigGeneral;
import br.com.rino.entity.ConfigPhoto;
import br.com.rino.rxtx.SerialComLeitura;

@ManagedBean(name = "rxtxBean")
@ApplicationScoped
public class RxtxBean {
	
	private ConfigGeneralDAO configGeneralDAO = new ConfigGeneralDAO();
	private ConfigPhotoDAO confPhotoDAO = new ConfigPhotoDAO();
	SerialComLeitura serialEscrita;
	
	public void sendString(String string){
		try{
			FacesContext context = FacesContext.getCurrentInstance();
			
			ConfigGeneral configGeneral = configGeneralDAO.getList().get(0);
			
			serialEscrita = new SerialComLeitura(configGeneral.getPortaSerial().toUpperCase(), 9600, 0);
			serialEscrita.HabilitarEscrita();
			serialEscrita.ObterIdDaPorta();
			serialEscrita.AbrirPorta();
			serialEscrita.EnviarUmaString(string);
			serialEscrita.FecharCom();
			
			System.out.println("String enviada:" + string);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Enviado a string: " + string + ", na porta");
			context.addMessage("messages", message);
		}catch(Exception e){
			System.out.println("Erro RxtxBean.sendString() : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void onLightLed(){
		ConfigPhoto confPhoto = confPhotoDAO.getList().get(0);
		this.sendString(confPhoto.getStringLed());
	}
	
	public void onFlashLed(){
		ConfigPhoto confPhoto = confPhotoDAO.getList().get(0);
		this.sendString(confPhoto.getStringFlash());
	}

	
}
