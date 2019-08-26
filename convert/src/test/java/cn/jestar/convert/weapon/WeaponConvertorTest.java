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
 * Created by ����Ժ on 2019/2/4.
 */
public class WeaponConvertorTest {

    private WeaponConvertor mConvertor;
    private String[] mUrls;

    /**
     * ��ʼ��
     */
    @Before
    public void init() {
        String name = "��";
        mConvertor = new WeaponConvertor(name);
        mUrls = new String[]{
                "data/1904.html",
                "data/2886.html",
                "data/2704.html"
        };
    }

    /**
     * ����Bean
     *
     * @throws Exception
     */
    @Test
    public void makeBean() throws Exception {
        mConvertor.makeBean(Arrays.asList(mUrls));
    }

    /**
     * ������֤
     * ��������һ��ҳ��һ������ҳ
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
        mConvertor.translateFile("ida/219225.html", map, list, null);
    }



    /**
     * ��֤����
     *
     * @throws Exception
     */
    @Test
    public void testRegex() throws Exception {
        String regex = WeaponConvertor.REGEX;
        String regex1 = WeaponConvertor.REGEX1;
        String text1 = "<a href=\"../ida/189724.html\">���ǡ���</a> x1<br>";
        String text2 = "<a href=\"../ida/219923.html\">����ʯ</a> x10<br>";
        String text3 = "<span style=\"background-color:#FFEFD3;\">���ֶ˲ģ�<a href=\"../ida/229914.html\">�ǵ��϶˲�</a> x2</span><br>";
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
     * ��鷭���ı�
     *
     * @throws Exception
     */
    @Test
    public void checkTranslatedTextTest() throws Exception {
        System.out.println(mConvertor.getNotTranslatedNames());
        System.out.println(mConvertor.getLostNamesInTranslation());
    }


    /**
     * ���䷭��
     */
    @Test
    public void subConvert() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("�K", "��");
        map.put("��", "ǿ");
        map.put("������һ���Ǥ�����������å��ǁK���椨�������ޤ���", "����һ��");
        map.put("�ڴ����ˤĤ��ƤϤ�����", "�ڴ�ֵ");
        map.put("�뤨�����`���ڴ���", "�ڴ�ֵ�������");
        map.put("���ڤ��ڴ����Ľ��", "Ĭ�����ڴ�ֵ��������");
        map.put("LV1�Ĥ�", "LV1");
        List<String> list = mConvertor.getList(map);
        List<String> urls = new ArrayList<>();
        String temp = "data/%s.html";
        for (int i = 1900; i <= 1913; i++) {
            urls.add(String.format(temp, i));
            urls.add(String.format(temp, i + 800));
        }
        for (int i = 2882; i <= 2890; i++) {
            urls.add(String.format(temp, i));
        }
        for (int i = 2893; i <= 2895; i++) {
            urls.add(String.format(temp, i));
        }
        for (String url : urls) {
            mConvertor.translateFile(url, map, list, null);
        }
    }

    /**
     * ̫������
     *
     * @throws Exception
     */
    @Test
    public void translateTaiDao() throws Exception {
        new WeaponConvertor("̫��").translation();
    }

    /**
     * �ܸ�����
     *
     * @throws Exception
     */
    @Test
    public void translateDunFu() throws Exception {
        new WeaponConvertor("�ܸ�").translation();
    }

    /**
     * ������
     *
     * @throws Exception
     */
    @Test
    public void translateZhongNu() throws Exception {
        new WeaponConvertor("����").translation();
    }

    /**
     * ˫������
     *
     * @throws Exception
     */
    @Test
    public void translateDoubleSword() throws Exception {
        new WeaponConvertor("˫��").translation();
    }

    /**
     * ������
     *
     * @throws Exception
     */
    @Test
    public void translateBow() throws Exception {
        new WeaponConvertor("��").translation();
    }

    /**
     * ������
     *
     * @throws Exception
     */
    @Test
    public void translateQingNu() throws Exception {
        new WeaponConvertor("����").translation();
    }

    /**
     * �ٳ������
     *
     * @throws Exception
     */
    @Test
    public void translateChongGun() throws Exception {
        new WeaponConvertor("�ٳ��").translation();
    }

    /**
     * �ǹ����
     *
     * @throws Exception
     */
    @Test
    public void translateChongQiang() throws Exception {
        new WeaponConvertor("�ǹ").translation();
    }

    /**
     * �ǹ����
     *
     * @throws Exception
     */
    @Test
    public void translateChangQiang() throws Exception {
        new WeaponConvertor("��ǹ").translation();
    }

    /**
     * �ǹ����
     *
     * @throws Exception
     */
    @Test
    public void translateDaChui() throws Exception {
        new WeaponConvertor("��").translation();
    }
}
