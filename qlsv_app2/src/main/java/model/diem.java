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
public class diem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long madiem;
	private Float value;
	private Boolean loaidiem;
	private String kihoc;
	//

	@ManyToOne
	@JoinColumn(name = "masinhvien")
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
	private sinhvien sinhvien;
	//
	@ManyToOne
	@JoinColumn(name = "mamonhoc")
	private monhoc monhoc;
	//

}
