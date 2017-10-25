package com.gym.shancai.http.base;

public class ServerUrl {
    static com.gym.shancai.http.base.ServerUrl instance;

    public static com.gym.shancai.http.base.ServerUrl getInstance() {
        synchronized (com.gym.shancai.http.base.ServerUrl.class) {
            if (instance == null) {
                synchronized (com.gym.shancai.http.base.ServerUrl.class) {
                    instance = new ServerUrl();
                }
            }
        }

        return instance;
    }

    /**
     * 服务器地址
     */
    public static String baseUrl = "http://shancai.zhuandj.com/index.php/Api/";
    public static String ServerUrl;

    public static String[] debugUrl = {"http://192.168.1.160/HotelConsortia/api/"};//测试服务器/H5GoodsInfo/companyInfo
//    public static String debug2Url = "http://192.168.1.102/zdjapi/api.php/Home/";//测试服务器/H5GoodsInfo/companyInfo


    /**
     * 接口地址
     */
    public String login = ServerUrl + "/Login/login";//登录
    public String home = ServerUrl + "/First/Index";//首页
    public String activityImage = ServerUrl + "/Activity/souyeActivityContent"; //首页活动图片

    public String search = ServerUrl + "/Activity/search"; //搜索接口

    public String my = ServerUrl + "/Mine/mine";//我的信息显示
    public String coolSummer = ServerUrl + "/Activity/coolsummer"; //清凉一夏
    public String findGoods = ServerUrl + "/Activity/findGoods"; //发现好货
    public String tasty = ServerUrl + "/Activity/tasty"; //好吃不停
    public String topline = ServerUrl + "/Activity/topline"; //品牌头条
    public String prefecture = ServerUrl + "/Activity/prefecture"; //预制菜专区
    public String newProduct = ServerUrl + "/Activity/newproduct"; //新品上市接口
    public String rankingList = ServerUrl + "/Activity/rankingList"; //排行榜接口

    public String specialToday = ServerUrl + "/Activity/specialToday"; //今日特价接口
    public String brand = ServerUrl + "/Activity/speperformance"; //品牌专场接口
    public String origin = ServerUrl + "/Activity/producingarea"; //产地直销

    public String sendMessage = ServerUrl + "/SendMessage/messageSend"; //发送验证码
    public String checkMessage = ServerUrl + "/SendMessage/checkMessage";//注册验证码对比
    public String forgetPWMessage = ServerUrl + "/SendMessage/forgerPassMessageSend";// 忘记密码发送验证码
    public String forgetPass = ServerUrl + "/Mine/forgetPass";// 忘记密码接口
    public String verifyForgetPWMes = ServerUrl + "/SendMessage/forgetPassCheckMessage";//忘记密码验证码验证对比
    public String registerSetPW = ServerUrl + "/Register/register"; //注册接口(填写密码下一步)
    public String selfInfo = ServerUrl + "/Mine/selfInformation"; //个人信息接口
    public String storeInfo = ServerUrl + "/Register/openStore"; //店铺信息接口

    public String checkPass = ServerUrl + "/Mine/editPass"; //修改密码
    public String changePhone = ServerUrl + "/Mine/changePhone"; //修改手机号
    public String changeUserName = ServerUrl + "/Mine/changeUsername"; //修改用户名
    public String changeAvatar = ServerUrl + "/Mine/editAvatar"; //修改头像接口
    public String editPass = ServerUrl + "/Mine/editPayPass"; //修改支付密码
    public String forgetPayPW = ServerUrl + "/Mine/forgetPayPass"; //忘记支付密码接口


    public String serverPhone = ServerUrl + "/Mine/serverPhone";// 客服电话接口
    public String classifyHeadData = ServerUrl + "/Type/rootType"; //分类列表 一级菜单
    public String classifyLeftData = ServerUrl + "/Type/sonType";//分类列表 左列表内容
    public String classifyRightData = ServerUrl + "/Type/typeShowgoods";//分类列表 右列表内容

