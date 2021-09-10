package com.skeqi.mes.service.lcy;

import java.util.Date;

public interface GetFlawRateService {

	int getProductionNumber(Date startDate, Date endDate,int lineId);

	int getFlawProductionNumber(Date startDate, Date endDate,int lineId);

}
