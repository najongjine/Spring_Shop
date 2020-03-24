package com.biz.tour.domain.userwater;

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
public class FishUserWaterCommentVO {
	private long ufw_id ;// AUTO_INCREMENT	PRIMARY KEY,
    private String ufw_username;// nVARCHAR(50),
    private String ufw_title;// nVARCHAR(150),
    private String ufw_date;// nVARCHAR(50),
    private String ufw_text;// nVARCHAR(2000)
}
