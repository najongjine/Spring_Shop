package com.biz.bbs.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * VO(value object)= DTO(Data Transfer Object) 클래스
 * 메서드와 메서드간에
 * web브라우저와 컨트롤러(메서드)간에 컨트롤러와
 * view.jsp 간에 데이터를 교환하는 매개체 역활을 수행
 * 일반적으로 select되는 table칼럼을 포함하고 web browser에서 form tag 사용하여
 * controller로 데이터를 보낼때 
 * form tag가 포함하는 input tag들의 name값들을 포함한다.
 * VO 클래스는 객체지향 특징중 추상화, 정보은닉, 캡슐화 특징을 포함하고 있다.
 * 초상화: 어떤 필드 변수들을 만들것인가.
 * 정보은닉: 필드변수를 private으로 선언
 * 캡슐화: getter,setter 메서드의 코드 정의
 */

/*
 * mybatis @Alias() mybatis 초기설정에서 typeAliasPackage를 설정하면
 * VO 클래스의 이름을 참조하여 Alias를 만드는데
 * Alias 이름을 명확하게 정의하고자 할때 사용한다.
 */
@Alias("bbsVO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BBsVO {
	private long b_id;//	NUMBER
	private long 	b_p_id;//	NUMBER
	private String b_date_time;//	VARCHAR2(30)
	private String b_writer;//	nVARCHAR2(30)
	private String b_subject;//	nVARCHAR2(125)
	private String b_content;//	nVARCHAR2(2000)
	private String b_file;//	nVARCHAR2(125)

}
