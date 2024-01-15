package model;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class lop {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long malop;
	private Integer buoi;
	private Integer ca;
	//
	@OneToMany(mappedBy = "lop", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng buoihoc
	private Collection<buoihoc> buoihocs;
	@ManyToOne
	@JoinColumn(name ="mamonhoc")
	private monhoc monhoc;
	//
	@ManyToOne
	@JoinColumn(name ="magiangvien")
	private giangvien giangvien;
	//

}
