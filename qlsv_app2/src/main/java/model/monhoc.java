package model;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class monhoc {

	@Id
	private Long mamonhoc;
	private String tenmonhoc;
	private Integer sotinchi;
	//
	@OneToMany(mappedBy = "monhoc", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng diem
	private Collection<diem> diems;
	@OneToMany(mappedBy = "monhoc", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng lop
	private Collection<lop> lops;
}