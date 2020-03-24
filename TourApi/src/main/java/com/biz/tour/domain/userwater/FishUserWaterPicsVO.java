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
public class FishUserWaterPicsVO {
	private long ufwp_id;// bigint AUTO_INCREMENT	PRIMARY KEY,
	private long ufwp_fk;// nVARCHAR(50),
	private String ufwp_originalFName;// nVARCHAR(50),
	private String ufwp_uploadedFName;// nVARCHAR(150)
}
