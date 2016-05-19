package br.com.rino.bean;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.configuration.ConfigurationException;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import br.com.rino.dao.ConfigPanelDAO;
import br.com.rino.dao.PanelDAO;
import br.com.rino.entity.ConfigPanel;
import br.com.rino.entity.Panel;
import br.com.rino.util.FileUtil;

@ManagedBean(name = "panelBean")
@ViewScoped
public class PanelBean {

	private Panel panel = new Panel();
	private ConfigPanel configPanel = new ConfigPanel();
	private PanelDAO panelDAO = new PanelDAO();
	private ConfigPanelDAO configPanelDAO = new ConfigPanelDAO();
	private UploadedFile file;
	private UploadedFile fileConfigInit;
	private UploadedFile fileConfigEnd;

	public String testButtonAction() {
		System.out.println("testButtonAction invoked");
		return "anotherPage.xhtml";
	}

	public void testButtonActionListener(ActionEvent event) {
		System.out.println("testButtonActionListener invoked");
	}

	public UploadedFile getFileConfigInit() {
		return fileConfigInit;
	}

	public void setFileConfigInit(UploadedFile fileConfigInit) {
		this.fileConfigInit = fileConfigInit;
	}

	public UploadedFile getFileConfigEnd() {
		return fileConfigEnd;
	}

	public void setFileConfigEnd(UploadedFile fileConfigEnd) {
		this.fileConfigEnd = fileConfigEnd;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
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

		if (!file.getFileName().isEmpty()) {
			try {
				String extension = this.getFile().getContentType().replace("image/", "");
				String fileName = FileUtil.generateUniqueFileName() + "." + extension;

				FileUtil.copyFile(fileName, this.getFile().getInputstream());

				panel.setNomeImagem(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (panel.getCodPanel() == 0) {
			panelDAO.insert(panel);
		} else {
			panelDAO.update(panel);
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
		context.addMessage("messages", message);

		request.execute("PF('modalEdit').hide();");
		request.update("form:dataTable");
	}
	
	public void savePanel() {
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext request = RequestContext.getCurrentInstance();

		if (!file.getFileName().isEmpty()) {
			try {
				String extension = this.getFile().getContentType().replace("image/", "");
				String fileName = FileUtil.generateUniqueFileName() + "." + extension;

				FileUtil.copyFile(fileName, this.getFile().getInputstream());

				panel.setNomeImagem(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (panel.getCodPanel() == 0) {
			panelDAO.insert(panel);
		} else {
			panelDAO.update(panel);
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
		context.addMessage("messages", message);

		request.execute("PF('modalEdit').hide();");
		request.update("form:dataTable");
	}

	public void saveConfigPanel(ConfigPanel configPanel) throws ConfigurationException {
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext request = RequestContext.getCurrentInstance();

		if (!fileConfigInit.getFileName().isEmpty()) {
			try {
				String extension = this.getFileConfigInit().getContentType().replace("image/", "");
				String fileName = FileUtil.generateUniqueFileName() + "." + extension;

				FileUtil.copyFile(fileName, this.getFileConfigInit().getInputstream());

				configPanel.setNomeImagemInit(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (!fileConfigEnd.getFileName().isEmpty()) {
			try {
				String extension = this.getFileConfigEnd().getContentType().replace("image/", "");
				String fileName = FileUtil.generateUniqueFileName() + "." + extension;

				FileUtil.copyFile(fileName, this.getFileConfigEnd().getInputstream());

				configPanel.setNomeImagemEnd(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (configPanel.getCodConfigPanel() == 0) {
			configPanelDAO.insert(configPanel);
		} else {
			configPanelDAO.update(configPanel);
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
		context.addMessage("messages", message);

		request.execute("PF('modalConfig').hide();");
		request.update("form:dataTable");
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
