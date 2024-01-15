package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class buoihoc {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long mabuoihoc;
	
	private Integer sobuoivang;
	private String kihoc;
	//
	@ManyToOne
	@JoinColumn(name ="masinhvien")
	private sinhvien sinhvien;
	@ManyToOne
	@JoinColumn(name ="malop")
	private lop lop;
}
