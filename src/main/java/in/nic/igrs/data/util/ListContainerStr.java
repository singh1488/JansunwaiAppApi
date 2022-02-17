package in.nic.igrs.data.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListContainerStr {
	@JsonProperty("Result")
	private String report;

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	
}
