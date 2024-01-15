package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.Data;

@Data
@Entity
@DynamicInsert
public class tkgv {

	@Id
	@Column(name = "magiangvien")
	private Long magiangvien;
	@ColumnDefault("'123'")
	private String matkhau;

	@OneToOne
	@MapsId
	@JoinColumn(name = "magiangvien")
	private giangvien giangvien;

}
