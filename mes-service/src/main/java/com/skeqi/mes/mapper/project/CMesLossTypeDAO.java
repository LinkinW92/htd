package com.skeqi.mes.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.project.CMesLossReasonT;
import com.skeqi.mes.pojo.project.CMesLossTypeT;

@Component
@MapperScan
public interface CMesLossTypeDAO {

	@Select("<script>" +
			"select * from c_mes_loss_type_t where 1=1"+
			  "<if test=\"name!='' and name!=null \"> and LOSS_NAME=#{name} </if>"
			  + "</script>")
	public List<CMesLossTypeT> findAllLoss(@Param("name")String name);


	@Insert("insert into c_mes_loss_type_t(LOSS_NAME,NOTE,DT) values(#{name},#{note},now())")
	public Integer addLoss(@Param("name")String name,@Param("note")String note);



	@Update("update c_mes_loss_type_t set LOSS_NAME=#{name},NOTE=#{note} where ID=#{id} ")
	public Integer updateLoss(@Param("name")String name,@Param("note")String note,@Param("id")Integer id);


	@Delete("delete from c_mes_loss_type_t where ID=#{id}")
	public Integer delLoss(@Param("id")Integer id);


	@Select("select reason.*,type.LOSS_NAME as lossName from c_mes_loss_reason_t reason,c_mes_loss_type_t type where type.ID=reason.LOSS_ID")
	public List<CMesLossReasonT> findReason();

	@Insert("insert into c_mes_loss_reason_t(REASON_NO,NAME,LOSS_ID,NOTE) values(#{reasonNo},#{name},#{lossId},#{note})")
	public Integer addReason(@Param("reasonNo")String reasonNo,@Param("lossId")Integer lossId,@Param("name")String name,@Param("note")String note);

	@Select("select count(*) from c_mes_loss_reason_t where NAME=#{name}")
	public Integer findByName(@Param("name")String name);

	@Select("select  count(*)  from c_mes_loss_reason_t where REASON_NO=#{reasonNo}")
	public Integer findByNo(@Param("reasonNo")String reasonNo);

	@Update("update c_mes_loss_reason_t set NAME=#{name},LOSS_ID=#{lossId},NOTE=#{note},REASON_NO=#{reasonNo}   where ID=#{id}")
	public Integer updateReason(@Param("reasonNo")String reasonNo,@Param("lossId")Integer lossId,@Param("name")String name,@Param("note")String note,@Param("id")Integer id);

	@Delete("delete from c_mes_loss_reason_t where ID=#{id}")
	public Integer delReason(Integer id);


	@Select("select count(*) from c_mes_loss_reason_t where NAME=#{name} and ID != #{id}")
	public Integer findByName1(@Param("name")String name,@Param("id")Integer id);

	@Select("select  count(*)  from c_mes_loss_reason_t where REASON_NO=#{reasonNo}  and ID != #{id}")
	public Integer findByNo1(@Param("reasonNo")String reasonNo,@Param("id")Integer id);

	@Select("select NAME as name from c_mes_loss_reason_t where REASON_NO=#{reasonNo} ")
	public String findReasonNameByNO(String id);
}
