package com.chi.ssetest;

public enum StockTestcaseName {
    QUOTE_REQUEST_EXAMPLE("TESTCASE_0"),//测试例子
    ADDVALUETEST_1("ADDVALUE_1"),//增值指标
    ADDVALUETEST_2("ADDVALUE_2"),//增值指标
    AFTERHOURSCHARTTEST_1("AFTERHOURSCHART_1"),//盘后走势接口（科创板）
    AFTERHOURSCHARTTEST_2("AFTERHOURSCHART_2"),//盘后走势接口（科创板）
    AHLISTTEST_1("AHLIST_1"),//AH股列表
    AHLISTTEST_2("AHLIST_2"),//AH股列表
    AHQUOTETEST_1("AHQUOTE_1"),
    BANKUAIQUOTETEST_1("BANKUAIQUOTE_1"),
    BANKUAISORTINGTEST_1("BANKUAISORTING_1"),
    BANKUAISORTINGTEST_2("BANKUAISORTING_2"),
    BIDCHARTTEST_1("BIDCHART_1"),
    BIDCHARTTEST_2("BIDCHART_2"),
    BROKERINFOTEST_1("BROKERINFO_1"),
    CATEQUOTETEST_1("CATEQUOTE_1"),
    CATESORTINGTEST_1("CATESORTING_1"),
    CATESORTINGTEST_2("CATESORTING_2"),
    CATESORTINGTEST_3("CATESORTING_3"),
    CATESORTINGTEST_4("CATESORTING_4"),
    CHARTSUBTEST_1("CHARTSUB_1"),
    CHARTSUBTEST_2("CHARTSUB_2"),
    CHARTV2TEST_1("CHARTV2TEST_1"),
    CHARTV2TEST_2("CHARTV2TEST_2"),
    CHARTV2TEST_3("CHARTV2TEST_3"),
    CHARTV2TEST_4("CHARTV2TEST_4"),
    CHARTV2TEST_5("CHARTV2TEST_5"),
    CHARTV2TEST_6("CHARTV2TEST_6"),
    COMPOUNDUPDOWNTEST_1("COMPOUNDUPDOWN_1"),
    CONVERTIBLETEST_1("CONVERTIBLE_1"),
    DRLINKQUOTETEST_1("DRLINKQUOTE_1"),
    DRQUOTELISTTEST_1("DRQUOTELIST_1"),
    HISTORYCHARTTEST_1("HISTORYCHART_1"),
    HISTORYCHARTTEST_2("HISTORYCHART_2"),
    HKMARINFOTEST_1("HKMARINFO_1"),
    HKPRICEINFOTEST_1("HKPRICEINFO_1"),
    HKSTOCKINFOTEST_1("HKSTOCKINFO_1"),
    HOLIDAYTEST_1("HOLIDAY_1"),
    HSAMOUNTTEST_1("HSAMOUNT_1"),
    L2TICKDETAILV2TEST_1("L2TICKDETAILV2_1"),
    L2TICKV2TEST_1("L2TICKV2_1"),
    MARKETUPDOWNTEST_1("MARKETUPDOWN_1"),
    MOREPRICETEST_1("MOREPRICE_1"),
    MOREPRICETEST_2("MOREPRICE_2"),
    OFFERQUOTETEST_1("OFFERQUOTE_1"),
    OFFERQUOTETEST_2("OFFERQUOTE_2"),
    OHLCSUBTEST_1("OHLCSUB_1"),
    OHLCTEST_1("OHLCTEST_1"),
    OHLCTEST_2("OHLCTEST_2"),
    OHLCV3TEST_1("OHLCV3_1"),
    OHLCV3TEST_2("OHLCV3_2"),
    OHLCV3TEST_3("OHLCV3_3"),
    OHLCV3TEST_4("OHLCV3_4"),
    OHLCV3TEST_5("OHLCV3_5"),
    OPTIONEXPIRETEST_1("OPTIONEXPIRE_1"),
    OPTIONLISTTEST_1("OPTIONLIST_1"),
    OPTIONQUOTETEST_1("OPTIONQUOTE_1"),
    OPTIONTQUOTETEST_1("OPTIONTQUOTE_1"),
    ORDERQUANTITYTEST_1("ORDERQUANTITY_1"),
    ORDERQUANTITYTEST_2("ORDERQUANTITY_2"),
    OVERLAYCHARTTEST_1("OVERLAYCHART_1"),
    OVERLAYCHARTTEST_2("OVERLAYCHART_2"),
    PLATEINDEXQUOTETEST_1("PLATEINDEXQUOTE_1"),
    PLATEINDEXQUOTETEST_2("PLATEINDEXQUOTE_2"),
    QUOTEDETAILTEST_1("QUOTEDETAIL_1"),
    QUOTEDETAILTEST_2("QUOTEDETAIL_2"),
    QUOTEDETAILTEST_3("QUOTEDETAIL_3"),
    QUOTETEST_1("QUOTE_1"),
    QUOTETEST_2("QUOTE_2"),
    SEARCHTEST_1("SEARTEST_1"),
    SEARCHTEST_2("SEARTEST_2"),
    SEARCHTEST_3("SEARTEST_3"),
    SEARCHTEST_4("SEARTEST_4"),
    SEARCHTEST_5("SEARTEST_5"),
    SUBNEWBONDSTOCKRANKINGTEST_1("SUBNEWBONDSTOCKRANKING_1"),
    SUBNEWSTOCKRANKINGTEST_1("SUBNEWSTOCKRANKING_1"),
    TICKTEST_1("TICK_1"),
    TRADEDATETEST_1("TRADEDATE_1"),
    TRADEDATETEST_2("TRADEDATE_2"),
    TRADEQUOTETEST_1("TRADEQUOTE_1"),
    UKQUOTETEST_1("UKQUOTE_1"),
    UPDOWNSTEST_1("UPDOWNS_1"),
//    F10
    F10_BONUSFINANCETEST_1("F10_BONUSFINANCE_1"),
    F10_CALENDARTEST_1("F10_CALENDAR_1"),//新股日历
    F10_COMPANYINFOTEST_1("F10_COMPANYINFO_1"),
    F10_COREBUSINESSTEST_1("F10_COREBUSINESS_1"),
    F10_FININFOIMAGETEST_1("F10_FININFOIMAGE_1"),
    F10_FORECASTRATINGTEST_1("F10_FORECASTRATING_1"),
    F10_FORECASTYEARTEST_1("F10_FORECASTYEAR_1"),
    F10_FUNDSHAREHOLDERINFOTEST_1("F10_FUNDSHAREHOLDERINFO_1"),
    F10_IMPORTANTNOTICETEST_1("F10_IMPORTANTNOTICE_1"),
    F10_LEADERPERSONINFOTEST_1("F10_LEADERPERSONINFO_1"),
    F10_MAINFINADATANASSTEST_1("F10_MAINFINADATANASS_1"),
    F10_MAINFINADATANASSTEST_2("F10_MAINFINADATANASS_2"),
    F10_MAINFINAINDEXNASTEST_1("F10_MAINFINAINDEXNAS_1"),
    F10_MAINFINAINDEXNASTEST_2("F10_MAINFINAINDEXNAS_2"),
    F10_NEWINDEXTEST_1("F10_NEWINDEX_1"),
    F10_NEWSLISTTEST_1("F10_NEWSLIST_1"),
    F10_NEWSTEST_1("F10_NEWS_1"),
    F10_SHAREHOLDERHISTORYINFOTEST_1("F10_SHAREHOLDERHISTORYINFO_1"),
    F10_STOCKBULLETINLISTTEST_1("F10_STOCKBULLETINLIST_1"),
    F10_STOCKBULLETINLISTTEST_2("F10_STOCKBULLETINLIST_2"),
    F10_STOCKBULLETINTEST_1("F10_STOCKBULLETIN_1"),
    F10_STOCKNEWSLISTTEST_1("F10_STOCKNEWSLIST_1"),
    F10_STOCKNEWSLISTTEST_2("F10_STOCKNEWSLIST_2"),
    F10_STOCKNEWSTEST_1("F10_STOCKNEWS_1"),
    F10_STOCKREPORTLISTTEST_1("F10_STOCKREPORTLIST_1"),
    F10_STOCKREPORTLISTTEST_2("F10_STOCKREPORTLIST_2"),
    F10_STOCKREPORTTEST_1("F10_STOCKREPORT_1"),
    F10_STOCKSHARECHANGEINFOTEST_1("F10_STOCKSHARECHANGEINFO_1"),
    F10_STOCKSHAREINFOTEST_1("F10_STOCKSHAREINFO_1"),
    F10_TOPLIQUIDSHAREHOLDERTEST_1("F10_TOPLIQUIDSHAREHOLDER_1"),
    F10_TOPSHAREHOLDERTEST_1("F10_TOPSHAREHOLDER_1"),
    F10_TRADEDETAILTEST_1("F10_TRADEDETAIL_1");

    private String stringVal;
    StockTestcaseName(String numVal) {
        this.stringVal = numVal;
    }

    public String val() {
        return stringVal;
    }

    public static StockTestcaseName fromString(String text) {
        for (StockTestcaseName b : StockTestcaseName.values()) {
            if (b.stringVal.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
