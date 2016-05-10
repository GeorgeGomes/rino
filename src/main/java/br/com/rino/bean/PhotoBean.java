package br.com.rino.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.xml.bind.DatatypeConverter;

import br.com.rino.dao.ConfigGeneralDAO;
import br.com.rino.dao.ConfigPhotoDAO;
import br.com.rino.dao.PhotoDAO;
import br.com.rino.entity.ConfigPhoto;
import br.com.rino.entity.Photo;
import br.com.rino.util.FileUtil;
import br.com.rino.util.JSFUtil;

@ManagedBean(name = "photoBean")
@SessionScoped
public class PhotoBean {

	private Photo photo = new Photo();
	private PhotoDAO photoDAO = new PhotoDAO();
	private ConfigPhotoDAO configPhotoDAO = new ConfigPhotoDAO();
	private ConfigGeneralDAO configGeneralDAO = new ConfigGeneralDAO();
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void editPhoto(String nomeFoto) {
		this.setPhoto(photoDAO.edit(nomeFoto));
	}

	public void transferAll() throws IOException, AddressException, MessagingException {
		List<Photo> photoList = this.listPhoto();
		if (photoList != null) {
			Iterator<Photo> photoIterator = photoList.iterator();
			while (photoIterator.hasNext()) {
				Photo photo = photoIterator.next();
				this.transferOffline(photo);
			}
		}
	}

	public void transferOnline(Photo p) throws IOException {
		transfer(p);
	}

	public void transferOffline(Photo p) throws IOException, AddressException, MessagingException {
		transfer(p);
	}

	public void transfer(Photo photo) throws IOException {
		try {
			ConfigPhoto configPhoto = configPhotoDAO.getList().get(0);

			URL url = new URL(configPhoto.getLinkTransfer());
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			String fileBase64 = FileUtil.convertByteToBase64(photo.getFile());

			writer.write("nomeEvento=" + photo.getNomeEvento());
			writer.write("&");
			writer.write("descricaoEvento=" + photo.getDescricaoEvento());
			writer.write("&");
			writer.write("nomeFoto=" + photo.getNomeFoto());
			writer.write("&");
			writer.write("email=" + photo.getEmail());
			writer.write("&");
			writer.write("pendente=SIM");
			writer.write("&");
			writer.write("image=" + fileBase64);
			writer.flush();

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String retorno = reader.readLine();

			if (retorno != null && retorno.trim().equalsIgnoreCase("SUCCESS")) {
				photoDAO.delete(photo);

				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
						"Imagem transferida com sucesso para o ambiente online!");
				context.addMessage("messages", message);
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
						"Ocorreu algum problema ao transferir a imagem. (Verifique a conex�o)");
				context.addMessage("messages", message);
			}

			writer.close();
			reader.close();

		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
					"Ocorreu algum problema ao transferir a imagem. (Verifique a conex�o)");
			context.addMessage("messages", message);
		}

	}

	public void saveEmail(String nomeFoto) {
		// this.editPhoto(nomeFoto);
		photoDAO.update(photo);
		JSFUtil.redirect("http://localhost:8080/rino/app/photo/endPhoto.xhtml");
	}

	public void dontSaveEmail() {
		JSFUtil.redirect("http://localhost:8080/rino/app/photo/endPhoto.xhtml");
	}

	public void saveImage() throws IOException {
		try {
			ConfigPhoto configPhoto = configPhotoDAO.getList().get(0);

			String base64Image = this.getImage();
			base64Image = base64Image.split(",")[1];

			byte[] decodedBytes = DatatypeConverter.parseBase64Binary(base64Image);

			Photo photo = new Photo();
			photo.setNomeFoto(FileUtil.generateUniqueFileName() + ".jpg");
			photo.setFile(decodedBytes);
			photo.setNomeEvento(configPhoto.getNomeEvento());
			photo.setDescricaoEvento(configPhoto.getDescricaoEvento());
			photoDAO.insert(photo);

			if (configPhoto.getOnline().equalsIgnoreCase("ONLINE")) {
				String urlGallery = configPhoto.getLinkGallery() + photo.getNomeFoto() + "&type=button";
				transferOnline(photo);
				refreshFacebook(urlGallery);
				JSFUtil.redirect(urlGallery);
			} else {
				JSFUtil.redirect("http://localhost:8080/rino/app/photo/offlinePhoto.xhtml?file=" + photo.getNomeFoto());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletePhoto(Photo photo) {
		photoDAO.delete(photo);

		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Registro deletado com sucesso!");
		context.addMessage("messages", message);
	}

	public void refreshFacebook(String urlGallery) throws IOException {

		String urlFacebook = "http://graph.facebook.com/?id=" + urlGallery + "&amp;scrape=true&amp;method=post";

		URL url = new URL(urlFacebook);

		InputStream is = url.openConnection().getInputStream();

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		// String line = null;
		// while( ( line = reader.readLine() ) != null ) {
		// System.out.println(line);
		// }
		reader.close();

	}

	public List<Photo> listPhoto() {
		return photoDAO.getList();
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

}
