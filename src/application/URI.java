package application;

public class URI {
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String label;
	private String url;
	
	public URI(String label, String url) {
		this.label = label;
		this.url = url;
	}
	
	

}
