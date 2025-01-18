package com.dging.dgingmarket.ship.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CarrierType {
    CJ_LOGISTICS("CJ대한통운", "kr.cjlogistics"),
    CU_POST("CU 편의점택배", "kr.cupost"),
    CHUNILPS("천일택배", "kr.chunilps"),
    GS_POSTBOX("GS Postbox", "kr.cvsnet"),
    DAESIN("대신택배", "kr.daesin"),
    EPOST("우체국택배", "kr.epost"),
    HOMEPICK("홈픽", "kr.homepick"),
    HANJIN("한진택배", "kr.hanjin"),
    HONAMLOGIS("한서호남택배", "kr.honamlogis"),
    ILYANGLOGIS("일양로지스", "kr.ilyanglogis"),
    KD_EXP("경동택배", "kr.kdexp"),
    KUNYOUNG("건영택배", "kr.kunyoung"),
    LOGEN("로젠택배", "kr.logen"),
    LOTTE("롯데택배", "kr.lotte"),
    TODAY_PICKUP("오늘의픽업", "kr.todaypickup"),
    YONGMALOGIS("용마로지스", "kr.yongmalogis"),
    HANIPS("HPL (한의사랑택배)", "kr.hanips"),
    HDEXP("합동택배", "kr.hdexp");

    private final String name;
    private final String code;

    public static CarrierType fromCode(String code) {
        for (CarrierType carrier : CarrierType.values()) {
            if (carrier.getCode().equals(code)) {
                return carrier;
            }
        }
        throw new IllegalArgumentException("No enum constant for code: " + code);
    }
}


