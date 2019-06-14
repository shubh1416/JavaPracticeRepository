

public enum Tea {

	TEA("Tea",5,1),WATER("water",60,5),MILK("milk",40,4),SUGER("sugar",15,2);
	
	private String material;
	private Integer consumptionAmount;
	private Integer wasteAmount;
	
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Integer getConsumptionAmount() {
		return consumptionAmount;
	}

	public void setConsumptionAmount(Integer consumptionAmount) {
		this.consumptionAmount = consumptionAmount;
	}

	public Integer getWasteAmount() {
		return wasteAmount;
	}

	public void setWasteAmount(Integer wasteAmount) {
		this.wasteAmount = wasteAmount;
	}

	private Tea(String material, Integer consumptionAmount, Integer wasteAmount) {
		this.material = material;
		this.consumptionAmount = consumptionAmount;
		this.wasteAmount = wasteAmount;
	}

	
	
	
}
