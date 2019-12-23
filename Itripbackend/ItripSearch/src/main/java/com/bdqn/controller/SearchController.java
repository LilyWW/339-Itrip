package com.bdqn.controller;

import cn.itrip.common.DtoUtil;
import cn.itrip.dto.Dto;
import com.bdqn.dao.BaseSolr;
import com.bdqn.entity.ItripHotelVO;
import com.bdqn.entity.Page;
import com.bdqn.entity.SearchHotCityVO;
import com.bdqn.entity.SearchHotelVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class SearchController {

    @RequestMapping(value = "/api/hotellist/searchItripHotelListByHotCity", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto getList(@RequestBody SearchHotCityVO searchHotCityVO) throws IOException, SolrServerException {
        System.out.print(searchHotCityVO.getCityId());
        BaseSolr baseSolr=new BaseSolr();
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.addFilterQuery("cityId:"+searchHotCityVO.getCityId());
        List<ItripHotelVO> list = baseSolr.getList(solrQuery);
        return DtoUtil.returnDataSuccess(list);
    }

    //分页显示数据

    @RequestMapping(value = "/api/hotellist/searchItripHotelPage", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto< Page<ItripHotelVO>> getListByPage(@RequestBody SearchHotelVO searchHotVO) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        BaseSolr baseSolr=new BaseSolr();
        //当前页
        if(searchHotVO.getPageNo()==null){
            searchHotVO.setPageNo(1);
        }
        //页面容量
        if(searchHotVO.getPageSize()==null){
            searchHotVO.setPageSize(6);
        }
        //关键字
        if(searchHotVO.getKeywords()!=null){
            solrQuery.addFilterQuery("keyword:"+searchHotVO.getKeywords());
        }
        //目的地
        if(searchHotVO.getDestination()!=null){
            solrQuery.addFilterQuery("destination:"+searchHotVO.getDestination());
        }
        //星级
        if(searchHotVO.getHotelLevel()!=null){
            solrQuery.addFilterQuery("hotelLevel:"+searchHotVO.getHotelLevel());
        }
        //特色
        if(searchHotVO.getFeatureIds()!=null){
            String [] str=searchHotVO.getFeatureIds().split(",");
            for(int i=0;i<str.length;i++) {
                if (i == 0) {
                    solrQuery.addFilterQuery("featureIds:*" + str[i] + "*");
                } else {
                    solrQuery.addFilterQuery(" or featureIds:*" + str[i] + "*");
                }
            }
        }
        //地理位置
        if(searchHotVO.getTradeAreaIds()!=null){
            String [] str=searchHotVO.getTradeAreaIds().split(",");
            for(int i=0;i<str.length;i++) {
                if (i == 0) {
                    solrQuery.addFilterQuery("tradingAreaIds:*" + str[i] + "*");
                } else {
                    solrQuery.addFilterQuery(" or tradingAreaIds:*" + str[i] + "*");
                }
            }
        }
        //价格
        if(searchHotVO.getMinPrice()!=null){
            solrQuery.addFilterQuery("minPrice:["+searchHotVO.getMinPrice()+" TO *]");
        }
        if(searchHotVO.getMaxPrice()!=null){
            solrQuery.addFilterQuery("minPrice:[* TO "+searchHotVO.getMaxPrice()+"]");
        }

        Page<ItripHotelVO> page=baseSolr.getListByPage(solrQuery,searchHotVO.getPageNo(),searchHotVO.getPageSize());


        return DtoUtil.returnDataSuccess(page);
    }

}
