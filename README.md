# weixin-smallprogram-login-server
微信小程序登录服务,通过小程序appid、secret、js_code、grant_type获取session_key及openid

### 接口说明
### 请求地址
/weixin/onLogin
### 请求方法 
支持 Post和get请求
### 请求参数
appid 小程序唯一标识
secret 小程序app的secret
js_code 登录时获取的code
grant_type 填写为 authorization_code

