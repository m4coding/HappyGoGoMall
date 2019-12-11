package com.m4coding.mallmbg;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.m4coding.mallmbg.bean.BrandBean;
import com.m4coding.mallmbg.bean.CategoryBean;
import com.m4coding.mallmbg.mbg.mapper.PmsBrandMapper;
import com.m4coding.mallmbg.mbg.mapper.PmsCategoryMapper;
import com.m4coding.mallmbg.mbg.model.PmsBrand;
import com.m4coding.mallmbg.mbg.model.PmsBrandExample;
import com.m4coding.mallmbg.mbg.model.PmsCategoryExample;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan({"com.m4coding.mallmbg.mbg.mapper"})
class MallMbgApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(MallMbgApplicationTests.class);

    @Autowired
    private  PmsCategoryMapper pmsCategoryMapper;
    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    /**
     * 产生分类数据
     */
    @Test
    public void generateCategoryData() {

        PmsCategoryExample pmsCategoryExample = new PmsCategoryExample();
        pmsCategoryExample.createCriteria().andIdIsNotNull();
        pmsCategoryMapper.deleteByExample(pmsCategoryExample);

        List<CategoryBean> list =  Arrays.asList (
                new CategoryBean("服装", getCurrentTime(), getCurrentTime())
                        .add(new CategoryBean("女装", getCurrentTime(), getCurrentTime()))
                        .add(new CategoryBean("男装", getCurrentTime(), getCurrentTime())),

                new CategoryBean("医药保健", getCurrentTime(), getCurrentTime())
                        .add(new CategoryBean("中西药品", getCurrentTime(), getCurrentTime()))
                        .add(new CategoryBean("营养成分", getCurrentTime(), getCurrentTime())),

                new CategoryBean("图书文娱", getCurrentTime(), getCurrentTime())
                        .add(new CategoryBean("文学", getCurrentTime(), getCurrentTime()))
                        .add(new CategoryBean("小说", getCurrentTime(), getCurrentTime()))
                        .add(new CategoryBean("动漫", getCurrentTime(), getCurrentTime())),

                new CategoryBean("手机", getCurrentTime(), getCurrentTime()),

                new CategoryBean("家电", getCurrentTime(), getCurrentTime())
                        .add(new CategoryBean("电视", getCurrentTime(), getCurrentTime()))
                        .add(new CategoryBean("空调", getCurrentTime(), getCurrentTime()))
                        .add(new CategoryBean("洗衣机", getCurrentTime(), getCurrentTime()))
                        .add(new CategoryBean("冰箱", getCurrentTime(), getCurrentTime()))
                        .add(new CategoryBean("厨卫大电", getCurrentTime(), getCurrentTime()))
                        .add(new CategoryBean("厨卫小电", getCurrentTime(), getCurrentTime()))
                        .add(new CategoryBean("生活电器", getCurrentTime(), getCurrentTime()))
        );

        for (CategoryBean categoryBean : list) {
            insertCategory(categoryBean);
        }
    }

    private  Long insertCategory(CategoryBean categoryBean) {
        if (1 == pmsCategoryMapper.insertSelective(categoryBean)) {
            if (categoryBean.getChildList() != null && categoryBean.getChildList().size() > 0) {
                for (int j = 0; j < categoryBean.getChildList().size(); j++) {
                    CategoryBean tmp = categoryBean.getChildList().get(j);
                    tmp.setPid(categoryBean.getId());
                    Long childId = insertCategory(tmp);
                    if (childId != null) {
                        if (!StrUtil.isEmpty(categoryBean.getChildId())) {
                            categoryBean.setChildId(categoryBean.getChildId() + "," + childId);
                        } else {
                            categoryBean.setChildId(childId + "");
                        }

                        pmsCategoryMapper.updateByPrimaryKeySelective(categoryBean);
                    }
                }
            }
        } else {
            LOGGER.error("insertSelective error,  {}", categoryBean.toString());
        }

        return categoryBean.getId();
    }

    /**
     * 产生品牌数据
     */
    @Test
    public void generateBrandData() {
        List<BrandBean> pmsBrandList = Arrays.asList (
                //服装
                new BrandBean("以纯", getCurrentTime(), getCurrentTime()),
                new BrandBean("七匹狼", getCurrentTime(), getCurrentTime()),
                new BrandBean("南极人", getCurrentTime(), getCurrentTime()),
                new BrandBean("秋水伊人", getCurrentTime(), getCurrentTime()),
                //医药保健
                new BrandBean("同仁堂", getCurrentTime(), getCurrentTime()),
                new BrandBean("本草纲目", getCurrentTime(), getCurrentTime()),
                new BrandBean("九芝堂", getCurrentTime(), getCurrentTime()),
                //图书文娱
                new BrandBean("博文视点", getCurrentTime(), getCurrentTime()),
                new BrandBean("人民邮电出版社", getCurrentTime(), getCurrentTime()),
                new BrandBean("电子工业出版社", getCurrentTime(), getCurrentTime()),
                //手机
                new BrandBean("小米", getCurrentTime(), getCurrentTime()),
                new BrandBean("华为", getCurrentTime(), getCurrentTime()),
                new BrandBean("OPPO", getCurrentTime(), getCurrentTime()),
                new BrandBean("VIVO", getCurrentTime(), getCurrentTime()),
                new BrandBean("魅族", getCurrentTime(), getCurrentTime()),
                new BrandBean("三星", getCurrentTime(), getCurrentTime()),
                new BrandBean("一加", getCurrentTime(), getCurrentTime()),
                //家电
                new BrandBean("美的", getCurrentTime(), getCurrentTime()),
                new BrandBean("康佳", getCurrentTime(), getCurrentTime()),
                new BrandBean("格力", getCurrentTime(), getCurrentTime()),
                new BrandBean("TCL", getCurrentTime(), getCurrentTime()),
                new BrandBean("海尔", getCurrentTime(), getCurrentTime())
        );

        for (BrandBean brandBean : pmsBrandList) {
            PmsBrandExample pmsBrandExample = new PmsBrandExample();
            pmsBrandExample.createCriteria().andBrandNameEqualTo(brandBean.getBrandName());
            List<PmsBrand> list =  pmsBrandMapper.selectByExample(pmsBrandExample);
            if (CollUtil.isEmpty(list)) {
                pmsBrandMapper.insertSelective(brandBean);
            }
        }
    }

    @Test
    public void generateSpuData() {

    }

    @Test
    public void generateSkuData() {

    }

    private  long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }

}
