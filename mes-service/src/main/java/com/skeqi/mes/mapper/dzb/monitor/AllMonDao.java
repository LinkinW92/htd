package com.skeqi.mes.mapper.dzb.monitor;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AllMonDao {

    Map findKanbanById(@Param("kanban") String kanban,@Param("id")int id);
    int getFlag();
    int getAuthFlag();
    List<Map> findKanbanList(@Param("kanban") String kanban);
    int haveImage(@Param("kanban") String kanban,@Param("id") int id);
    int saveKanban(Map saveMap);
    int copyKanban(Map copyMap);
    void updateKanban(Map updateMap);
    void removeKanban(@Param("kanban") String kanban,@Param("id")int id);
    void saveBgimg(@Param("path") String path);
    List listBgImg();
    void removeBgImg(@Param("path") String path);

    //region 菜单操作
    void saveMune(@Param("kanban") String kanban,@Param("name") String name,@Param("id") int id);
    void removeMenu(@Param("kanban") String kanban,@Param("id") int id);
    void updateMenu(@Param("kanban") String kanban,@Param("name") String name,@Param("id") int id);
    //endregion

    Map orderInfo();
    List<Map> listOrder();
//    Map workOrderInfo();
    Integer outputByHourAndLine(@Param("condits") String condits,@Param("hourNo") int hourNo);
    Integer okByHourAndLine(@Param("condits") String condits,@Param("hourNo") int hourNo);
    Integer ngByHourAndLine(@Param("condits") String condits,@Param("hourNo") int hourNo);
    Integer outputByDayAndLine(@Param("condits") String condits,@Param("dayNo") int dayNo);
    Integer okByDayAndLine(@Param("lineId") int lineId,@Param("dayNo") int dayNo);
    Integer ngByDayAndLine(@Param("lineId") int lineId,@Param("dayNo") int dayNo);
    Integer okByMonthAndLine(@Param("condits") String condits,@Param("monthNo") int monthNo);
    Integer ngByMonthAndLine(@Param("condits") String condits,@Param("monthNo") int monthNo);
    Integer okByYearAndLine(@Param("condits") String condits,@Param("yearNo") int yearNo);
    Integer ngByYearAndLine(@Param("condits") String condits,@Param("yearNo") int yearNo);



    void addSn(@Param("barcode")String barcode,@Param("status")String status);

    String getObjName(@Param("eleId")Integer eleId);
    List listAttr(@Param("eleId")Integer eleId);
    List listCondit(@Param("eleId")Integer eleId);

    Integer getObjIdByEleId(@Param("eleId")Integer eleId);
    List<Integer>  listAttrIdsByEleId(@Param("eleId")Integer eleId);
    List listObjsByEleName(@Param("eleName")String eleName);
    List listAttrsByObjId(@Param("objId")Integer objId);
    void remarkEleAttrByEleId(@Param("eleId")Integer eleId);
    void saveEleAttrs(@Param("eleId")Integer eleId,@Param("attrs")int[] attrs);
    Integer getMaxEleId();
    String getApiByAttrId(int attrId);
    void removeAttrsByEleId(int eleId);
}
