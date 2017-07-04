package kr.co.esjee.sjcms.admin.user.service;

import java.util.ArrayList;
import java.util.List;

public class DataResult {

	private String result = "00";
	private String reason = "";

	private List<Object> items = new ArrayList<Object>();

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<Object> getItems() {
		return items;
	}

	public void setItems(List<Object> items) {
		this.items = items;
	}

	public void addDataVO(Object data) {
		items.add(data);
	}

}
