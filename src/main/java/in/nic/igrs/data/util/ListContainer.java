package in.nic.igrs.data.util;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("rawtypes")
public class ListContainer {
	@JsonProperty("Result")
	private List report;

	public List getReport() {
		return report;
	}
	public void setReport(List report) {
		this.report = report;
	}
}
