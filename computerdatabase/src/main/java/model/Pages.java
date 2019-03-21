package model;

import java.util.List;

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
	
	public int nextPage(){
		if(index + pageSize< data.size()) {
			index+=pageSize;
		}else{
			index = data.size()-1;
		}
		return index;
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
		if (this.pageSize == null)
			return (((Pages<?>) o).pageSize ==null);
		else
			return this.pageSize.equals( ((Pages<?>) o).pageSize);
	}
	
}
