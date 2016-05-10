//package br.com.rino.bean;
//
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.util.List;
//
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.FacesContext;
//import javax.imageio.ImageIO;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.xml.bind.DatatypeConverter;
//
//import org.primefaces.context.RequestContext;
//import org.primefaces.model.UploadedFile;
//
//import br.com.rino.dao.FacebookDAO;
//import br.com.rino.util.FileUtil;
//import br.com.rino.util.JSFUtil;
//import facebook4j.Facebook;
//import facebook4j.FacebookException;
//import facebook4j.FacebookFactory;
//import facebook4j.Media;
//import facebook4j.PagePhotoUpdate;
//import facebook4j.conf.ConfigurationBuilder;
//
//@ManagedBean(name = "facebookBean")
//@SessionScoped
//public class FacebookBean_Deprecated {
//
//	private br.com.rino.entity.Facebook facebook = new br.com.rino.entity.Facebook();
//	private FacebookDAO facebookDAO = new FacebookDAO();
//	private UploadedFile fileImagemOnline;
//	private UploadedFile fileImagemOffline;
//	private UploadedFile fileImagemAgradecimento;
//	private UploadedFile file;
//	private String image;
//
//	public String getImage() {
//		return image;
//	}
//
//	public void setImage(String image) {
//		this.image = image;
//	}
//
//	public UploadedFile getFileImagemOnline() {
//		return fileImagemOnline;
//	}
//
//	public void setFileImagemOnline(UploadedFile fileImagemOnline) {
//		this.fileImagemOnline = fileImagemOnline;
//	}
//
//	public UploadedFile getFileImagemOffline() {
//		return fileImagemOffline;
//	}
//
//	public void setFileImagemOffline(UploadedFile fileImagemOffline) {
//		this.fileImagemOffline = fileImagemOffline;
//	}
//
//	public UploadedFile getFileImagemAgradecimento() {
//		return fileImagemAgradecimento;
//	}
//
//	public void setFileImagemAgradecimento(UploadedFile fileImagemAgradecimento) {
//		this.fileImagemAgradecimento = fileImagemAgradecimento;
//	}
//
//	public void callback() throws IOException, ServletException, FacebookException {
//		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
//				.getRequest();
//		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
//				.getResponse();
//		br.com.rino.entity.Facebook facebookConfig = facebookDAO.getList().get(0);
//
//		// Descomentar carro de erro.
//		// facebook4j.Facebook facebook = (Facebook)
//		// request.getSession().getAttribute("facebook");
//		facebook4j.Facebook facebook = (Facebook) JSFUtil.getSessionObj("facebook");
//		String oauthCode = request.getParameter("code");
//		try {
//			facebook.getOAuthAccessToken(oauthCode);
//		} catch (FacebookException e) {
//			throw new ServletException(e);
//		}
//
//		File outputfile = new File("C://imagePosted.png");
//
//		Media media = new Media(outputfile);
//		PagePhotoUpdate pagePhotoUpdate = new PagePhotoUpdate(media);
//		pagePhotoUpdate.setMessage(facebookConfig.getHashtag());
//
//		facebook.postPagePhoto(pagePhotoUpdate);
//		outputfile.delete();
//
//		if (facebookConfig.getLogoutFacebook().equalsIgnoreCase("SIM")) {
//			logout();
//		} else {
//			String endURL = "http://localhost:8080/rino/app/facebook/endFacebook.xhtml";
//			response.sendRedirect(endURL);
//		}
//	}
//
//	public void Signin() throws IOException, FacebookException {
//		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
//				.getRequest();
//		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
//				.getResponse();
//		br.com.rino.entity.Facebook facebookConfig = facebookDAO.getList().get(0);
//
//		// Facebook facebook = new FacebookFactory().getInstance();
//
//		ConfigurationBuilder cb = new ConfigurationBuilder();
//		cb.setDebugEnabled(true).setOAuthAppId(facebookConfig.getAppId())
//				.setOAuthAppSecret(facebookConfig.getAppSecret())
//				.setOAuthPermissions("email,public_profile,user_photos,user_posts,publish_actions");
//		FacebookFactory ff = new FacebookFactory(cb.build());
//		Facebook facebook = ff.getInstance();
//
//		JSFUtil.setSessionObj("facebook", facebook);
//
//		String callbackURL = "http://localhost:8080/rino/app/facebook/callbackFacebook.xhtml";
//		String fac = facebook.getOAuthAuthorizationURL(callbackURL);
//
//		this.saveImage(facebook);
//
//		response.sendRedirect(fac);
//		// response.sendRedirect(request.getRequestURL().toString());
//	}
//
//	protected void logout() throws ServletException, IOException {
//		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
//				.getRequest();
//		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
//				.getResponse();
//
//		facebook4j.Facebook facebook = (Facebook) JSFUtil.getSessionObj("facebook");
//		String accessToken;
//		try {
//			accessToken = facebook.getOAuthAccessToken().getToken();
//
//			// facebook.deleteAllPermissions();
//
//		} catch (Exception e) {
//			throw new ServletException(e);
//		}
//
//		// Invalidate the Session
//		// request.getSession().invalidate();
//
//		// Logout from the Facebook
//		String endURL = "http://localhost:8080/rino/app/facebook/endFacebook.xhtml";
//		response.sendRedirect("http://www.facebook.com/logout.php?next=" + URLEncoder.encode(endURL, "UTF-8")
//				+ "&access_token=" + accessToken);
//	}
//
//	public void saveImage(Facebook facebook) throws IOException, FacebookException {
//		try {
//			String base64Image = this.getImage();
//			base64Image = base64Image.split(",")[1];
//
//			byte[] decodedBytes = DatatypeConverter.parseBase64Binary(base64Image);
//			BufferedImage bfi = ImageIO.read(new ByteArrayInputStream(decodedBytes));
//			File outputfile = new File("C://imagePosted.png");
//			ImageIO.write(bfi, "png", outputfile);
//			bfi.flush();
//
//			/*
//			 * Media media = new Media(outputfile); PagePhotoUpdate
//			 * pagePhotoUpdate = new PagePhotoUpdate(media);
//			 * pagePhotoUpdate.setMessage("#Teste teset #teste");
//			 * 
//			 * facebook.postPagePhoto(pagePhotoUpdate); outputfile.delete();
//			 */
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//	}
//
//	public UploadedFile getFile() {
//		return file;
//	}
//
//	public void setFile(UploadedFile file) {
//		this.file = file;
//	}
//
//	public void newFacebook() {
//		br.com.rino.entity.Facebook facebook = new br.com.rino.entity.Facebook();
//		facebook.setCodFacebook(0l);
//		this.setFacebook(facebook);
//	}
//
//	public void editFacebook(Long codFacebook) {
//		this.setFacebook(facebookDAO.edit(codFacebook));
//	}
//
//	public void deleteFacebook(br.com.rino.entity.Facebook facebook) {
//		facebookDAO.delete(facebook);
//		FacesContext context = FacesContext.getCurrentInstance();
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
//				"Registro deletado com sucesso!");
//		context.addMessage("messages", message);
//	}
//
//	public void saveFacebook(br.com.rino.entity.Facebook facebook) {
//		FacesContext context = FacesContext.getCurrentInstance();
//		RequestContext request = RequestContext.getCurrentInstance();
//
//		if (!this.getFileImagemOnline().getFileName().isEmpty()) {
//			try {
//				String extension = this.getFileImagemOnline().getContentType().replace("image/", "");
//				String fileName = FileUtil.generateUniqueFileName() + "." + extension;
//
//				FileUtil.copyFile(fileName, this.getFileImagemOnline().getInputstream());
//
//				facebook.setNomeImagemOnline(fileName);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		if (!this.getFileImagemOffline().getFileName().isEmpty()) {
//			try {
//				String extension = this.getFileImagemOffline().getContentType().replace("image/", "");
//				String fileName = FileUtil.generateUniqueFileName() + "." + extension;
//
//				FileUtil.copyFile(fileName, this.getFileImagemOffline().getInputstream());
//
//				facebook.setNomeImagemOffline(fileName);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		if (!this.getFileImagemAgradecimento().getFileName().isEmpty()) {
//			try {
//				String extension = this.getFileImagemAgradecimento().getContentType().replace("image/", "");
//				String fileName = FileUtil.generateUniqueFileName() + "." + extension;
//
//				FileUtil.copyFile(fileName, this.getFileImagemAgradecimento().getInputstream());
//
//				facebook.setNomeImagemAgradecimento(fileName);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		if (facebook.getCodFacebook() == 0) {
//			facebookDAO.insert(facebook);
//		} else {
//			facebookDAO.update(facebook);
//		}
//
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
//		context.addMessage("messages", message);
//
//		request.execute("PF('modalEdit').hide();");
//		request.update("form:dataTable");
//	}
//
//	public List<br.com.rino.entity.Facebook> listFacebook() {
//		return facebookDAO.getList();
//	}
//
//	public br.com.rino.entity.Facebook getFacebook() {
//		return facebook;
//	}
//
//	public void setFacebook(br.com.rino.entity.Facebook facebook) {
//		this.facebook = facebook;
//	}
//
//	public void verifyConnectionAndRedirect() {
//		br.com.rino.entity.Facebook facebook = facebookDAO.getList().get(0);
//		if (facebook.getOnline().equals("SIM")) {
//			JSFUtil.redirect("/rino/app/facebook/onlineFacebook.xhtml");
//		} else if (facebook.getOnline().equals("NÃO")) {
//			JSFUtil.redirect("/rino/app/facebook/offlineFacebook.xhtml");
//		}
//
//	}
//
//}
