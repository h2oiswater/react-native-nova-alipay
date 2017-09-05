package cn.idealsense.reactnative.alipay;

import com.alipay.sdk.app.PayTask;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;

/**
 * Created by gongjiangpeng on 05/09/2017.
 */

public final class AlipayMoudle extends ReactContextBaseJavaModule {

  /**
   * Alipay result status enum.
   */
  private enum ResultStatus {
    /**
     * 订单支付成功.
     */
    SUCCESS(9000),
    /**
     * 正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态.
     */
    PROCESSING(8000),
    /**
     * 订单支付失败.
     */
    FAILED(4000),
    /**
     * 重复请求.
     */
    REPEATED(5000),
    /**
     * 用户中途取消.
     */
    USER_CANCEL(6001),
    /**
     * 网络连接出错.
     */
    NETWORK_ERROR(6002),
    /**
     * 支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态.
     */
    UNKNOW(6004);
    /**
     * status.
     */
    private int mStatus;

    /**
     * default constructor.
     * @param status status.
     */
    ResultStatus(final int status) {
      this.mStatus = status;
    }

    /**
     * Get status.
     * @return status.
     */
    public int getStatus() {
      return mStatus;
    }
  }

  /**
   * default constructor.
   * @param reactContext params.
   */
  public AlipayMoudle(final ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "AlipayModule";
  }

  /**
   * Alipay method.
   * @param payInfo signed pay string.
   * @param promise promise.
   */
  @ReactMethod
  public void pay(final String payInfo, final Promise promise) {
    Runnable payRunnable = new Runnable() {
      @Override
      public void run() {
        try {
          PayTask alipay = new PayTask(getCurrentActivity());
          Map<String, String> result = alipay.payV2(payInfo, true);
          String resultStatus = result.get("resultStatus");

          int status;
          try {
            status = Integer.parseInt(resultStatus);
          } catch (NumberFormatException e) {
            status = -1;
          }

          String memo;

          if (result.get("memo") == null) {
            memo = "Unknown error";
          } else {
            memo = result.get("memo");
          }

          if (status >= ResultStatus.PROCESSING.getStatus()) {
            promise.resolve("success");
          } else {
            promise.reject(memo,
                new RuntimeException(
                    memo.concat(" with result status")
                        .concat(String.valueOf(status))));
          }
        } catch (Exception e) {
          promise.reject(e.getLocalizedMessage(), e);
        }
      }
    };

    Thread payThread = new Thread(payRunnable);
    payThread.start();
  }
}
