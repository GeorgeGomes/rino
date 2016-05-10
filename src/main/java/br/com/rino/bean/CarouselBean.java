package br.com.rino.bean;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.configuration.ConfigurationException;
import org.primefaces.context.RequestContext;
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
	
	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
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
	
	public void saveCarousel(Carousel carousel) {
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext request = RequestContext.getCurrentInstance();
				
		if (!file.getFileName().isEmpty()) {
			try {
				String extension = this.getFile().getContentType().replace("image/", "");
				String fileName = FileUtil.generateUniqueFileName() + "." + extension;

				FileUtil.copyFile(fileName, this.getFile().getInputstream());

				carousel.setNomeImagem(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (carousel.getCodCarousel() == 0) {
			carouselDAO.insert(carousel);
		} else {
			carouselDAO.update(carousel);
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
		context.addMessage("messages", message);

		request.execute("PF('modalEdit').hide();");
		request.update("form:dataTable");
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
