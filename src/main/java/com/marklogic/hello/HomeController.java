package com.marklogic.hello;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.DocumentIdentifier;
import com.marklogic.client.XMLDocumentManager;
import com.marklogic.client.io.DOMHandle;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		DatabaseClient client = DatabaseClientFactory.connect("localhost", 8003, "admin", "admin", Authentication.DIGEST);
		XMLDocumentManager docMgr = client.newXMLDocumentManager();
		DocumentIdentifier docId = client.newDocId("ASDF.xml");
		DOMHandle handle = new DOMHandle();
		docMgr.read(docId, handle);
		Document document = handle.get();

		model.addAttribute("doc", document.getDocumentElement().getTagName());

		client.release();
		return "home";
	}

}
