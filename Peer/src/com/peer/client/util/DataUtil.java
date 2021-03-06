package com.peer.client.util;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class DataUtil {
	
	private final static RestTemplate jsonTemplate = new RestTemplate();
	static {
		jsonTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		jsonTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		jsonTemplate.getMessageConverters().add(new StringHttpMessageConverter());
	}
	public static RestTemplate getJsonTemplate(){
		return jsonTemplate;
	}
	private final static RestTemplate formTemplate = new RestTemplate();
	static {
		
		List<HttpMessageConverter<?>> formPartConverters = new ArrayList<HttpMessageConverter<?>>();
		formPartConverters.add(new ByteArrayHttpMessageConverter());
		StringHttpMessageConverter formStringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		formStringHttpMessageConverter.setWriteAcceptCharset(false);
		formPartConverters.add(formStringHttpMessageConverter);
		formPartConverters.add(new ResourceHttpMessageConverter());
		
		List<HttpMessageConverter<?>> partConverters = new ArrayList<HttpMessageConverter<?>>();
		partConverters.addAll(formPartConverters);
		
		FormHttpMessageConverter formConverter = new FormHttpMessageConverter();
		formConverter.setCharset(Charset.forName("UTF-8"));
	    formConverter.setPartConverters(formPartConverters);
	    
	    partConverters.add(formConverter);
	    partConverters.add(new GsonHttpMessageConverter());
	    
	    formTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	    formTemplate.setMessageConverters(partConverters);
	}

	public static <T> ResponseEntity<T> postJson(String url, Object data, Class<T> type,  Object... uriVariables){						
		return jsonTemplate.postForEntity(url, data, type, uriVariables);
	}
	
	public static void putEntity(String url, Object data,  Object... uriVariables){						
		 formTemplate.put(url, data, uriVariables);
	}
	
	public static void deleteEntity(String url, Object... uriVariables){						
		jsonTemplate.delete(url,uriVariables);
	}
	
	public static <T> ResponseEntity<T> postEntity(String url, Object data, Class<T> type,  Object... uriVariables){						
		return jsonTemplate.postForEntity(url, data, type, uriVariables);
	}
	
	public static <T> ResponseEntity<T> getJson(String url, Class<T> type,  Object... uriVariables){						
		return jsonTemplate.getForEntity(url, type, uriVariables);
	}
	
	public static <T> ResponseEntity<T> postForm(String url, Object data, Class<T> type, Object... uriVariables){						
		return formTemplate.postForEntity(url, data, type, uriVariables);
	}
	
	public static <T> ResponseEntity<T> templateExchange(String url,HttpMethod method, HttpEntity<T> requestEntity, Class<T> responseType, Object... uriVariables){
		return (ResponseEntity<T>) jsonTemplate.exchange(url, method, requestEntity,responseType,uriVariables);
	}
}
