package model;

import java.util.List;

public class Page<T> {

	private List<T> data;
	private Integer index;
	private Integer size;
	

	
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Page(List<T> data,Integer index,Integer size) {
		this.data = data;
		this.index = index;
		this.size = size;
	}
	
	public List<T> getData(){
		return data.subList(index, Math.min(data.size()-1,index+size));
	}
	
	public void nextPage(){
		if(index + size< data.size()) {
			index+=size;
		}
	}
	
	public void previousPage(){
		if(index >= size) {
			index-= size;
		}
	}
}
