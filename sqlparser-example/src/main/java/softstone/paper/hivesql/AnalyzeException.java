package softstone.paper.hivesql;

import java.util.List;
import java.util.Map;

public class AnalyzeException extends Exception {
	 private boolean flag = true;
	  private Map<String, List<String>> responseHeaders = null;
	  private String responseBody = null;

	  public AnalyzeException() {}

	  public AnalyzeException(Throwable throwable) {
	    super(throwable);
	  }

	  public AnalyzeException(String message) {
	    super(message);
	  }

	  public AnalyzeException(String message, Throwable throwable, boolean flag, Map<String, List<String>> responseHeaders, String responseBody) {
	    super(message, throwable);
	    this.flag = flag;
	    this.responseHeaders = responseHeaders;
	    this.responseBody = responseBody;
	  }

	  public AnalyzeException(String message, boolean flag, Map<String, List<String>> responseHeaders, String responseBody) {
	    this(message, (Throwable) null, flag, responseHeaders, responseBody);
	  }

	  public AnalyzeException(String message, Throwable throwable, boolean flag, Map<String, List<String>> responseHeaders) {
	    this(message, throwable, flag, responseHeaders, null);
	  }

	  public AnalyzeException(boolean flag, Map<String, List<String>> responseHeaders, String responseBody) {
	    this((String) null, (Throwable) null, flag, responseHeaders, responseBody);
	  }

	  public AnalyzeException(boolean flag, String message) {
	    super(message);
	    this.flag = flag;
	  }

	  public AnalyzeException(boolean flag, String message, Map<String, List<String>> responseHeaders, String responseBody) {
	    this(flag, message);
	    this.responseHeaders = responseHeaders;
	    this.responseBody = responseBody;
	  }

	  

	  public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	   * Get the HTTP response headers.
	   */
	  public Map<String, List<String>> getResponseHeaders() {
	    return responseHeaders;
	  }

	  /**
	   * Get the HTTP response body.
	   */
	  public String getResponseBody() {
	    return responseBody;
	  }
}
