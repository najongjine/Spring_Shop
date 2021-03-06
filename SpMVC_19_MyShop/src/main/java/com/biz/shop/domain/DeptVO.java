package com.biz.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * 거래처 정보를 저장할 테이블 Entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "tbl_dept",schema = "emsDB")
public class DeptVO {
	@Id
	@Column(name = "d_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 5,unique = true,nullable = false)
	@Size(min = 5,max = 5)
	@NotEmpty(message = "* 거래처 코드는 반드시 입력 하세요")
	private String d_code;
	
	@Column(length = 30,nullable = false)
	@NotEmpty(message = "* 거래처 코드는 반드시 입력 하세요")
	private String d_name;
	
	@Column(length = 30)
	private String d_ceo;
	
	@Column(length = 13,unique = true,nullable = false)
	@Size(min = 13,max = 13)
	private String d_sid;
	
	@Column(length = 20)
	private String d_tel;
	
	@Column
	private String d_addr;
	
	@Column(length = 30)
	private String d_manager;
	
	@Column(length = 20)
	private String d_mtel;
	
	@Column
	private String d_rem;
}
