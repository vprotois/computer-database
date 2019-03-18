package model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PagesBuilder<T> {

	private static Logger log= LoggerFactory.getLogger(PagesBuilder.class);
	
	private List<T> data;
	private Integer index;
	private Integer size;
	
	public Pages<T> build(){
		if(data == null) {
			log.warn("Pages without data, null returned");
			return null;
		}
		if(index == null) {
			index = new Integer(0);
		}
		if(size == null) {
			size = new Integer(10);
		}
		return new Pages<T>(data,index,size);
	}
	
	public PagesBuilder<T> withData(List<T> list) {
		this.data = list;
		return this;
	}
	
	public PagesBuilder<T> withIndex(Integer index) {
		if(this.data == null) {
			log.warn("Can't initialize Index without data");
			return this;
		}
		if(index>this.data.size()){
			log.warn("Invalid Index while building page");
			return this;
		}
		this.index = index;
		return this;
	}
	
	public PagesBuilder<T> withSize(Integer size) {
		this.size = size;
		return this;
	}
}
