package com.chi.ssetest.cases;

import android.content.SyncStatusObserver;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.chi.ssetest.StockTestcase;
import com.chi.ssetest.StockTestcaseName;
import com.chi.ssetest.protos.SetupConfig;
import com.chi.ssetest.setup.RunnerSetup;
import com.chi.ssetest.setup.TestcaseConfigRule;
import com.mitake.core.OHLCItem;
import com.mitake.core.QuoteItem;
import com.mitake.core.bean.log.ErrorInfo;
import com.mitake.core.request.OHLCRequestV3;
import com.mitake.core.request.QuoteDetailRequest;
import com.mitake.core.response.IResponseCallback;
import com.mitake.core.response.IResponseInfoCallback;
import com.mitake.core.response.OHLCResponse;
import com.mitake.core.response.QuoteResponse;
import com.mitake.core.response.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//历史K线  方法四历史数据(包含当天,仅支持沪深市场)
@RunWith(AndroidJUnit4.class)
@StockTestcase(StockTestcaseName.OHLCV3TEST_4)
public class OHLCV3Test_4 {
    private static final StockTestcaseName testcaseName = StockTestcaseName.OHLCV3TEST_4;
    private static SetupConfig.TestcaseConfig testcaseConfig;

    @BeforeClass
    public static void setup() throws Exception {
        Log.d("OHLCV3Test_4", "Setup");
        testcaseConfig = RunnerSetup.getInstance().getTestcaseConfig(testcaseName);
        if (testcaseConfig == null ) {
            throw new Exception(String.format("Testcase(%s) setup failed, config is empty", testcaseName));
        }
    }

    @Rule
    public TestcaseConfigRule rule = new TestcaseConfigRule(testcaseConfig);

    @Test(timeout = 5000)
    public void requestWork() throws Exception {
        Log.d("OHLCV3Test_4", "requestWork");
        // TODO get custom args from param
        final String []CODES = rule.getParam().optString("CODES", "").split(",");
        final String []BeginDates = rule.getParam().optString("BeginDates", "").split(",");
        final String []EndDates = rule.getParam().optString("EndDates", "").split(",");
        final String []Types = rule.getParam().optString("TYPES", "").split(",");
        final String []FqTypes = rule.getParam().optString("FqTypes", "").split(",");
//        OHLChartType
        final CompletableFuture result = new CompletableFuture<JSONObject>();

        for (int i=0;i<CODES.length;i++){
            final int a=i;
            QuoteDetailRequest quoteDetailRequest=new QuoteDetailRequest();
            quoteDetailRequest.send(CODES[i], new IResponseInfoCallback() {
                @Override
                public void callback(Response response) {
                    QuoteResponse quoteResponse=(QuoteResponse) response;
                    QuoteItem quoteItem=quoteResponse.quoteItems.get(0);
                    final int b=a;
                    if (BeginDates[b].equals("null")){
                        BeginDates[b]=null;
                    }if (EndDates[b].equals("null")){
                        EndDates[b]=null;
                    }
                    OHLCRequestV3 request = new OHLCRequestV3();
                    request.send(quoteItem, BeginDates[b], EndDates[b], Types[b], Integer.parseInt(FqTypes[b]), new IResponseCallback() {
                        @Override
                        public void callback(Response response) {
                            OHLCResponse ohlcResponse = (OHLCResponse) response;
                            assertNotNull(ohlcResponse.historyItems);
                            JSONObject uploadObj = new JSONObject();
                            // TODO fill uploadObj with QuoteResponse value
                            try {
                                uploadObj.put("fake_result",CODES);
                            } catch (JSONException e) {
                                result.completeExceptionally(e);
                            }
                            for (OHLCItem item : ohlcResponse.historyItems) {
                                Log.d("StockUnittest", CODES[b]+item.datetime);
                            }
                            result.complete(uploadObj);
                        }

                        @Override
                        public void exception(int i, String s) {
                            result.completeExceptionally(new Exception(s));
                        }
                    });
//                    request.send(quoteItem,BeginDates[b],EndDates[b],Types[b],Integer.parseInt(FqTypes[b]), new IResponseInfoCallback() {
//                        @Override
//                        public void callback(Response response) {
//                            OHLCResponse ohlcResponse = (OHLCResponse) response;
//                            assertNotNull(ohlcResponse.historyItems);
//                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
//                            JSONObject uploadObj = new JSONObject();
//                            // TODO fill uploadObj with QuoteResponse value
//                            try {
//                                uploadObj.put("fake_result",CODES);
//                            } catch (JSONException e) {
//                                result.completeExceptionally(e);
//                            }
//                            if (ohlcResponse.historyItems.size()>0){
//                                for (OHLCItem item : ohlcResponse.historyItems) {
//                                    Log.d("StockUnittest", CODES[b]+item.datetime);
//                                }
//                            }else {
//                                System.out.println("null__=+_+=-=-==——+——=——=");
//                            }
//
//                            result.complete(uploadObj);
//                        }
//                        @Override
//                        public void exception(ErrorInfo errorInfo) {
//                            result.completeExceptionally(new Exception(errorInfo.toString()));
//                        }
//                    });
                }
                @Override
                public void exception(ErrorInfo errorInfo) {
                    result.completeExceptionally(new Exception(errorInfo.toString()));
                }
            });
            try {
                JSONObject resultObj = (JSONObject)result.get(5000, TimeUnit.MILLISECONDS);
                RunnerSetup.getInstance().getCollector().onTestResult(testcaseName, resultObj);
            } catch (Exception e) {
                throw new Exception(e);
            }
        }
    }
}