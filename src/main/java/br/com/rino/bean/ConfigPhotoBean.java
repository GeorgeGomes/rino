package br.com.rino.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import br.com.rino.dao.ConfigPhotoDAO;
import br.com.rino.entity.ConfigPhoto;

@ManagedBean(name = "configPhotoBean")
@SessionScoped
public class ConfigPhotoBean {

	private ConfigPhoto configPhoto = new ConfigPhoto();
	private ConfigPhotoDAO configPhotoDAO = new ConfigPhotoDAO();
	private String image;
	private Part fileImagemAgradecimento;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Part getFileImagemAgradecimento() {
		return fileImagemAgradecimento;
	}

	public void setFileImagemAgradecimento(Part fileImagemAgradecimento) {
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

	public void handleFileUpload(FileUploadEvent event){
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
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

//		if (!this.getFileImagemAgradecimento().) {
//			try {
//				String extension = this.getFileImagemAgradecimento().getContentType().replace("image/", "");
//				String fileName = FileUtil.generateUniqueFileName() + "." + extension;
//
//				FileUtil.copyFile(fileName, this.getFileImagemAgradecimento().getInputstream());
//
//				configPhoto.setNomeImagemAgradecimento(fileName);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

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
