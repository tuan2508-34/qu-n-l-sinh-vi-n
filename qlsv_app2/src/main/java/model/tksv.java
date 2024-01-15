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
public class tksv {

	@Id
	@Column(name = "masinhvien")
	private Long masinhvien;
	@ColumnDefault("'123'")
	private String matkhau;
	@OneToOne
	@MapsId
	@JoinColumn(name = "masinhvien")
	private sinhvien sinhvien;

}
