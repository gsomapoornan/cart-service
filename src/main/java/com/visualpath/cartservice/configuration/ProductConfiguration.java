package com.visualpath.cartservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("product")
public class ProductConfiguration {

	private String url;
	private String appname;
	private String prodIdresource;

	public String getProdIdresource() {
		return prodIdresource;
	}

	public void setProdIdresource(String prodIdresource) {
		this.prodIdresource = prodIdresource;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
