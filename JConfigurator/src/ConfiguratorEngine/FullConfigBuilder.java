package ConfiguratorEngine;

public class FullConfigBuilder {
	private Psu myPsu;
	private Gpu myGpu;
	private Motherboard myMotherboard;
	private Cpu myCpu;
	private Case myCase1;
	private Storage myStorage;
	private Ram myRam;
	private ComputerShop myComputerShop;
	private static int psuOverhead = 50;

	private boolean complete=false;
	
	
	public int getPsuOverhead() {
		return psuOverhead;
	}

	public void setPsuOverhead(int psuOverhead) {
		FullConfigBuilder.psuOverhead = psuOverhead;
	}

	private static FullConfigBuilder instance=null;		// Singleton
	
	private FullConfigBuilder() {}
	
	public static FullConfigBuilder getIstance() {
		if(instance==null)
			instance=new FullConfigBuilder();
		return instance;
	}
	
	
	public FullConfigBuilder setPsu(Psu myPsu) {
		this.myPsu=myPsu;
		return this;
	}
	
	public FullConfigBuilder setGpu(Gpu myGpu) {
		this.myGpu = myGpu;
		return this;
	}
	
	public FullConfigBuilder setMotherboard(Motherboard myMotherboard) {
		this.myMotherboard = myMotherboard;
		return this;
	}
	public FullConfigBuilder setCpu(Cpu myCpu) {
		this.myCpu = myCpu;
		return this;
	}
	public FullConfigBuilder setCase(Case myCase1) {
		this.myCase1 = myCase1;
		return this;
	}
	public FullConfigBuilder setStorage(Storage myStorage) {
		this.myStorage = myStorage;
		return this;
	}
	public FullConfigBuilder setRam(Ram myRam) {
		this.myRam = myRam;
		return this;
	}
	
	public boolean isComplete() {
		if(this.myCpu != null && this.myGpu != null && this.myMotherboard != null && this.myCase1 != null && this.myStorage != null && this.myRam != null) {
			complete=true;
		}
		return complete;
	}
	
	public FullConfigBuilder setComputerShop(ComputerShop myComputerShop) {
		this.myComputerShop = myComputerShop;
		return this;
	}

	public Psu getMyPsu() {
		return myPsu;
	}

	public Gpu getMyGpu() {
		return myGpu;
	}

	public Motherboard getMyMotherboard() {
		return myMotherboard;
	}

	public Cpu getMyCpu() {
		return myCpu;
	}

	public Case getMyCase1() {
		return myCase1;
	}

	public Storage getMyStorage() {
		return myStorage;
	}

	public Ram getMyRam() {
		return myRam;
	}

	public static FullConfigBuilder getInstance() {
		return instance;
	}
	
	public FullConfig buildFullConfig() {
		return new FullConfig(myCpu, myGpu, myMotherboard, myRam, myCase1, myStorage, myPsu, this.getTotalEstimatedPower());
	}

	public int getTotalEstimatedPower() {
		int allPower=0;
		allPower+=this.getMyCpu().getPower();
		allPower+=this.getMyGpu().getPower();
		allPower+=this.getMyMotherboard().getPower();
		allPower+=this.getMyRam().getPower();
		allPower+=this.getMyCase1().getPower();
		allPower+=this.getMyStorage().getPower();

		return allPower;
	}
	
	public int getTotalPrice() {
		int allPrice=0;
		allPrice+=this.getMyCpu().getPrice();
		allPrice+=this.getMyGpu().getPrice();
		allPrice+=this.getMyMotherboard().getPrice();
		allPrice+=this.getMyRam().getPrice();
		allPrice+=this.getMyCase1().getPrice();
		allPrice+=this.getMyStorage().getPrice();
		allPrice+=this.getMyPsu().getPrice();
		return allPrice;
	}
	
	public ComputerShop getMyComputerShop() {
		return myComputerShop;
	}

	public void setMyComputerShop(ComputerShop myComputerShop) {
		this.myComputerShop = myComputerShop;
	}


	@Override
	public String toString() {
		return "FullConfig : \n myCpu=" + myCpu + "\n myGpu=" + myGpu + "\n myMotherboard=" + myMotherboard + "\n myRam="
				+ myRam + "\n myCase1=" + myCase1 + "\n myStorage=" + myStorage + "\n myPsu=" + myPsu + "\n myComputerShop="
				+ myComputerShop;
	}


	
}
