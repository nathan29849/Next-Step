package next.web;

import java.util.HashMap;
import java.util.Map;
import next.web.controller.HomeController;
import next.web.controller.ListUserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMapping {

	private static final Logger log = LoggerFactory.getLogger(RequestMapping.class);
	private final Map<String, Controller> mappings;

	public RequestMapping() {
		this.mappings = new HashMap<>();
		initMapping();
	}

	private void initMapping() {
		this.mappings.put("/", new HomeController());
		this.mappings.put("/users/form", new ForwardController("/user/form.jsp"));
		this.mappings.put("/users/loginForm", new ForwardController("/user/form.html"));
		this.mappings.put("/users", new ListUserController());
	}

	public Controller findController(String url) {
		return mappings.get(url);
	}

}
