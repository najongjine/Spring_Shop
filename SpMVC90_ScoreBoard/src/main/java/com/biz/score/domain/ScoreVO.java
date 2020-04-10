package com.biz.score.domain;

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
public class ScoreVO {
	private long s_id;// bigint primary key,
	private long s_num;// bigint, fk
	private String s_subject;// nvarchar(50),
	private int s_score;// int
}
