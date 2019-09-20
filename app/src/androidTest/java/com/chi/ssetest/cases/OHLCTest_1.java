package com.chi.ssetest.cases;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.chi.ssetest.protos.SetupConfig;
import com.chi.ssetest.setup.RunnerSetup;
import com.chi.ssetest.StockTestcase;
import com.chi.ssetest.StockTestcaseName;
import com.chi.ssetest.setup.TestcaseConfigRule;
import com.mitake.core.AddValueModel;
import com.mitake.core.NewIndex;
import com.mitake.core.OHLCItem;
import com.mitake.core.QuoteItem;
import com.mitake.core.bean.log.ErrorInfo;
import com.mitake.core.parser.FQItem;
import com.mitake.core.request.AddValueRequest;
import com.mitake.core.request.OHLCRequest;
import com.mitake.core.request.OHLChartType;
import com.mitake.core.request.QuoteDetailRequest;
import com.mitake.core.request.QuoteRequest;
import com.mitake.core.request.chart.BidChartRequest;
import com.mitake.core.response.AddValueResponse;
import com.mitake.core.response.IResponseCallback;
import com.mitake.core.response.IResponseInfoCallback;
import com.mitake.core.response.OHLCResponse;
import com.mitake.core.response.QuoteResponse;
import com.mitake.core.response.Response;
import com.mitake.core.response.chart.BidChartResponse;
import com.mitake.core.response.chart.BidItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *k线数据1--无复权
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@StockTestcase(StockTestcaseName.OHLCTEST_1)
public class OHLCTest_1 {
    private static final StockTestcaseName testcaseName = StockTestcaseName.OHLCTEST_1;
    private static SetupConfig.TestcaseConfig testcaseConfig;
  // OHLChartType
    @BeforeClass

    public static void setup() throws Exception {
        Log.d("  OHLCTest_1", "Setup");
        testcaseConfig = RunnerSetup.getInstance().getTestcaseConfig(testcaseName);
        if (testcaseConfig == null ) {
            throw new Exception(String.format("Testcase(%s) setup failed, config is empty", testcaseName));
        }
    }

    @Rule
    public TestcaseConfigRule rule = new TestcaseConfigRule(testcaseConfig);

    @Test(timeout = 5000)
    public void requestWork() throws Exception {
        Log.d(" OHLCTest_1", "requestWork");
        // TODO get custom args from param
        final String quoteNumbers = rule.getParam().optString("stk");
        final String quoteNumbers1 = rule.getParam().optString("type");
      //  OHLChartType
        final CompletableFuture result = new CompletableFuture<JSONObject>();
//        for (int i = 0; i < quoteNumbers.length; i++) {
            QuoteDetailRequest quoteDetailRequest = new QuoteDetailRequest();
            quoteDetailRequest.send(quoteNumbers, new IResponseInfoCallback() {
                @Override
                public void callback(Response response) {
                    QuoteResponse quoteResponse=(QuoteResponse) response;
                    QuoteItem quoteItem = quoteResponse.quoteItems.get(0);
                    final OHLCRequest request = new OHLCRequest();
                    request.send(quoteItem, quoteNumbers1, new IResponseCallback() {
                        @Override
                        public void callback(Response response) {
                            OHLCResponse ohlcResponse = (OHLCResponse) response;
                            try {
                                assertNotNull(ohlcResponse.historyItems);
                            } catch (AssertionError e) {
                                result.completeExceptionally(e);
                            }
                            CopyOnWriteArrayList<OHLCItem> list =ohlcResponse.historyItems;
                            CopyOnWriteArrayList<FQItem> list2=ohlcResponse.fq;
                            List<JSONObject> items=new ArrayList<>();
                            List<JSONObject> items_1=new ArrayList<>();
                            JSONObject uploadObj = new JSONObject();
                            for (int k=0;k<list.size();k++){
                                try {
                                    JSONObject uploadObj_1 = new JSONObject();
                                    uploadObj_1.put("code",quoteNumbers);
                                    uploadObj_1.put("datetime",list.get(k).datetime);
                                    uploadObj_1.put("openPrice",list.get(k).openPrice);
                                    uploadObj_1.put("highPrice",list.get(k).highPrice);
                                    uploadObj_1.put("lowPrice",list.get(k).lowPrice);
                                    uploadObj_1.put("closePrice",list.get(k).closePrice);
                                    uploadObj_1.put("tradeVolume",list.get(k).tradeVolume);
                                    uploadObj_1.put("reference_price",list.get(k).reference_price);
                                    uploadObj_1.put("transaction_price",list.get(k).transaction_price);
                                    uploadObj_1.put("fp_volume",list.get(k).fp_volume);
                                    uploadObj_1.put("fp_amount",list.get(k).fp_amount);
                                    items.add(uploadObj_1);
                                } catch (JSONException e) {
                                    result.completeExceptionally(e);
                                }
                            }
                            if (list2!=null){
                                for (int j=0;j<list2.size();j++){
                                    try {
                                        JSONObject uploadObj_2 = new JSONObject();
                                        uploadObj_2.put("code",quoteNumbers);
                                        uploadObj_2.put("dateTime",list2.get(j).dateTime);
                                        uploadObj_2.put("increasePrice",list2.get(j).increasePrice);
                                        uploadObj_2.put("allotmentPrice",list2.get(j).allotmentPrice);
                                        uploadObj_2.put("bonusAmount",list2.get(j).bonusAmount);
                                        uploadObj_2.put("bonusProportion",list2.get(j).bonusProportion);
                                        uploadObj_2.put("increaseProportion",list2.get(j).increaseProportion);
                                        uploadObj_2.put("increaseVolume",list2.get(j).increaseVolume);
                                        uploadObj_2.put("allotmentProportion",list2.get(j).allotmentProportion);
                                        items_1.add(uploadObj_2);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                try {
                                    uploadObj.put("items_1",new JSONArray(items_1));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    JSONArray jsonArray_1 = uploadObj.getJSONArray("items_1");
                                    for (int i=0;i<jsonArray_1.length();i++){
                                        JSONObject jsonObject = jsonArray_1.getJSONObject(i);
                                        Log.d("data_1", String.valueOf(jsonObject));
                                        //                            System.out.println(jsonObject.optString("code")+","+jsonObject.optString("datetime"));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }else {
                                Log.d("Fqitem","null");
                            }
                            try {
                                uploadObj.put("items",new JSONArray(items));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //解析输出JSON
                            try {
                                JSONArray jsonArray = uploadObj.getJSONArray("items");
                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    Log.d("data", String.valueOf(jsonObject));
                                    //                            System.out.println(jsonObject.optString("code")+","+jsonObject.optString("datetime"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            result.complete(uploadObj);
                        }
                        @Override
                        public void exception(int i, String s) {
                            result.completeExceptionally(new Exception(s));
                        }
                    });
                }
               @Override
               public void exception(ErrorInfo errorInfo) {
                   result.completeExceptionally(new Exception(errorInfo.toString()));
               }
             });
            try {
                JSONObject resultObj = (JSONObject) result.get(5000, TimeUnit.MILLISECONDS);
                RunnerSetup.getInstance().getCollector().onTestResult(testcaseName, rule.getParam(), resultObj);
            } catch (Exception e) {
                throw new Exception(e);
            }
//        }
    }
}