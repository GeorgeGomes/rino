package br.com.rino.bean;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.configuration.ConfigurationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.rino.dao.CarouselDAO;
import br.com.rino.dao.ConfigCarouselDAO;
import br.com.rino.entity.Carousel;
import br.com.rino.entity.ConfigCarousel;
import br.com.rino.util.FileUtil;

@ManagedBean(name = "carouselBean")
@SessionScoped
public class CarouselBean {

	private Carousel carousel = new Carousel();
	private ConfigCarousel configCarousel = new ConfigCarousel();
	private CarouselDAO carouselDAO = new CarouselDAO();
	private ConfigCarouselDAO configCarouselDAO = new ConfigCarouselDAO();
	
	
	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Arquivo adicionado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		RequestContext request = RequestContext.getCurrentInstance();
		
		try {
			String extension = event.getFile().getContentType().replace("image/", "");
			String fileName = FileUtil.generateUniqueFileName() + "." + extension;

			FileUtil.copyFile(fileName, event.getFile().getInputstream());
			
			carousel.setNomeImagem(fileName);
			
			if (carousel.getCodCarousel() == 0) {
				carouselDAO.insert(carousel);
			} else {
				carouselDAO.update(carousel);
			}
			
			request.update("formModalEdit");
			request.update("form:dataTable");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFileUpload() {
		if (FileUtil.deleteFile(carousel.getNomeImagem())) {
			carouselDAO.delete(carousel);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
					"Arquivo excluído com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
			RequestContext request = RequestContext.getCurrentInstance();
			
			request.execute("PF('modalEdit').hide();");
			request.update("form:dataTable");
			
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao deletar arquivo!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void newCarousel() {
		Carousel carousel = new Carousel();
		carousel.setCodCarousel(0l);
		this.setCarousel(carousel);
	}
	
	public void editCarousel(Long codCarousel) {
		this.setCarousel(carouselDAO.edit(codCarousel));
	}
	
	public void deleteCarousel(Carousel carousel) {
		carouselDAO.delete(carousel);
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Registro deletado com sucesso!");
		context.addMessage("messages", message);
	}
	
	public void editConfigCarousel() throws ConfigurationException {
		if(configCarouselDAO.getList() != null && configCarouselDAO.getList().size() > 0){
			this.setConfigCarousel(configCarouselDAO.getList().get(0));
		}else{
			ConfigCarousel configCarousel = new ConfigCarousel();
			configCarousel.setCodConfigCarousel(0l);
			this.setConfigCarousel(configCarousel);
		}
	}
	
	public void saveConfigCarousel(ConfigCarousel configCarousel) throws ConfigurationException {
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext request = RequestContext.getCurrentInstance();
		
		if (configCarousel.getCodConfigCarousel() == 0) {
			configCarouselDAO.insert(configCarousel);
		} else {
			configCarouselDAO.update(configCarousel);
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
		context.addMessage("messages", message);

		request.execute("PF('modalConfig').hide();");
		request.update("form:dataTable");
	}
	
	public List<ConfigCarousel> listConfigCarousel() {
		return configCarouselDAO.getList();
	}
	
	public List<Carousel> listCarousel() {
		return carouselDAO.getList();
	}

	public Carousel getCarousel() {
		return carousel;
	}

	public void setCarousel(Carousel carousel) {
		this.carousel = carousel;
	}

	public ConfigCarousel getConfigCarousel() {
		return configCarousel;
	}

	public void setConfigCarousel(ConfigCarousel configCarousel) {
		this.configCarousel = configCarousel;
	}

}
