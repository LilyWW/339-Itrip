package com.bdqn.text;

import com.bdqn.entity.HotelEntity;
import com.bdqn.entity.ItripHotelVO;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.util.List;

public class testSolr {
    public static void main(String[] args) throws IOException, SolrServerException {


        HttpSolrClient httpSolrClient=new HttpSolrClient("http://localhost:8080/solr/hotel-Core");

        httpSolrClient.setParser(new XMLResponseParser());//设置相应解析器
        httpSolrClient.setConnectionTimeout(500);//建立连接的最长时间

        SolrQuery solrQuery=new SolrQuery();//新建连接，用于设置查询的对象
        solrQuery.setQuery("*:*");

        solrQuery.setStart(0);
        solrQuery.setRows(100);

        QueryResponse queryResponse= httpSolrClient.query(solrQuery);

        List<ItripHotelVO> list=queryResponse.getBeans(ItripHotelVO.class);//指定返回的对象
        for (ItripHotelVO h:list){
            System.out.println(h.getId()+h.getHotelName()+h.getMinPrice());
        }


    }
}