    //省市区数据
    public String getProvinceDataList = ServerUrl + "/Linkage/province";//获取省列表
    public String getCityDataList = ServerUrl + "/Linkage/city";//获取市列表
    public String getAreaDataList = ServerUrl + "/Linkage/area";//获得区列表

    public String commonInventory = ServerUrl + "/Mine/collect"; //常用清单
    public String myWallet = ServerUrl + "/MyWallet/walletIndex"; //我的钱包首页
    public String myWalletTopUp = ServerUrl + "/MyWallet/chargeActivity"; //充值活动页面
    public String billDetail = ServerUrl + "/Mine/detail"; //账单明细
    public String detail = ServerUrl + "/MyWallet/trateDetail"; //交易明细
    public String monthDetail = ServerUrl + "/Mine/monthDetail"; //账单明细月账单
    public String lackMoneyQuery = ServerUrl + "/MyWallet/overOrLower"; //差款查询
    public String refundPayment = ServerUrl + "/MyWallet/backMoney"; //退款收款


    public String commonQuestions = ServerUrl + "/Mine/question"; //常用问题接口

    public String addressList = ServerUrl + "/AddressManagement/addressIndex"; //地址列表
    public String delAddress = ServerUrl + "/AddressManagement/addressDel"; //删除地址接口
    public String defAddressSet = ServerUrl + "/AddressManagement/addressDefault"; //设置默认地址接口
    public String modifyAddress = ServerUrl + "/AddressManagement/addressDoedit"; //编辑地址接口
    public String addAddress = ServerUrl + "/AddressManagement/addressAdd"; //添加地址接口
    public String goodsDetail = ServerUrl + "/CartManagement/goodsdetail"; //商品详情
    public String addShopCarMall = ServerUrl + "/CartManagement/addCart";//添加到购物车


    public String allIndent = ServerUrl + "/Mine/allOrder";//全部订单
    public String kindIndent = ServerUrl + "/Mine/kindOrder";//筛选订单
    public String cancelOrder = ServerUrl + "/Mine/orderCancel";//取消订单
    public String delOrder = ServerUrl + "/Mine/orderDel";//订单删除
    public String confirmReceiveOrder = ServerUrl + "/Mine/sureReceive";//确认收货
    public String tipOrder = ServerUrl + "/Mine/remarkSend";//提醒发货
    public String configOrderDetai = ServerUrl + "/CartManagement/sureOrder";//确认订单获取信息发货

    public String delCommonList = ServerUrl + "/Mine/collectDel";//删除清单接口
    public String addToCommonList = ServerUrl + "/Mine/collectAdd";//添加到常用清单

    public String getWxRechargeinfo=ServerUrl+"/PayMoney/scwrechargeOrder";//获取微信充值的订单信息


    public String cartIndexList = ServerUrl + "/CartManagement/CartIndex"; //购物车列表（和编辑共用）
    public String carTotal = ServerUrl + "/CartManagement/cartTotal"; //选择要结算的商品后显示相应的总计
    public String carDel = ServerUrl + "/CartManagement/cartDel"; //删除商品


    public String oneCreateOrder = ServerUrl + "/CartManagement/beOrder"; ///CartManagement/beOrder 立即购买生成订单接口
    public String carCreateOrder = ServerUrl + "/CartManagement/cartBeOrder"; //购物车生成订单
    public String shopCarNumChange = ServerUrl + "/CartManagement/cartChangeNum"; //购物车个数改变
    public String judgeShopCarNum = ServerUrl + "/CartManagement/judgeNum"; //编辑购物车商品数量范围改变判断
    public String orderSetAddress = ServerUrl + "/CartManagement/submitendOrder"; //修改订单地址
    public String pay = ServerUrl + "/CartManagement/balancePay"; //余额支付接口
    public String orderInspection = ServerUrl + "/CartManagement/judgeCancel";//点击确认并付款时判断该订单是否失效接口



    //  public String =ServerUrl+"";//
    //  public String =ServerUrl+"";//

}
