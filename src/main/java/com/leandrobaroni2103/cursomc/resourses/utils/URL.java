package com.leandrobaroni2103.cursomc.resourses.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	//transforma cada item de inteiros do findPage de ProdutoDTO em uma lista
	public static List<Integer> decodeIntList(String s){
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;


		/*
		 * outra forma é esta abaixo
		 * return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		 */
	}
}