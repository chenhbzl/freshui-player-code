package com.yingyonghui.market.model;

public class Payment
{
  public class AlipayCardStatus
  {
    public String statusDescription;
    public boolean success = 0;
  }

  public class BeanResult
  {
    public float balance;
    public String hmac;
    public String msg;
    public int payType;
    public boolean success = 0;
    public String transactionId;
  }

  public class YeepayCardResult
  {
    public String hmac;
    public String r0_Cmd;
    public String r6_Order;
    public String rq_ReturnMsg;
    public boolean success = 0;
  }

  public class YeepayCardStatus
  {
    public float amount;
    public float balance;
    public float cardConfirmAmount;
    public String cardNo;
    public float cardRealAmount;
    public String frpId;
    public int id;
    public String statusDescription;
    public boolean success = 0;
    public String time;
    public String transactionId;
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.model.Payment
 * JD-Core Version:    0.6.0
 */