package br.com.rino.bean;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import br.com.rino.dao.ConfigPhotoDAO;
import br.com.rino.entity.ConfigPhoto;
import br.com.rino.util.FileUtil;

@Controller("configPhotoBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ConfigPhotoBean {

	private ConfigPhoto configPhoto = new ConfigPhoto();
	private ConfigPhotoDAO configPhotoDAO = new ConfigPhotoDAO();

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Arquivo adicionado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		RequestContext request = RequestContext.getCurrentInstance();

		try {
			String extension = event.getFile().getContentType().replace("image/", "");
			String fileName = FileUtil.generateUniqueFileName() + "." + extension;

			FileUtil.copyFile(fileName, event.getFile().getInputstream());

			configPhoto.setNomeImagemAgradecimento(fileName);
			configPhotoDAO.update(configPhoto);
			request.update("formModalEdit");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void handleFileUploadOffline(FileUploadEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Arquivo adicionado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		RequestContext request = RequestContext.getCurrentInstance();

		try {
			String extension = event.getFile().getContentType().replace("image/", "");
			String fileName = FileUtil.generateUniqueFileName() + "." + extension;

			FileUtil.copyFile(fileName, event.getFile().getInputstream());

			configPhoto.setNomeImagemOffline(fileName);
			configPhotoDAO.update(configPhoto);
			request.update("formModalEdit");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteFileUpload() {
		if (FileUtil.deleteFile(configPhoto.getNomeImagemAgradecimento())) {
			configPhoto.setNomeImagemAgradecimento("");
			configPhotoDAO.update(configPhoto);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
					"Arquivo excluído com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
					"Erro ao deletar arquivo!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void deleteFileUploadOffline() {
		if (FileUtil.deleteFile(configPhoto.getNomeImagemAgradecimento())) {
			configPhoto.setNomeImagemOffline("");
			configPhotoDAO.update(configPhoto);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
					"Arquivo excluído com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
					"Erro ao deletar arquivo!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public ConfigPhoto getConfigPhoto() {
		return configPhoto;
	}

	public void setConfigPhoto(ConfigPhoto configPhoto) {
		this.configPhoto = configPhoto;
	}

	public void newConfigPhoto() {
		ConfigPhoto configPhoto = new ConfigPhoto();
		configPhoto.setCodConfigPhoto(0l);
		this.setConfigPhoto(configPhoto);
	}

	public void editConfigPhoto(Long codConfigPhoto) {
		this.setConfigPhoto(configPhotoDAO.edit(codConfigPhoto));
	}

	public void deleteConfigPhoto(ConfigPhoto configPhoto) {
		configPhotoDAO.delete(configPhoto);

		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Registro deletado com sucesso!");
		context.addMessage("messages", message);
	}

	public void saveConfigPhoto(ConfigPhoto configPhoto) {
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext request = RequestContext.getCurrentInstance();

		if (configPhoto.getCodConfigPhoto() == 0) {
			configPhotoDAO.insert(configPhoto);
		} else {
			configPhotoDAO.update(configPhoto);
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
		context.addMessage("messages", message);

		request.execute("PF('modalEdit').hide();");
		request.update("form:dataTable");
	}

	public List<ConfigPhoto> listConfigPhoto() {
		return configPhotoDAO.getList();
	}

}
