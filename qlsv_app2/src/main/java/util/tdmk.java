package util;

import dao.tkgvDAO;
import dao.tksvDAO;
import model.tkgv;
import model.tksv;

public class tdmk {
	private Long id;
	private String role;
	private String mkc;
	private String mkm;
	private String xnmk;

	public tdmk(String role, String mkc, String mkm, String xnmk, Long id) {
		super();
		this.role = role;
		this.mkc = mkc;
		this.mkm = mkm;
		this.xnmk = xnmk;
		this.id=id;
	}
	
	public static void main(String[] args) {
		tdmk tdmk=new tdmk("sv","123","321","321",new Long(20182367));
		System.out.println(tdmk.ktmkc());
		System.out.println(tdmk.kt());
	}
   public boolean ktmkc() {
	   if(role.equals("sv")) {
		   tksv tksv=new tksv();
		   tksv.setMasinhvien(id);
		   tksvDAO tksvDao=new tksvDAO();
		   
		   if(tksvDao.selectById(tksv).getMatkhau().equals(mkc)) {
			   return true;
		   }
	   }
	   if(role.equals("gv")) {
		   tkgv tkgv=new tkgv();
		   tkgv.setMagiangvien(id);
		   tkgvDAO tkgvDao=new tkgvDAO();
		   if(tkgvDao.selectById(tkgv).getMatkhau().equals(mkc)) {
			   return true;
		   }
	   }
	   return false;
   }
   public boolean ktxn() {
	   if(mkm.equals(xnmk)) {
		   return true;
	   }
	   return false;
   }
   public String kt() {
	   if(mkc==null||mkc==""||mkm==null||mkm==""||xnmk==null||xnmk=="") {
		   return "Bạn phải nhập đủ thông tin !";
	   }else if(ktxn()==false) {
		   return "Xác nhận mật khẩu mới chưa chính xác !";
	   }
	   else if(ktmkc()==false) {
		   return "Mật khẩu cũ chưa chính xác !";
	   }
	   else {
		   if(role.equals("sv")) {
			   tksv tksv=new tksv();
			   tksv.setMasinhvien(id);
			   tksv.setMatkhau(mkm);
			   tksvDAO tksvDao=new tksvDAO();
			   tksvDao.update(tksv);
		   }else if(role.equals("gv")){
			   tkgv tkgv=new tkgv();
			   tkgv.setMagiangvien(id);
			   tkgv.setMatkhau(mkm);
			   tkgvDAO tkgvDao=new tkgvDAO();
			   tkgvDao.update(tkgv);
		   }
		   return "Đã cập nhật mật khẩu mới !";
	   }
	  
   }
}
