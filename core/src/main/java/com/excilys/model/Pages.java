package com.excilys.model;

import java.util.List;
import java.util.Objects;

public class Pages<T> {

	private List<T> data;
	private Integer index;
	private Integer pageSize;
	

	
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getSize() {
		return pageSize;
	}

	public void setSize(Integer size) {
		this.pageSize = size;
	}

	public Pages(List<T> data,Integer index,Integer size) {
		this.data = data;
		this.index = index;
		this.pageSize = size;
	}
	
	public Integer getDataSize() {
		return data.size();
	}
	
	public List<T> getData(){
		return data;
	}
	
	public List<T> getPageData(){
		return data.subList(index, Math.min(data.size(),index+pageSize));
	}
	
	public void nextPage(){
		if(index + pageSize< data.size()) {
			index+=pageSize;
		}else{
			index = data.size() > 0 ? data.size()-1 : 0;
		}
	}
	
	public int nextIndex(){
		if(index + pageSize< data.size()) {
			return index+ pageSize;
		}else{
			return data.size() > 0 ? data.size()-1 : 0;
		}
	}
	
	
	public int previousPage(){
		if(index >= pageSize) {
			index-= pageSize;
		}
		else {
			index = 0;
		}
		return index;
	}
	
	public int previousIndex(){
		if(index >= pageSize) {
			return index - pageSize;
		}
		else {
			return 0;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(data);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pages<?> other = (Pages<?>) obj;
		return Objects.equals(data, other.data);
	}
	
}
