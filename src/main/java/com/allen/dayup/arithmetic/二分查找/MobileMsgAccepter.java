package com.allen.dayup.arithmetic.二分查找;

public class MobileMsgAccepter {

	private String name;
	private String phone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
    public boolean equals(Object obj) {
        return (this.phone.equals(((MobileMsgAccepter)obj).getPhone()));
    }
}
