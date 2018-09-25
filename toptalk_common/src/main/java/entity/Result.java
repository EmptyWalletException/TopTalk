package entity;

/**
* @Author: MasterCV
* @Date: 2018/9/25 18:26
* @Description: 用于控制器返回结果；
*/
public class Result<T>{

    private boolean flag;//是否成功
    private Integer code;// 返回码，相见设计文档或后端开发第一天的pdf文档
    private String message;//返回信息
    private Object data;// 返回数据

    public Result() {
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
