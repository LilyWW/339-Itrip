package cn.bdqn.itrip;

import cn.itrip.common.DtoUtil;
import cn.itrip.dao.itripAreaDic.ItripAreaDicMapper;
import cn.itrip.dao.itripLabelDic.ItripLabelDicMapper;
import cn.itrip.dto.Dto;
import cn.itrip.pojo.ItripAreaDic;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("api")
public class CityContoller {



    @Resource
    ItripAreaDicMapper dao;

    //热门城市
    @RequestMapping(value = "/hotel/queryhotcity/{type}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public  Dto GetHot(@PathVariable("type") Integer t)
    {
        List<ItripAreaDic> list=dao.getHost(t);
        return DtoUtil.returnDataSuccess(list);
    }
    //查询地域
    @RequestMapping(value = "/hotel/querytradearea/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public  Dto getCityByParent(@PathVariable("cityId") Integer parentId)
    {
        List<ItripAreaDic> list=dao.getCityByParent(parentId);
        return DtoUtil.returnDataSuccess(list);
    }

    @Resource
    ItripLabelDicMapper mapper;
    //酒店
    @RequestMapping(value = "/hotel/queryhotelfeature", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public  Dto getList()
    {
        return DtoUtil.returnDataSuccess(mapper.getList());
    }




}
