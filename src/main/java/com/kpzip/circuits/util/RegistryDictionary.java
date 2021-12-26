package com.kpzip.circuits.util;

import java.util.ArrayList;
import java.util.List;


//Simple way to store Items and their registry names
public class RegistryDictionary <T> {
	
	public List<T> Registerables;
	
	public List<String> RegistryNames;
	
	public RegistryDictionary() {
		Registerables = new ArrayList<T>();
		RegistryNames = new ArrayList<String>();
	}
	
	public T add(T obj, String name) {
		Registerables.add(obj);
		RegistryNames.add(name);
		return obj;
	}
	
	public int size() {
		return Registerables.size();
	}
	
	public T getObject(int index) {
		return Registerables.get(index);
	}
	
	public String getRegistryName(int index) {
		return RegistryNames.get(index);
	}

}
