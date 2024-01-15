package model;



import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class sinhvien {

	@Id
	private Long masinhvien;
	private String hovadem;
	private String ten;
	private Integer gioitinh;
	private String quequan;
	private String dantoc;
	private LocalDate date;
	private Integer trangthaihoc;
	//
	@OneToOne(mappedBy = "sinhvien", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private tksv tksv;
	
	
	
	@OneToMany(mappedBy = "sinhvien", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng diem
     //MapopedBy trỏ tới tên biến Address ở trong Person.
     //@EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
     //@ToString.Exclude // Khoonhg sử dụng trong toString()
	private Collection<diem> diems;
	
	
	@OneToMany(mappedBy = "sinhvien", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng buoihoc
    //MapopedBy trỏ tới tên biến Address ở trong Person.
    //@EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    //@ToString.Exclude // Khoonhg sử dụng trong toString()
	private Collection<buoihoc> buoihocs;
	//


	public sinhvien(Long masinhvien, String hovadem, String ten, Integer gioitinh, String quequan, String dantoc,
			LocalDate date, Integer trangthaihoc) {
		super();
		this.masinhvien = masinhvien;
		this.hovadem = hovadem;
		this.ten = ten;
		this.gioitinh = gioitinh;
		this.quequan = quequan;
		this.dantoc = dantoc;
		this.date = date;
		this.trangthaihoc = trangthaihoc;
	}


	
	
}