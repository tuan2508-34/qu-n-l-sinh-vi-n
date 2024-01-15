package model;


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
public class giangvien {

	@Id
	private Long magiangvien;
	private String hovaten;
	//
	@OneToOne(mappedBy = "giangvien", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private tkgv tkgv;
	
	
	
	@OneToMany(mappedBy = "giangvien", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng lớp
	private Collection<lop> lops;

}