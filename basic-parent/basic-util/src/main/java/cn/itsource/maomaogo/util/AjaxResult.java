package cn.itsource.maomaogo.util;



public class AjaxResult {

    private boolean success = true;
    private String message;
    private String errorCode;/*错误码 */
    private Object data;/*AjaxResult中封装数据*/


    public boolean isSuccess() {
        return success;
    }

    /**
     *  返回 AjaxResult对象，就可以继续对它里面的方法进行操作【链式操作】
     *
     *  失败：   添加失败,创建 AjaxResult.me()。把success设置为false。message并说明原因【通过AjaxResult的set、
     *  方法设置】
     * AjaxResult.me().setSuccess(false).setMessage("添加失败");
     *
     * @param success
     * @return
     */
    public AjaxResult setSuccess(boolean success) {
        this.success = success;
        return this;/*表示当前 AjaxResult 对象*/
    }

    public String getMessage() {
        return message;
    }

    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;/*表示当前 AjaxResult 对象*/
    }

    public String getErrorCode() {
        return errorCode;
    }

    public AjaxResult setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;/*表示当前 AjaxResult 对象*/
    }

    public Object getData() {
        return data;
    }

    public AjaxResult setData(Object data) {
        this.data = data;
        return this;/*表示当前 AjaxResult 对象*/
    }


    /**
     * 成功  就调用该 构造方法。success默认为true标示成功
     * @return
     */
    public static AjaxResult me(){
        return new AjaxResult();
    }

}
