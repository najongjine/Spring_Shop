package com.biz.score.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StudentVO {
	private long st_num;// bigint primary key auto_increment,
	private String st_name;// nvarchar(50),
	private int st_class;// int, 학년
	private String st_group;// nvarchar(50)
	
	List<ScoreVO> scoreList;
	
	private int totalScore;
	private int averageScore;
	private int rank;
}
