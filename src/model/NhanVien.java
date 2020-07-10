package model;

import java.util.Calendar;
import java.util.Date;

public class NhanVien {

	private int msnv;
	private String hoten;
	private String email;
	private String sdt;
	private Date ngaysinh;
	private String luong;
	private String diachi;
	
	public int getMsnv() {
		return msnv;
	}
	public void setMsnv(int msnv) {
		this.msnv = msnv;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getLastName() {
		String b = hoten;
		String[] c = b.split(" ");
		return c[c.length-1];
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	
	public Date getNgaysinh() {
		return ngaysinh;
	}
	
	public int getYearOfBirthday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.ngaysinh);
		return calendar.get(Calendar.YEAR);
	}
	
	public int getMonthOfBirthday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.ngaysinh);
		return calendar.get(Calendar.MONTH);
	}
	
	public int getDayOfBirthday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.ngaysinh);
		return calendar.get(Calendar.DATE);
	}
	
	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	
	public String getLuong() {
		return luong;
	}
	public void setLuong(String luong) {
		this.luong = luong;
	}
	
	public Double getDoubleLuong() {
		return Double.parseDouble(luong);
	}
	
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public NhanVien() {}
	
	public NhanVien(int msnv, String hoten, String email, String sdt, Date ngaysinh, String diachi, String luong) {
		super();
		this.msnv = msnv;
		this.hoten = hoten;
		this.email = email;
		this.sdt = sdt;
		this.ngaysinh = ngaysinh;
		this.luong = luong;
		this.diachi = diachi;
	}

	public boolean validateMSNV(int msnv){
		String ms = Integer.toString(msnv);
		String regex = "^[0-9]{6}$";
		return ms.matches(regex);
	}
	
	public boolean validateName(String name){
		String regex = "^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\s]+$";
		return name.matches(regex);
	}

	public boolean validateBirthday(String birthday){
		String regex = "^(1[0-2]|0[1-9])/(3[01]"+ "|[12][0-9]|0[1-9])/[0-9]{4}$";
		return birthday.matches(regex);
	}
			
	public boolean validateEmail(String Email){
		String regex = "^[\\w-\\+]+(\\.[\\w]+)*@vnext\\.com\\.vn$";
		return Email.matches(regex);
	}
	
	public boolean validatePhone(String phone) {
//			throws InvalidValueException{
		String regex = "\\d{10,11}$";
//		if(phone.matches(regex)==false) {
//			throw new InvalidValueException("Không hợp lệ");
//		}
		return phone.matches(regex);
	}	
	
	public boolean vaildateLuong(String luong){
		String regex = "^[1-9][0-9]{6,}$";
		return luong.matches(regex);
	}

	public boolean validateAddress(String address) {
		String regex = "^[0-9a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\/\s]+$";
		return address.matches(regex);
	}
}
