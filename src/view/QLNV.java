package view;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.util.LinkedList;
import model.NhanVien;
import dao.*;

public class QLNV {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		int msnv,id;
		String hoten,email,sdt,diachi;
		String luong;
		String ngaysinh;
		
		NhanVienDAO dao = new NhanVienDAO();
		int x,y,z;	//for switch case	
		do {
			System.out.println("\n-----QUẢN LÝ NHÂN VIÊN-----");
			System.out.println("0. Thoát");
			System.out.println("1. Thêm nhân viên");
			System.out.println("2. Xem danh sách tất cả nhân viên");
			System.out.println("3. Xem thông tin nhân viên theo mã số");
			System.out.println("4. Chỉnh sửa thông tin nhân viên");
			System.out.println("5. Xóa nhân viên khỏi hệ thống");
			System.out.println("6. Load dữ liệu từ file csv trong folder input");
			System.out.println("7. Xuất dữ liệu ra file csv");
			System.out.println("8. Sắp xếp danh sách nhân viên");
			x = in.nextInt();
			in.nextLine();
			switch(x) {
			case 0:
				System.out.println("----------Thoát chương trình----------");
				dao.disconect();
				break;
			case 1: 
				System.out.println("Nhập thông tin nhân viên cần thêm: ");
				NhanVien nv = new NhanVien();
				System.out.println("Nhập msnv: ");
				msnv = in.nextInt();
				NhanVien nv11 = new NhanVien();
				nv11 = dao.getNV(msnv);
				while(nv.validateMSNV(msnv)==false) {
					System.out.println("Nhập lại mã số nhân viên (6 chữ số): ");
					msnv = in.nextInt();
					nv11 = dao.getNV(msnv);
					while(nv11.getMsnv()==nv.getMsnv()) {
						System.out.println("Nhập lại mã số nhân viên (6 chữ số): ");
						msnv = in.nextInt();
					}
				}
				while(nv11.getMsnv()==nv.getMsnv()) {
					System.out.println("Nhập lại mã số nhân viên (6 chữ số): ");
					msnv = in.nextInt();
				}
				nv.setMsnv(msnv);
				System.out.println("Nhập họ và tên nhân viên: ");
				hoten = in.nextLine();
				while(nv.validateName(hoten)==false) {
					System.out.println("Nhập lại tên nhân viên (chỉ gồm chữ cái): ");
					hoten = in.nextLine();
				}
				nv.setHoten(hoten);
				System.out.println("Nhập email nhân viên: ");
				email = in.nextLine();
				while(nv.validateEmail(email)==false) {
					System.out.println("Nhập lại mail (vnext mail): ");
					email = in.nextLine();
				}
				nv.setEmail(email);
				System.out.println("Nhập địa chỉ nhân viên: ");
				diachi = in.nextLine();
				while(nv.validateAddress(diachi)==false) {
					System.out.println("Nhập lại địa chỉ (không chứa các ký tự đặc biệt): ");
					diachi = in.nextLine();
				}
				nv.setDiachi(diachi);
				System.out.println("Nhập ngày sinh: ");
				ngaysinh = in.nextLine();
				while(nv.validateBirthday(ngaysinh)==false) {
					System.out.println("Nhập lại ngày sinh (ngày/tháng/năm): ");
					ngaysinh = in.nextLine();
				}
				try {
					Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(ngaysinh);
					nv.setNgaysinh(date1);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Nhập số điện thoại: ");
				sdt = in.nextLine();
				while(nv.validatePhone(sdt)==false) {
					System.out.println("Nhập lại số điện thoại (10-11 chữ số): ");
					sdt = in.nextLine();
				}
				nv.setSdt(sdt);
				System.out.println("Nhập lương của nhân viên: ");
				luong = in.nextLine();
				while(nv.vaildateLuong(luong)==false) {
					System.out.println("Nhập lại lương nhân viên (ít nhất 7 chữ số): ");
					sdt = in.nextLine();
				}
				nv.setLuong(luong);
				dao.AddNV(nv);
				break;
			case 2: 
				List<NhanVien> nv1 = dao.GetAllNV();
				System.out.printf("%-7s%-13s%-30s%-25s%-20s%-20s%-20s%-10s\n","STT","MSNV","Họ tên nhân viên","Email","Ngày sinh","Địa chỉ","Số điện thoại","Lương");
				for(int i=0; i<nv1.size();i++) {
					System.out.printf("%-7d%-13d%-30s%-25s%-2td/%-2tm/%-14tY%-20s%-20s%-10s\n",i+1,nv1.get(i).getMsnv(),nv1.get(i).getHoten(),nv1.get(i).getEmail(),nv1.get(i).getNgaysinh(),nv1.get(i).getNgaysinh(),nv1.get(i).getNgaysinh(),nv1.get(i).getDiachi(),nv1.get(i).getSdt(),nv1.get(i).getLuong());
				}
				System.out.println("----------------------------------------------------------------------------------------------");
				break;
			case 3:
				System.out.println("Nhập ID nhân viên cần xem thông tin: ");
				id = in.nextInt();
				in.nextLine();
				NhanVien getnv = dao.getNV(id);
				if(getnv.getMsnv()!=0) {
				System.out.printf("%-13s%-30s%-25s%-20s%-20s%-20s%-10s\n","MSNV","Họ tên nhân viên","Email","Ngày sinh","Địa chỉ","Số điện thoại","Lương");
				System.out.printf("%-13d%-30s%-25s%-2td/%-2tm/%-14tY%-20s%-20s%-10s\n",getnv.getMsnv(),getnv.getHoten(),getnv.getEmail(),getnv.getNgaysinh(),getnv.getNgaysinh(),getnv.getNgaysinh(),getnv.getDiachi(),getnv.getSdt(),getnv.getLuong());
				}
				else if(getnv.getMsnv()==0) {
					System.out.println("Không tìm thấy nhân viên trong hệ thống!");
				}
				break;
			case 4:
				System.out.println("Nhập id nhân viên cần chỉnh sửa thông tin: ");
				id = in.nextInt();
				in.nextLine();
				NhanVien nv2 = dao.getNV(id);
				do {
					System.out.println("-----UPDATE INFORMATION-----");
					System.out.println("0. Về Menu");
					System.out.println("1. Chỉnh sửa họ tên");
					System.out.println("2. Chỉnh sửa email");
					System.out.println("3. Chỉnh sửa ngày sinh");
					System.out.println("4. Chỉnh sửa địa chỉ");
					System.out.println("5. Chỉnh sửa số điện thoại");
					System.out.println("6. Chỉnh sửa lương");
					y = in.nextInt();
					switch(y) {
					case 0: 
						break;
					case 1:
						System.out.println("Nhập tên nhân viên mới: ");
						hoten = in.nextLine();
						while(nv2.validateName(hoten)==false) {
							System.out.println("Nhập lại tên nhân viên (chỉ gồm chữ cái): ");
							hoten = in.nextLine();
						}
						nv2.setHoten(hoten);
					case 2:
						System.out.println("Nhập email thay đổi: ");
						email = in.nextLine();
						while(nv2.validateEmail(email)==false) {
							System.out.println("Nhập lại mail (vnext mail): ");
							email = in.nextLine();
						}
						nv2.setEmail(email);
						break;
					case 3:
						System.out.println("Nhập ngày sinh mới: ");
						ngaysinh = in.nextLine();
						while(nv2.validateBirthday(ngaysinh)==false) {
							System.out.println("Nhập lại ngày sinh (ngày/tháng/năm): ");
							ngaysinh = in.nextLine();
						}
						try {
							Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(ngaysinh);
							nv2.setNgaysinh(date1);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 4: 
						System.out.println("Nhập địa chỉ mới: ");
						diachi = in.nextLine();
						nv2.setDiachi(diachi);
						break;
					case 5:
						System.out.println("Nhập số điện thoại mới: ");
						sdt = in.nextLine();
						while(nv2.validatePhone(sdt)==false) {
							System.out.println("Nhập lại số điện thoại (10-11 chữ số): ");
							sdt = in.nextLine();
						}
						nv2.setSdt(sdt);
						break;
					case 6:
						System.out.println("Nhập lương mới của nhân viên: ");
						luong = in.nextLine();
						while(nv2.vaildateLuong(luong)==false) {
							System.out.println("Nhập lại lương nhân viên (ít nhất 7 chữ số): ");
							sdt = in.nextLine();
						}
						nv2.setLuong(luong);
						break;
					}
				}while(y!=0);
				dao.UpdateNV(nv2);
				break;
			case 5: 
				System.out.println("Nhập ID nhân viên cần xóa: ");
				int delId = in.nextInt();
				in.nextLine();
				NhanVien checkDel = new NhanVien();
				checkDel = dao.getNV(delId);
				if(checkDel.getMsnv()!=0) {
				dao.DeleteNV(delId);
				System.out.println("Xóa thành công");
				}
				else {
					System.out.println("Không tìm thấy nhân viên trong hệ thống!");
				}
			case 6: 
				System.out.println("Đang load dữ liệu từ file csv");
				final File folder = new File("input");
				List<String> filename = new LinkedList<String>();
				for(final File f: folder.listFiles()) {
						if(f.getName().endsWith(".csv")) {
							filename.add(f.getName());
						}
				}
				dao.AddFromFile(filename);
				break;
			case 7:
				String filenameoutput = "output/";
				System.out.println("Nhập tên file cần xuất dữ liệu: ");
				filenameoutput += in.nextLine();
				filenameoutput += ".csv";
				dao.exportFile(filenameoutput);
				break;
			case 8:
				System.out.println("Chọn điều kiện cần sắp xếp:");
				do {
					System.out.println("0. Quay về menu");
					System.out.println("1. Sắp xếp theo tên");
					System.out.println("2. Sắp xếp theo lương");
					System.out.println("3. Sắp xếp theo mã số nhân viên");
					System.out.println("4. Sắp xếp theo tên và lương");
					System.out.println("5. Sắp xếp theo năm sinh");
					System.out.println("6. Sắp xếp theo địa chỉ");
					z= in.nextInt();
					switch(z) {
					case 0: break;
					case 1: 
						List<NhanVien> sortName = dao.SortName();
						System.out.printf("%-7s%-13s%-30s%-25s%-20s%-20s%-20s%-10s\n","STT","MSNV","Họ tên nhân viên","Email","Ngày sinh","Địa chỉ","Số điện thoại","Lương");
						for(int i=0; i<sortName.size();i++) {
							System.out.printf("%-7d%-13d%-30s%-25s%-2td/%-2tm/%-14tY%-20s%-20s%-10s\n",
									i+1,sortName.get(i).getMsnv(),sortName.get(i).getHoten(),sortName.get(i).getEmail(),
									sortName.get(i).getNgaysinh(),sortName.get(i).getNgaysinh(),sortName.get(i).getNgaysinh(),
									sortName.get(i).getDiachi(),sortName.get(i).getSdt(),sortName.get(i).getLuong());
						}
						System.out.println("----------------------------------------------------------------------------------------------");
						break;
					case 2:
						List<NhanVien> sortSalary = dao.SortSalary();
						System.out.printf("%-7s%-13s%-30s%-25s%-20s%-20s%-20s%-10s\n","STT","MSNV","Họ tên nhân viên","Email","Ngày sinh","Địa chỉ","Số điện thoại","Lương");
						for(int i=0; i<sortSalary.size();i++) {
							System.out.printf("%-7d%-13d%-30s%-25s%-2td/%-2tm/%-14tY%-20s%-20s%-10s\n",
									i+1,sortSalary.get(i).getMsnv(),sortSalary.get(i).getHoten(),sortSalary.get(i).getEmail(),
									sortSalary.get(i).getNgaysinh(),sortSalary.get(i).getNgaysinh(),sortSalary.get(i).getNgaysinh(),
									sortSalary.get(i).getDiachi(),sortSalary.get(i).getSdt(),sortSalary.get(i).getLuong());
						}
						System.out.println("----------------------------------------------------------------------------------------------");
						break;
					case 3:
						List<NhanVien> sortRollno = dao.SortRollno();
						System.out.printf("%-7s%-13s%-30s%-25s%-20s%-20s%-20s%-10s\n","STT","MSNV","Họ tên nhân viên","Email","Ngày sinh","Địa chỉ","Số điện thoại","Lương");
						for(int i=0; i<sortRollno.size();i++) {
							System.out.printf("%-7d%-13d%-30s%-25s%-2td/%-2tm/%-14tY%-20s%-20s%-10s\n",
									i+1,sortRollno.get(i).getMsnv(),sortRollno.get(i).getHoten(),sortRollno.get(i).getEmail(),
									sortRollno.get(i).getNgaysinh(),sortRollno.get(i).getNgaysinh(),sortRollno.get(i).getNgaysinh(),
									sortRollno.get(i).getDiachi(),sortRollno.get(i).getSdt(),sortRollno.get(i).getLuong());
						}
						System.out.println("----------------------------------------------------------------------------------------------");
						break;
					case 4:
						List<NhanVien> sort2 = dao.Sort2Fields();
						System.out.printf("%-7s%-13s%-30s%-25s%-20s%-20s%-20s%-10s\n","STT","MSNV","Họ tên nhân viên","Email","Ngày sinh","Địa chỉ","Số điện thoại","Lương");
						for(int i=0; i<sort2.size();i++) {
							System.out.printf("%-7d%-13d%-30s%-25s%-2td/%-2tm/%-14tY%-20s%-20s%-10s\n",
									i+1,sort2.get(i).getMsnv(),sort2.get(i).getHoten(),sort2.get(i).getEmail(),
									sort2.get(i).getNgaysinh(),sort2.get(i).getNgaysinh(),sort2.get(i).getNgaysinh(),
									sort2.get(i).getDiachi(),sort2.get(i).getSdt(),sort2.get(i).getLuong());
						}
						System.out.println("----------------------------------------------------------------------------------------------");
						break;
					case 5:
						List<NhanVien> sortYear = dao.SortYear();
						System.out.printf("%-7s%-13s%-30s%-25s%-20s%-20s%-20s%-10s\n","STT","MSNV","Họ tên nhân viên","Email","Ngày sinh","Địa chỉ","Số điện thoại","Lương");
						for(int i=0; i<sortYear.size();i++) {
							System.out.printf("%-7d%-13d%-30s%-25s%-2td/%-2tm/%-14tY%-20s%-20s%-10s\n",
									i+1,sortYear.get(i).getMsnv(),sortYear.get(i).getHoten(),sortYear.get(i).getEmail(),
									sortYear.get(i).getNgaysinh(),sortYear.get(i).getNgaysinh(),sortYear.get(i).getNgaysinh(),
									sortYear.get(i).getDiachi(),sortYear.get(i).getSdt(),sortYear.get(i).getLuong());
						}
						System.out.println("----------------------------------------------------------------------------------------------");
						break;
					case 6:
						List<NhanVien> groupByAddress = dao.GroupByAddress();
						System.out.printf("%-7s%-13s%-30s%-25s%-20s%-20s%-20s%-10s\n","STT","MSNV","Họ tên nhân viên","Email","Ngày sinh","Địa chỉ","Số điện thoại","Lương");
						for(int i=0; i<groupByAddress.size();i++) {
							System.out.printf("%-7d%-13d%-30s%-25s%-2td/%-2tm/%-14tY%-20s%-20s%-10s\n",
									i+1,groupByAddress.get(i).getMsnv(),groupByAddress.get(i).getHoten(),groupByAddress.get(i).getEmail(),
									groupByAddress.get(i).getNgaysinh(),groupByAddress.get(i).getNgaysinh(),groupByAddress.get(i).getNgaysinh(),
									groupByAddress.get(i).getDiachi(),groupByAddress.get(i).getSdt(),groupByAddress.get(i).getLuong());
						}
						System.out.println("----------------------------------------------------------------------------------------------");
						break;
					}
				}while(z!=0);
			}	
		}while(x!=0);
	}
}
