package br.com.rino.bean;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import br.com.rino.dao.ConfigPhotoDAO;
import br.com.rino.entity.ConfigPhoto;
import br.com.rino.util.FileUtil;

@ManagedBean(name = "configPhotoBean")
@SessionScoped
public class ConfigPhotoBean {

	private ConfigPhoto configPhoto = new ConfigPhoto();
	private ConfigPhotoDAO configPhotoDAO = new ConfigPhotoDAO();
	private String image;
	private UploadedFile fileImagemOnline;
	private UploadedFile fileImagemOffline;
	private UploadedFile fileImagemAgradecimento;
	private UploadedFile file;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public UploadedFile getFileImagemOnline() {
		return fileImagemOnline;
	}

	public void setFileImagemOnline(UploadedFile fileImagemOnline) {
		this.fileImagemOnline = fileImagemOnline;
	}

	public UploadedFile getFileImagemOffline() {
		return fileImagemOffline;
	}

	public void setFileImagemOffline(UploadedFile fileImagemOffline) {
		this.fileImagemOffline = fileImagemOffline;
	}

	public UploadedFile getFileImagemAgradecimento() {
		return fileImagemAgradecimento;
	}

	public void setFileImagemAgradecimento(UploadedFile fileImagemAgradecimento) {
		this.fileImagemAgradecimento = fileImagemAgradecimento;
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

		if (!this.getFileImagemOnline().getFileName().isEmpty()) {
			try {
				String extension = this.getFileImagemOnline().getContentType().replace("image/", "");
				String fileName = FileUtil.generateUniqueFileName() + "." + extension;

				FileUtil.copyFile(fileName, this.getFileImagemOnline().getInputstream());

				configPhoto.setNomeImagemOnline(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (!this.getFileImagemOffline().getFileName().isEmpty()) {
			try {
				String extension = this.getFileImagemOffline().getContentType().replace("image/", "");
				String fileName = FileUtil.generateUniqueFileName() + "." + extension;

				FileUtil.copyFile(fileName, this.getFileImagemOffline().getInputstream());

				configPhoto.setNomeImagemOffline(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (!this.getFileImagemAgradecimento().getFileName().isEmpty()) {
			try {
				String extension = this.getFileImagemAgradecimento().getContentType().replace("image/", "");
				String fileName = FileUtil.generateUniqueFileName() + "." + extension;

				FileUtil.copyFile(fileName, this.getFileImagemAgradecimento().getInputstream());

				configPhoto.setNomeImagemAgradecimento(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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
