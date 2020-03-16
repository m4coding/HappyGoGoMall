package com.m4coding.mallforeground.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.m4coding.mallbase.api.CommonPage;
import com.m4coding.mallforeground.dto.HomeCommonItemResult;
import com.m4coding.mallforeground.dto.HomePageInfoResult;
import com.m4coding.mallforeground.dto.HomeProductCategoryQueryParam;
import com.m4coding.mallforeground.dto.HomeProductCategoryResult;
import com.m4coding.mallforeground.dto.childitem.HomeGalleryChildItem;
import com.m4coding.mallforeground.dto.childitem.HomeProductCardChildItem;
import com.m4coding.mallforeground.dto.childitem.HomeTabChannelChildItem;
import com.m4coding.mallforeground.service.HomePageService;
import com.m4coding.mallmbg.mbg.mapper.*;
import com.m4coding.mallmbg.mbg.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页服务具体实现
 */
@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    private SmsHomeAdvertiseMapper smsHomeAdvertiseMapper;
    @Autowired
    private SmsHomeTabMapper smsHomeTabMapper;
    @Autowired
    private PmsSpuMapper pmsSpuMapper;
    @Autowired
    private PmsSkuMapper pmsSkuMapper;
    @Autowired
    private PmsSpuSkuAttrMapper pmsSpuSkuAttrMapper;
    @Autowired
    private PmsCategoryMapper pmsCategoryMapper;

    @Override
    public HomePageInfoResult pageContentInfo() {

        HomePageInfoResult homePageInfoResult = new HomePageInfoResult();

        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        example.createCriteria().andTypeEqualTo(1).andStatusEqualTo(1);
        example.setOrderByClause("sort desc");
        List<SmsHomeAdvertise> advertiseList = smsHomeAdvertiseMapper.selectByExample(example);

        List<HomeCommonItemResult> sections = new ArrayList<>();
        //广告画栏添加
        if (!CollectionUtil.isEmpty(advertiseList)) {
            List<HomeGalleryChildItem.Child> galleryChildList = new ArrayList<>();
            for (SmsHomeAdvertise smsHomeAdvertise : advertiseList) {
                if (smsHomeAdvertise.getStatus() == 1 && smsHomeAdvertise.getType() == 1) {
                    HomeGalleryChildItem.Child galleryChild = new HomeGalleryChildItem.Child();
                    galleryChild.setImageUrl(smsHomeAdvertise.getPic());
                    galleryChild.setActionUrl(smsHomeAdvertise.getUrl());
                    galleryChildList.add(galleryChild);
                }
            }

            if (!CollectionUtil.isEmpty(galleryChildList)) {
                HomeCommonItemResult<HomeGalleryChildItem> galleryItem = HomeGalleryChildItem.createCommonItem();
                galleryItem.getBody().setItems(galleryChildList);
                sections.add(galleryItem);
            }
        }

        //底部tab
        HomeCommonItemResult<HomeTabChannelChildItem> tabChannelItem = HomeTabChannelChildItem.createCommonItem();
        SmsHomeTabExample smsHomeTabExample = new SmsHomeTabExample();
        List<SmsHomeTab> homeTabList = smsHomeTabMapper.selectByExample(smsHomeTabExample);
        if (!CollectionUtil.isEmpty(homeTabList)) {
            List<HomeTabChannelChildItem.Child> tabChildList = new ArrayList<>();
            for (SmsHomeTab smsHomeTab : homeTabList) {
                HomeTabChannelChildItem.Child tabChild = new HomeTabChannelChildItem.Child();
                tabChild.setTitle(smsHomeTab.getTitle());
                tabChild.setSubTitle(smsHomeTab.getSubtitle());
                tabChild.setType(smsHomeTab.getType());
                tabChildList.add(tabChild);
            }

            if (!CollectionUtil.isEmpty(tabChildList)) {
                HomeCommonItemResult<HomeTabChannelChildItem> tabItem = HomeTabChannelChildItem.createCommonItem();
                tabItem.getBody().setItems(tabChildList);
                sections.add(tabItem);
            }

            homePageInfoResult.setSections(sections);
        }

        return homePageInfoResult;
    }

    @Override
    public CommonPage<HomeCommonItemResult> pageListInfo(Integer pageSize, Integer pageNum) {
        Page page = new Page(pageNum, pageSize); //创建一个默认的page

        List<HomeCommonItemResult> itemList = new ArrayList<>();

        PmsSpuExample pmsSpuExample = new PmsSpuExample();
        PmsSpuExample.Criteria criteria = pmsSpuExample.createCriteria();

        criteria.andProductNameLike("%");
        List<PmsSpu> spuList = pmsSpuMapper.selectByExample(pmsSpuExample);
        for (PmsSpu pmsSpu : spuList) {
            PmsSkuExample pmsSkuExample = new PmsSkuExample();
            pmsSkuExample.createCriteria().andSpuIdEqualTo(pmsSpu.getProductId());
            page = PageHelper.startPage(pageNum, pageSize); //对sku进行分页
            List<PmsSku> skuList = pmsSkuMapper.selectByExample(pmsSkuExample);
            for (PmsSku pmsSku : skuList) {

                HomeProductCardChildItem.Child productCardChildItem = new HomeProductCardChildItem.Child();

                //市场价、销售价
                productCardChildItem.setProductOrgPrice(pmsSku.getMarketPrice() + "");
                productCardChildItem.setProductPrice(pmsSku.getSalePrice() + "");

                //商品图
                List<String> urlList = StrUtil.splitTrim(pmsSku.getBannerUrl(), ",");
                if (!CollectionUtil.isEmpty(urlList)) {
                    productCardChildItem.setImageUrl(urlList.get(0));
                }

                //商品名称
                StringBuilder productNameBuilder = new StringBuilder(pmsSpu.getProductName());
                PmsSpuSkuAttrExample pmsSpuSkuAttrExample = new PmsSpuSkuAttrExample();
                pmsSpuSkuAttrExample.createCriteria().andSkuIdEqualTo(pmsSku.getId());
                List<PmsSpuSkuAttr> pmsSpuSkuAttrList = pmsSpuSkuAttrMapper.selectByExample(pmsSpuSkuAttrExample);
                if (!CollectionUtil.isEmpty(pmsSpuSkuAttrList)) {
                    for (PmsSpuSkuAttr pmsSpuSkuAttr : pmsSpuSkuAttrList) {
                        productNameBuilder.append(" ")
                                .append(pmsSpuSkuAttr.getAttrValueName());
                    }
                }
                productCardChildItem.setProductName(productNameBuilder.toString());

                //加入list
                HomeCommonItemResult<HomeProductCardChildItem> commonItemResult = HomeProductCardChildItem.createItem();
                HomeProductCardChildItem body = commonItemResult.getBody();
                if (CollectionUtil.isEmpty(body.getItems())) {
                    body.setItems(new ArrayList<>());
                }
                body.getItems().add(productCardChildItem);

                itemList.add(commonItemResult);
            }
        }

        return CommonPage.restPage(page, itemList);
    }

    @Override
    public CommonPage<HomeProductCategoryResult> getProductCategoryList(HomeProductCategoryQueryParam homeProductCategoryQueryParam, Integer pageSize, Integer pageNum) {
        List<HomeProductCategoryResult> resultList = new ArrayList<>();

        PmsCategoryExample pmsCategoryExample = new PmsCategoryExample();
        PmsCategoryExample.Criteria criteria = pmsCategoryExample.createCriteria();

        //名称模糊搜索
        String keyWord = StrUtil.isEmpty(homeProductCategoryQueryParam.getKeyword()) ? "" : homeProductCategoryQueryParam.getKeyword();
        criteria.andCategoryNameLike("%" + keyWord + "%")
                .andPidEqualTo(0L);
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<PmsCategory> categoryList = pmsCategoryMapper.selectByExample(pmsCategoryExample);
        for (PmsCategory pmsCategory : categoryList) {
            //加入list
            resultList.add(getChildCategory(pmsCategory));
        }

        return CommonPage.restPage(page, resultList);
    }

    private HomeProductCategoryResult getChildCategory(PmsCategory pmsCategory) {
        HomeProductCategoryResult listProductCategoryResult = new HomeProductCategoryResult();
        listProductCategoryResult.setName(pmsCategory.getCategoryName());
        listProductCategoryResult.setCategoryId(pmsCategory.getId());

        List<String> childIdList = StrUtil.splitTrim(pmsCategory.getChildId(), ",");
        if (!CollectionUtil.isEmpty(childIdList)) {
            List<Long> childIdLongList = new ArrayList<>();
            for (String childIdStr : childIdList) {
                childIdLongList.add(Long.parseLong(childIdStr));
            }
            PmsCategoryExample pmsCategoryExample = new PmsCategoryExample();
            PmsCategoryExample.Criteria criteria = pmsCategoryExample.createCriteria();
            criteria.andIdIn(childIdLongList);

            List<PmsCategory> list = pmsCategoryMapper.selectByExample(pmsCategoryExample);
            if (!CollectionUtil.isEmpty(list)) {
                listProductCategoryResult.setChildCategoryList(new ArrayList<>());
                for (PmsCategory pmsCategory1 : list) {
                    HomeProductCategoryResult temp = getChildCategory(pmsCategory1);
                    listProductCategoryResult.getChildCategoryList().add(temp);
                }
            }
        }

        return listProductCategoryResult;
    }
}
