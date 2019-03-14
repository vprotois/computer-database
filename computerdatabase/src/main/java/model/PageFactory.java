package model;

import java.util.ArrayList;
import java.util.List;

public class PageFactory<T> {

	private List<T> data;
	private Integer index;
	private Integer size;
	
	public Page<T> build(){
		if(data == null) {
			data = new ArrayList<T>();
		}
		if(index == null) {
			index = new Integer(0);
		}
		if(size == null) {
			size = new Integer(10);
		}
		return new Page<T>(data,index,size);
	}
	
	public PageFactory<T> withData(List<T> list) {
		this.data = list;
		return this;
	}
	
	public PageFactory<T> withIndex(Integer index) {
		return this;
	}
}
