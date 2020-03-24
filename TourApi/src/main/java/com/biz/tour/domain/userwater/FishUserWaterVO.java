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
public class FishUserWaterVO {
	private long ufwc_id;// bigint AUTO_INCREMENT	PRIMARY KEY,
	private long ufwc_fk;// nVARCHAR(50),
	private String ufwc_username;// nVARCHAR(50),
	private String ufwc_title;// nVARCHAR(150),
	private String ufwc_date;// nVARCHAR(50),
	private String ufwc_text;// nVARCHAR(2000)
}
