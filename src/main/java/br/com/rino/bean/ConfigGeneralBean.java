package br.com.rino.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.rino.dao.ConfigGeneralDAO;
import br.com.rino.entity.ConfigGeneral;

@ManagedBean(name="configGeneralBean")
@SessionScoped
public class ConfigGeneralBean {

	private ConfigGeneral configGeneral = new ConfigGeneral();
	private ConfigGeneralDAO configGeneralDAO = new ConfigGeneralDAO();
	
	
	public void newConfigGeneral() {
		ConfigGeneral configGeneral = new ConfigGeneral();
		configGeneral.setCodConfig(0l);
		this.setConfigGeneral(configGeneral);
	}

	public void editConfigGeneral(Long codConfigGeneral) {
		this.setConfigGeneral(configGeneralDAO.edit(codConfigGeneral));
	}
	
	public void deleteConfigGeneral(ConfigGeneral configGeneral) {
		configGeneralDAO.delete(configGeneral);
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Registro deletado com sucesso!");
		context.addMessage("messages", message);
	}
	
	public void saveConfigGeneral(ConfigGeneral configGeneral) {
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext request = RequestContext.getCurrentInstance();
				
		if (configGeneral.getCodConfig() == 0) {
			configGeneralDAO.insert(configGeneral);
		} else {
			configGeneralDAO.update(configGeneral);
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
		context.addMessage("messages", message);

		request.execute("PF('modalEdit').hide();");
		request.update("form:dataTable");
	}

	public List<ConfigGeneral> listConfigGeneral() {
		return configGeneralDAO.getList();
	}

	public ConfigGeneral getConfigGeneral() {
		return configGeneral;
	}

	public void setConfigGeneral(ConfigGeneral configGeneral) {
		this.configGeneral = configGeneral;
	}
	
	
	
}
