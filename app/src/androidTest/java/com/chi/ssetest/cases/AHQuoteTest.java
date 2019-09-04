package com.chi.ssetest.cases;


import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.chi.ssetest.StockTestcase;
import com.chi.ssetest.StockTestcaseName;
import com.chi.ssetest.protos.SetupConfig;
import com.chi.ssetest.setup.RunnerSetup;
import com.chi.ssetest.setup.TestcaseConfigRule;
import com.mitake.core.QuoteItem;
import com.mitake.core.bean.log.ErrorInfo;
import com.mitake.core.request.AHQuoteRequest;
import com.mitake.core.request.QuoteRequest;
import com.mitake.core.response.AHQuoteResponse;
import com.mitake.core.response.IResponseInfoCallback;
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
//AH股联动
@RunWith(AndroidJUnit4.class)
@StockTestcase(StockTestcaseName.AHQUOTETEST_1)
public class AHQuoteTest {
    private static final StockTestcaseName testcaseName = StockTestcaseName.AHQUOTETEST_1;
    private static SetupConfig.TestcaseConfig testcaseConfig;

    @BeforeClass
    public static void setup() throws Exception {
        Log.d("AHQuoteTest", "Setup");
        testcaseConfig = RunnerSetup.getInstance().getTestcaseConfig(testcaseName);
        if (testcaseConfig == null ) {
            throw new Exception(String.format("Testcase(%s) setup failed, config is empty", testcaseName));
        }
    }

    @Rule
    public TestcaseConfigRule rule = new TestcaseConfigRule(testcaseConfig);

    @Test(timeout = 5000)
    public void requestWork() throws Exception {
        Log.d("AHQuoteTest", "requestWork");
        // TODO get custom args from param
        final String []quoteNumbers = rule.getParam().optString("CODES", "").split(",");
        final CompletableFuture result = new CompletableFuture<JSONObject>();

        for (int i=0;i<quoteNumbers.length;i++){
            AHQuoteRequest request = new AHQuoteRequest();
            request.send(quoteNumbers[0], new IResponseInfoCallback() {
                @Override
                public void callback(Response response) {
                    AHQuoteResponse ahQuoteResponse = (AHQuoteResponse) response;
                    assertNotNull(ahQuoteResponse);
                    JSONObject uploadObj = new JSONObject();
                    // TODO fill uploadObj with QuoteResponse value
                    try {
//                        uploadObj.put("fake_result", quoteNumbers);
                        uploadObj.put("code",ahQuoteResponse.code);
                        uploadObj.put("lastPrice",ahQuoteResponse.lastPrice);
                        uploadObj.put("premium",ahQuoteResponse.premium);
                        uploadObj.put("preClosePrice",ahQuoteResponse.preClosePrice);
                        uploadObj.put("changeRate",ahQuoteResponse.changeRate);

                    } catch (JSONException e) {
                        result.completeExceptionally(e);
                    }
//                for (QuoteItem item : quoteResponse.quoteItems) {
                    Log.d("StockUnittest", ahQuoteResponse.code);
//                }
                    result.complete(uploadObj);
                }
                @Override
                public void exception(ErrorInfo errorInfo) {
                    result.completeExceptionally(new Exception(errorInfo.toString()));
                }
            });
        }
        try {
            JSONObject resultObj = (JSONObject)result.get(5000, TimeUnit.MILLISECONDS);
            RunnerSetup.getInstance().getCollector().onTestResult(testcaseName, resultObj);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}