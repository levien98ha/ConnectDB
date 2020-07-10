package dao;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.UnsupportedEncodingException;


import model.NhanVien;
import util.DBUtil;

public class NhanVienDAO {
 	private static Connection cnn;
	public NhanVienDAO() {
		cnn =  DBUtil.getConnection();
	}
	
	
	@SuppressWarnings("deprecation")
	public void AddNV(NhanVien nv) {
		try {
			PreparedStatement stm = cnn.prepareStatement("insert into nhanvien(msnv, hoten, email, ngaysinh, diachi, sdt, luong) value(?, ?, ?, ?, ?, ?, ?)");
			stm.setInt(1, nv.getMsnv());
			stm.setString(2, nv.getHoten());
			stm.setString(3, nv.getEmail());
			stm.setDate(4, new java.sql.Date(nv.getNgaysinh().getDate()));
			stm.setString(5, nv.getDiachi());
			stm.setString(6, nv.getSdt());
			stm.setString(7, nv.getLuong());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Lỗi truy vấn dữ liệu");
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public void UpdateNV(NhanVien nv) {
		try {
			PreparedStatement stm = cnn.prepareStatement("update nhanvien set hoten=?, email=?, ngaysinh=?, diachi=?, sdt=?, luong=? where msnv=?");
			stm.setString(1, nv.getHoten());
			stm.setString(2, nv.getEmail());
			stm.setDate(3, new java.sql.Date(nv.getNgaysinh().getDate()));
			stm.setString(4, nv.getDiachi());
			stm.setString(5, nv.getSdt());
			stm.setString(6, nv.getLuong());
			stm.setInt(7, nv.getMsnv());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Lỗi truy vấn dữ liệu");
		}
	}
	
	
	public void DeleteNV(int id) {
		try {
			PreparedStatement stm = cnn.prepareStatement("delete from nhanvien where msnv=?");
			stm.setInt(1, id);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Lỗi truy vấn dữ liệu");
		}
	}
	
	
	public List<NhanVien> GetAllNV(){
		List<NhanVien> nv = new ArrayList<NhanVien>();
		try {
			Statement stm = cnn.createStatement();
			ResultSet rs = stm.executeQuery("select * from nhanvien");
			while(rs.next()) {
				NhanVien nv1 = new NhanVien();
				nv1.setMsnv(rs.getInt("msnv"));
				nv1.setHoten(rs.getString("hoten"));
				nv1.setEmail(rs.getString("email"));
				nv1.setNgaysinh(rs.getDate("ngaysinh"));
				nv1.setDiachi(rs.getString("diachi"));
				nv1.setSdt(rs.getString("sdt"));
				nv1.setLuong(rs.getString("luong"));
				nv.add(nv1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Lỗi truy vấn dữ liệu");
		}
		return nv;
	}
	
	
	public NhanVien getNV(int id) {
		NhanVien nv1 = new NhanVien();
		try {
			PreparedStatement stm = cnn.prepareStatement("select * from nhanvien where msnv=?");
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				nv1.setMsnv(rs.getInt("msnv"));
				nv1.setHoten(rs.getString("hoten"));
				nv1.setEmail(rs.getString("email"));
				nv1.setNgaysinh(rs.getDate("ngaysinh"));
				nv1.setDiachi(rs.getString("diachi"));
				nv1.setSdt(rs.getString("sdt"));
				nv1.setLuong(rs.getString("luong"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Lỗi truy vấn dữ liệu");
		}
		return nv1;
	}
	
	
	public void disconect() {
		try {
			cnn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SQL Error");
		}
	}
	
	
	public void AddFromFile(List<String> filename) {
		int batchsize = 10;
		try {
			PreparedStatement stm = cnn.prepareStatement("insert into nhanvien(msnv, hoten, email, ngaysinh, diachi, sdt, luong) value(?, ?, ?, ?, ?, ?, ?)");
			
		for(int i = 0; i<filename.size(); i++) {
			int count = 1;
			String csvFileName = filename.get(i);
			try (BufferedReader br = new BufferedReader(new FileReader("input/"+csvFileName))){
				String lineText = null;
				br.readLine();
				while((lineText = br.readLine())!=null) {
					String[] data = lineText.split(",");
					int msnv = Integer.parseInt(data[0]);
					NhanVien nv = getNV(msnv);
					
					if(nv.getMsnv()!= msnv) {
						stm.setInt(1, Integer.parseInt(data[0]));
						stm.setString(2, data[1]);
						stm.setString(3, data[2]);
						try {				
							SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
							java.util.Date date1 = f.parse(data[3]);	
							java.sql.Date date = new java.sql.Date(date1.getTime());
							stm.setDate(4, date);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						stm.setString(5, data[4]);
						stm.setString(6, data[5]);
						stm.setString(7, data[6]);
						stm.addBatch();
						if(count%batchsize==0) {
							stm.executeBatch();
						}
						count++;
					}
				}
				stm.executeBatch();
				br.close();
					
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Không tìm thấy file csv từ folder");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Lỗi file!");
			} 

		}
		}
		 catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Lỗi truy vấn dữ liệu");
			}
		System.out.println("Đã import dữ liệu thành công.");
	}	
	
	
	public List<NhanVien> SortName(){
		List<NhanVien> nv = new ArrayList<NhanVien>();
		nv = GetAllNV();
		Collections.sort(nv, Comparator.comparing(NhanVien::getLastName));
		return nv;
	}

	
	public List<NhanVien> SortSalary(){
		List<NhanVien> nv = new ArrayList<NhanVien>();
		nv = GetAllNV();
		Collections.sort(nv, Comparator.comparing(NhanVien::getDoubleLuong));
		return nv;
	}
	

	public List<NhanVien> SortRollno(){
		List<NhanVien> nv = new ArrayList<NhanVien>();
		nv = GetAllNV();
		Collections.sort(nv, Comparator.comparing(NhanVien::getMsnv));
		return nv;
	} 
	
	
	public List<NhanVien> Sort2Fields(){
		List<NhanVien> nv = new ArrayList<NhanVien>();
		nv = GetAllNV();
		Collections.sort(nv, Comparator.comparing(NhanVien::getLastName)
				.thenComparing(NhanVien::getDoubleLuong));
		return nv;
	}
	
	
	public List<NhanVien> SortYear(){
		List<NhanVien> nv = new ArrayList<NhanVien>();
		nv = GetAllNV();
		Collections.sort(nv, Comparator.comparing(NhanVien::getYearOfBirthday)
				.thenComparing(NhanVien::getMonthOfBirthday)
				.thenComparing(NhanVien::getDayOfBirthday));
		return nv;
	}
	
	public List<NhanVien> GroupByAddress(){
		List<NhanVien> nv = new ArrayList<NhanVien>();
		nv = GetAllNV();
		Collections.sort(nv, Comparator.comparing(NhanVien::getDiachi));
		return nv;
	}
	
	public void exportFile(String filename) {
		List<NhanVien> nv = new ArrayList<NhanVien>();
		nv = GetAllNV();
		try {
			FileOutputStream os = new FileOutputStream(filename);
			OutputStreamWriter wr = new OutputStreamWriter(os, "utf-8");
			wr.write("MSNV,Họ tên,Email,Ngày sinh,Địa chỉ,Số điện thoại,Lương");
			wr.write("\n");
			for(int i =0;i < nv.size();i++) {
				wr.write(nv.get(i).getMsnv()+","+nv.get(i).getHoten()+","+nv.get(i).getEmail()+","+nv.get(i).getNgaysinh()+","+nv.get(i).getDiachi()+","+nv.get(i).getSdt()+","+nv.get(i).getLuong());
				wr.write("\n");
			}
			wr.flush();
			wr.close();
			os.close();
			System.out.println("Đã xuất dữ liệu ra file csv tên: "+filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
