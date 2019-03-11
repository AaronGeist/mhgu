package cn.jestar.convert.weapon;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import cn.jestar.convert.utils.RegexUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by 花京院 on 2019/2/4.
 */
public class WeaponConvertorTest {

    private String mName;
    private WeaponConvertor mConvertor;
    private String[] mUrls;

    @Before
    public void init() {
        mName = "操虫棍";
        mConvertor = new WeaponConvertor(mName);
        mUrls = new String[]{
                "data/1910.html",
                "data/2710.html"
        };
    }

    /**
     * 检查翻译文本
     *
     * @throws Exception
     */
    @Test
    public void checkTranslatedTextTest() throws Exception {
        System.out.println(mConvertor.getNotTranslatedNames());
        System.out.println(mConvertor.getLostNamesInTranslation());
    }

    /**
     * 创建Bean
     *
     * @throws Exception
     */
    @Test
    public void makeBean() throws Exception {
        mConvertor.makeBean(Arrays.asList(mUrls));
    }

    /**
     * 验证正则
     *
     * @throws Exception
     */
    @Test
    public void testRegex() throws Exception {
        String regex = WeaponConvertor.REGEX;
        String regex1 = WeaponConvertor.REGEX1;
        String text1 = "<a href=\"../ida/189724.html\">龙骨【大】</a> x1<br>";
        String text2 = "<a href=\"../ida/219923.html\">辉龙石</a> x10<br>";
        String text3 = "<span style=\"background-color:#FFEFD3;\">入手端材：<a href=\"../ida/229914.html\">骨的上端材</a> x2</span><br>";
        assertTrue(text1.matches(regex));
        assertTrue(text2.matches(regex));
        assertTrue(text3.matches(regex1));
        assertEquals(RegexUtils.getMatchText(text3, regex1), "ida/229914.html");
        WeaponConvertor convertor = new WeaponConvertor(null);
        String url = "ida/230223.html";
        TreeSet<String> set = new TreeSet<>();
        StringBuilder text = convertor.getText(url, set);
        System.out.println(set);
    }

    /**
     * 翻译验证
     *
     * @throws Exception
     */
    @Test
    public void translateStepTest() throws Exception {
        Map<String, String> map = mConvertor.getMap();
        List<String> list = mConvertor.getList(map);
        TreeSet<String> set = new TreeSet<>();
        for (String url : mUrls) {
            mConvertor.translateFile(url, map, list, set);
        }
        mConvertor.translateFile("ida/218109.html", map, list, null);
    }

    /**
     * 补充翻译
     */
    @Test
    public void coustomConvert() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("終","终");
        map.put("強","强");
        map.put("武器的一览です。列名クリックで並び替えが出来ます。","武器一览");
        map.put("期待値についてはこちら","期待值");
        map.put("与えるダメージ期待値","期待值计算相关");
        map.put("初期は期待値的降順","默认以期待值降序排列");
        map.put("LV1的み","LV1");
        List<String> list = mConvertor.getList(map);
        List<String> urls=new ArrayList<>();
        String temp="data/%s.html";
        for (int i = 1900; i <=1913; i++) {
            urls.add(String.format(temp,i));
            urls.add(String.format(temp,i+800));
        }
        for (int i = 2882; i <=2890; i++) {
            urls.add(String.format(temp,i));
        }
        for (int i = 2893; i <=2895; i++) {
            urls.add(String.format(temp,i));
        }
        for (String url : urls) {
            mConvertor.translateFile(url, map, list, null);
        }
    }

    /**
     * 太刀翻译
     *
     * @throws Exception
     */
    @Test
    public void translateTaiDao() throws Exception {
        new WeaponConvertor("太刀").translation();
    }

    /**
     * 盾斧翻译
     *
     * @throws Exception
     */
    @Test
    public void translateDunFu() throws Exception {
        new WeaponConvertor("盾斧").translation();
    }

    /**
     * 重弩翻译
     *
     * @throws Exception
     */
    @Test
    public void translateZhongNu() throws Exception {
        new WeaponConvertor("重弩").translation();
    }

    /**
     * 双剑翻译
     *
     * @throws Exception
     */
    @Test
    public void translateDoubleSword() throws Exception {
        new WeaponConvertor("双剑").translation();
    }

    /**
     * 弓翻译
     *
     * @throws Exception
     */
    @Test
    public void translateBow() throws Exception {
        new WeaponConvertor("弓").translation();
    }

    /**
     * 弓翻译
     *
     * @throws Exception
     */
    @Test
    public void translateQingNu() throws Exception {
        new WeaponConvertor("轻弩").translation();
    }

    /**
     * 操虫棍翻译
     *
     * @throws Exception
     */
    @Test
    public void translateChongGun() throws Exception {
        new WeaponConvertor("操虫棍").translation();
    }
}
