package ua.training.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Certificate;
import ua.training.model.entity.User;
import ua.training.model.service.CertificateService;

public class DisplayCertificatesCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.USER);
		String username = user.getUsername();
		
		CertificateService service = new CertificateService();
		
		List<Certificate> certificates = service.findAllByUsername(username);
		
		request.getSession().setAttribute(Constants.CERTIFICATES, certificates);
		
		return "redirect:/user/my-certificates.jsp";
	}

}
