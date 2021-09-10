package com.skeqi.finance.controller.endhandle;

import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.exception.CustomException;
import com.skeqi.finance.domain.endhandle.VchQueryBo;
import com.skeqi.finance.enums.VchSourceEnum;
import com.skeqi.finance.service.endhandle.transfer.ITGlAutoTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/vch")
public class VchEndHandleController {

	@Autowired
	ITGlAutoTransferService iTGlAutoTransferService;

	@PostMapping("/log/queryVchPage")
	public AjaxResult queryVchPage(@Validated  @RequestBody VchQueryBo bo){
		if(null==VchSourceEnum.getObj(bo.getExecuteType())){
			throw new CustomException("凭证来源信息不存在");
		}
		return iTGlAutoTransferService.queryVchLogPage(bo);
	}


	/**
	 * 结账
	 * @return
	 */
	@PostMapping("/settleAcct/{ids}")
	public AjaxResult settleAcct(@NotEmpty(message = "主键不能为空")
									 @PathVariable("ids") String ids){
		List<Integer> idList= Arrays.asList(ids.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return iTGlAutoTransferService.settleAcct(idList);
	}

	/**
	 * 反结账
	 * @return
	 */
	@PostMapping("/settleAcctNo/{ids}")
	public AjaxResult settleAcctNo(@NotEmpty(message = "主键不能为空")
								 @PathVariable("ids") String ids){
		List<Integer> idList= Arrays.asList(ids.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return iTGlAutoTransferService.settleAcctNo(idList);
	}

}
