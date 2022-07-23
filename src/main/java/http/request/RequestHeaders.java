package http.request;

import java.util.HashMap;
import java.util.Map;
import util.HttpRequestUtils.Pair;

public class RequestHeaders {

	private final Map<String, String> headers = new HashMap<>();

	public void addHeader(Pair pair) {
		if (pair == null) {
			return;
		}
		headers.put(pair.getKey(), pair.getValue());
	}

	public String getHeader(String key) {
		return headers.get(key);
	}

	public boolean containsKey(String key) {
		return headers.containsKey(key);
	}

	public String getCookie() {
		if (headers.containsKey("Cookie")) {
			return headers.get("Cookie");
		}
		return null;
	}
}
