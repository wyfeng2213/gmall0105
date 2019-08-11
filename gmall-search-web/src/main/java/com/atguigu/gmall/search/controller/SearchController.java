package com.atguigu.gmall.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.annotations.LoginRequired;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.service.AttrService;
import com.atguigu.gmall.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author : 熊亚东
 * @description :
 * @date : 2019/7/15 | 8:54
 **/
/*跨域操作@CrossOrigin，前后端分离*/
@Controller
@CrossOrigin
public class SearchController {

    @Reference
    SearchService searchService;

    @Reference
    AttrService attrService;

    /**
     * 可能存在问题 1.可变参数的方法  2.面包屑
     * @param pmsSearchParam
     * @param modelMap
     * @return
     */
    @RequestMapping("list.html")
    public String list(PmsSearchParam pmsSearchParam, ModelMap modelMap) {//接受前端的三级分类id，关键字。。。。。
        /*调用搜索服务，返回搜索结果*/
        List<PmsSearchSkuInfo> pmsSearchSkuInfos = null;
        try {
            pmsSearchSkuInfos = searchService.list(pmsSearchParam);
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelMap.put("skuLsInfoList", pmsSearchSkuInfos);
        /*抽取检索结果的平台属性，平台属性有重复，可以利用set集合的不可重复性去重*/
        Set<String> valueIdSet = new HashSet<>();
        for (PmsSearchSkuInfo pmsSearchSkuInfo : pmsSearchSkuInfos) {
            List<PmsSkuAttrValue> pmsSkuAttrValue = pmsSearchSkuInfo.getPmsSkuAttrValue();
            for (PmsSkuAttrValue skuAttrValue : pmsSkuAttrValue) {
                String valueId = skuAttrValue.getValueId();
                valueIdSet.add(valueId);
            }
        }
        System.out.println("程序运行到这里");
        /*根据valueId将相关属性列表查询出来*/
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = attrService.getAttrValueListByValueId(valueIdSet);
        modelMap.put("attrList", pmsBaseAttrInfos);


        /*================去掉当前valueId所在的属性组*/
        String[] delValueIds = pmsSearchParam.getValueId();
        /*判断请求路径中是否有valueId*/
        if (delValueIds != null) {
            //====1.面包屑实现
            List<PmsSearchCrumb> pmsSearchCrumbs = new ArrayList<>();
            //如果valuelds参数不为空,说明当前请求中包含属性的参数,每一个属性参数,都会生成一个面包屑
            for (String delValueId : delValueIds) {
                /*iterator()迭代器,删除数据后数组不会重新组合，导致数组下标越界出错 */
                //需要重新获取一次迭代器
                Iterator<PmsBaseAttrInfo> iterator = pmsBaseAttrInfos.iterator();

                //====2.生成面包屑参数
                PmsSearchCrumb pmsSearchCrumb = new PmsSearchCrumb();
                pmsSearchCrumb.setValueId(delValueId);
                pmsSearchCrumb.setUrlParam(getUrlParam(pmsSearchParam, delValueId));

                while (iterator.hasNext()) {
                    PmsBaseAttrInfo pmsBaseAttrInfo = iterator.next();
                    List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getPmsBaseAttrValueList();
                    for (PmsBaseAttrValue attrValue : attrValueList) {
                        String valueId = attrValue.getId();
                        /*判断pmsBaseAttrInfo中属性是否有与pmsSearchParam中相同的属性*/
                        if (delValueId.equals(valueId)) {
                            //=====3.面包屑的名称
                            pmsSearchCrumb.setValuleName(attrValue.getValueName());

                            /*有该属性就删除该属性组*/
                            iterator.remove();
                        }
                    }
                }
                pmsSearchCrumbs.add(pmsSearchCrumb);
            }
            // 4.面包屑
            modelMap.put("attrValueSelectedList", pmsSearchCrumbs);
        }

        /*获取参数地址*/
        String urlParam = getUrlParam(pmsSearchParam);
        /*实现面包屑的请求参数：等于当前地址加上新请求的地址，新请求的地址就是urlParam,在前端请求时候有一个字符串的拼接*/
        modelMap.put("urlParam", urlParam);
        /*前台接受实现并显示面包屑*/
        String keyword = pmsSearchParam.getKeyword();
        if (StringUtils.isNotBlank(keyword)) {
            modelMap.put("keyword", keyword);
        }
        return "list";
    }

    /**
     * 可变型参数
     *
     * @param pmsSearchParam
     * @param delValueId
     * @return
     */
    private String getUrlParam(PmsSearchParam pmsSearchParam, String... delValueId) {
        String keyword = pmsSearchParam.getKeyword();
        String catalog3Id = pmsSearchParam.getCatalog3Id();
        String[] pmsSkuAttrValue = pmsSearchParam.getValueId();

        String urlParam = "";
        /*search.gmall.com/list.html?keyword=荣耀&valueId=1请求路径第一次前面不能加&符号
        * 所以if (StringUtils.isNotBlank(urlParam)){
              urlParam = urlParam + "&";
            }判断一下。
        */
        if (StringUtils.isNotBlank(keyword)) {
            if (StringUtils.isNotBlank(urlParam)) {
                urlParam = urlParam + "&";
            }
            urlParam = urlParam + "keyword=" + keyword;
        }
        if (StringUtils.isNotBlank(catalog3Id)) {
            if (StringUtils.isNotBlank(urlParam)) {
                urlParam = urlParam + "&";
            }
            urlParam = urlParam + "catalog3Id=" + catalog3Id;
        }
        if (pmsSkuAttrValue != null) {
            for (String skuAttrValue : pmsSkuAttrValue) {
                if (StringUtils.isNotBlank(urlParam)) {
                    urlParam = urlParam + "&";
                }
                // =====排除面包屑的valueId
                if (!skuAttrValue.equals(delValueId)) {
                    urlParam = urlParam + "valueId=" + skuAttrValue;
                }
            }
        }
        return urlParam;
    }

//    private String getUrlParam(PmsSearchParam pmsSearchParam) {
//        String keyword = pmsSearchParam.getKeyword();
//        String catalog3Id = pmsSearchParam.getCatalog3Id();
//        String[] pmsSkuAttrValue = pmsSearchParam.getValueId();
//
//        String urlParam = "";
//        /*search.gmall.com/list.html?keyword=荣耀&valueId=1请求路径第一次前面不能加&符号
//        * 所以if (StringUtils.isNotBlank(urlParam)){
//              urlParam = urlParam + "&";
//            }判断一下。
//        */
//        if (StringUtils.isNotBlank(keyword)) {
//            if (StringUtils.isNotBlank(urlParam)) {
//                urlParam = urlParam + "&";
//            }
//            urlParam = urlParam + "keyword=" + keyword;
//        }
//        if (StringUtils.isNotBlank(catalog3Id)) {
//            if (StringUtils.isNotBlank(urlParam)) {
//                urlParam = urlParam + "&";
//            }
//            urlParam = urlParam + "catalog3Id=" + catalog3Id;
//        }
//        if (pmsSkuAttrValue != null) {
//            for (String skuAttrValue : pmsSkuAttrValue) {
//                if (StringUtils.isNotBlank(urlParam)) {
//                    urlParam = urlParam + "&";
//                }
//                urlParam = urlParam + "valueId=" + skuAttrValue;
//            }
//        }
//        return urlParam;
//    }

    @RequestMapping("index")
    @LoginRequired(loginSuccess = false)/*拦截器*/
    public String index(HttpServletRequest request, ModelMap modelMap) {
        String token = request.getParameter("token");
        modelMap.put("token", token);
        return "index";
    }

}
