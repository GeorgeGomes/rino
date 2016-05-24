package br.com.rino.bean;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.configuration.ConfigurationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import br.com.rino.dao.ConfigPanelDAO;
import br.com.rino.dao.PanelDAO;
import br.com.rino.entity.ConfigPanel;
import br.com.rino.entity.Panel;
import br.com.rino.util.FileUtil;

@Controller("panelBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PanelBean {

	private Panel panel = new Panel();
	private ConfigPanel configPanel = new ConfigPanel();
	
	private PanelDAO panelDAO = new PanelDAO();
	private ConfigPanelDAO configPanelDAO = new ConfigPanelDAO();

	
	public void handleFileUploadInit(FileUploadEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Arquivo adicionado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		RequestContext request = RequestContext.getCurrentInstance();
		
		try {
			String extension = event.getFile().getContentType().replace("image/", "");
			String fileName = FileUtil.generateUniqueFileName() + "." + extension;

			FileUtil.copyFile(fileName, event.getFile().getInputstream());
			
			configPanel.setNomeImagemInit(fileName);
			configPanelDAO.update(configPanel);
			request.update("formModalConfig");
			request.update("form:dataTable");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFileUploadInit() {
		if (FileUtil.deleteFile(configPanel.getNomeImagemInit())) {
			configPanel.setNomeImagemInit("");
			configPanelDAO.update(configPanel);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
					"Arquivo excluído com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
			RequestContext request = RequestContext.getCurrentInstance();
			
			request.update("form:dataTable");
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao deletar arquivo!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void handleFileUploadEnd(FileUploadEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Arquivo adicionado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		RequestContext request = RequestContext.getCurrentInstance();
		
		try {
			String extension = event.getFile().getContentType().replace("image/", "");
			String fileName = FileUtil.generateUniqueFileName() + "." + extension;

			FileUtil.copyFile(fileName, event.getFile().getInputstream());
			
			configPanel.setNomeImagemEnd(fileName);
			configPanelDAO.update(configPanel);
			request.update("formModalConfig");
			request.update("form:dataTable");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Arquivo adicionado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		RequestContext request = RequestContext.getCurrentInstance();
		
		try {
			String extension = event.getFile().getContentType().replace("image/", "");
			String fileName = FileUtil.generateUniqueFileName() + "." + extension;

			FileUtil.copyFile(fileName, event.getFile().getInputstream());
			
			panel.setNomeImagem(fileName);
			panelDAO.update(panel);
			request.update("formModalEdit");
			request.update("form:dataTable");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFileUploadEnd() {
		if (FileUtil.deleteFile(configPanel.getNomeImagemInit())) {
			configPanel.setNomeImagemEnd("");
			configPanelDAO.update(configPanel);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
					"Arquivo excluído com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
			RequestContext request = RequestContext.getCurrentInstance();
			
			request.update("form:dataTable");
			
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao deletar arquivo!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}


	
	public void deleteFileUpload() {
		if (FileUtil.deleteFile(panel.getNomeImagem())) {
			panel.setNomeImagem("");
			panelDAO.update(panel);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
					"Arquivo excluído com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
			RequestContext request = RequestContext.getCurrentInstance();
			
			request.update("form:dataTable");
			
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao deletar arquivo!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public ConfigPanel getConfigPanel() {
		return configPanel;
	}

	public void setConfigPanel(ConfigPanel configPanel) {
		this.configPanel = configPanel;
	}

	public void newPanel() {
		Panel panel = new Panel();
		panel.setCodPanel(0l);
		this.setPanel(panel);
	}

	public void editPanel(Long codPanel) {
		this.setPanel(panelDAO.edit(codPanel));
	}

	public void editConfigPanel() throws ConfigurationException {
		if (configPanelDAO.getList() != null && configPanelDAO.getList().size() > 0) {
			this.setConfigPanel(configPanelDAO.getList().get(0));
		} else {
			ConfigPanel ConfigPanel = new ConfigPanel();
			ConfigPanel.setCodConfigPanel(0l);
			this.setConfigPanel(ConfigPanel);
		}
	}

	public void deletePanel(Panel panel) {
		panelDAO.delete(panel);
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Registro deletado com sucesso!");
		context.addMessage("messages", message);
	}

	public void savePanel(Panel panel) {
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext request = RequestContext.getCurrentInstance();

		if (panel.getCodPanel() == 0) {
			panelDAO.insert(panel);
		} else {
			panelDAO.update(panel);
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
		context.addMessage("messages", message);

		//request.execute("PF('modalEdit').hide();");
		request.update("form:dataTable");
		request.update("messages");
	}

	public void savePanel_DELETE() {
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext request = RequestContext.getCurrentInstance();

		if (panel.getCodPanel() == 0) {
			panelDAO.insert(panel);
		} else {
			panelDAO.update(panel);
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
		context.addMessage("messages", message);

		//request.execute("PF('modalEdit').hide();");
		request.update("form:dataTable");
		request.update("messages");
	}

	public void saveConfigPanel(ConfigPanel configPanel) throws ConfigurationException {
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext request = RequestContext.getCurrentInstance();

		if (configPanel.getCodConfigPanel() == 0) {
			configPanelDAO.insert(configPanel);
		} else {
			configPanelDAO.update(configPanel);
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
		context.addMessage("messages", message);

		//request.execute("PF('modalEdit').hide();");
		//request.update("form:dataTable");
		request.update("messages");
	}

	public List<Panel> listPanel() {
		return panelDAO.getList();
	}

	public List<ConfigPanel> listConfigPanel() {
		return configPanelDAO.getList();
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}

}
