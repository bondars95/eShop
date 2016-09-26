package com.eshop.main.product;

public class SuggestItem {
	
	public SuggestItem(){
	}
	
	private String value;
	private String label;
	private String desc;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return "SuggestItem [value=" + value + ", label=" + label + ", desc=" + desc + "]";
	}
	
	
}
