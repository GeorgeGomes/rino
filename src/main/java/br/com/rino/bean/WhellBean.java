package br.com.rino.bean;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import br.com.rino.dao.WhellDAO;
import br.com.rino.entity.Whell;
import br.com.rino.util.FileUtil;

@ManagedBean(name = "whellBean")
@SessionScoped
public class WhellBean {

	private Whell whell = new Whell();
	private WhellDAO whellDAO = new WhellDAO();
	private UploadedFile file;
	private Long qtdeJogadas;

	public Long getQtdeJogadas() {
		return qtdeJogadas;
	}

	public void setQtdeJogadas(Long qtdeJogadas) {
		this.qtdeJogadas = qtdeJogadas;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Double totalPremios() {
		List<Whell> whellList = whellDAO.getList();
		Double total = 0.0;

		Iterator<Whell> whellIterator = whellList.iterator();

		while (whellIterator.hasNext()) {
			Whell whell = whellIterator.next();
			if (whell.getPremios() != null) {
				total += whell.getPremios();
			}
		}
		return total;
	}

	public void newWhell() {
		Whell whell = new Whell();
		whell.setCodWhell(0l);
		whell.setVitorias(0);
		this.setWhell(whell);
	}

	public void editWhell(Long codWhell) {
		this.setWhell(whellDAO.edit(codWhell));
	}

	public void deleteWhell(Whell whell) {
		whellDAO.delete(whell);
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Registro deletado com sucesso!");
		context.addMessage("messages", message);
	}

	public void saveJogadas(Whell whell) {
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext request = RequestContext.getCurrentInstance();

		if (!this.getFile().getFileName().isEmpty()) {
			try {
				String extension = this.getFile().getContentType().replace("image/", "");
				String fileName = FileUtil.generateUniqueFileName() + "." + extension;

				FileUtil.copyFile(fileName, this.getFile().getInputstream());

				whell.setNomeImagem(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (whell.getCodWhell() == 0) {
			whellDAO.insert(whell);
		} else {
			whellDAO.update(whell);
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
		context.addMessage("messages", message);

		request.execute("PF('modalEdit').hide();");
		request.update("form:dataTable");
	}

	public void saveWhell(Whell whell) {
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext request = RequestContext.getCurrentInstance();

		if (!this.getFile().getFileName().isEmpty()) {
			try {
				String extension = this.getFile().getContentType().replace("image/", "");
				String fileName = FileUtil.generateUniqueFileName() + "." + extension;

				FileUtil.copyFile(fileName, this.getFile().getInputstream());

				whell.setNomeImagem(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (whell.getCodWhell() == 0) {
			whellDAO.insert(whell);
		} else {
			whellDAO.update(whell);
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!");
		context.addMessage("messages", message);

		request.execute("PF('modalEdit').hide();");
		request.update("form:dataTable");
	}

	public List<Whell> listWhell() {
		return whellDAO.getList();
	}

	public Whell getWhell() {
		return whell;
	}

	public void setWhell(Whell whell) {
		this.whell = whell;
	}

}
