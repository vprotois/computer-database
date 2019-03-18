package model;

import java.util.List;

public class Pages<T> {

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

	public Pages(List<T> data,Integer index,Integer size) {
		this.data = data;
		this.index = index;
		this.size = size;
	}
	
	public List<T> getData(){
		return data.subList(index, Math.min(data.size(),index+size));
	}
	
	public void nextPage(){
		if(index + size< data.size()) {
			index+=size;
		}else{
			index = data.size()-1;
		}
	}
	
	public void previousPage(){
		if(index >= size) {
			index-= size;
		}
		else {
			index = 0;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(! (o instanceof Pages)) {
			return false;
		}
		
		return dataEquals(o) && sizeEquals(o);
		
	}

	private boolean dataEquals(Object o) {
		if (this.data == null)
			return (((Pages<?>) o).data ==null);
		else
			return this.data.equals(((Pages<?>) o).data);
	}
	
	private boolean sizeEquals(Object o) {
		if (this.size == null)
			return (((Pages<?>) o).size ==null);
		else
			return this.size.equals( ((Pages<?>) o).size);
	}
	
}
