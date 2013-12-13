package com.cpm.delegates;

public class SkuStoreBean {

	protected String sku;

	protected String SkuId;

	protected String faceup= "0";

	protected String stock= "0";

	public String getFaceup() {
		return faceup;
	}

	public void setFaceup(String faceup) {
		this.faceup = faceup;
	}

	public String getSkuId() {
		return SkuId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public void setSkuId(String skuId) {
		SkuId = skuId;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getSkuitemid() {
		return SkuId;
	}

	public void setSkuitemid(String skuid) {
		this.SkuId = skuid;
	}

}
