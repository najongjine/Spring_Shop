package com.biz.bbs.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Alias("commentVO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CommentVO {
	private long c_id;//	number
	private long c_b_id;//	number 게시판
	private long c_p_id;//	number 댓글에 댓글
	private String c_date_time;//	varchar2(30 byte)
	private String c_writer;//	nvarchar2(30 char)
	private String c_subject;//	nvarchar2(125 char)
}
